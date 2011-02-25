/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;


public interface IRootObjectType extends INodeObjectType {

	/**
	 * Returns the unique identifier for the link object type, which must be a positive integer.
	 * @return the unique id, which must comply with the postcondition: <code>getUniqueId() > 0</code>.
	 */
	@Override
	int getUniqueId();

	/**
	 * Gets the parenting rules for the object type.
	 * @see #org.pathwayeditor.businessobjects.typedefn.INodeObjectType.getParentingRules() 
	 */
	@Override
	IRootObjectParentingRules getParentingRules();

}
