/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.rendering;

/**
 * IFigureChangeEvent is an interface that defines an attribute change in a
 * figure controller.
 * 
 * @author Stuart Moodie
 *
 */
public interface IFigureRenderingControllerAttributeChangeEvent {

	/**
	 * The figure controller from which this event originated.
	 * @return the figure controller, which cannot be null.
	 */
	IFigureRenderingController getFigureController();
	
	/**
	 * The attribute change type.
	 * @return the type of attribute change.
	 */
	FigureChangeType getType();
	
	/**
	 * The original value of the attribute.
	 * @return the original attribute value.
	 */
	Object getOldValue();
	
	/**
	 * The new value of the attribute.
	 * @return the new attribute value.
	 */
	Object getNewValue();
	
}
