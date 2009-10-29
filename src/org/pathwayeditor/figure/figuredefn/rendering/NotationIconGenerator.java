package org.pathwayeditor.figure.figuredefn.rendering;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.figure.figuredefn.FigureController;
import org.pathwayeditor.figure.figuredefn.GraphicsInstructionList;
import org.pathwayeditor.figure.figuredefn.IFigureController;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figurevm.FigureDefinitionCompiler;
import org.pathwayeditor.figurevm.IFigureDefinition;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class NotationIconGenerator {
	private Document svgDom = null;
	private double lineWidth = 1.0;
	private LineStyle lineStyle = LineStyle.SOLID;
	private RGB fillColour = RGB.WHITE;
	private RGB lineColour = RGB.BLACK;
	private String figureDefn;
	private Dimension size = new Dimension(20.0, 20.0);
	private final List<IPropertyDefinition> props;
	
	public NotationIconGenerator(){
		this.props = new LinkedList<IPropertyDefinition>();
	}
	
	public void buildSvg() {
		this.svgDom = createDom();
	}

	public double getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public RGB getFillColour() {
		return this.fillColour;
	}

	public void setFillColour(RGB fillColour) {
		this.fillColour = fillColour;
	}

	public RGB getLineColour() {
		return this.lineColour;
	}

	public void setLineColour(RGB lineColour) {
		this.lineColour = lineColour;
	}

	public String getFigureDefn() {
		return this.figureDefn;
	}

	public void setFigureDefn(String figureDefn) {
		this.figureDefn = figureDefn;
	}

	public Dimension getSize() {
		return this.size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public List<IPropertyDefinition> getProps() {
		return this.props;
	}

	public void setProperties(Iterator<IPropertyDefinition> iter){
		while(iter.hasNext()){
			this.props.add(iter.next());
		}
	}
	
	public InputStream getSvgAsInputStream(){
		// UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		File outFile = null;
		OutputStream os = null;
		InputStream retVal = null;
		try {
			outFile = File.createTempFile("shapeFigure", "svg");
			os = new FileOutputStream(outFile);
			Writer out = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			SVGGraphics2D svgGenerator = new SVGGraphics2D(this.svgDom);
			svgGenerator.stream(out, useCSS);
			os.close();
			os = null;
			retVal = new FileInputStream(outFile);
			return retVal;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally{
			try {
				if(os != null){
					os.close();
				}
				if(outFile != null){
					outFile.delete();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private Document createDom(){
		FigureDefinitionCompiler compiler = new FigureDefinitionCompiler(this.figureDefn);
		compiler.compile();
		IFigureDefinition defn = compiler.getCompiledFigureDefinition();
		IFigureController controller = new FigureController(defn);
		Set<IPropertyDefinition> boundProps = getBoundProperties(defn);
		assignedBoundValues(controller, boundProps);
		controller.setRequestedEnvelope(new Envelope(new Point(0, 0), this.size));
		controller.setFillColour(this.fillColour);
		controller.setLineColour(this.lineColour);
		controller.setLineWidth(this.lineWidth + 1.0);
		controller.setLineStyle(this.lineStyle);
		controller.generateFigureDefinition();
		GraphicsInstructionList graphicsInst = controller.getFigureDefinition();
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document document = impl.createDocument(svgNS, "svg", null);
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
		// compensate for elements of shape that are out of bounds
		Envelope drawnBounds = controller.getConvexHull().getEnvelope();
		double xOffset = drawnBounds.getOrigin().getX();
		double yOffset = drawnBounds.getOrigin().getY();
		if(xOffset < 0.0){
			xOffset = -xOffset;
		}
		if(yOffset < 0.0){
			yOffset = -yOffset;
		}
		// adding padding
		double edgePadding = this.lineWidth + 2.0;
		double figureWidth = drawnBounds.getDimension().getWidth()  + (edgePadding*2);
		double figureHeight = drawnBounds.getDimension().getHeight()  + (edgePadding*2);
		double canvasDimension = Math.max(figureHeight, figureWidth);
		Rectangle2D.Double drawnBounds2D = new Rectangle2D.Double(0, 0,
				canvasDimension+(edgePadding/2), canvasDimension+(edgePadding/2));
		svgGenerator.setSVGCanvasSize(drawnBounds2D.getBounds().getSize());
		AffineTransform scaleTransform = svgGenerator.getTransform();
		svgGenerator.setTransform(scaleTransform);
		AffineTransform lineWidthTransfrom = svgGenerator.getTransform();
		lineWidthTransfrom.translate(xOffset+edgePadding, yOffset+edgePadding);
		svgGenerator.setTransform(lineWidthTransfrom);
		AffineTransform squareCanvasTransfrom = svgGenerator.getTransform();
		squareCanvasTransfrom.translate((canvasDimension-figureWidth)/2.0, (canvasDimension-figureHeight)/2.0);
		svgGenerator.setTransform(squareCanvasTransfrom);
//		// Ask the test to render into the SVG Graphics2D implementation.
		SVGWriter test = new SVGWriter(graphicsInst);
		test.paint(svgGenerator);
		svgGenerator.getRoot(document.getDocumentElement());
		return document;
	}
	
	
	private void assignedBoundValues(IFigureController controller, Set<IPropertyDefinition> boundProps) {
		for(IPropertyDefinition propDefn : boundProps){
			if(propDefn instanceof IIntegerPropertyDefinition){
				controller.setBindInteger(propDefn.getName(), ((IIntegerPropertyDefinition)propDefn).getDefaultValue());
			}
			else if(propDefn instanceof IIntegerPropertyDefinition){
				controller.setBindInteger(propDefn.getName(), ((IIntegerPropertyDefinition)propDefn).getDefaultValue());
			}
			else if(propDefn instanceof IBooleanPropertyDefinition){
				controller.setBindBoolean(propDefn.getName(), ((IBooleanPropertyDefinition)propDefn).getDefaultValue());
//				controller.setBindBoolean(propDefn.getName(), Boolean.TRUE);
			}
			else if(propDefn instanceof INumberPropertyDefinition){
				controller.setBindDouble(propDefn.getName(), ((INumberPropertyDefinition)propDefn).getDefaultValue().doubleValue());
			}
			else{
				controller.setBindString(propDefn.getName(),propDefn.getDefaultValue().toString());
			}
		}
	}

	private Set<IPropertyDefinition> getBoundProperties(IFigureDefinition defn){
		Set<IPropertyDefinition> boundProperties = new HashSet<IPropertyDefinition>();
		Set<String> bindVarNames = defn.getBindVariableNames();
		for(String varName : bindVarNames){
			if(this.containsPropertyDefinition(varName)){
				IPropertyDefinition propDefn = this.getPropertyDefinition(varName);
				boundProperties.add(propDefn);
				
			}
			else{
				throw new RuntimeException("No property found for this bind variable: " + varName);
			}
		}
		return boundProperties;
	}

	private boolean containsPropertyDefinition(String varName) {
		boolean retVal = false; 
		Iterator<IPropertyDefinition> iter = this.props.iterator();
		while(iter.hasNext() && retVal){
			IPropertyDefinition prop = iter.next();
			retVal = prop.getName().equals(varName);
		}
		return retVal;
	}

	private IPropertyDefinition getPropertyDefinition(String varName) {
		IPropertyDefinition retVal = null; 
		Iterator<IPropertyDefinition> iter = this.props.iterator();
		while(iter.hasNext() && retVal != null){
			IPropertyDefinition prop = iter.next();
			if(prop.getName().equals(varName)){
				retVal = prop;
			}
		}
		return retVal;
	}
}
