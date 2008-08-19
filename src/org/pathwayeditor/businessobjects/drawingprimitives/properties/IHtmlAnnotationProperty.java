package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.typedefn.IHtmlPropertyDefinition;

public interface IHtmlAnnotationProperty extends IVisualisableAnnotationProperty, IAnnotationProperty {

	IHtmlPropertyDefinition getDefinition();
	
	String getRichTextValue();
	void setRichTextValue(String newText);
	
}
