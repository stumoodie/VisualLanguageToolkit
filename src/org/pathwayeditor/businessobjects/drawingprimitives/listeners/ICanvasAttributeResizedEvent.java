/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * ICanvasAttributeResizedEvent is an interface that describes an canvas attribute resize event. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeResizedEvent {

	/**
	 * Gets the canvas attribute that this event originated from. 
	 * @return the canvas attribute that initiated this event.
	 */
	ICanvasElementAttribute getAttibuteChanged();

	/**
	 * Gets the change to origin of the bounding rectangle of the canvas attribute that was made. 
	 * @return the change to the origin of the bounding rectangle.
	 */
	Point getOriginChange();
	
	/**
	 * Gets the change to the size of the bounding rectangle of the attribute that was made.
	 * @return the change to the size of the bounding rectangle of the affected attribute.
	 */
	Dimension getSizeChange();
}
