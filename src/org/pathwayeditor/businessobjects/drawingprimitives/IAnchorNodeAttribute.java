/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * IAnchorNodeAttribute
 *
 * @author Stuart Moodie
 *
 */
public interface IAnchorNodeAttribute extends ITypedDrawingNodeAttribute {

	@Override
	IShapeObjectType getObjectType();
	
	ICurveSegment getAssociatedCurveSegment();
	
	String getShapeDefinition();
	
}
