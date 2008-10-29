package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.math.BigDecimal;

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
	BigDecimal getValue();
	
	
	/**
	 * Get the numerical value associated with this property.
	 */
	void setValue(BigDecimal newValue);
	
}
