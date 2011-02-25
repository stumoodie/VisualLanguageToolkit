/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * @author Stuart Moodie
 *
 */
public interface IBooleanAnnotationProperty extends IAnnotationProperty {

	@Override
	IBooleanPropertyDefinition getDefinition();
	
	@Override
	Boolean getValue();
	
	void setValue(Boolean boolValue);

}
