/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author Stuart Moodie
 *
 */
public interface IShapeAttributeFactory extends ICanvasAttributeFactory {

	/**
	 * Get the object type of the new shape attribute to be created.
	 * @return the object type 
	 */
	IShapeObjectType getObjectType();

	/**
	 * Get the attribute that will be the parent of the newly created attribute
	 * @return the attribute, which cannot be null.
	 */
	@Override
	ICanvasElementAttribute getDestinationAttribute();

	/**
	 * @param objectType
	 */
	void setObjectType(IShapeObjectType objectType);
	
	@Override
	IShapeAttribute createAttribute();

}
