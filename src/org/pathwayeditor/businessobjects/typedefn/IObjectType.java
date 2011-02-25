/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;

/**
 * IObjectType
 * 
 * An object type is equal if it belongs to the same notation and has the same UniqueId().
 * Otherwise it should comply with the standard equals contract.

 * @author Stuart Moodie
 */
public interface IObjectType extends Comparable<IObjectType> {

	/**
	 * Gets the notation syntax service that this object type belongs to.
	 * @return the syntax service which cannot be null.
	 */
	INotationSyntaxService getSyntaxService();

	/**
	 * The identifier that uniquely identifies the object type within this notation.
	 * This should be a positive number, i.e. <code>getUniqueId() >= 0</code>.
	 * @return the unique object type id.
	 */
	int getUniqueId();
	
	/**
	 * The human readable name name of the object type. It should be unique within the notation. 
	 * @return the name, which cannot be null or an empty string.
	 */
	String getName();

	/**
	 * Description of the object type.
	 * @return the description that cannot be null, but can be an empty string.
	 */
	String getDescription();

	/**
	 * Get the parenting rules for this node object type, which must exist.
	 * @return the parenting rules, which cannot be null.
	 */
	IObjectTypeParentingRules getParentingRules();
	
	/**
	 * @param other the other object to compare.
	 * @return true if equal based on above criteria, false otherwise.
	 */
}
