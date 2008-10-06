package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

public interface IPropertyDefinition {

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
	 * Is the property visualisable
	 * @return True if it is, false otherwise.
	 */
	boolean isVisualisable();

	
	/**
	 * Is the property editable?
	 * @return True if it is, false otherwise.
	 */
	boolean isEditable();
	
	ILabelAttributeDefaults getLabelDefaults();
	
	IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder);

	/**
	 * @param propertyBuilder
	 * @return the copied annotation property.
	 */
	IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder);
}
