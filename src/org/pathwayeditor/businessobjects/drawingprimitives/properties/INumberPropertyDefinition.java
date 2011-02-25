/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.math.BigDecimal;

public interface INumberPropertyDefinition extends IPropertyDefinition {

	@Override
	BigDecimal getDefaultValue();
	
}
