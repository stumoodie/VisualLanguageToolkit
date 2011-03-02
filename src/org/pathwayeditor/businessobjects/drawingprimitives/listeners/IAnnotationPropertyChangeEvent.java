/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

/**
 * Records change to an annotation property's value.
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnnotationPropertyChangeEvent  {

	/**
	 * The annotation property affected by this change event.
	 * @return the annotation property, which cannot be null.
	 */
	IAnnotationProperty getPropertyDefinition();
	
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
