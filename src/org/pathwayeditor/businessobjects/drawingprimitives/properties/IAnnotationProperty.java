package org.pathwayeditor.businessobjects.drawingprimitives.properties;


public interface IAnnotationProperty {
	
	/**
	 * Get the definition associated with this PlainTextProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	IPropertyDefinition getDefinition();
	
	/**
	 * Gets the owner of this property
	 * @return the owner of this property, which cannot be null. 
	 */
	IAnnotatedObject getOwner();
	
	/**
	 * Get the an object representation of the value of this property
	 * @return An object representing the value of the property. Cannot be null.
	 */
	Object getValue();
}
