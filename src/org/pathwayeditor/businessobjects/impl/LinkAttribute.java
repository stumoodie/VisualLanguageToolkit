/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.impl;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

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
	private final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem(this);
	private final IRootAttribute rootAttribute;
	private final IBendPointContainer lineSegement;
	
	public LinkAttribute(IRootAttribute hibCanvas, int linkIndex, ILinkObjectType objectType) {
		super(linkIndex, objectType.getDefaultAttributes());
		this.rootAttribute = hibCanvas;
		this.objectType = objectType;
		this.srcTerminus = new LinkTerminus(this, LinkTermType.SOURCE, objectType.getSourceTerminusDefinition());
		this.tgtTerminus = new LinkTerminus(this, LinkTermType.TARGET, objectType.getTargetTerminusDefinition());
		this.lineSegement = new BendPointContainer(this);
		addDefaults(objectType.getDefaultAttributes());
	}

	/**
	 * Constructs new instance that is a copy of another one.
	 * @param hibCanvas
	 * @param linkIndex
	 * @param otherAttribute
s	 */
	public LinkAttribute(IRootAttribute hibCanvas, int linkIndex, ILinkAttribute otherAttribute) {
		super(linkIndex, otherAttribute);
		this.rootAttribute = hibCanvas;
		this.objectType = otherAttribute.getObjectType();
		this.lineColour = otherAttribute.getLineColour();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		this.srcTerminus = new LinkTerminus(this, otherAttribute.getSourceTerminus());
		this.tgtTerminus = new LinkTerminus(this, otherAttribute.getTargetTerminus());
		this.lineSegement = new BendPointContainer(this, otherAttribute.getBendPointContainer());
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
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_STYLE, oldValue, this.lineStyle);
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
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_WIDTH, oldValue, this.lineWidth);
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
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldValue, this.lineStyle);
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
	public void addChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#getChangeListeners()
	 */
	@Override
	public List<ICanvasAttributePropertyChangeListener> getChangeListeners() {
		return this.listenablePropertyChangeItem.getChangeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#addbendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint, int)
//	 */
//	@Override
//	public void changeBendPointPosition(IBendPoint bendPoint, int indexPos) {
//		if(!this.containsBendPoint(bendPoint)) throw new IllegalArgumentException("bendPoint must be contained by this attribute");
//
//		if(indexPos >= this.bendPoints.size() || indexPos < 0){
//			throw new IndexOutOfBoundsException("list size= " + this.bendPoints.size() + ", no bendpoint at position: " + indexPos);
//		}
//		else{
//			int oldIdx = this.bendPoints.indexOf(bendPoint);
//			if(indexPos < this.bendPoints.size()-1) {
//				this.bendPoints.remove(oldIdx);
//				// insert at new position
//				this.bendPoints.add(indexPos, (BendPoint)bendPoint);
//			}
//			else {
//				this.bendPoints.remove(oldIdx);
//				// we want to add it to the end of the list so we append it bp to the list.
//				this.bendPoints.add((BendPoint)bendPoint);
//			}
//			this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_REINDEXED, bendPoint, oldIdx, indexPos);
//		}
//	}

	@Override
	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	@Override
	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#getRootAttribute()
	 */
	@Override
	public IRootAttribute getRootAttribute() {
		return this.rootAttribute;
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
		return this.lineSegement;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeCopyFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeCopyFactory() {
		return new LinkAttributeCopyFactory(this.rootAttribute.getCreationSerialCounter(), this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		return new ElementAttributeMoveFactory(this);
	}


}
