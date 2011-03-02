/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * IModelFactory is an interface that defines a factory for creating a
 * new <code>IModel</code>. This factory is intended to be used to
 * create new instances of a model as well as models that are to be 
 * unmarshalled from an XML Schema representation. To support the
 * latter case methods are present that allow a client to restore the
 * state of the model to its persisted state. These methods should generally
 * not be used by the typical client of this library. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IModelFactory {

	/**
	 * Sets the serial number of the root attribute. Typically this method should
	 * not be used as the default value will be correct for new model creation.
	 * @param serialIdx the serial index number.
	 */
	void setRootCreationSerial(int serialIdx);

	/**
	 * Gets the root attribute creation serial number that us currently set. 
	 * @return the root attribute creation serial number in current use.
	 */
	int getRootCreationSerial();
	
	/**
	 * Sets the last creation serial number that was assigned to a canvas attribute
	 * by the model. This should be ignored by the typical client as the default client will
	 * be correct for new model creation.
	 * @param lastSerialIdx the last serial index number.
	 */
	void setLastCreationSerial(int lastSerialIdx);

	/**
	 * Get the currently set last creation serial number.
	 * @return the last creation serial number.
	 */
	int getLastCreationSerial();
	
	/**
	 * Set the name of the new model to be created.
	 * @param name the name of the new model, which should not be null or an empty
	 * string.
	 * @throws IllegalArgumentException if the model name is invalid.
	 */
	void setName(String name);
	
	/**
	 * Gets the name currently set for the the next model. 
	 */
	String getName();

	/**
	 * Sets the notation subsystem to be used with the next model to be created. 
	 * @param notationSubsystem the notation subsystem, which should not be null.
	 */
	void setNotationSubsystem(INotationSubsystem notationSubsystem);

	/**
	 * Gets the notation subsystem currently set.
	 * @return the notation subsystem currently set.
	 */
	INotationSubsystem getNotationSubsystem();
	
	/**
	 * Tests if the factory is in the correct state to create a new model. Typically
	 * this factory will require a name and notation subsystem to have been set. 
	 * @return true if a new model could be created, false otherwise.
	 */
	boolean canCreateModel();
	
	/**
	 * Creates a new model using the values set in this factory.
	 * @return the newly created model.
	 * @throws IllegalStateException if <code>canCreateModel() == false</code>.
	 */
	IModel createModel();

}
