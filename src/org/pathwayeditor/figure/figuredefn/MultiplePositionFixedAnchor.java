package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.geometry.PointList;

public class MultiplePositionFixedAnchor implements IAnchorLocator {
	private final PointList refAnchorPoints;
	private final IConvexHull shape;
	private Point otherEndPoint = null;
	
	
	public MultiplePositionFixedAnchor(PointList refAnchorPoints, IConvexHull hull){
		this.refAnchorPoints = refAnchorPoints;
		this.shape = hull;
	}
	
	public IConvexHull getShapeConvexHull(){
		return this.shape;
	}
	
	public Point calcAnchorPosition(){
		Point retVal = this.refAnchorPoints.nearestPointTo(this.otherEndPoint);
//		double shortedDistance = Double.POSITIVE_INFINITY;
//		for(Point p : this.refAnchorPoints){
//			double refDist = p.getDistance(this.otherEndPoint);
//			if(refDist < shortedDistance){
//				shortedDistance = refDist;
//				retVal = p;
//			}
//		}
		return retVal;
	}

	public boolean canCalcAnchorPosition() {
		return this.otherEndPoint != null;
	}

	public IConvexHull getOwningShapeHull() {
		return this.shape;
	}

	public void setOtherEndPoint(Point otherEndPoint) {
		this.otherEndPoint  = otherEndPoint;
	}

	public Point getOtherEndPoint() {
		return this.otherEndPoint;
	}

}
