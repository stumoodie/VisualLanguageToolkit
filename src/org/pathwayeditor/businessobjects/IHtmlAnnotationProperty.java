package org.pathwayeditor.businessobjects;

import org.pathwayeditor.businessobjects.typedefn.IHtmlPropertyDefinition;

public interface IHtmlAnnotationProperty extends IAnnotationProperty {

	IHtmlPropertyDefinition getDefinition();
	
	String getValue();
	void setValue(String newText);
	
}
