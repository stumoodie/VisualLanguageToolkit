/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * 
 * IBendPointChangeListener is an interface defining a bend-point container listener that is invoked
 * when a change to the bend-point structure or properties is detected.  
 *
 * @author Stuart Moodie
 *
 */
public interface IBendPointContainerListener {

	/**
	 * A change to the structure of the bend-point container is detected.
	 * @param e the event that has been detected.
	 */
	void structureChange(IBendPointStructureChangeEvent e);

	/**
	 * A change to the location of a bend-point in the container has been detected.
	 * @param e the event that has been detected.
	 */
	void locationChange(IBendPointLocationChangeEvent e);

}
