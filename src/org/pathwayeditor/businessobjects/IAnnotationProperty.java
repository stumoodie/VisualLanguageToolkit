package org.pathwayeditor.businessobjects;

import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

public interface IAnnotationProperty {

	int getPropertySerial();
	
	IPropertyDefinition getDefinition();
	
	Object getValue();
}
