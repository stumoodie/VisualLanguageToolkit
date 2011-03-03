/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */

package org.pathwayeditor.figure.rendering;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.definition.ICompiledFigureDefinition;
import org.pathwayeditor.figure.geometry.ConvexHullCalculator;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;

/**
 * 
 * @author Stuart Moodie
 *
 */
public class FigureRenderingController implements IFigureRenderingController {
	private static final int DEFAULT_LINE_WIDTH = 1;
	private final Logger logger = Logger.getLogger(this.getClass());
	private final FigureRenderingBuilder builder;
	private GraphicsInstructionList figureInstructions;
	private Envelope requestedEnvelope;
	private final ICompiledFigureDefinition shapeDefinition;
	private final List<IFigureRenderingControllerListener> listeners = new LinkedList<IFigureRenderingControllerListener>();

	public FigureRenderingController(ICompiledFigureDefinition shapeDefinition){
		try {
			this.shapeDefinition = shapeDefinition;
			ConvexHullCalculator hullCalc = new ConvexHullCalculator();
			this.builder = new FigureRenderingBuilder(shapeDefinition, hullCalc);
			this.builder.setLineWidth(DEFAULT_LINE_WIDTH);
			this.requestedEnvelope = new Envelope(0, 0, 100, 100);
		} catch (RuntimeException ex) {
			logger.error("An error occured reading the figure definition", ex);
			throw ex;
		}
	}

	@Override
	public IConvexHull getConvexHull() {
		return this.builder.getConvexHull();
	}

	@Override
	public Envelope getEnvelope() {
		Envelope retVal = null;
		if(this.builder.getConvexHull() != null){
			retVal = this.builder.getConvexHull().getEnvelope();
		}
		return retVal;
	}

	@Override
	public RGB getFillColour() {
		return this.builder.getFillColour();
	}

	@Override
	public RGB getLineColour() {
		return this.builder.getLineColour();
	}

	@Override
	public double getLineWidth() {
		return this.builder.getLineWidth();
	}

	@Override
	public void setBindBoolean(String name, Boolean value) {
		Boolean oldValue = this.builder.getBindBooleanValue(name);
		this.builder.setBindBoolean(name, value);
		notifyEvent(FigureChangeType.BOUND_VALUE, oldValue, value);
	}

	@Override
	public void setBindDouble(String name, double value) {
		Double oldValue = this.builder.getBindDoubleValue(name);
		this.builder.setBindDouble(name, value);
		notifyEvent(FigureChangeType.BOUND_VALUE, oldValue, value);
	}

	@Override
	public void setBindInteger(String name, int value) {
		Integer oldValue = this.builder.getBindIntegerValue(name);
		this.builder.setBindInteger(name, value);
		notifyEvent(FigureChangeType.BOUND_VALUE, oldValue, value);
	}

	@Override
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
		IFigureRenderingControllerAttributeChangeEvent event = new IFigureRenderingControllerAttributeChangeEvent(){

			@Override
			public Object getNewValue() {
				return value;
			}

			@Override
			public Object getOldValue() {
				return oldValue;
			}

			@Override
			public FigureChangeType getType() {
				return type;
			}

			@Override
			public IFigureRenderingController getFigureController() {
				return FigureRenderingController.this;
			}
			
		};
		for(IFigureRenderingControllerListener listener : this.listeners){
			listener.attributeChange(event);
		}
	}

	@Override
	public Set<String> getBindVariableNames(){
		return this.shapeDefinition.getBindVariableNames();
	}
	
	@Override
	public void setRequestedEnvelope(Envelope newEnvelope) {
		Envelope oldValue = this.requestedEnvelope;
		this.requestedEnvelope = newEnvelope;
		this.notifyEvent(FigureChangeType.REQUESTED_ENVELOPE, oldValue, newEnvelope);
	}

	@Override
	public void setFillColour(RGB newFillColour) {
		RGB oldValue = this.builder.getFillColour();
		this.builder.setFillColour(newFillColour);
		this.notifyEvent(FigureChangeType.FILL_COLOUR, oldValue, newFillColour);
	}

	@Override
	public void setLineColour(RGB newLineColour) {
		RGB oldValue = this.builder.getLineColour();
		this.builder.setLineColour(newLineColour);
		this.notifyEvent(FigureChangeType.LINE_COLOUR, oldValue, newLineColour);
	}

	@Override
	public void setLineWidth(double lineWidth) {
		Double oldValue = this.builder.getLineWidth();
		this.builder.setLineWidth(lineWidth);
		this.notifyEvent(FigureChangeType.LINE_WIDTH, oldValue, Double.valueOf(lineWidth));
	}

	@Override
	public void generateFigureDefinition() {
		this.builder.setEnvelope(requestedEnvelope);
		if(logger.isDebugEnabled()){
			logger.debug("Generating figure: env=" + this.requestedEnvelope);
		}
		GraphicsInstructionList oldValue = this.builder.getRenderingInstructions();
		this.builder.generateFigureRendering();
		this.figureInstructions = this.builder.getRenderingInstructions();
		if(this.logger.isInfoEnabled() && !this.requestedEnvelope.contains(this.getEnvelope())){
			StringBuilder buf = new StringBuilder(200);
			buf.append("The convex hull (env=");
			buf.append(this.getEnvelope());
			buf.append(") spills outside the requested envelope (");
			buf.append(this.requestedEnvelope);
			logger.info(buf.toString());
		}
		if(logger.isDebugEnabled()){
			logger.debug("Calcuated convex hull=" + this.getConvexHull());
		}
		this.notifyEvent(FigureChangeType.FIGURE_DEFN, oldValue,this.builder.getRenderingInstructions());
	}

	@Override
	public GraphicsInstructionList getFigureDefinition() {
		return this.figureInstructions;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IFigureController#getAnchorCalculator()
	 */
	@Override
	public IAnchorLocatorFactory getAnchorLocatorFactory() {
		return this.builder.getAnchorLocatorFactory();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IFigureController#getLineStyle()
	 */
	@Override
	public LineStyle getLineStyle() {
		return this.builder.getLineStyle();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IFigureController#getRequestedEnvelope()
	 */
	@Override
	public Envelope getRequestedEnvelope() {
		return this.requestedEnvelope;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IFigureController#setLineStyle()
	 */
	@Override
	public void setLineStyle(LineStyle lineStyle) {
		LineStyle oldValue = this.builder.getLineStyle();
		this.builder.setLineStyle(lineStyle);
		this.notifyEvent(FigureChangeType.LINE_WIDTH, oldValue,lineStyle);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IFigureController#addListener(org.pathwayeditor.figure.rendering.IFigureChangeListener)
	 */
	@Override
	public void addListener(IFigureRenderingControllerListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IFigureController#listenerIterator()
	 */
	@Override
	public List<IFigureRenderingControllerListener> listenerIterator() {
		return new ArrayList<IFigureRenderingControllerListener>(this.listeners);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.rendering.IFigureController#removeListener(org.pathwayeditor.figure.rendering.IFigureChangeListener)
	 */
	@Override
	public void removeListener(IFigureRenderingControllerListener listener) {
		this.listeners.remove(listener);
	}
	
	
	
}
