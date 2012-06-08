/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeObjectType;

/**
 * IAnchorNodeAttributeFactory
 *
 * @author Stuart Moodie
 *
 */
public interface IAnchorNodeAttributeFactory extends ICanvasAttributeFactory {

	/**
	 * @param linkEndObjectType
	 */
	void setObjectType(IAnchorNodeObjectType linkEndObjectType);
	
	IAnchorNodeObjectType getObjectType();
	
	void setAssociateCurveSegment(ICurveSegment associatedCurveSegment);

	ICurveSegment getAssociatedCurveSegment();
	
}
