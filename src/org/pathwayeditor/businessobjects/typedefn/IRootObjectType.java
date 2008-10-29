package org.pathwayeditor.businessobjects.typedefn;


public interface IRootObjectType extends INodeObjectType {

	/**
	 * Returns the unique identifier for the link object type, which must be a positive integer.
	 * @return the unique id, which must comply with the postcondition: <code>getUniqueId() > 0</code>.
	 */
	int getUniqueId();

	/**
	 * Gets the patenting rules for the object type.
	 * @see #org.pathwayeditor.businessobjects.typedefn.INodeObjectType.getParentingRules() 
	 */
	IRootObjectParentingRules getParentingRules();

}
