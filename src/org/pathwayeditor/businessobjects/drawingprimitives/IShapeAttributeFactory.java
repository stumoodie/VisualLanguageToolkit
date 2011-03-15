/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * IShapeAttributeFactory is an interface that defines a factory used to
 * create shape attributes. The factory must have an object type associated
 * with it before {@link IElementAttributeFactory#canCreateAttribute} can be true.
 *  
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
	 * Sets the object type to be used when creating the next attribute.
	 * @param objectType the object type to use.
	 */
	void setObjectType(IShapeObjectType objectType);
	
	@Override
	IShapeAttribute createAttribute();

}
