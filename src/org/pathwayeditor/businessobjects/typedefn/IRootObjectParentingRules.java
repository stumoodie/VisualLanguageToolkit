/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

/**
 * IRootObjectParentingRules is an interface that defines the parent/child syntax for 
 * a root node's object type.
 * 
 * @author Stuart Moodie
 *
 */
public interface IRootObjectParentingRules extends IObjectTypeParentingRules {

	/**
	 * Get object type that the rules belong to.
	 * @return an valid instance that is guaranteed to be non-null.
	 */
	@Override
	IRootObjectType getObjectType();
	
}
