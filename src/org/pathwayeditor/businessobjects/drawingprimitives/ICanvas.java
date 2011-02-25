/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListenee;
import org.pathwayeditor.figure.geometry.Envelope;

/**
 * ICanvas is an interface that holds the key properties of a canvas upon which a diagram is drawn. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvas extends ICanvasPropertyChangeListenee {

	/**
	 * Get the size of this map
	 * @return the size. Cannot be null.
	 */	
	Envelope getCanvasBounds () ;
	
	/**
	 * Set the size of this map
	 * @throws IllegalArgumentException if value is null.
	 */	
	void setCanvasBounds (Envelope bounds) ;

	/**
	 * Set the background color of this Canvas.
	 * @throws IllegalArgumentException if value is null.
	 */	
	void setBackgroundColour(RGB backgroundColour);
	
	/**
	 * Get the background color of this Canvas.
	 * @return the RGB representation of the color of the Canvas.
	 */	
	RGB getBackgroundColour();
	
}
