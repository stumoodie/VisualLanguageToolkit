/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * 
 * IPropertyDefinition is an interface that provide a definition for an @{link IAnnotationProperty}. The property definition
 * has a name and provides a default (initial) value for the annotation property it defines. In addition it applies the Visitor design pattern
 * to provide a mechanism for the creation or copying of sub-types of <code>IAnnotationProperty</code>.   
 *
 * @author Stuart Moodie
 *
 */
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
	 * Creates a copy of an annotation property, using an instance of <code>IPropertyBuilder</code> to create the copy. This
	 * method uses the Visitor design pattern.  
	 * @param propertyBuilder is the Visitor instance that provides subtype specific operations to copy the anotation property provided by <code>otherProperty</code>.
	 * @param otherProperty the annotation property to be copied, which should not be null. 
	 * @return the copied annotation property.
	 */
	IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProperty);
	
}
