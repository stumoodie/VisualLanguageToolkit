/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

/**
 * 
 * IObjectTypeParentingRules is an interface that defines the parent child
 * syntax of an {@link IObjectType}. 
 *
 * @author Stuart Moodie
 *
 */
public interface IObjectTypeParentingRules {

	/**
	 * Get object type that the rules belong to.
	 * @return an valid instance that is guaranteed to be non-null.
	 */
	IObjectType getObjectType();
	
	/**
	 * Tests if a shape type is a valid child of the owning object type. 
	 * @param possibleChild non-null instance of enumerated type.
	 * @return true if valid, false otherwise.
	 * @throws IllegalArgumentException if possibleChild is null.
	 */
	boolean isValidChild(IObjectType possibleChild);
}
