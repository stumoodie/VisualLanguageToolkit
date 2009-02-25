/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;

/**
 * The hibernate notatation factory provides a mapping between the object type supplied by the notation subsystem
 * and a persistent copy that is stored and can be used to populate a fall-back notation subsystem. The notation subsystem has
 * 3 states, Uninitialised, Initialised, Failed. The latter occurs when an error occurs matching the Notation Subsystem with the
 * persistent version. This shoudln't happen and will only occur if releases of a notation susbsystem are not named and versioned
 * appropriately.   
 * @author smoodie
 *
 */
public interface IHibNotationFactory {

	/**
	 * Initialises the notation factory and reconciles the persisted notation definition
	 * with that supplied by the application. This will result in <code>isInitialised()==true</code> after completion.
	 * If there were errors during initialisation, then <code>hasInitialisationFailed()==true</code>. 
	 */
	void initialise();
	
	/**
	 * Tests if the factory has been initialised.
	 * @return true if it has, false otherwise.
	 */
	boolean isInitialised();
	
	/**
	 * Tests if initialisation has been attempted and failed.
	 * @return true if initialisation has failed, false otherwise.
	 */
	boolean hasInitialisationFailed();
	
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
	 * Does the factory contain a mapping for this object type.
	 * @param objectType the object type to test, which can be null.
	 * @return true if this factory has a mapping for this object type, false otherwise.
	 */
	boolean containsObjectType(IObjectType objectType);
	
	/**
	 * Gets the hibernate object type that maps to the objectType.   
	 * @param objectType the object type to get the mapping for, which cannot be null.
	 * @return the mapped hibernate object type.
	 * @throws IllegalArgumentException if <code>conatinsObjectType(objectType) == false</code>. 
	 */
	HibObjectType getObjectType(IObjectType objectType);
	
	/**
	 * Tests if this factory is a fallback, using a fallback notation susbsystem. Typically this will occur if
	 * the appropriate notation subsystem was not supplied by the application (e.g. because it does have it installed).
	 * This implies the <code>getNotationSubsystem().isFallback() == true</code>.  
	 * @return true if it is a fallback, false otherwise.
	 */
	boolean isFallback();
}
