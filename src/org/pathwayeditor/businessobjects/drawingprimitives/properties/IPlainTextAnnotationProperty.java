package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * Defines a text property, which can be plain text or html.
 * @author smoodie
 *
 */
public interface IPlainTextAnnotationProperty extends IAnnotationProperty {
	
	/**
	 * Get the definition associated with this PlainTextProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	IPlainTextPropertyDefinition getDefinition();
	
	/**
	 * Get the textual value associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	String getValue();
	
	/**
	 * Set the textual value associated with this property.
	 * @throws IllegalArgumentException The property value cannot be null.
	 */
	void setValue(String textValue);
}
