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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.BendPointChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableBendPointChangeItem;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public class BendPointContainer implements IBendPointContainer {
	private final ListenableBendPointChangeItem listenableBendPointChangeItem = new ListenableBendPointChangeItem(this);
	private List<Point> bendPoints;
	private final ILinkAttribute linkAttribute;

	/**
	 * @param linkAttribute
	 */
	public BendPointContainer(ILinkAttribute linkAttribute) {
		this.linkAttribute = linkAttribute;
		this.bendPoints = new LinkedList<Point>();
	}

	public BendPointContainer(ILinkAttribute otherLinkAtt, IBendPointContainer otherBendPointContainer) {
		this.linkAttribute = otherLinkAtt;
		this.bendPoints = new LinkedList<Point>();
		Iterator<Point> iter = otherBendPointContainer.bendPointIterator();
		while(iter.hasNext()){
			Point p = iter.next();
			this.bendPoints.add(p);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#containsBendPoint(int)
	 */
	@Override
	public boolean containsBendPoint(int index) {
		boolean retVal = index >= 0 && index < this.bendPoints.size();
		if(retVal){
			retVal = this.bendPoints.get(index) != null; 
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getBendPoint(int)
	 */
	@Override
	public Point getBendPoint(int index) {
		if(!containsBendPoint(index)) throw new IllegalArgumentException("not bendpoint at this index position");
		
		return this.bendPoints.get(index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#removeBendPoint(int)
	 */
	@Override
	public void removeBendPoint(int index) {
		if(!containsBendPoint(index)) throw new IllegalArgumentException("not bendpoint at this index position");

		Point removedPoint = this.bendPoints.remove(index);
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_REMOVED, removedPoint, index, index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#createNewBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	@Override
	public void createNewBendPoint(Point location) {
		this.bendPoints.add(location);
		int index = this.bendPoints.size()-1;
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_ADDED, location, index, index);
	}

	@Override
	public void createNewBendPoint(int position, Point location) {
		if(position < 0 || position > this.bendPoints.size()) {
			throw new IndexOutOfBoundsException("list size= " + this.bendPoints.size() + ", no bendpoint can be added at position: " + position);
		}
		else if(position < this.bendPoints.size()) {
			this.bendPoints.add(position, location);
		}
		else {
			this.bendPoints.add(location);
		}
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointChange.BEND_POINT_ADDED, location, position, position);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#bendPointIterator()
	 */
	@Override
	public Iterator<Point> bendPointIterator() {
		return this.bendPoints.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#numBendPoints()
	 */
	@Override
	public int numBendPoints() {
		return bendPoints.size();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener)
	 */
	@Override
	public void addChangeListener(IBendPointChangeListener listener) {
		this.listenableBendPointChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener)
	 */
	@Override
	public void removeChangeListener(IBendPointChangeListener listener) {
		this.listenableBendPointChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#bendPointListenerIterator()
	 */
	@Override
	public Iterator<IBendPointChangeListener> bendPointListenerIterator() {
		return this.listenableBendPointChangeItem.bendPointListenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinearLineSegment#translateBendPoint(int, org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translateBendPoint(int idx, Point translation) {
		if(!translation.equals(Point.ORIGIN)){
			Point bp = this.bendPoints.get(idx);
			Point newLocation = bp.translate(translation);
			this.bendPoints.set(idx, newLocation);
			this.listenableBendPointChangeItem.notifyPropertyChange(idx, bp, newLocation);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinearLineSegment#getLinkAttribute()
	 */
	@Override
	public ILinkAttribute getLinkAttribute() {
		return this.linkAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer#translateAll(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translateAll(Point translation) {
		if(!translation.equals(Point.ORIGIN)){
			List<Point> newBendPoints = new LinkedList<Point>();
			for(Point bp : this.bendPoints){
				Point newLocation = bp.translate(translation);
				newBendPoints.add(newLocation);
			}
			List<Point> oldPoints = this.bendPoints;
			this.bendPoints = newBendPoints;
			this.listenableBendPointChangeItem.notifyTranslation(translation, oldPoints, newBendPoints);
			Iterator<Point> oldBpIter = oldPoints.iterator();
			Iterator<Point> newBpIter = newBendPoints.iterator();
			for(int i = 0; i < this.bendPoints.size(); i++){
				Point oldPoint = oldBpIter.next();
				Point newPoint = newBpIter.next();
				this.listenableBendPointChangeItem.notifyPropertyChange(i, oldPoint, newPoint);
			}
		}
	}

}
