/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibBooleanProperty;

/**
 * @author smoodie
 *
 */
public class FallbackBooleanPropertyDefinition extends FallbackPropertyDefinition implements IBooleanPropertyDefinition {
	
	public FallbackBooleanPropertyDefinition(HibBooleanProperty property) {
		super(property);
	}
	
	public Boolean getDefaultValue() {
		return false;
	}

}
