/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

public class ChopBoxAnchorCalculator implements IAnchorLocator {
	private final IConvexHull shape;
	private Point otherEndPoint;
	private Point requestedPosn;
	
    public ChopBoxAnchorCalculator(IConvexHull hull) {
    	this.shape = hull;
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
	public Point calcAnchorPosition() {
		IConvexHull hull = this.shape;
		return hull.getPointLineIntersects(this.otherEndPoint);
	}


	@Override
	public void setOtherEndPoint(Point otherEndPoint) {
		this.otherEndPoint = otherEndPoint;
	}


	public void setRequestedPosition(Point requestedPosn) {
		this.requestedPosn = requestedPosn;
	}


	@Override
	public Point getOtherEndPoint() {
		return this.otherEndPoint;
	}


	public Point getRequestedPosition() {
		return this.requestedPosn;
	}

}