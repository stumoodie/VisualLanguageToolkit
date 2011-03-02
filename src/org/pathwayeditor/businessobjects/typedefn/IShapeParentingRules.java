/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

/**
 * 
 * IShapeParentingRules is an interface that defines the parebting rules
 * for a shape object type.
 *
 * @author Stuart Moodie
 *
 */
public interface IShapeParentingRules extends IObjectTypeParentingRules {

	/**
	 * Get object type that the rules belong to.
	 * @return an valid instance that is guaranteed to be non-null.
	 */
	@Override
	IShapeObjectType getObjectType();
}
