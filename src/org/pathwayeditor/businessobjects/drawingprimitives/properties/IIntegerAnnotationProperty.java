/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * IIntegerAnnotationProperty is an interface the defines an annotation property of integer values.
 * 
 * @author Stuart Moodie
 *
 */
public interface IIntegerAnnotationProperty extends IAnnotationProperty {

	@Override
	IIntegerPropertyDefinition getDefinition();
	
	@Override
	Integer getValue();
	
	/**
	 * Sets a new property value.
	 * @param boolValue the new property value to set. 
	 */
	void setValue(Integer boolValue);

}
