package org.pathwayeditor.figure.figuredefn;

import java.util.Set;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.ConvexHullCalculator;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.IConvexHullCalculator;
import org.pathwayeditor.figurevm.IFigureDefinition;

public class FigureController implements IFigureController {
	private static final int DEFAULT_LINE_WIDTH = 1;
	private final Logger logger = Logger.getLogger(this.getClass());
	private final FigureBuilder builder;
	private IConvexHull convexHull = null;
	private GraphicsInstructionList figureInstructions;
	private Envelope refBounds;
	private final IFigureDefinition shapeDefinition; 

	public FigureController(IFigureDefinition shapeDefinition){
		try {
			this.shapeDefinition = shapeDefinition;
			ConvexHullCalculator hullCalc = new ConvexHullCalculator();
			this.builder = new FigureBuilder(shapeDefinition, hullCalc);
			this.builder.setLineWidth(DEFAULT_LINE_WIDTH);
			this.refBounds = new Envelope(0, 0, 100, 100);
		} catch (RuntimeException ex) {
			logger.error("An error occured reading the figure definition", ex);
			throw ex;
		}
	}

	public IConvexHull getConvexHull() {
		return this.convexHull;
	}

	public Envelope getEnvelope() {
		return this.refBounds;
	}

	public RGB getFillColour() {
		return this.builder.getFillColour();
	}

	public RGB getLineColour() {
		return this.builder.getLineColour();
	}

	public double getLineWidth() {
		return this.builder.getLineWidth();
	}

	public void setBindBoolean(String name, Boolean value) {
		this.setBindBoolean(name, value);
	}

	public void setBindDouble(String name, double value) {
		this.setBindDouble(name, value);
	}

	public void setBindInteger(String name, int value) {
		this.setBindInteger(name, value);
	}

	public void setBindString(String name, String value) {
		this.setBindString(name, value);
	}

	public Set<String> getBindVariableNames(){
		return this.shapeDefinition.getBindVariableNames();
	}
	
	public void setEnvelope(Envelope newEnvelope) {
		this.refBounds = newEnvelope;
	}

	public void setFillColour(RGB newFillColour) {
		this.builder.setFillColour(newFillColour);
	}

	public void setLineColour(RGB newLineColour) {
		this.builder.setLineColour(newLineColour);
	}

	public void setLineWidth(double lineWidth) {
		this.builder.setLineWidth(lineWidth);
	}

	public void generateFigureDefinition() {
//		double x = this.refBounds.getOrigin().getX();
//		double y = this.refBounds.getOrigin().getY();
//		double w = this.refBounds.getDimension().getWidth();
//		double h =this.refBounds.getDimension().getHeight();
//		this.builder.setBindDouble("x", x);
//		this.builder.setBindDouble("y", y);
//		this.builder.setBindDouble("w", w);
//		this.builder.setBindDouble("h", h);
		this.builder.setEnvelope(refBounds);
		logger.debug("Generating figure: env=" + this.refBounds);
		this.builder.generateFigure();
		this.figureInstructions = this.builder.getFigureDefinition();
		IConvexHullCalculator calc = this.builder.getConvexHullCalculator();
		calc.calculate();
		this.convexHull = calc.getConvexHull();
		if(!this.refBounds.contains(this.convexHull.getEnvelope())){
			logger.warn("The convex hull (env=" + this.convexHull.getEnvelope() + ") spills outside the requested envelope ("
					+ this.refBounds + ". This may cause rendering problems");
		}
		logger.debug("Calcuated convex hull=" + this.convexHull);
	}

	public GraphicsInstructionList getFigureDefinition() {
		return this.figureInstructions;
	}
	
	
	
}
