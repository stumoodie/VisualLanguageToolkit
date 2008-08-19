package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.typedefn.IPlainTextPropertyDefinition;


/**
 * Defines a text property, which can be plain text or html.
 * @author smoodie
 *
 */
public interface IPlainTextAnnotationProperty extends IVisualisableAnnotationProperty, IAnnotationProperty {

	IPlainTextPropertyDefinition getDefinition();
	
	String getTextValue();
	
	void setTextValue(String textValue);
}
