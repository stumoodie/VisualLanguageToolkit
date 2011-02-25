/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.math.BigDecimal;

public interface INumberAnnotationProperty extends IAnnotationProperty {
	
	/**
	 * Get the definition associated with this NumberProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	INumberPropertyDefinition getDefinition();
	
	/**
	 * Get the numerical value associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	@Override
	BigDecimal getValue();
	
	
	/**
	 * Get the numerical value associated with this property.
	 */
	void setValue(BigDecimal newValue);
	
}
