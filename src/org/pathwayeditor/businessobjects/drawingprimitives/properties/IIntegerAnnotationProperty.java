/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * @author smoodie
 *
 */
public interface IIntegerAnnotationProperty extends IAnnotationProperty {

	IIntegerPropertyDefinition getDefinition();
	
	Integer getValue();
	
	void setValue(Integer boolValue);

}
