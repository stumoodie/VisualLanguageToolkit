package org.pathwayeditor.businessobjects.typedefn;

public interface IShapeParentingRules extends IObjectTypeParentingRules {

	/**
	 * Get object type that the rules belong to.
	 * @return an valid instance that is guaranteed to be non-null.
	 */
	IShapeObjectType getObjectType();
}
