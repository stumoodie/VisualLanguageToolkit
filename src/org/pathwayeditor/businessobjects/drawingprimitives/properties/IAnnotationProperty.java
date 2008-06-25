package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

public interface IAnnotationProperty {

	IAnnotatedObject getOwningObject();
	
	int getPropertySerial();
	
	IPropertyDefinition getDefinition();
	
	Object getValue();
}
