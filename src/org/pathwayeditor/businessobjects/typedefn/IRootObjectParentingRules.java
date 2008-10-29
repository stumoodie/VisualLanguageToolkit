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
	
}
