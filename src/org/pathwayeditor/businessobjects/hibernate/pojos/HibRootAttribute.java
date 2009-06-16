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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.geometry.IConvexHull;

/**
 * @author smoodie
 *
 */
public class HibRootAttribute extends HibCanvasAttribute implements IDrawingNodeAttribute {
	private HibObjectType hibObjectType;
	private IRootObjectType objectType;
	private IRootNode rootNode;
	private IConvexHull convexHull;
	private Bounds bounds = new Bounds(0, 0, 0, 0); 
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem;

	/**
	 * @deprecated Only to be used by hibernate, not application code.
	 */
	HibRootAttribute(){
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
	}
	
	public HibRootAttribute(HibCanvas canvas, int creationSerial, IRootObjectType objectType, HibObjectType hibObjectType) {
		super(canvas, creationSerial);
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
		this.objectType = objectType;
		this.hibObjectType = hibObjectType;
	}

	public HibRootAttribute(HibCanvas canvas, int creationSerial, HibRootAttribute otherAttribute) {
		super(canvas, creationSerial);
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
		this.objectType = otherAttribute.getObjectType();
		this.hibObjectType = otherAttribute.getHibObjectType();
	}

	@Override
	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
		this.objectType = (IRootObjectType)objectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	public IRootObjectType getObjectType() {
		return this.objectType;
	}

	void setRootNode(IRootNode rootNode) {
		this.rootNode = rootNode;
	}
	
	IRootNode getRootNode() {
		return this.rootNode;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getCurrentDrawingElement()
	 */
	public IDrawingElement getCurrentDrawingElement() {
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
	public Location getLocation() {
		return this.bounds.getOrigin();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getSize()
	 */
	public Size getSize() {
		return this.bounds.getSize();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Location newLocation) {
		Location oldLocation = this.bounds.getOrigin();
		if(!oldLocation.equals(newLocation)){
			this.bounds = this.bounds.changeOrigin(newLocation);
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LOCATION, oldLocation, newLocation);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Size newSize) {
		Size oldSize = this.bounds.getSize();
		if(!oldSize.equals(newSize)){
			this.bounds = this.bounds.changeSize(newSize);
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.SIZE, oldSize, newSize);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	public Bounds getBounds() {
		return this.bounds;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	public void setBounds(Bounds newBounds) {
		Location oldLocation = this.bounds.getOrigin();
		Size oldSize = this.bounds.getSize();
		this.bounds = newBounds;
		if(!oldSize.equals(this.bounds.getSize())){
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.SIZE, oldSize, this.bounds.getSize());
		}
		if(!oldLocation.equals(this.bounds.getOrigin())){
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LOCATION, oldLocation, this.bounds.getOrigin());
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getConvexHull()
	 */
	public IConvexHull getConvexHull() {
		return this.convexHull;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setConvexHull(org.pathwayeditor.figure.geometry.IConvexHull)
	 */
	public void setConvexHull(IConvexHull newHull) {
		this.convexHull = newHull;
	}

}
