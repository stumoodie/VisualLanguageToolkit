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
package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.DrawingNodeAttributeListenerHandler;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.IRootCompoundNode;

/**
 * @author smoodie
 *
 */
public class RootAttribute extends CanvasAttribute implements IRootAttribute {
	private static final Point INITIAL_POS = new Point(-0.5 * Double.MAX_VALUE, -0.5 *Double.MAX_VALUE);
	private static final Dimension INITIAL_SIZE = new Dimension(Double.MAX_VALUE, Double.MAX_VALUE);
	private final IRootObjectType objectType;
	private Point location = INITIAL_POS; 
	private Dimension size = INITIAL_SIZE; 
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem(this);
	private transient final DrawingNodeAttributeListenerHandler listenerHandler = new DrawingNodeAttributeListenerHandler(this);

	public RootAttribute(ICanvas canvas, int creationSerial, IRootObjectType objectType) {
		super(canvas, creationSerial);
		this.objectType = objectType;
	}

	public RootAttribute(ICanvas canvas, int creationSerial, RootAttribute otherAttribute) {
		super(canvas, creationSerial);
		this.objectType = otherAttribute.getObjectType();
	}

//	@Override
//	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
//		this.objectType = (IRootObjectType)objectType;
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	@Override
	public IRootObjectType getObjectType() {
		return this.objectType;
	}

	@Override
	public IRootNode getCurrentDrawingElement() {
		return this.getCanvas().getMapper().getRootNode((IRootCompoundNode)this.getCurrentElement());
	}

	@Override
	public boolean isValid() {
		return this.getObjectType() != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getLocation()
	 */
	@Override
	public Point getLocation() {
		return this.location;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getSize()
	 */
	@Override
	public Dimension getSize() {
		return this.size;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	@Override
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
	@Override
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
	@Override
	public Envelope getBounds() {
		return new Envelope(this.location, this.size);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	@Override
	public void setBounds(Envelope newBounds) {
//		this.convexHull = this.getConvexHull().changeEnvelope(newBounds);
		setLocation(newBounds.getOrigin());
		setSize(newBounds.getDimension());
	}

	@Override
	public final void addChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	@Override
	public final Iterator<ICanvasAttributePropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	public final void notifyPropertyChange(CanvasAttributePropertyChange type, Object oldValue, Object newValue) {
		this.listenablePropertyChangeItem.notifyPropertyChange(type, oldValue, newValue);
	}

	@Override
	public final void removeChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)
	 */
	@Override
	public void resize(Point locationDelta, Dimension sizeDelta) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#addDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)
	 */
	@Override
	public void addDrawingNodeAttributeListener(IDrawingNodeAttributeListener listener) {
		this.listenerHandler.addDrawingNodeAttributeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#getDrawingNodeAttributeListeners()
	 */
	@Override
	public List<IDrawingNodeAttributeListener> getDrawingNodeAttributeListeners() {
		return this.listenerHandler.getDrawingNodeAttributeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#removeDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)
	 */
	@Override
	public void removeDrawingNodeAttributeListener(IDrawingNodeAttributeListener listener) {
		this.listenerHandler.removeDrawingNodeAttributeListener(listener);
	}
}
