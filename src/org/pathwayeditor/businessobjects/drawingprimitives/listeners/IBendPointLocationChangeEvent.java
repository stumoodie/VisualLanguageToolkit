/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.figure.geometry.Point;

/**
 * 
 * IBendPointLocationChangeEvent is an interface that defines an event fired when a bend-point's
 * location changes.
 *
 * @author Stuart Moodie
 *
 */
public interface IBendPointLocationChangeEvent {
	
	/**
	 * Gets the bend-point container that fired this event.
	 * @return then bend-point container.
	 */
	IBendPointContainer getBendPointContainer();
	
	/**
	 * Gets the index of the bend-point that changed.
	 * @return the bend-point index.
	 */
	int getBendPointIndex();

	/**
	 * Gets the original position of the bend-point.
	 * @return the original position, which cannot be null.
	 */
	Point getOldPosition();

	/**
	 * Gets the new bend-point position.
	 * @return the new position, which cannot be null.
	 */
	Point getNewPosition();
}
