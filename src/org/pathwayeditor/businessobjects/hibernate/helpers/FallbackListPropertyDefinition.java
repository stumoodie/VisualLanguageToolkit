/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import java.util.Collections;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation.FallbackPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibListProperty;

/**
 * @author smoodie
 *
 */
public class FallbackListPropertyDefinition extends FallbackPropertyDefinition implements IPropertyDefinition {

	/**
	 * @param property
	 */
	public FallbackListPropertyDefinition(HibListProperty property) {
		super(property);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getDefaultValue()
	 */
	public List<String> getDefaultValue() {
		return Collections.emptyList();
	}

}
