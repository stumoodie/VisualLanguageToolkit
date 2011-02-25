/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

/**
 * 
 * IAnchorLocator
 *
 * @author Stuart Moodie
 *
 */
public interface IAnchorLocator {

	IConvexHull getOwningShapeHull();
		
	void setOtherEndPoint(Point otherEndPoint);
	
	Point getOtherEndPoint();
	
	boolean canCalcAnchorPosition();
	
	Point calcAnchorPosition();
}
