/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * @author smoodie
 *
 */
public interface IBooleanAnnotationProperty extends IAnnotationProperty {

	@Override
	IBooleanPropertyDefinition getDefinition();
	
	@Override
	Boolean getValue();
	
	void setValue(Boolean boolValue);

}
