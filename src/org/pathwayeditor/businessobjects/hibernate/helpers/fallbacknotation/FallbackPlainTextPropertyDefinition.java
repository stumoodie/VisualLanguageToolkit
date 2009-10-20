/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibTextProperty;

/**
 * @author smoodie
 *
 */
public class FallbackPlainTextPropertyDefinition extends FallbackPropertyDefinition implements IPlainTextPropertyDefinition {
	
	public FallbackPlainTextPropertyDefinition(HibTextProperty property) {
		super(property);
	}
	
	public String getDefaultValue() {
		return "";
	}

}
