package org.pathwayeditor.figure.figuredefn;

import java.util.Set;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.ConvexHullCalculator;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figurevm.IFigureDefinition;

public class FigureController implements IFigureController {
	private static final int DEFAULT_LINE_WIDTH = 1;
	private final Logger logger = Logger.getLogger(this.getClass());
	private final FigureBuilder builder;
	private GraphicsInstructionList figureInstructions;
	private Envelope requestedEnvelope;
	private final IFigureDefinition shapeDefinition; 

	public FigureController(IFigureDefinition shapeDefinition){
		try {
			this.shapeDefinition = shapeDefinition;
			ConvexHullCalculator hullCalc = new ConvexHullCalculator();
			this.builder = new FigureBuilder(shapeDefinition, hullCalc);
			this.builder.setLineWidth(DEFAULT_LINE_WIDTH);
			this.requestedEnvelope = new Envelope(0, 0, 100, 100);
		} catch (RuntimeException ex) {
			logger.error("An error occured reading the figure definition", ex);
			throw ex;
		}
	}

	public IConvexHull getConvexHull() {
		return this.builder.getConvexHull();
	}

	public Envelope getEnvelope() {
		return this.builder.getConvexHull().getEnvelope();
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
		this.builder.setBindBoolean(name, value);
	}

	public void setBindDouble(String name, double value) {
		this.builder.setBindDouble(name, value);
	}

	public void setBindInteger(String name, int value) {
		this.builder.setBindInteger(name, value);
	}

	public void setBindString(String name, String value) {
		this.builder.setBindString(name, value);
	}

	public Set<String> getBindVariableNames(){
		return this.shapeDefinition.getBindVariableNames();
	}
	
	public void setRequestedEnvelope(Envelope newEnvelope) {
		this.requestedEnvelope = newEnvelope;
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
		this.builder.setEnvelope(requestedEnvelope);
		logger.debug("Generating figure: env=" + this.requestedEnvelope);
		this.builder.generateFigure();
		this.figureInstructions = this.builder.getFigureDefinition();
		if(!this.requestedEnvelope.contains(this.getEnvelope())){
			logger.info("The convex hull (env=" + this.getEnvelope() + ") spills outside the requested envelope ("
					+ this.requestedEnvelope + ". This may cause rendering problems");
		}
		logger.debug("Calcuated convex hull=" + this.getConvexHull());
	}

	public GraphicsInstructionList getFigureDefinition() {
		return this.figureInstructions;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureController#getAnchorCalculator()
	 */
	public IAnchorLocatorFactory getAnchorLocatorFactory() {
		return this.builder.getAnchorLocatorFactory();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureController#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		return this.builder.getLineStyle();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureController#getRequestedEnvelope()
	 */
	public Envelope getRequestedEnvelope() {
		return this.requestedEnvelope;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureController#setLineStyle()
	 */
	public void setLineStyle(LineStyle lineStyle) {
		this.builder.setLineStyle(lineStyle);
	}
	
	
	
}
