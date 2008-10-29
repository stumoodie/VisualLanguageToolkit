package org.pathwayeditor.businessobjects.drawingprimitives.properties;

public interface IHtmlAnnotationProperty extends IVisualisableAnnotationProperty, IAnnotationProperty {
	
	/**
	 * Get the definition associated with this HtmlProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	IHtmlPropertyDefinition getDefinition();
	
	/**
	 * Get the textual value associated with this HtmlProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	String getRichTextValue();
	
	/**
	 * Set the textual value associated with this property.
	 * @throws IllegalArgumentException The property value cannot be null.
	 */
	void setValue(String newText);
	
}
