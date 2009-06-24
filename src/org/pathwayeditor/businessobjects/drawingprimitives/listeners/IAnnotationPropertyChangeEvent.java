/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author smoodie
 *
 */
public interface IAnnotationPropertyChangeEvent  {

	IPropertyDefinition getPropertyDefinition();
	
	/**
	 * Gets the old property value. 
	 * @return the old property value, which can be null if the old value was null.
	 */
	Object getOldValue();
	
	/**
	 * Gets the new property value. 
	 * @return the new property value, which can be null if the new value was null.
	 */
	Object getNewValue();
}
