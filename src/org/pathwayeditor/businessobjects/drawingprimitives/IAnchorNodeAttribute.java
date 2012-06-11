/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeObjectType;
import org.pathwayeditor.figure.geometry.Point;

/**
 * IAnchorNodeAttribute
 *
 * @author Stuart Moodie
 *
 */
public interface IAnchorNodeAttribute extends ITypedDrawingNodeAttribute {

	@Override
	IAnchorNodeObjectType getObjectType();
	
	ICurveSegment getAssociatedCurveSegment();
	
	String getShapeDefinition();

	void setAnchorLocation(Point anchorPosn);
	
	Point getAnchorLocation();
	
}
