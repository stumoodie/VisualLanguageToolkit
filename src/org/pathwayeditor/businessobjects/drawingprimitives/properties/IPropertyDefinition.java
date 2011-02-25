/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


public interface IPropertyDefinition extends Comparable<IPropertyDefinition> {
	/**
	 * The property value as an object appropriate for its type.
	 * This can be redefined by implementing classes. 
	 * @return An object type appropriate for the property type. Guaranteed to be non-null. 
	 */
	Object getDefaultValue();
	
	/**
	 * The property name
	 * @return S string that is not null.
	 */
	String getName();
	
	/**
	 * Get the display name.
	 * @return the display name which cannot be null.
	 */
	String getDisplayName();
	
	/**
	 * Is the property editable?
	 * @return True if it is, false otherwise.
	 */
	boolean isEditable();
	
	/**
	 * A method to create the property using the visitor pattern. 
	 * @param propertyBuilder
	 * @return the newly created property.
	 */
	IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder);

	/**
	 * @param propertyBuilder
	 * @return the copied annotation property.
	 */
	IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProperty);
	
}
