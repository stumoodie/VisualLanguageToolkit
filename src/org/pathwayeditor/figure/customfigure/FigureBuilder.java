package org.pathwayeditor.figure.customfigure;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.customfigure.IFont.Style;
import org.pathwayeditor.figure.geometry.IConvexHullCalculator;
import org.pathwayeditor.figurevm.IOpCodeHandler;
import org.pathwayeditor.figurevm.InstructionList;
import org.pathwayeditor.figurevm.ShapeDefinitionInterpreter;

public class FigureBuilder {
//	private static class GraphicsState {
//		private static final double DEFAULT_LINE_WIDTH = 1;
//		private RGB fill; 
//		private RGB line;
//		private double lineWidth;
//		private IFont font;
//		
////		public GraphicsState(IGraphicsEngine g){
////			this.fill = g.getFillColour();
////			this.line = g.getLineColour();
////			this.lineWidth = g.getLineWidth();
////		}
//		
//		public GraphicsState(){
//			this.fill = RGB.WHITE;
//			this.line = RGB.BLACK;
//			this.lineWidth = DEFAULT_LINE_WIDTH;
//		}
//		
//		public GraphicsState(GraphicsState other){
//			this.fill = other.fill;
//			this.line = other.line;
//			this.lineWidth = other.lineWidth;
//		}
//		
//		public void restoreState(IGraphicsEngine g){
//			if(this.fill != null){
//				g.setFillColor(fill);
//			}
//			if(this.line != null){
//				g.setLineColor(this.line);
//			}
//			g.setLineWidth(this.lineWidth);
//		}
//
//		public double getLineWidth() {
//			return lineWidth;
//		}
//
//		public void setLineWidth(double newLineWidth){
//			this.lineWidth = newLineWidth;
//		}
//		
//		public RGB getFillColour() {
//			return this.fill;
//		}
//
//		public void setFillColour(RGB newFillColour){
//			this.fill = newFillColour;
//		}
//
//		public RGB getLineColour() {
//			return this.line;
//		}
//		
//		public void setLineColour(RGB newLineColour){
//			this.line = newLineColour;
//		}
//
//		public IFont getFont() {
//			return this.font;
//		}
//	}
	
	
	private final Logger logger = Logger.getLogger(this.getClass());

//	private static final double MAX_X_DIM = 100.0;
//	private static final double MAX_Y_DIM = 100.0;
	private static final int RGB_LIST_SIZE = 3;
	private static final char ITALIC_STYLE = 'I';
	private static final char BOLD_STYLE = 'B';
//	private static final int MIN_LINE_WIDTH = 1;
//	private static final double CANVAS_WIDTH = 100.0;

	private static final double MIN_DIMENSION_SIZE = 0;

//	private IGraphicsEngine g;
//	private PrecisionRectangle drawableArea;
//	private Envelope drawableArea;
//	private boolean fill = true;
//	private boolean line = true;
	private final InstructionList instList;
	private final IConvexHullCalculator hullCalc;
	private final ShapeDefinitionInterpreter producer;
	private final Stack<GraphicsState> graphicsStack;
	private GraphicsState currentState;
	private final List<GraphicsInstruction> graphicsInstructions;
	private final GraphicsInstructionFactory g;
	private GraphicsInstructionList figureDefn = null;
	
	public FigureBuilder(InstructionList outlineDefinition, IConvexHullCalculator hullCalc){
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
	
	public void generateFigure(){
		this.graphicsStack.clear();
		this.hullCalc.reset();
		this.graphicsInstructions.clear();
		this.figureDefn = null;
		writeGraphicsState();
		producer.execute();
		this.figureDefn = new GraphicsInstructionList(this.graphicsInstructions);
	}
	
	
	private void writeGraphicsState(){
		this.graphicsInstructions.add(g.setLineWidth(this.currentState.getLineWidth()));
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
		// we also need to setup the hull with the current state too
		this.hullCalc.setCurrentLineWidth(this.currentState.getLineWidth());
	}
	
//	public void paintFigure(IGraphicsEngine g){
//		this.g = g;
////		this.drawableArea = drawableArea;
//		this.currentState = new GraphicsState(g);
//		this.graphicsStack.clear();
////		this.fill = true;
////		this.line = true;
//		this.hullCalc.reset();
////		g.fillRectangle(this.drawableArea);
//		producer.execute();
//	}
	
	public <T> void setBindBoolean(String name, Boolean value){
		this.producer.setBindBoolean(name, value);
	}
	
//	private int getScaledHeight(double h) {
//		return (int)Math.round(h/MAX_Y_DIM * this.drawableArea.getDimension().getHeight());
//	}
//
//	private double getUnScaledHeight(int h) {
//		return ((double)h)/this.drawableArea.getDimension().getHeight() * MAX_Y_DIM;
//	}
//
//	private int getScaledWidth(double w) {
//		return (int)Math.round(w/MAX_X_DIM * this.drawableArea.getDimension().getWidth());
//	}
//
//	private double getUnScaledWidth(int w) {
//		return ((double)w)/this.drawableArea.getDimension().getWidth() * MAX_X_DIM;
//	}

//	private int getScaledY(double y) {
//		return (int)Math.round((y/MAX_Y_DIM * this.drawableArea.getDimension().getHeight())	+ this.drawableArea.getOrigin().getY());
//	}

//	private int getScaledXPos(double x) {
//		return (int)Math.round((x/MAX_X_DIM * this.drawableArea.getDimension().getWidth()) + this.drawableArea.getOrigin().getX());
//	}
	
	
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
		logger.debug("drawRectangle: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
	}

	private void drawPoint(double x, double y){
		logger.debug("drawPoint: requests x=" + x + ", y=" + y);
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawPoint(x, y));
		}
		this.hullCalc.addPoint(x, y);
		logger.debug("drawPoint: x=" + x + ", y=" + y);
	}

	private void drawArc(double x, double y, double width, double height, double offset, double length){
		logger.debug("drawArc: requests x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillArc(x, y, width, height, offset, length));
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawArc(x, y, width, height, offset, length));
		}
		this.hullCalc.addArc(x, y, width, height, offset, length);
		logger.debug("drawArc: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
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
		logger.debug("drawPolygon: points=" + pointArr);
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
		logger.debug("drawPolyline: points=" + pointArr);
	}

	private void drawOval(double x, double y, double width, double height){
		if(getFillColour() != null){
			graphicsInstructions.add(g.fillOval(x, y, width, height));
		}
		if(getLineColour() != null){
			graphicsInstructions.add(g.drawOval(x, y, width, height));
		}
		this.hullCalc.addOval(x, y, width, height);
		logger.debug("drawOval: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
	}

	private void drawRoundedRectangle(double x, double y, double width, double height, double arcWidth, double arcHeight){
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillRoundRectangle(x, y, width, height, arcWidth, arcHeight));
			logger.debug("drawFill: fgCol=" + getLineColour() + ",bgCol=" + getFillColour());
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight));
			logger.debug("drawOutline: fgCol=" + getLineColour() + ",bgCol=" + getFillColour());
		}
		this.hullCalc.addRoundRectangle(x, y, width, height, arcWidth, arcHeight);
		logger.debug("drawRoundedRectangle: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);
	}


	public RGB getLineColour() {
		return currentState.getLineColour();
	}

	public RGB getFillColour() {
		return currentState.getFillColour();
	}

	private void drawLine(double xBegin, double yBegin, double xEnd, double yEnd){
		logger.debug("drawLine: requests startX=" + xBegin + ", startY=" + yBegin
				+ ", endX=" + xEnd + ", endY=" + yEnd);
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawLine(xBegin, yBegin, xEnd, yEnd));
		}
		this.hullCalc.addLine(xBegin, yBegin, xEnd, yEnd);
		logger.debug("drawLine: startX=" + xBegin + ", startY=" + yBegin + ", endX=" + xEnd + ", endY=" + yEnd);
	}
	
	
	private void drawText(double x, double y, String text){
		logger.debug("drawText: requests x=" + x + ", y=" + y + ", text=" + text);
//		Font font = g.getFont();
//		FontData fontData[] = font.getFontData();
//		for (int i = 0; i < fontData.length; i++) {
//			fontData[i].setStyle(SWT.BOLD|SWT.ITALIC);
//			fontData[i].setHeight(10);
//		}
//		Font newFont = new Font(font.getDevice(), fontData);
//		g.setFont(newFont);
//		int xPos = (int)getScaledXPos(x);
//		int yPos = (int)getScaledYPos(y);
		if(getFillColour() != null){
			this.graphicsInstructions.add(g.fillText(x, y, text));
		}
		if(getLineColour() != null){
			this.graphicsInstructions.add(g.drawText(x, y, text));
		}
		logger.debug("drawText: x=" + x + ", y=" + y);
	}

	private class OpCodeHandler implements IOpCodeHandler {

		public void handleArc(double x, double y, double width, double height,
				double offset, double length) {
			drawArc(x, y, width, height, offset, length);
		}

		public void handleLine(double startX, double startY, double endX,
				double endY) {
			drawLine(startX, startY, endX, endY);
		}

		public void handleOval(double x, double y, double width, double height) {
			drawOval(x, y, width, height);
		}

		public void handlePoint(double x, double y) {
			drawPoint(x, y);
		}

		public void handlePolygon(double[] pointArr) {
			drawPolygon(pointArr);
		}

		public void handlePolyline(double[] pointArr) {
			drawPolyline(pointArr);
		}

		public void handleRectangle(double x, double y, double width, double height) {
			drawRectangle(x, y, width, height);
		}

		public void handleRoundRectangle(double x, double y, double width,
				double height, double arcWidth, double arcHeight) {
			drawRoundedRectangle(x, y, width, height, arcWidth, arcHeight);
		}

		public void handleText(double x, double y, String text) {
			drawText(x, y, text);
		}

		public List<Integer> getCurFillColour() {
			List<Integer> retVal = null;
			if(getFillColour() != null){
				retVal = rgbToList(currentState.getFillColour());
			}
			return retVal;
		}

		public int getCurFontSize() {
			int fontSize = currentState.getFont().getFontSize();
			return fontSize;
		}

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

		public List<Integer> getCurLineColour() {
			List<Integer> retVal = null;
			if(getLineColour() != null){
				retVal = rgbToList(currentState.getLineColour());
			}
			return retVal;
		}

		public double getTextHeight(String text) {
			double textHeight = currentState.getFont().getStringExtends(text).getHeight();
//			double retVal = getUnScaledHeight(textHeight);
			double retVal = textHeight;
			logger.debug("getTextHeight: SWTHgt=" + textHeight + ",scaledHgt=" + retVal);
			return retVal;
		}

		public double getTextLength(String text) {
			double textLen = currentState.getFont().getStringExtends(text).getWidth();
//			double retVal = getUnScaledWidth(textLen);
			double retVal = textLen;
			logger.debug("getTextLength: SWTLen=" + textLen + ",scaledLen=" + retVal);
			return retVal;
		}

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
			logger.debug("restoreGraphicsState: restoring graphics state: lineW=" + currentState.getLineWidth() + ",lineCol=" + getLineColour() + ",fillCol=" + getFillColour());
		}

		public void saveGraphicsState() {
			GraphicsState gState = new GraphicsState(currentState);
			graphicsStack.push(gState);
			logger.debug("saveGraphicsState: saving graphics state: lineW=" + currentState.getLineWidth() + ",lineCol=" + currentState.getLineColour() + ",fillCol=" + currentState.getFillColour());
		}

		public void setFillColour(int red, int green, int blue) {
			RGB newColour = new RGB(red, green, blue);
			graphicsInstructions.add(g.fillColour(newColour));
			currentState.setFillColour(newColour);
		}

		public void setFontSize(int fontSize) {
			IFont f = currentState.getFont();
			f.setSize(fontSize);
		}

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
			f.setStyle(style);
		}

		public void setLineColour(int red, int green, int blue) {
			RGB newCol = new RGB(red, green, blue);
			graphicsInstructions.add(g.lineColour(newCol));
			currentState.setLineColour(newCol);
		}

		public void setNoFill() {
			currentState.setFillColour(null);
		}

		public void setNoLine() {
			currentState.setLineColour(null);
		}

		public double currentLineWidth() {
			return getCurLineWidth();
		}

		public void setLineWidth(double lineWidth) {
			setCurrLineWidth(lineWidth);
		}
		
	}

	public List<Integer> rgbToList(RGB backgroundColor) {
		List<Integer> retVal = new ArrayList<Integer>(RGB_LIST_SIZE);
		retVal.add(backgroundColor.getRed());
		retVal.add(backgroundColor.getGreen());
		retVal.add(backgroundColor.getBlue());
		return retVal;
	}

	private void setCurrLineWidth(double lineWidth) {
		logger.debug("setCurrLineWidth: requested lineWidth=" + lineWidth +")");
		graphicsInstructions.add(g.setLineWidth(lineWidth));
		currentState.setLineWidth(lineWidth);
		this.hullCalc.setCurrentLineWidth(lineWidth);
		logger.debug("setCurrLineWidth: drawing lineWidth=" + lineWidth +")");
	}

	private double getCurLineWidth() {
		double lineWidth = currentState.getLineWidth();
		logger.debug("getCurrLineWidth: SWT lineWidth=" + lineWidth +")");
		double retVal = lineWidth; //getUnscaledLineWidth(lineWidth);
		logger.debug("getCurrLineWidth: scaled lineWidth=" + retVal +")");
		return retVal;
	}

//	private int getScaledLineWidth(double lineWidth){
//		double scaledLineWidth = lineWidth/CANVAS_WIDTH * Math.max(this.drawableArea.getDimension().getHeight(), this.drawableArea.getDimension().getWidth());
//		scaledLineWidth = Math.max(scaledLineWidth, MIN_LINE_WIDTH);
//		return (int)Math.round(scaledLineWidth);
//	}
	

//	private double getUnscaledLineWidth(int lineWidth) {
//		lineWidth = Math.max(lineWidth, MIN_LINE_WIDTH);
//		double scaledLineWidth = (((double)lineWidth) / (Math.max(this.drawableArea.getDimension().getHeight(), this.drawableArea.getDimension().getWidth()))) * CANVAS_WIDTH;
//		return Math.round(scaledLineWidth);
//	}

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
}
