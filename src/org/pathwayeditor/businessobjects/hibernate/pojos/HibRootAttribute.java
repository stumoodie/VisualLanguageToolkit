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
/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public class HibRootAttribute extends HibCanvasAttribute implements IRootAttribute {
	private static final Point INITIAL_POS = new Point(-0.5 * Double.MAX_VALUE, -0.5 *Double.MAX_VALUE);
	private static final Dimension INITIAL_SIZE = new Dimension(Double.MAX_VALUE, Double.MAX_VALUE);
	private HibObjectType hibObjectType;
	private IRootObjectType objectType;
	private HibRootNode rootNode;
//	private IConvexHull convexHull;
	private Point location = INITIAL_POS; 
	private Dimension size = INITIAL_SIZE; 
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem(this);

	/**
	 * @deprecated Only to be used by hibernate, not application code.
	 */
	HibRootAttribute(){
	}
	
	public HibRootAttribute(HibCanvas canvas, int creationSerial, IRootObjectType objectType, HibObjectType hibObjectType) {
		super(canvas, creationSerial);
		this.objectType = objectType;
		this.hibObjectType = hibObjectType;
	}

	public HibRootAttribute(HibCanvas canvas, int creationSerial, HibRootAttribute otherAttribute) {
		super(canvas, creationSerial);
		this.objectType = otherAttribute.getObjectType();
		this.hibObjectType = otherAttribute.getHibObjectType();
	}

//	@Override
//	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
//		this.objectType = (IRootObjectType)objectType;
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	public IRootObjectType getObjectType() {
		return this.objectType;
	}

	void setRootNode(IRootNode rootNode) {
		this.rootNode = (HibRootNode)rootNode;
	}
	
	void setCurrentRootNode(HibRootNode newNode){
		this.rootNode = newNode;
	}

	HibRootNode getCurrentRootNode(){
		return this.rootNode;
	}

	IRootNode getRootNode() {
		return this.rootNode;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getCurrentDrawingElement()
	 */
	public IRootNode getCurrentDrawingElement() {
		return this.rootNode;
	}

	public boolean isValid() {
		return this.getObjectType() != null;
	}

	/**
	 * @param hibObjectType the hibObjectType to set
	 */
	void setHibObjectType(HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType;
	}

	/**
	 * @return the hibObjectType
	 */
	public HibObjectType getHibObjectType() {
		return hibObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getLocation()
	 */
	public Point getLocation() {
		return this.location;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getSize()
	 */
	public Dimension getSize() {
		return this.size;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Point newLocation) {
		Point oldLocation = this.location;
		if(!oldLocation.equals(newLocation)){
			this.location = newLocation;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LOCATION, oldLocation, newLocation);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Dimension newSize) {
		Dimension oldSize = this.size;
		if(!oldSize.equals(newSize)){
			this.size = newSize;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.SIZE, oldSize, newSize);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	public Envelope getBounds() {
		return new Envelope(this.location, this.size);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	public void setBounds(Envelope newBounds) {
//		this.convexHull = this.getConvexHull().changeEnvelope(newBounds);
		setLocation(newBounds.getOrigin());
		setSize(newBounds.getDimension());
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getConvexHull()
//	 */
//	public IConvexHull getConvexHull() {
//		if(convexHull == null){
//			this.convexHull = new RectangleHull(getBounds());
//		}
//		return this.convexHull;
//	}

	public void setObjectType(IRootObjectType objectType) {
		this.objectType = objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#injectObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector)
	 */
	@Override
	public void injectObjectType(IObjectTypeInjector injector) throws InconsistentNotationDefinitionException {
		injector.inject(this);
	}

	public final void addChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	public final Iterator<ICanvasAttributePropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	public final void notifyPropertyChange(CanvasAttributePropertyChange type, Object oldValue, Object newValue) {
		this.listenablePropertyChangeItem.notifyPropertyChange(type, oldValue, newValue);
	}

	public final void removeChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}
}
