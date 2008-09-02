package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.typedefn.INumberPropertyDefinition;

public interface INumberAnnotationProperty extends IVisualisableAnnotationProperty, IAnnotationProperty {
	
	/**
	 * Get the definition associated with this NumberProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	INumberPropertyDefinition getDefinition();
	
	/**
	 * Get the numerical value associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	BigDecimal getNumberValue();
	
	
	/**
	 * Get the numerical value associated with this property.
	 */
	void setNumberValue(BigDecimal newValue);
	
}
