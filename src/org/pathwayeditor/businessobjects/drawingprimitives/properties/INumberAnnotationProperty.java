package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.typedefn.INumberPropertyDefinition;

public interface INumberAnnotationProperty extends IAnnotationProperty {

	INumberPropertyDefinition getDefinition();
	
	BigDecimal getNumberValue();
	
	void setNumberValue(BigDecimal newValue);
	
}
