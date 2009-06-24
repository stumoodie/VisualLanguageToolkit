package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

public interface IAnchorLocator {

	IConvexHull getOwningShapeHull();
		
	void setOtherEndPoint(Point otherEndPoint);
	
	Point getOtherEndPoint();
	
	boolean canCalcAnchorPosition();
	
	Point calcAnchorPosition();
}
