/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * @author Stuart Moodie
 *
 */
public interface ILinkAttributeFactory extends ICanvasAttributeFactory {

	@Override
	ILinkAttribute createAttribute();
	
	void setObjectType(ILinkObjectType objectType);
	
	ILinkObjectType getObjectType();

	@Override
	IShapeAttribute getOutAttribute();

	@Override
	IShapeAttribute getInAttribute();
	
}
