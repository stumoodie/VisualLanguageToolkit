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
import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.figure.geometry.Point;

/**
 * BendPoint generated by hbm2java
 */
public class HibBendPoint implements IBendPoint, Serializable {
	private static final Point DEFAULT_LOCATION = Point.ORIGIN;

	private Long id;
	private HibLinkAttribute owningLink;
	private int creationSerial;
	private int indexPos;
	private Point position = DEFAULT_LOCATION;
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem();

	/**
	 * Default constructor that should only be used by hibernate.
	 * 
	 * @deprecated should not be used by hibernate code, use one of the other constructors.
	 */
	HibBendPoint() {
	}

	public HibBendPoint(HibLinkAttribute owningLink, int creationSerial, Point position) {
		this.owningLink = owningLink;
		this.creationSerial = creationSerial;
		this.position = position;
	}

	public HibBendPoint(HibLinkAttribute newOwningLink, HibBendPoint otherBendPoint) {
		this.owningLink = newOwningLink;
		this.creationSerial = otherBendPoint.creationSerial;
		this.position = otherBendPoint.position;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ILinkAttribute getOwningLink() {
		return this.owningLink;
	}

	public void setOwningLink(HibLinkAttribute owningLink) {
		this.owningLink = owningLink;
	}

	@SuppressWarnings("unused")
	private int getIndexPos() {
		return this.indexPos;
	}

	@SuppressWarnings("unused")
	private void setIndexPos(int indexPos) {
		this.indexPos = indexPos;
	}

    public double getXPosition() {
        return this.position.getX();
    }
    
    public void setXPosition(double XPosition) {
        this.position = position.newX(XPosition);
    }
    
    public double getYPosition() {
        return this.position.getY();
    }
    
    public void setYPosition(double YPosition) {
        this.position = this.position.newY(YPosition);
    }


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibBendPoint))
			return false;
		HibBendPoint castOther = (HibBendPoint) other;

		return ((this.getOwningLink() == castOther.getOwningLink()) || (this.getOwningLink() != null
				&& castOther.getOwningLink() != null && this.getOwningLink().equals(castOther.getOwningLink())))
				&& (this.getCreationSerial() == castOther.getCreationSerial());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOwningLink() == null ? 0 : this.getOwningLink().hashCode());
		result = 37 * result + this.getCreationSerial();

		return result;
	}

	// The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = 6225340614454215601L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint#getLocation()
	 */
	public Point getLocation() {
		return this.position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes
	 * .Location)
	 */
	public void setLocation(Point location) {
		if(!this.position.equals(location)){
			Point oldLocation = this.position;
			this.position = location;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LOCATION, oldLocation, this.position);
		}
	}


	public int getCreationSerial() {
		return this.creationSerial;
	}

	void setCreationSerial(int creationSerial) {
		this.creationSerial = creationSerial;
	}

	public final void addChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	public final void firePropertyChange(IPropertyChangeEvent evt) {
		this.listenablePropertyChangeItem.firePropertyChange(evt);
	}

	public final List<IPropertyChangeListener> getListeners() {
		return this.listenablePropertyChangeItem.getListeners();
	}

	public final Iterator<IPropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	public final void removeChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(attribute=");
		builder.append(this.owningLink);
		builder.append(", creationSerial=");
		builder.append(this.creationSerial);
		builder.append(", location=");
		builder.append(this.position);
		builder.append(")");
		return builder.toString();
	}
}
