/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.impl;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointContainerListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ILinkTerminusChangeListener;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.CompoundNodePair;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

public class LinkAttribute extends AnnotatedCanvasAttribute implements ILinkAttribute {
	private static final long serialVersionUID = 8124494867402957446L;
	private static final RGB DEFAULT_LINE_COLOUR = new RGB(255, 255, 255);
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final double DEFAULT_LINE_WIDTH = 1.0;
	private static final double MIN_LINE_WIDTH = 1.0;
	
	private final ILinkObjectType objectType;
	private RGB lineColour = DEFAULT_LINE_COLOUR;
	private LineStyle lineStyle = DEFAULT_LINE_STYLE;
	private double lineWidth = DEFAULT_LINE_WIDTH;
	private final ILinkTerminus srcTerminus;
	private final ILinkTerminus tgtTerminus;
	private final CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper = new CanvasAttributeChangeListenerHelper(this);
	private final IBendPointContainer bpContainer;
	
	public LinkAttribute(IModel hibCanvas, int linkIndex, ILinkObjectType objectType) {
		super(hibCanvas, linkIndex, objectType.getDefaultAttributes());
		this.objectType = objectType;
		this.srcTerminus = new LinkTerminus(this, LinkTermType.SOURCE, objectType.getSourceTerminusDefinition());
		this.tgtTerminus = new LinkTerminus(this, LinkTermType.TARGET, objectType.getTargetTerminusDefinition());
		this.bpContainer = new BendPointContainer(this);
		addDefaults(objectType.getDefaultAttributes());
	}

	/**
	 * Constructs new instance that is a copy of another one.
	 * @param hibCanvas
	 * @param linkIndex
	 * @param otherAttribute
s	 */
	public LinkAttribute(IModel hibCanvas, int linkIndex, ILinkAttribute otherAttribute) {
		super(hibCanvas, linkIndex, otherAttribute);
		this.objectType = otherAttribute.getObjectType();
		this.lineColour = otherAttribute.getLineColour();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		this.srcTerminus = new LinkTerminus(this, otherAttribute.getSourceTerminus());
		this.tgtTerminus = new LinkTerminus(this, otherAttribute.getTargetTerminus());
		this.bpContainer = new BendPointContainer(this, otherAttribute.getBendPointContainer());
//		Iterator<Point> bpIter = otherAttribute.bendPointIterator();
//		while(bpIter.hasNext()){
//			Point otherBp = bpIter.next();
//			this.bendPoints.add(otherBp);
//		}
	}

	/**
	 * @param linkAttributeDefaults
	 */
	private void addDefaults(ILinkAttributeDefaults linkAttributeDefaults) {
		this.lineColour = linkAttributeDefaults.getLineColour();
		this.lineStyle = linkAttributeDefaults.getLineStyle();
		this.lineWidth = linkAttributeDefaults.getLineWidth();
	}

	@Override
	public ILinkObjectType getObjectType() {
		return this.objectType;
	}
	
	@Override
	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	@Override
	public void setLineStyle(LineStyle lineStyle) {
		if ( lineStyle == null )
			throw new IllegalArgumentException ( "Line style cannot be null." ) ;
		
		LineStyle oldValue = this.lineStyle;
		this.lineStyle = lineStyle;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_STYLE, oldValue, this.lineStyle);
	}

	@Override
	public double getLineWidth() {
		return this.lineWidth;
	}

	@Override
	public void setLineWidth(double lineWidth) {
		if ( lineWidth < MIN_LINE_WIDTH)
			throw new IllegalArgumentException ( "Line width cannot be less than " + MIN_LINE_WIDTH) ;
		
		double oldValue = this.lineWidth;
		this.lineWidth = lineWidth;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_WIDTH, oldValue, this.lineWidth);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLineColor()
	 */
	@Override
	public RGB getLineColour() {
		return this.lineColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#setLineColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	@Override
	public void setLineColour(RGB newColor) {
		if ( newColor == null)
			throw new IllegalArgumentException ("Line colour cannot be null") ;

		RGB oldValue = this.lineColour;
		this.lineColour = newColor;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldValue, this.lineColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getSourceTerminus()
	 */
	@Override
	public ILinkTerminus getSourceTerminus() {
		return this.srcTerminus;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getTargetTerminus()
	 */
	@Override
	public ILinkTerminus getTargetTerminus() {
		return this.tgtTerminus;
	}

	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#getChangeListeners()
	 */
	@Override
	public List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.canvasAttributeChangeListenerHelper.getChangeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor)
	 */
	@Override
	public void visit(ICanvasElementAttributeVisitor visitor) {
		visitor.visitLink(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getBendPointContainer()
	 */
	@Override
	public IBendPointContainer getBendPointContainer() {
		return this.bpContainer;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		return new ElementAttributeMoveFactory(this);
	}


	@Override
	public ICompoundEdge getCurrentElement(){
		return (ICompoundEdge)super.getCurrentElement();
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeCopyFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeCopyFactory() {
		LinkAttributeCopyFactory retVal = new LinkAttributeCopyFactory(this);
		CompoundNodePair pair = this.getCurrentElement().getConnectedNodes();
		retVal.setInAttribute(pair.getInNode().getAttribute());
		retVal.setOutAttribute(pair.getOutNode().getAttribute());
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		// suppress listeners as only want the link listeners to fire when the whole link is translated
		
		List<IBendPointContainerListener> bpListeners = this.bpContainer.bendPointListeners();
		List<ILinkTerminusChangeListener> srcTermListeners = this.srcTerminus.getLinkTerminusChangeListeners();
		List<ILinkTerminusChangeListener> tgtTermListeners = this.tgtTerminus.getLinkTerminusChangeListeners();
		suppressBpListeners(bpListeners);
		suppressLinkTermListeners(this.srcTerminus, srcTermListeners);
		suppressLinkTermListeners(this.tgtTerminus, tgtTermListeners);
		this.bpContainer.translateAll(delta);
		this.srcTerminus.translate(delta);
		this.tgtTerminus.translate(delta);
		restoreBpListeners(bpListeners);
		restoreLinkTermListeners(this.srcTerminus, srcTermListeners);
		restoreLinkTermListeners(this.tgtTerminus, tgtTermListeners);
		this.canvasAttributeChangeListenerHelper.notifyNodeTranslation(delta);
	}
	
	private void restoreLinkTermListeners(ILinkTerminus term, List<ILinkTerminusChangeListener> termListeners) {
		for(ILinkTerminusChangeListener listener : termListeners){
			term.addLinkTerminusChangeListener(listener);
		}
	}

	private void suppressLinkTermListeners(ILinkTerminus term, List<ILinkTerminusChangeListener> termListeners) {
		for(ILinkTerminusChangeListener listener : termListeners){
			term.removeLinkTerminusChangeListener(listener);
		}
	}

	private void suppressBpListeners(List<IBendPointContainerListener> bpListeners){
		for(IBendPointContainerListener listener : bpListeners){
			this.bpContainer.removeChangeListener(listener);
		}
	}
	
	private void restoreBpListeners(List<IBendPointContainerListener> bpListeners){
		for(IBendPointContainerListener listener : bpListeners){
			this.bpContainer.addChangeListener(listener);
		}
	}
}
