/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

/**
 * @author smoodie
 *
 */
public interface IIntegerAnnotationProperty extends IAnnotationProperty {

	@Override
	IIntegerPropertyDefinition getDefinition();
	
	@Override
	Integer getValue();
	
	void setValue(Integer boolValue);

}
