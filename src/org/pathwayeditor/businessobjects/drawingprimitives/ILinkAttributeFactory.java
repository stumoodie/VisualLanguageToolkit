/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * ILinkAttributeFactory is an interface defining a factory for link attributes @{link ILinkAttribute}.
 * The factory requires an object type to create a link attribute.
 * The method {@link #canCreateAttribute()} should return false if this is not set.
 *     
 * @author Stuart Moodie
 *
 */
public interface ILinkAttributeFactory extends ICanvasAttributeFactory {

	@Override
	ILinkAttribute createAttribute();
	
	/**
	 * Sets the object type to be used when creating the next link attribute.
	 * @param objectType the object type to be used for the next link attribute to be created.
	 */
	void setObjectType(ILinkObjectType objectType);
	
	/**
	 * Gets the link object type to be used when creating the next link attribute.
	 * @return the link object type, which can be null if not set.
	 */
	ILinkObjectType getObjectType();

	@Override
	IShapeAttribute getOutAttribute();

	@Override
	IShapeAttribute getInAttribute();
	
}
