/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * IPlainTextAnnotationProperty is an interface the defines an annotation property of <code>String</code> values.
 * 
 * @author Stuart Moodie
 *
 */
public interface IPlainTextAnnotationProperty extends IAnnotationProperty {
	
	/**
	 * Get the definition associated with this PlainTextProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	IPlainTextPropertyDefinition getDefinition();
	
	/**
	 * Get the textual value associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	String getValue();
	
	/**
	 * Set the textual value associated with this property.
	 * @throws IllegalArgumentException The property value cannot be null.
	 */
	void setValue(String textValue);
}
