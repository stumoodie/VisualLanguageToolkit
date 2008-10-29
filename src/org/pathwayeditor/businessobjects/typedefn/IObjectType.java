package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;

/**
 * @author smoodie
 *
 */
public interface IObjectType extends Comparable<IObjectType> {

	INotationSyntaxService getSyntaxService();

	/**
	 * The identifier that uniquely identifies the object type within this notation.
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
	 * @return the desscription that cannot be null, but can be an empty string.
	 */
	String getDescription();

	/**
	 * An object type is equal if it belongs to the same notation and has the same UniqueId().
	 * Otherwise it should comply with the standard equals contract.
	 * @param other the other object to compare.
	 * @return true if equal based on above criteria, false otherwise.
	 */
	boolean equals(Object other);
	
	/**
	 * Gets the hash code, which is based on the notation and uniqueId. 
	 * @return the hashcode.
	 */
	int hashCode();
}
