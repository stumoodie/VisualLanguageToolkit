/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

/**
 * 
 * IRootObjectType is an interface defining an object type for root nodes in
 * a model. See {@link IObjectType} for more information on object types.
 *
 * @author Stuart Moodie
 *
 */
public interface IRootObjectType extends INodeObjectType {

	/**
	 * Returns the unique identifier for the link object type, which must be a positive integer.
	 * @return the unique id, which must comply with the postcondition: <code>getUniqueId() > 0</code>.
	 */
	@Override
	int getUniqueId();

	/**
	 * Gets the parenting rules for the object type.
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getParentingRules() 
	 */
	@Override
	IRootObjectParentingRules getParentingRules();

}
