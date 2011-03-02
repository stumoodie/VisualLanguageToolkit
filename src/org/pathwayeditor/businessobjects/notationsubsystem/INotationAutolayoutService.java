/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * 
 * INotationAutolayoutService is an interface that defines the auto-layout service
 * provided by the notation subsystem.
 *
 * @author Stuart Moodie
 *
 */
public interface INotationAutolayoutService extends INotationService {

	/**
	 * The NS does not need to implement the auto-layout service. This method tests is it is implemented.
	 * @return true if service is implemented, false otherwise.
	 */
	boolean isImplemented();
	
	/**
	 * @param model which will be laid out by this service
	 */
	void layout(IModel model);
	
}
