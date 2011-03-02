/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.math.BigDecimal;

/**
 * INumberPropertyDefinition is an interface that defines a number property.
 * 
 * @author Stuart Moodie
 *
 */
public interface INumberPropertyDefinition extends IPropertyDefinition {

	@Override
	BigDecimal getDefaultValue();
	
}
