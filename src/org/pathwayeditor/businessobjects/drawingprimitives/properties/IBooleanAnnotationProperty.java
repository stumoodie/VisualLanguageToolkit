/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * @author smoodie
 *
 */
public interface IBooleanAnnotationProperty extends IAnnotationProperty {

	IBooleanPropertyDefinition getDefinition();
	
	Boolean getValue();
	
	void setValue(Boolean boolValue);

}
