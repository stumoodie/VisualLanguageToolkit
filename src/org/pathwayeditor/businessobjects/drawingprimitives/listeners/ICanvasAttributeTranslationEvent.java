/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.figure.geometry.Point;

/**
 * ICanvasAttributeTranslationEvent is an interface that describes an translation event on a canvas attribute.
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeTranslationEvent {

	/**
	 * Gets the canvas attribute from which this event originated.
	 * @return the canvas attribute.
	 */
	ICanvasElementAttribute getAttibuteChanged();
	
	/**
	 * Gets the change in the position of the canvas attribute's bounding rectangle.
	 * @return the change in position - the translation.
	 */
	Point getTranslationDelta();
	
}
