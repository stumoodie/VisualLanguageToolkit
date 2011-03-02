/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

/**
 * 
 * ICanvasPropertyChangeListener is an interface describing a listener for attribute value changes to the
 * drawing canvas.  
 *
 * @author Stuart Moodie
 *
 */
public interface ICanvasPropertyChangeListener {

	/**
	 * A change to an attribute in the canvas has been made. 
	 * @param e the record of the attribute change 
	 */
	void propertyChange(ICanvasPropertyChangeEvent e);

}
