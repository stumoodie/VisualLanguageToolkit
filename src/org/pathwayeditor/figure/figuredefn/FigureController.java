package org.pathwayeditor.figure.figuredefn;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	private final List<IFigureChangeListener> listeners = new LinkedList<IFigureChangeListener>();

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
		Boolean oldValue = this.builder.getBindBooleanValue(name);
		this.builder.setBindBoolean(name, value);
		notifyEvent(FigureChangeType.BOUND_VALUE, oldValue, value);
	}

	public void setBindDouble(String name, double value) {
		Double oldValue = this.builder.getBindDoubleValue(name);
		this.builder.setBindDouble(name, value);
		notifyEvent(FigureChangeType.BOUND_VALUE, oldValue, value);
	}

	public void setBindInteger(String name, int value) {
		Integer oldValue = this.builder.getBindIntegerValue(name);
		this.builder.setBindInteger(name, value);
		notifyEvent(FigureChangeType.BOUND_VALUE, oldValue, value);
	}

	public void setBindString(String name, String value) {
		String oldValue = this.builder.getBindStringValue(name);
		this.builder.setBindString(name, value);
		notifyEvent(FigureChangeType.BOUND_VALUE, oldValue, value);
	}

	/**
	 * @param bound_value
	 * @param oldValue
	 * @param value
	 */
	private void notifyEvent(final FigureChangeType type, final Object oldValue, final Object value) {
		IFigureChangeEvent event = new IFigureChangeEvent(){

			public Object getNewValue() {
				return value;
			}

			public Object getOldValue() {
				return oldValue;
			}

			public FigureChangeType getType() {
				return type;
			}
			
		};
		for(IFigureChangeListener listener : this.listeners){
			listener.figureChange(event);
		}
	}

	public Set<String> getBindVariableNames(){
		return this.shapeDefinition.getBindVariableNames();
	}
	
	public void setRequestedEnvelope(Envelope newEnvelope) {
		Envelope oldValue = this.requestedEnvelope;
		this.requestedEnvelope = newEnvelope;
		this.notifyEvent(FigureChangeType.REQUESTED_ENVELOPE, oldValue, newEnvelope);
	}

	public void setFillColour(RGB newFillColour) {
		RGB oldValue = this.builder.getFillColour();
		this.builder.setFillColour(newFillColour);
		this.notifyEvent(FigureChangeType.FILL_COLOUR, oldValue, newFillColour);
	}

	public void setLineColour(RGB newLineColour) {
		RGB oldValue = this.builder.getLineColour();
		this.builder.setLineColour(newLineColour);
		this.notifyEvent(FigureChangeType.LINE_COLOUR, oldValue, newLineColour);
	}

	public void setLineWidth(double lineWidth) {
		Double oldValue = this.builder.getLineWidth();
		this.builder.setLineWidth(lineWidth);
		this.notifyEvent(FigureChangeType.LINE_WIDTH, oldValue, Double.valueOf(lineWidth));
	}

	public void generateFigureDefinition() {
		this.builder.setEnvelope(requestedEnvelope);
		logger.debug("Generating figure: env=" + this.requestedEnvelope);
		GraphicsInstructionList oldValue = this.builder.getFigureDefinition();
		this.builder.generateFigure();
		this.figureInstructions = this.builder.getFigureDefinition();
		if(!this.requestedEnvelope.contains(this.getEnvelope())){
			StringBuilder buf = new StringBuilder(200);
			buf.append("The convex hull (env=");
			buf.append(this.getEnvelope());
			buf.append(") spills outside the requested envelope (");
			buf.append(this.requestedEnvelope);
			logger.info(buf.toString());
		}
		logger.debug("Calcuated convex hull=" + this.getConvexHull());
		this.notifyEvent(FigureChangeType.FIGURE_DEFN, oldValue,this.builder.getFigureDefinition());
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
		LineStyle oldValue = this.builder.getLineStyle();
		this.builder.setLineStyle(lineStyle);
		this.notifyEvent(FigureChangeType.LINE_WIDTH, oldValue,lineStyle);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureController#addListener(org.pathwayeditor.figure.figuredefn.IFigureChangeListener)
	 */
	public void addListener(IFigureChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureController#listenerIterator()
	 */
	public Iterator<IFigureChangeListener> listenerIterator() {
		return this.listeners.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.figuredefn.IFigureController#removeListener(org.pathwayeditor.figure.figuredefn.IFigureChangeListener)
	 */
	public void removeListener(IFigureChangeListener listener) {
		this.listeners.remove(listener);
	}
	
	
	
}
