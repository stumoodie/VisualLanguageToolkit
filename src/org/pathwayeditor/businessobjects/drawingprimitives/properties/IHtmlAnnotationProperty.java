package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.typedefn.IHtmlPropertyDefinition;

public interface IHtmlAnnotationProperty extends IAnnotationProperty {

	IHtmlPropertyDefinition getDefinition();
	
	String getValue();
	void setValue(String newText);
	
}
