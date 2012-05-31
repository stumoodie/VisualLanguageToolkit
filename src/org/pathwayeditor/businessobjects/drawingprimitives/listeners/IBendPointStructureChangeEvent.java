/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.figure.geometry.Point;

/**
 * IBendPointStructureChangeEvent is an interface that defines a change to the structure of a bend
 * point container.
 *
 * @author Stuart Moodie
 *
 */
public interface IBendPointStructureChangeEvent {

	/**
	 * Gets the change type.
	 * @return the change type.
	 */
	BendPointStructureChange getChangeType();
	
	/**
	 * Gets the bend point container that this event refers to.
	 * @return the modified bendpoint container, which cannot be null.
	 */
	IBendPointContainer getBendPointContainer();
	
	/**
	 * gets the position of the bend point that has been restructured. 
	 * @return the position, which cannot be null.
	 */
	Point getBendPoint();
	
	/**
	 * Gets the original index position of this bend point. If a new bend-point has
	 * been added this value should be ignored. 
	 * @return the original index position.
	 */
	int getOldIndexPos();

	/**
	 * Gets the new index position of this bend point. If a bend-point has
	 * been removed this value should be ignored. 
	 * @return the new index position.
	 */
	int getNewIndexPos();
}
