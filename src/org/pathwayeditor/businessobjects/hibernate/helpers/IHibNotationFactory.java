/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * @author smoodie
 *
 */
public interface IHibNotationFactory {

	/**
	 * Gets the the notation subsystem that this mapping corresponds to. 
	 * @return the notation  subsystem that cannot be null.
	 */
	INotationSubsystem getNotationSubsystem();
	
	/**
	 * Gets the hibernate notation corresponding to the specified notation. 
	 * @return the hibernate notation corresponding to this notation. 
	 */
	HibNotation getNotation();

	/**
	 * Gets the hibernate object type that maps to the objectType.   
	 * @param objectType the object type to get the mapping for, which cannot be null.
	 * @return the mapped hibernate object type.
	 * @throws IllegalArgumentException if <code>objectType == null</code>. 
	 */
	HibObjectType getObjectType(IObjectType objectType);
}
