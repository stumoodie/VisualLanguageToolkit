/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * IBooleanAnnotationProperty is an interface the defines an annotation property of boolean values.
 * 
 * @author Stuart Moodie
 *
 */
public interface IBooleanAnnotationProperty extends IAnnotationProperty {

	@Override
	IBooleanPropertyDefinition getDefinition();
	
	@Override
	Boolean getValue();
	
	/**
	 * Sets a new property value.
	 * @param boolValue the new property value to set. 
	 */
	void setValue(Boolean boolValue);

}
