package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;

public interface IAnnotationProperty {
	
	ICanvas getCanvas();
	
	/**
	 * Get the serial number of this property.
	 * @return The property serial. Cannot be null.
	 */
	int getCreationSerial();
	
	/**
	 * Get the definition associated with this PlainTextProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	IPropertyDefinition getDefinition();
	
	/**
	 * Get the an object representation of the value of this property
	 * @return An object representing the value of the property. Cannot be null.
	 */
	Object getValue();
}
