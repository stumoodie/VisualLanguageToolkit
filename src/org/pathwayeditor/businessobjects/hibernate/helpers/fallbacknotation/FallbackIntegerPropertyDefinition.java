/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibIntegerProperty;

/**
 * @author smoodie
 *
 */
public class FallbackIntegerPropertyDefinition extends FallbackPropertyDefinition implements IIntegerPropertyDefinition {
	
	public FallbackIntegerPropertyDefinition(HibIntegerProperty property) {
		super(property);
	}
	
	public Integer getDefaultValue() {
		return 1;
	}

}
