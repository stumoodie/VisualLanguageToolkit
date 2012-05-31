/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/


package org.pathwayeditor.businessobjects.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.BendPointStructureChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointContainerListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableBendPointChangeItem;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public class BendPointContainer implements IBendPointContainer {
	private final ListenableBendPointChangeItem listenableBendPointChangeItem = new ListenableBendPointChangeItem(this);
	private List<ICurveSegment> bendPoints;
	private final ILinkAttribute linkAttribute;

	/**
	 * @param linkAttribute
	 */
	public BendPointContainer(ILinkAttribute linkAttribute) {
		this.linkAttribute = linkAttribute;
		this.bendPoints = new LinkedList<ICurveSegment>();
		this.bendPoints.add(new StraightLineCurveSegment(linkAttribute.getSourceTerminus().getLocation(), linkAttribute.getTargetTerminus().getLocation()));
	}

	public BendPointContainer(ILinkAttribute otherLinkAtt, IBendPointContainer otherBendPointContainer) {
		this.linkAttribute = otherLinkAtt;
		this.bendPoints = new LinkedList<ICurveSegment>();
		Iterator<ICurveSegment> iter = otherBendPointContainer.curveIterator();
		while(iter.hasNext()){
			ICurveSegment p = iter.next();
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
		
		ICurveSegment seg = this.bendPoints.get(index); 
		return seg.getEndPoint();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#removeBendPoint(int)
	 */
	@Override
	public void removeBendPoint(int index) {
		if(!containsBendPoint(index)) throw new IllegalArgumentException("not bendpoint at this index position");

		ICurveSegment leftSeg = this.bendPoints.remove(index);
		ICurveSegment rightSeg = this.bendPoints.remove(index+1);
		ICurveSegment newSeg = new StraightLineCurveSegment(leftSeg.getStartPoint(), rightSeg.getEndPoint());
		this.bendPoints.add(index, newSeg);
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointStructureChange.BEND_POINT_REMOVED, leftSeg.getEndPoint(), Arrays.asList(new ICurveSegment[]{leftSeg, rightSeg}), Arrays.asList(new ICurveSegment[]{newSeg}), index, index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#createNewBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	@Override
	public void createNewBendPoint(Point location) {
		int index = this.bendPoints.size()-1;
		createNewBendPoint(index, location);
//		// remove current bp
//		ICurveSegment oldSeg = this.bendPoints.remove(index);
//		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointStructureChange.BEND_POINT_ADDED, location, index, index);
	}

	@Override
	public void createNewBendPoint(int position, Point location) {
		if(position < 0 || position > this.bendPoints.size()) {
			throw new IndexOutOfBoundsException("list size= " + this.bendPoints.size() + ", no bendpoint can be added at position: " + position);
		}
		ICurveSegment oldSeg = this.bendPoints.remove(position);
		ICurveSegment leftSeg = new StraightLineCurveSegment(oldSeg.getStartPoint(), location);
		ICurveSegment rightSeg = new StraightLineCurveSegment(oldSeg.getEndPoint(), location);
		List<ICurveSegment> newSegs = Arrays.asList(new ICurveSegment[]{leftSeg, rightSeg});
		if(position < this.bendPoints.size()) {
			this.bendPoints.addAll(position, newSegs);
		}
		else {
			this.bendPoints.addAll(newSegs);
		}
		this.listenableBendPointChangeItem.notifyPropertyChange(BendPointStructureChange.BEND_POINT_ADDED, location, Arrays.asList(new ICurveSegment[]{oldSeg}), newSegs, position, position);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#bendPointIterator()
	 */
	@Override
	public Iterator<Point> bendPointIterator() {
		int numSegs = this.bendPoints.size();
		int i = 0;
		List<Point> retVal = new ArrayList<Point>(numSegs); 
		for(ICurveSegment seg : this.bendPoints){
			// if last segment then don't and in end point
			if(i < numSegs-1){
				retVal.add(seg.getEndPoint());
			}
		}
		return retVal.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#numBendPoints()
	 */
	@Override
	public int numBendPoints() {
		return bendPoints.size()-1;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener)
	 */
	@Override
	public void addChangeListener(IBendPointContainerListener listener) {
		this.listenableBendPointChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListener)
	 */
	@Override
	public void removeChangeListener(IBendPointContainerListener listener) {
		this.listenableBendPointChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IBendPointChangeListenee#bendPointListenerIterator()
	 */
	@Override
	public List<IBendPointContainerListener> bendPointListeners() {
		return this.listenableBendPointChangeItem.bendPointListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinearLineSegment#translateBendPoint(int, org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translateBendPoint(int idx, Point translation) {
		if(!translation.equals(Point.ORIGIN)){
			ICurveSegment leftSeg = this.bendPoints.get(idx);
			Point bp = leftSeg.getEndPoint();
			ICurveSegment rightSeg = this.bendPoints.get(idx+1);
			Point newLocation = bp.translate(translation);
			leftSeg.setEndPoint(newLocation);
			rightSeg.setStartPoint(newLocation);
//			this.listenableBendPointChangeItem.notifyPropertyChange(idx, bp, newLocation);
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
//			List<Point> newBendPoints = new LinkedList<Point>();
			for(ICurveSegment bp : this.bendPoints){
				bp.translate(translation);
//				newBendPoints.add(newLocation);
			}
//			List<Point> oldPoints = this.bendPoints;
//			this.bendPoints = newBendPoints;
////			this.listenableBendPointChangeItem.notifyTranslation(translation, oldPoints, newBendPoints);
//			Iterator<Point> oldBpIter = oldPoints.iterator();
//			Iterator<Point> newBpIter = newBendPoints.iterator();
//			for(int i = 0; i < this.bendPoints.size(); i++){
//				Point oldPoint = oldBpIter.next();
//				Point newPoint = newBpIter.next();
//				this.listenableBendPointChangeItem.notifyPropertyChange(i, oldPoint, newPoint);
//			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer#curveIterator()
	 */
	@Override
	public Iterator<ICurveSegment> curveIterator() {
		return this.bendPoints.iterator();
	}

}
