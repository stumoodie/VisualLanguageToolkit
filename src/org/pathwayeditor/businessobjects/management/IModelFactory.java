/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author Stuart Moodie
 *
 */
public interface IModelFactory {

	/**
	 * @param serialIdx
	 */
	void setRootCreationSerial(int serialIdx);

	int getRootCreationSerial();
	
	/**
	 * @param lastSerialIdx
	 */
	void setLastCreationSerial(int lastSerialIdx);

	int getLastCreationSerial();
	
	/**
	 * @param name
	 */
	void setName(String name);
	
	String getName();

	void setNotationSubsystem(INotationSubsystem notationSubsystem);

	INotationSubsystem getNotationSubsystem();
	
	boolean canCreateModel();
	
	IModel createModel();

}
