/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */

package org.pathwayeditor.figure.figuredefn;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.figuredefn.GraphicsInstruction.GraphicalTextAlignment;
import org.pathwayeditor.figure.figuredefn.IFont.Style;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.IConvexHullCalculator;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.geometry.PointList;
import org.pathwayeditor.figurevm.IFigureDefinition;
import org.pathwayeditor.figurevm.IOpCodeHandler;
import org.pathwayeditor.figurevm.IOpCodeHandler.TextAlignment;
import org.pathwayeditor.figurevm.ShapeDefinitionInterpreter;

/**
 * 
 * @author Stuart Moodie
 *
 */

public class FigureBuilder {
	private final Logger logger = Logger.getLogger(this.getClass());

	private static final int RGB_LIST_SIZE = 3;
	private static final char ITALIC_STYLE = 'I';
	private static final char BOLD_STYLE = 'B';
	private static final double MIN_DIMENSION_SIZE = 0;
	private static final String FONT_NAME = "Arial";

	private final IFigureDefinition instList;
	private final IConvexHullCalculator hullCalc;
	private final ShapeDefinitionInterpreter producer;
	private final Stack<GraphicsState> graphicsStack;
	private GraphicsState currentState;
	private final List<GraphicsInstruction> graphicsInstructions;
	private final GraphicsInstructionFactory g;
	private GraphicsInstructionList figureDefn = null;
	private IAnchorLocatorFactory anchorCalc = null;
	
	public FigureBuilder(IFigureDefinition outlineDefinition, IConvexHullCalculator hullCalc){
		this.instList = outlineDefinition;
		this.hullCalc = hullCalc;
		producer = new ShapeDefinitionInterpreter(instList, new OpCodeHandler(), null);
		this.graphicsStack = new Stack<GraphicsState>();
		this.graphicsInstructions = new LinkedList<GraphicsInstruction>();
		this.g = GraphicsInstructionFactory.getInstance();
		this.currentState = new GraphicsState();
	}

	public IConvexHullCalculator getConvexHullCalculator(){
		return this.hullCalc;
	}
	
	public IAnchorLocatorFactory getAnchorLocatorFactory(){
		return this.anchorCalc;
	}
	
	public void generateFigure(){
		this.graphicsStack.clear();
		this.hullCalc.reset();
		this.graphicsInstructions.clear();
		this.figureDefn = null;
		writeGraphicsState();
		producer.execute();
		this.hullCalc.calculate();
		this.figureDefn = new GraphicsInstructionList(this.graphicsInstructions);
	}
	
	
	private void writeGraphicsState(){
		this.graphicsInstructions.add(g.setLineWidth(this.currentState.getLineWidth()));
		this.graphicsInstructions.add(g.setLineStyle(this.currentState.getLineStyle()));
		RGB fillColour = currentState.getFillColour();
		if(fillColour != null){
			this.graphicsInstructions.add(g.fillColour(fillColour));
		}
		RGB lineColour = currentState.getLineColour();
		if(lineColour != null){
			this.graphicsInstructions.add(g.lineColour(lineColour));
		}
		IFont font = currentState.getFont();
		this.graphicsInstructions.add(g.fontSize(font.getFontSize()));
		this.graphicsInstructions.add(g.fontStyle(font.getStyle()));
	}
	
	public <T> void setBindBoolean(String name, Boolean value){
		this.producer.setBindBoolean(name, value);
	}
	
	private void drawRectangle(double x, double y, double width, double height){
		if(width < MIN_DIMENSION_SIZE){
			width = MIN_DIMENSION_SIZE;
		}
		if(height < MIN_DIMENSION_SIZE){
			height = MIN_DIMENSION_SIZE;
		}
		logger.debug("drawRectangle: requests x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillRectangle(x, y, width, height));
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawRectangle(x, y, width, height));
		}
		this.hullCalc.addRectangle(x, y, width, height);
		if(logger.isDebugEnabled()){
			logger.debug("drawRectangle: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
		}
	}

	private void drawPoint(double x, double y){
		logger.debug("drawPoint: requests x=" + x + ", y=" + y);
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawPoint(x, y));
		}
		this.hullCalc.addPoint(x, y);
		if(logger.isDebugEnabled()){
			logger.debug("drawPoint: x=" + x + ", y=" + y);
		}
	}

	private void drawArc(double x, double y, double width, double height, double offset, double length){
		if(logger.isDebugEnabled()){
			logger.debug("drawArc: requests x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
		}
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillArc(x, y, width, height, offset, length));
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawArc(x, y, width, height, offset, length));
		}
		this.hullCalc.addArc(x, y, width, height, offset, length);
		if(logger.isDebugEnabled()){
			logger.debug("drawArc: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
		}
	}

	private void drawPolygon(double[] pointArr){
		int numberOfPoints = pointArr.length;
		for(int i = 0; i < numberOfPoints ;){
			double x = pointArr[i++];
			double y = pointArr[i++];
			this.hullCalc.addPoint(x, y);
		}
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillPolygon(pointArr));
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawPolygon(pointArr));
		}
		if(logger.isDebugEnabled()){
			logger.debug("drawPolygon: points=" + pointArr);
		}
	}

	private void drawPolyline(double[] pointArr){
		int numberOfPoints = pointArr.length;
		for(int i = 0; i < numberOfPoints ;){
			double x = pointArr[i++];
			double y = pointArr[i++];
			this.hullCalc.addPoint(x, y);
		}
		if(getLineColour() != null){
			graphicsInstructions.add(g.drawPolyline(pointArr));
		}
		if(logger.isDebugEnabled()){
			logger.debug("drawPolyline: points=" + pointArr);
		}
	}

	private void drawOval(double x, double y, double width, double height){
		if(getFillColour() != null){
			graphicsInstructions.add(g.fillOval(x, y, width, height));
		}
		if(getLineColour() != null){
			graphicsInstructions.add(g.drawOval(x, y, width, height));
		}
		this.hullCalc.addOval(x, y, width, height);
		if(logger.isDebugEnabled()){
			logger.debug("drawOval: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
		}
	}

	private void drawRoundedRectangle(double x, double y, double width, double height, double arcWidth, double arcHeight){
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillRoundRectangle(x, y, width, height, arcWidth, arcHeight));
			if(logger.isDebugEnabled()){
				logger.debug("drawFill: fgCol=" + getLineColour() + ",bgCol=" + getFillColour());
			}
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight));
			if(logger.isDebugEnabled()){
				logger.debug("drawOutline: fgCol=" + getLineColour() + ",bgCol=" + getFillColour());
			}
		}
		this.hullCalc.addRoundRectangle(x, y, width, height, arcWidth, arcHeight);
		if(logger.isDebugEnabled()){
			logger.debug("drawRoundedRectangle: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height + ", arcW=" + arcWidth + ", arcH=" + arcHeight);
		}
	}


	public RGB getLineColour() {
		return currentState.getLineColour();
	}

	public RGB getFillColour() {
		return currentState.getFillColour();
	}

	private void drawLine(double xBegin, double yBegin, double xEnd, double yEnd){
		if(logger.isDebugEnabled()){
			logger.debug("drawLine: requests startX=" + xBegin + ", startY=" + yBegin
					+ ", endX=" + xEnd + ", endY=" + yEnd);
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawLine(xBegin, yBegin, xEnd, yEnd));
		}
		this.hullCalc.addLine(xBegin, yBegin, xEnd, yEnd);
		if(logger.isDebugEnabled()){
			logger.debug("drawLine: startX=" + xBegin + ", startY=" + yBegin + ", endX=" + xEnd + ", endY=" + yEnd);
		}
	}
	
	
	private void drawText(double x, double y, TextAlignment alignment, String text){
		if(logger.isDebugEnabled()){
			logger.debug("drawText: requests x=" + x + ", y=" + y + ", text=" + text);
		}
		GraphicalTextAlignment graphicalAlign = getGraphicalAlignment(alignment);
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillText(x, y, graphicalAlign, text));
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawText(x, y, graphicalAlign, text));
		}
		if(logger.isDebugEnabled()){
			logger.debug("drawText: x=" + x + ", y=" + y);
		}
	}

	public GraphicalTextAlignment getGraphicalAlignment(TextAlignment textAlign){
		GraphicalTextAlignment retVal = null;
		if(textAlign.equals(TextAlignment.C)){
			retVal = GraphicalTextAlignment.C;
		}
		else if(textAlign.equals(TextAlignment.N)){
			retVal = GraphicalTextAlignment.N;
		}
		else if(textAlign.equals(TextAlignment.S)){
			retVal = GraphicalTextAlignment.S;
		}
		else if(textAlign.equals(TextAlignment.E)){
			retVal = GraphicalTextAlignment.E;
		}
		else if(textAlign.equals(TextAlignment.W)){
			retVal = GraphicalTextAlignment.W;
		}
		else if(textAlign.equals(TextAlignment.NW)){
			retVal = GraphicalTextAlignment.NW;
		}
		else if(textAlign.equals(TextAlignment.SW)){
			retVal = GraphicalTextAlignment.SW;
		}
		else if(textAlign.equals(TextAlignment.NE)){
			retVal = GraphicalTextAlignment.NE;
		}
		else if(textAlign.equals(TextAlignment.SE)){
			retVal = GraphicalTextAlignment.SE;
		}
		return retVal;
	}
	
	private class OpCodeHandler implements IOpCodeHandler {

		@Override
		public void handleArc(double x, double y, double width, double height,
				double offset, double length) {
			drawArc(x, y, width, height, offset, length);
		}

		@Override
		public void handleLine(double startX, double startY, double endX,
				double endY) {
			drawLine(startX, startY, endX, endY);
		}

		@Override
		public void handleOval(double x, double y, double width, double height) {
			drawOval(x, y, width, height);
		}

		@Override
		public void handlePoint(double x, double y) {
			drawPoint(x, y);
		}

		@Override
		public void handlePolygon(double[] pointArr) {
			drawPolygon(pointArr);
		}

		@Override
		public void handlePolyline(double[] pointArr) {
			drawPolyline(pointArr);
		}

		@Override
		public void handleRectangle(double x, double y, double width, double height) {
			drawRectangle(x, y, width, height);
		}

		@Override
		public void handleRoundRectangle(double x, double y, double width,
				double height, double arcWidth, double arcHeight) {
			drawRoundedRectangle(x, y, width, height, arcWidth, arcHeight);
		}

		@Override
		public void handleText(double x, double y, TextAlignment alignment, String text) {
			drawText(x, y, alignment, text);
		}
		
		@Override
		public List<Double> getCurBounds(){
			final int numVariables = 4;
			Envelope e = currentState.getEnvelope();
			List<Double> retVal = new ArrayList<Double>(numVariables);
			retVal.add(e.getOrigin().getX());
			retVal.add(e.getOrigin().getY());
			retVal.add(e.getDimension().getWidth());
			retVal.add(e.getDimension().getHeight());
			return retVal;
		}

		@Override
		public List<Integer> getCurFillColour() {
			List<Integer> retVal = null;
			if(getFillColour() != null){
				retVal = rgbToList(currentState.getFillColour());
			}
			return retVal;
		}

		@Override
		public double getCurFontSize() {
			double fontSize = currentState.getFont().getFontSize();
			return fontSize;
		}

		@Override
		public String getCurFontStyle() {
			EnumSet<Style> style = currentState.getFont().getStyle();
			StringBuilder builder = new StringBuilder();
			if(style.contains(Style.ITALIC)){
				builder.append(ITALIC_STYLE);
			}
			if(style.contains(Style.BOLD)){
				builder.append(BOLD_STYLE);
			}
			return builder.toString();
		}

		@Override
		public List<Integer> getCurLineColour() {
			List<Integer> retVal = null;
			if(getLineColour() != null){
				retVal = rgbToList(currentState.getLineColour());
			}
			return retVal;
		}

		@Override
		public void restoreGraphicsState() {
			GraphicsState previousState = currentState;
			currentState = graphicsStack.pop();
			if(previousState.getLineColour() != null && currentState.getLineColour() != null
					&& !previousState.getLineColour().equals(currentState.getLineColour())){
				graphicsInstructions.add(g.lineColour(currentState.getLineColour()));
			}
			if(previousState.getFillColour() != null && currentState.getFillColour() != null
					&& !previousState.getFillColour().equals(currentState.getFillColour())){
				graphicsInstructions.add(g.fillColour(currentState.getFillColour()));
			}
			if(previousState.getLineWidth() != currentState.getLineWidth()){
				graphicsInstructions.add(g.setLineWidth(currentState.getLineWidth()));
			}
			if(previousState.getFont().equals(currentState.getFont())){
				graphicsInstructions.add(g.fillColour(currentState.getFillColour()));
			}
			if(logger.isDebugEnabled()){
				logger.debug("restoreGraphicsState: restoring graphics state: lineW=" + currentState.getLineWidth() + ",lineCol=" + getLineColour() + ",fillCol=" + getFillColour());
			}
		}

		@Override
		public void saveGraphicsState() {
			GraphicsState gState = new GraphicsState(currentState);
			graphicsStack.push(gState);
			if(logger.isDebugEnabled()){
				logger.debug("saveGraphicsState: saving graphics state: lineW=" + currentState.getLineWidth() + ",lineCol=" + currentState.getLineColour() + ",fillCol=" + currentState.getFillColour());
			}
		}

		@Override
		public void setFillColour(int red, int green, int blue) {
			RGB newColour = new RGB(red, green, blue);
			graphicsInstructions.add(g.fillColour(newColour));
			currentState.setFillColour(newColour);
		}

		@Override
		public void setFontSize(double fontSize) {
			IFont f = currentState.getFont();
			f = f.newSize(fontSize);
			graphicsInstructions.add(g.fontSize(f.getFontSize()));
			currentState.setFont(f);
		}

		@Override
		public void setFontStyle(String styleString) {
			EnumSet<Style> style = EnumSet.noneOf(Style.class);
			for(int i = 0; i < styleString.length(); i++){
				if(styleString.charAt(i) == ITALIC_STYLE){
					style.add(Style.ITALIC);
				}
				else if(styleString.charAt(i) == BOLD_STYLE){
					style.add(Style.BOLD);
				}
			}
			IFont f = currentState.getFont();
			f = f.newStyle(style);
			graphicsInstructions.add(g.fontStyle(f.getStyle()));
			currentState.setFont(f);
		}

		@Override
		public void setLineColour(int red, int green, int blue) {
			RGB newCol = new RGB(red, green, blue);
			graphicsInstructions.add(g.lineColour(newCol));
			currentState.setLineColour(newCol);
		}

		@Override
		public void setNoFill() {
			currentState.setFillColour(null);
		}

		@Override
		public void setNoLine() {
			currentState.setLineColour(null);
		}

		@Override
		public double currentLineWidth() {
			return getCurLineWidth();
		}

		@Override
		public void setLineWidth(double lineWidth) {
			setCurrLineWidth(lineWidth);
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.figurevm.IOpCodeHandler#setChopHullAnchor()
		 */
		@Override
		public void setChopHullAnchor() {
			anchorCalc = new ChopBoxAnchorCalculatorFactory();
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.figurevm.IOpCodeHandler#setSemiFixedAnchorCode(java.util.List)
		 */
		@Override
		public void setSemiFixedAnchorCode(PointList points) {
			anchorCalc = new MultiplePositionFixedAnchorFactory(points);
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.figurevm.IOpCodeHandler#getTextBounds()
		 */
		@Override
		public List<Double> getTextBounds(String text) {
			return handleGetTextBounds(text);
		}
		
	}

	public List<Integer> rgbToList(RGB backgroundColor) {
		List<Integer> retVal = new ArrayList<Integer>(RGB_LIST_SIZE);
		retVal.add(backgroundColor.getRed());
		retVal.add(backgroundColor.getGreen());
		retVal.add(backgroundColor.getBlue());
		return retVal;
	}

	/**
	 * @param text
	 * @return
	 */
	private List<Double> handleGetTextBounds(String text) {
		double size = this.currentState.getFont().getFontSize();
		int style = Font.PLAIN;
		if(this.currentState.getFont().getStyle().contains(Style.ITALIC)){
			style |= Font.ITALIC;
		}
		if(this.currentState.getFont().getStyle().contains(Style.BOLD)){
			style |= Font.BOLD;
		}
    	Font f = new Font(FONT_NAME, style, (int)Math.ceil(size));
    	AffineTransform af = new AffineTransform();
    	FontRenderContext ctx = new FontRenderContext(af, false, false);
    	Rectangle2D bounds = f.getStringBounds(text, ctx);
    	List<Double> retVal = new ArrayList<Double>(2);
    	retVal.add(bounds.getWidth());
    	retVal.add(bounds.getHeight());
		return retVal;
	}

	private void setCurrLineWidth(double lineWidth) {
		if(logger.isDebugEnabled()){
			logger.debug("setCurrLineWidth: requested lineWidth=" + lineWidth +")");
		}
		graphicsInstructions.add(g.setLineWidth(lineWidth));
		currentState.setLineWidth(lineWidth);
		if(logger.isDebugEnabled()){
			logger.debug("setCurrLineWidth: drawing lineWidth=" + lineWidth +")");
		}
	}

	private double getCurLineWidth() {
		double lineWidth = currentState.getLineWidth();
		if(logger.isDebugEnabled()){
			logger.debug("getCurrLineWidth: SWT lineWidth=" + lineWidth +")");
		}
		double retVal = lineWidth;
		if(logger.isDebugEnabled()){
			logger.debug("getCurrLineWidth: scaled lineWidth=" + retVal +")");
		}
		return retVal;
	}
	
	public void setEnvelope(Envelope newEnvelope){
		this.currentState.setEnvelope(newEnvelope);
	}

	public void setBindString(String string, String value) {
		this.producer.setBindString(string, value);
	}

	public void setBindInteger(String name, int value) {
		this.producer.setBindInteger(name, value);
	}

	public void setBindDouble(String name, double value) {
		this.producer.setBindDouble(name, value);
	}

	public GraphicsInstructionList getFigureDefinition() {
		return this.figureDefn;
	}

	public void setLineWidth(double i) {
		this.currentState.setLineWidth(i);
	}
	
	public double getLineWidth(){
		return this.currentState.getLineWidth();
	}

	public void setFillColour(RGB newFillColour) {
		this.currentState.setFillColour(newFillColour);
	}

	public void setLineColour(RGB newLineColour) {
		this.currentState.setLineColour(newLineColour);
	}

	public LineStyle getLineStyle() {
		return this.currentState.getLineStyle();
	}

	/**
	 * @param lineStyle
	 */
	public void setLineStyle(LineStyle lineStyle) {
		this.currentState.setLineStyle(lineStyle);
	}

	public IConvexHull getConvexHull() {
		return this.hullCalc.getConvexHull();
	}
	
	private class ChopBoxAnchorCalculatorFactory implements IAnchorLocatorFactory {

		/* (non-Javadoc)
		 * @see org.pathwayeditor.figure.figuredefn.IAnchorLocatorFactory#createAnchorLocator()
		 */
		@Override
		public IAnchorLocator createAnchorLocator() {
			return new ChopBoxAnchorCalculator(getConvexHull());
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.figure.figuredefn.IAnchorLocatorFactory#createAnchorLocator(org.pathwayeditor.figure.geometry.IConvexHull)
		 */
		@Override
		public IAnchorLocator createAnchorLocator(Envelope newBounds) {
			IConvexHull newHull = getConvexHull().changeEnvelope(newBounds);
			return new ChopBoxAnchorCalculator(newHull);
		}
		
	}
	
	private class MultiplePositionFixedAnchorFactory implements IAnchorLocatorFactory {
		private final PointList points;
		
		/**
		 * @param points
		 */
		public MultiplePositionFixedAnchorFactory(PointList points) {
			this.points = points;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.figure.figuredefn.IAnchorLocatorFactory#createAnchorLocator()
		 */
		@Override
		public IAnchorLocator createAnchorLocator() {
			return new MultiplePositionFixedAnchor(points, getConvexHull());
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.figure.figuredefn.IAnchorLocatorFactory#createAnchorLocator(org.pathwayeditor.figure.geometry.IConvexHull)
		 */
		@Override
		public IAnchorLocator createAnchorLocator(Envelope newBounds) {
			IConvexHull newHull = getConvexHull().changeEnvelope(newBounds);
			Envelope oldBounds = getConvexHull().getEnvelope(); 
			Iterator<Point> pointIter = this.points.iterator();
			List<Point> newPoints = new LinkedList<Point>();
			while(pointIter.hasNext()){
				Point p = pointIter.next();
				Point newP = oldBounds.transformPointToNewEnvelope(p, newBounds);
				newPoints.add(newP);
			}
			return new MultiplePositionFixedAnchor(new PointList(newPoints), newHull);
		}
		
	}

	public Boolean getBindBooleanValue(String name) {
		return this.producer.getBindBooleanValue(name);
	}

	public Double getBindDoubleValue(String name) {
		return this.producer.getBindDoubleValue(name);
	}

	public Integer getBindIntegerValue(String name) {
		return this.producer.getBindIntegerValue(name);
	}

	public String getBindStringValue(String name) {
		return this.producer.getBindStringValue(name);
	}
}
