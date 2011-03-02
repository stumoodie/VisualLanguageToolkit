/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

/**
 * ILabelObjectTypeParentingRules is an interface that defines parenting rules
 * for use by a label object type.
 * 
 * @author Stuart Moodie
 *
 */
public interface ILabelObjectTypeParentingRules extends IObjectTypeParentingRules {

	/**
	 * Get object type that the rules belong to.
	 * @return an valid instance that is guaranteed to be non-null.
	 */
	@Override
	ILabelObjectType getObjectType();
	
}
