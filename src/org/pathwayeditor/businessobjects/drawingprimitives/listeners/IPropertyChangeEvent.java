package org.pathwayeditor.businessobjects.drawingprimitives.listeners;


public interface IPropertyChangeEvent {

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
	PropertyChange getPropertyChange();
	
}
