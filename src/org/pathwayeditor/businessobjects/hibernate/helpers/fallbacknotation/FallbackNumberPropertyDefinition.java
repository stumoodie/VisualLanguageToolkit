/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNumberProperty;

/**
 * @author smoodie
 *
 */
public class FallbackNumberPropertyDefinition extends FallbackPropertyDefinition implements INumberPropertyDefinition {
	
	public FallbackNumberPropertyDefinition(HibNumberProperty property) {
		super(property);
	}
	
	public BigDecimal getDefaultValue() {
		return BigDecimal.ONE;
	}

}
