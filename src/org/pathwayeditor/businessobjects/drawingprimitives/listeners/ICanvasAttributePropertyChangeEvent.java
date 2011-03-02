/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;

/**
 * 
 * ICanvasAttributePropertyChangeEvent is an interface that provides a record of a change to an 
 * attribute of a canvas attribute. The nature of the change is indicated by <code>CanvasAttributePropertyChange</code>.
 *
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributePropertyChangeEvent {

	/**
	 * Gets the canvas attribute being listened to.
	 * @return the canvas attribute being listened to.
	 */
	ICanvasElementAttribute getAttribute();
	
	/**
	 * Gets the old property value. 
	 * @return the old property value, which can be null if the old value was null.
	 */
	Object getOldValue();
	
	/**
	 * Gets the new property value. 
	 * @return the new property value, which can be null if the new value was null.
	 */
	Object getNewValue();
	
	/**
	 * Get the type of the property.
	 * @return the type of the property, which cannot be null.
	 */
	CanvasAttributePropertyChange getPropertyChange();
	
}
