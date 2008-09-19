/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

/**
 * @author smoodie
 *
 */
public interface IRootObjectParentingRules extends IObjectTypeParentingRules {

	/**
	 * Get object type that the rules belong to.
	 * @return an valid instance that is guaranteed to be non-null.
	 */
	IRootObjectType getObjectType();
	
	/**
	 * Tests if a shape type is a valid child of the owning object type. 
	 * @param possibleChild non-null instance of the shape type.
	 * @return true if valid, false otherwise.
	 * @throws IllegalArgumentException if possibleChild is null.
	 */
	boolean isValidChildByCode(IRootObjectType possibleChild);
}
