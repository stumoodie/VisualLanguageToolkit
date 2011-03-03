/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.rendering;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.geometry.PointList;

/**
 * 
 * MultiplePositionFixedAnchor
 *
 * @author Stuart Moodie
 *
 */
public class MultiplePositionFixedAnchor implements IAnchorLocator {
	private final PointList refAnchorPoints;
	private final IConvexHull shape;
	private Point otherEndPoint = null;
	
	
	public MultiplePositionFixedAnchor(PointList refAnchorPoints, IConvexHull hull){
		this.refAnchorPoints = refAnchorPoints;
		this.shape = hull;
	}
	
	@Override
	public Point calcAnchorPosition(){
		Point retVal = this.refAnchorPoints.nearestPointTo(this.otherEndPoint);
		return retVal;
	}

	@Override
	public boolean canCalcAnchorPosition() {
		return this.otherEndPoint != null;
	}

	@Override
	public IConvexHull getOwningShapeHull() {
		return this.shape;
	}

	@Override
	public void setOtherEndPoint(Point otherEndPoint) {
		this.otherEndPoint  = otherEndPoint;
	}

	@Override
	public Point getOtherEndPoint() {
		return this.otherEndPoint;
	}

}
