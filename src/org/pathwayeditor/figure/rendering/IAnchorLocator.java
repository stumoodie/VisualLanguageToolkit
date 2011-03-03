/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.rendering;

import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

/**
 * 
 * IAnchorLocator is am interface that defines an anchor locator. The anchor locator
 * calculates the correct anchor location for a link. The calculation is defined by the
 * anchor location algorithm provided by the implementing class.   
 *
 * @author Stuart Moodie
 *
 */
public interface IAnchorLocator {

	/**
	 * Get the convex hull of the shape on which the anchor will be located.
	 * @return the convex hull of the owning shape, which cannot be null.
	 */
	IConvexHull getOwningShapeHull();
		
	/**
	 * Set the other end point of the link or line segment, which will be used for reference when calculating
	 * the new anchor location.
	 * @param otherEndPoint the other end point of the link, which should not be null.
	 */
	void setOtherEndPoint(Point otherEndPoint);
	
	/**
	 * Get the other end point used as a reference by this locator. 
	 * @return the other end point.
	 */
	Point getOtherEndPoint();
	
	/**
	 * Will the calculation of anchor location succeed? At a minimum the locator will need to
	 * have another end point specified.    
	 * @return true if the anchor position can be calculated, false otherwise. 
	 */
	boolean canCalcAnchorPosition();
	
	/**
	 * Calculate the anchor location based on the other end point and the specified 
	 * convex hull. 
	 * @return the calculated anchor location.
	 * @throws IllegalStateException if <code>canCalcAnchorPosition() == false</code>.
	 */
	Point calcAnchorPosition();
}
