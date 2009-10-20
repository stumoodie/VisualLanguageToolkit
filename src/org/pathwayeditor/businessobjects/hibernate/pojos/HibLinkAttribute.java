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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelSubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.BendPointChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableBendPointChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.figure.geometry.Point;

import uk.ed.inf.graph.util.IndexCounter;

public class HibLinkAttribute extends HibAnnotatedCanvasAttribute implements ILinkAttribute , Serializable {
	private static final long serialVersionUID = 8124494867402957446L;
	private static final int VALID_NUM_LINK_TERMINI = 2;
	private static final RGB DEFAULT_LINE_COLOUR = new RGB(255, 255, 255);
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final double DEFAULT_LINE_WIDTH = 1.0;
	private static final double MIN_LINE_WIDTH = 1.0;
	
	private HibObjectType hibObjectType;
	private ILinkObjectType objectType;
	private RGB lineColour = DEFAULT_LINE_COLOUR;
	private LineStyle lineStyle = DEFAULT_LINE_STYLE;
	private double lineWidth = DEFAULT_LINE_WIDTH;
	private List<HibBendPoint> hibBendPoints = new ArrayList<HibBendPoint>();
	private HibLinkEdge edge ;
	private List<HibLinkTerminus> linkTermini = new ArrayList<HibLinkTerminus>();
	private IndexCounter bendPointCounter = new IndexCounter();
	private final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem(this);
	private final ListenableBendPointChangeItem listenableBendPointChangeItem = new ListenableBendPointChangeItem(this);
	
	/**
	 * Default constructor to be used only by hibernate.
	 * @deprecated use any of the other constructors to construct this class in application code.
	 */
	HibLinkAttribute() {
		super();
	}

	/**
	 * Constructs new instance of this class.
	 * @param hibCanvas
	 * @param linkIndex
	 * @param objectType
	 * @param hibObjectType
	 */
	public HibLinkAttribute(HibCanvas hibCanvas, int linkIndex, ILinkObjectType objectType, HibObjectType hibObjectType) {
		super(hibCanvas, linkIndex, objectType.getDefaultAttributes());
		this.objectType = objectType;
		this.hibObjectType = hibObjectType;
		// the ordering here is important as the code expects the SOURCe to be first. Not satisfactory
		// FIXME: the following is very fragile. The LinkTermType is used later on to lookup the correct terminus.
		// This is a bug waiting to happen and needs fixing.
		this.linkTermini.add(new HibLinkTerminus(hibCanvas, hibCanvas.getCreationSerialCounter().nextIndex(), this, LinkTermType.SOURCE, objectType.getSourceTerminusDefinition()));
		this.linkTermini.add(new HibLinkTerminus(hibCanvas, hibCanvas.getCreationSerialCounter().nextIndex(), this, LinkTermType.TARGET, objectType.getTargetTerminusDefinition()));
		addDefaults(objectType.getDefaultAttributes());
	}

	/**
	 * Constructs new instance that is a copy of another one.
	 * @param hibCanvas
	 * @param linkIndex
	 * @param otherAttribute
s	 */
	public HibLinkAttribute(HibCanvas hibCanvas, int linkIndex, HibLinkAttribute otherAttribute) {
		super(hibCanvas, linkIndex, otherAttribute);
		this.objectType = otherAttribute.objectType;
		this.hibObjectType = otherAttribute.hibObjectType;
		this.lineColour = otherAttribute.getLineColor();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		for(HibLinkTerminus linkTerm : otherAttribute.getLinkTermini()){
			HibLinkTerminus copiedTerminus = new HibLinkTerminus(hibCanvas, hibCanvas.getCreationSerialCounter().nextIndex(), this, linkTerm);
			this.linkTermini.add(copiedTerminus);
		}
		this.bendPointCounter = new IndexCounter(otherAttribute.bendPointCounter.getLastIndex());
		for(HibBendPoint bendPoint : otherAttribute.hibBendPoints) {
			this.hibBendPoints.add(new HibBendPoint(this, bendPoint));
		}
	}

	/**
	 * @param linkAttributeDefaults
	 */
	private void addDefaults(ILinkAttributeDefaults linkAttributeDefaults) {
		this.lineColour = linkAttributeDefaults.getLineColour();
		this.lineStyle = linkAttributeDefaults.getLineStyle();
		this.lineWidth = linkAttributeDefaults.getLineWidth();
	}

	void setCurrentEdge(HibLinkEdge edge) {
		this.edge = edge;
	}
	
	public ILinkObjectType getObjectType() {
		return this.objectType;
	}
	
	public HibObjectType getHibObjectType () {
		return this.hibObjectType ;
	}
	
	void setHibObjectType (HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType ;
	}

//	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
//		this.objectType = (ILinkObjectType)objectType;
//		injectPropertyDefinitions(this.objectType.getDefaultAttributes());
//		this.getSourceTerminus().injectLinkTerminusDefaults(this.objectType.getSourceTerminusDefinition());
//		this.getTargetTerminus().injectLinkTerminusDefaults(this.objectType.getTargetTerminusDefinition());
//	}

	public int getLineRed() {
		return this.lineColour.getRed();
	}

	public void setLineRed(int lineRed) {
		this.lineColour = this.lineColour.newRed(lineRed);
	}

	public int getLineGreen() {
		return this.lineColour.getGreen();
	}

	public void setLineGreen(int lineGreen) {
		this.lineColour = this.lineColour.newGreen(lineGreen);
	}

	public int getLineBlue() {
		return this.lineColour.getBlue();
	}

	public void setLineBlue(int lineBlue) {
		this.lineColour = this.lineColour.newBlue(lineBlue);
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		if ( lineStyle == null )
			throw new IllegalArgumentException ( "Line style cannot be null." ) ;
		
		LineStyle oldValue = this.lineStyle;
		this.lineStyle = lineStyle;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_STYLE, oldValue, this.lineStyle);
	}

	public double getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		if ( lineWidth < MIN_LINE_WIDTH)
			throw new IllegalArgumentException ( "Line width cannot be less than " + MIN_LINE_WIDTH) ;
		
		double oldValue = this.lineWidth;
		this.lineWidth = lineWidth;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_WIDTH, oldValue, this.lineWidth);
	}

	public List<HibBendPoint> getBendPoints() {
		return this.hibBendPoints;
	}

	void setBendPoints(List<HibBendPoint> hibBendPoints) {
		this.hibBendPoints = hibBendPoints;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#bendPointIterator()
	 */
	public Iterator<IBendPoint> bendPointIterator() {
		List<IBendPoint> bendPoints = new ArrayList<IBendPoint> ( this.hibBendPoints) ;
		return bendPoints.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#containsBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public boolean containsBendPoint(IBendPoint bendPoint) {
		boolean retVal = false;
		if ( bendPoint != null && bendPoint.getOwningLink() == this){
			retVal = this.hibBendPoints.contains((HibBendPoint) bendPoint );
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#numBendPoints()
	 */
	public int numBendPoints() {
		return hibBendPoints.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#removeBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public void removeBendPoint(IBendPoint bendPoint) {
		if ( !containsBendPoint(bendPoint) ) throw new IllegalArgumentException ("no bendpoint") ;

		HibBendPoint hibBendPoint = (HibBendPoint)bendPoint;
		int index = this.hibBendPoints.indexOf(hibBendPoint);
		this.hibBendPoints.remove(hibBendPoint);
		// break link to this attribute so it will be deleted.
		hibBendPoint.setOwningLink(null);
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_REMOVED, hibBendPoint, index, index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLineColor()
	 */
	public RGB getLineColor() {
		return this.lineColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#setLineColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColor(RGB newColor) {
		if ( newColor == null)
			throw new IllegalArgumentException ("Line colour cannot be null") ;

		RGB oldValue = this.lineColour;
		this.lineColour = newColor;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldValue, this.lineStyle);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLinkEdge()
	 */
	public HibLinkEdge getCurrentDrawingElement() {
		return this.edge;
	}

	HibLinkEdge getCurrentEdge(){
		return this.edge;
	}
	
	
	public List<HibLinkTerminus> getLinkTermini() {
		return this.linkTermini;
	}

	public void setLinkTermini(List<HibLinkTerminus> linkTermini) {
		this.linkTermini = linkTermini;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getSourceTerminus()
	 */
	public HibLinkTerminus getSourceTerminus() {
		return this.linkTermini.get(LinkTermType.SOURCE.toInt());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getTargetTerminus()
	 */
	public HibLinkTerminus getTargetTerminus() {
		return this.linkTermini.get(LinkTermType.TARGET.toInt());
	}

	
	void setLastBendPointSerial(int lastSerial) {
		this.bendPointCounter = new IndexCounter(lastSerial);
	}
	

	int getLastBendPointSerial() {
		return this.bendPointCounter.getLastIndex();
	}
	

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	public Iterator<ICanvasAttributePropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#addbendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint, int)
	 */
	public void changeBendPointPosition(IBendPoint bendPoint, int indexPos) {
		if(!this.containsBendPoint(bendPoint)) throw new IllegalArgumentException("bendPoint must be contained by this attribute");

		if(indexPos >= this.hibBendPoints.size() || indexPos < 0){
			throw new IndexOutOfBoundsException("list size= " + this.hibBendPoints.size() + ", no bendpoint at position: " + indexPos);
		}
		else{
			int oldIdx = this.hibBendPoints.indexOf(bendPoint);
			if(indexPos < this.hibBendPoints.size()-1) {
				this.hibBendPoints.remove(oldIdx);
				// insert at new position
				this.hibBendPoints.add(indexPos, (HibBendPoint)bendPoint);
			}
			else {
				this.hibBendPoints.remove(oldIdx);
				// we want to add it to the end of the list so we append it bp to the list.
				this.hibBendPoints.add((HibBendPoint)bendPoint);
			}
			this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_REINDEXED, bendPoint, oldIdx, indexPos);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#containsBendPoint(int)
	 */
	public boolean containsBendPoint(int index) {
		boolean retVal = index >= 0 && index < this.hibBendPoints.size();
		if(retVal){
			retVal = this.hibBendPoints.get(index) != null; 
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getBendPoint(int)
	 */
	public IBendPoint getBendPoint(int index) {
		if(!containsBendPoint(index)) throw new IllegalArgumentException("not bendpoint at this index position");
		
		return this.hibBendPoints.get(index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#removeBendPoint(int)
	 */
	public void removeBendPoint(int index) {
		if(!containsBendPoint(index)) throw new IllegalArgumentException("not bendpoint at this index position");

		HibBendPoint hibBendPoint = this.hibBendPoints.get(index);
		this.hibBendPoints.remove(index);
		hibBendPoint.setOwningLink(null);
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_REMOVED, hibBendPoint, index, index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#createNewBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public IBendPoint createNewBendPoint(Point location) {
		HibBendPoint retVal = new HibBendPoint(this, this.bendPointCounter.nextIndex(), location);
		this.hibBendPoints.add(retVal);
		int index = this.hibBendPoints.size()-1;
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_ADDED, retVal, index, index);
		return retVal;
	}

	public IBendPoint createNewBendPoint(int position, Point location) {
		HibBendPoint retVal = new HibBendPoint(this, this.bendPointCounter.nextIndex(), location);
		if(position < 0 || position > this.hibBendPoints.size()) {
			throw new IndexOutOfBoundsException("list size= " + this.hibBendPoints.size() + ", no bendpoint can be added at position: " + position);
		}
		else if(position < this.hibBendPoints.size()) {
			this.hibBendPoints.add(position, retVal);
		}
		else {
			this.hibBendPoints.add(retVal);
		}
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_ADDED, retVal, position, position);
		return retVal;
	}

	public boolean isValid() {
		boolean retVal = true;
		if(this.getObjectType() != null && this.getLinkTermini().size() == VALID_NUM_LINK_TERMINI
				&& this.getSourceTerminus() != null && this.getTargetTerminus() != null
				// check edge points to this node
				&& this.getCurrentDrawingElement() != null && this.getCurrentDrawingElement().getAttribute().equals(this)
				// check property
				&& this.getObjectType().getLinkConnectionRules().isValidTarget(this.getCurrentDrawingElement().getSourceShape().getAttribute().getObjectType(),
								this.getCurrentDrawingElement().getTargetShape().getAttribute().getObjectType())){
			// now check properties
			retVal = this.arePropertiesValid(this.getObjectType().getDefaultAttributes());
			if(retVal) {
				// validate termini
				retVal = this.getSourceTerminus().isValid() && this.getTargetTerminus().isValid();
			}
		}
		else {
			retVal = false;
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getLabelSubModel()
	 */
	public ILabelSubModel getLabelSubModel() {
		return this.getCurrentDrawingElement().getOwningSubModel();
	}

	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}
	
	public void makeSelfBendPoints(Point src, Point tgt) {
		this.getSourceTerminus().setLocation(src);
		this.getTargetTerminus().setLocation(tgt);
		double startHeight= this.getCurrentDrawingElement().getSourceShape().getAttribute().getHeight();
		double startWidth = this.getCurrentDrawingElement().getSourceShape().getAttribute().getWidth();
		this.createNewBendPoint(src.translate(startWidth/2.0, 0.0));
		this.createNewBendPoint(src.translate(startWidth/2.0, startHeight*2/3));
		this.createNewBendPoint(new Point(tgt.getX(), src.getY() + startHeight*2/3));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#injectObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector)
	 */
	@Override
	public void injectObjectType(IObjectTypeInjector injector) throws InconsistentNotationDefinitionException {
		injector.inject(this);
		injector.inject(this.getSourceTerminus());
		injector.inject(this.getTargetTerminus());
	}

	public void setObjectType(ILinkObjectType objectType) {
		this.objectType = objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener)
	 */
	public void addChangeListener(IBendPointChangeListener listener) {
		this.listenableBendPointChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#bendPointListenerIterator()
	 */
	public Iterator<IBendPointChangeListener> bendPointListenerIterator() {
		return this.listenableBendPointChangeItem.bendPointListenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener)
	 */
	public void removeChangeListener(IBendPointChangeListener listener) {
		this.listenableBendPointChangeItem.removeChangeListener(listener);
	}

}
