package org.pathwayeditor.businessobjects.typedefn;

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
