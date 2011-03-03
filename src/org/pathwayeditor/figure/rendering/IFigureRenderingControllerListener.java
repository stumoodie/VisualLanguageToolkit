/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.rendering;

/**
 * IFigureChangeListener is an interface that defines a listener that is notified of 
 * attribute value changes in the {@link IFigureRenderingController}. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IFigureRenderingControllerListener {

	/**
	 * Notify the listener that a change event has occurred in the figure controller. 
	 * @param event the event recording the attribute change made.
	 */
	void attributeChange(IFigureRenderingControllerAttributeChangeEvent event);
	
}
