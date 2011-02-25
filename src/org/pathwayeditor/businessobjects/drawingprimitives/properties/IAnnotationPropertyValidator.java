/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


/**
 * @author Stuart Moodie
 *
 */
public interface IAnnotationPropertyValidator {

	boolean isValidIntegerAnnotationProperty(Object prop);
	
	boolean isValidBooleanAnnotationProperty(Object prop);
	
	boolean isValidPlainTextAnnotationProperty(Object prop);
	
	boolean isValidNumberAnnotationProperty(Object prop);
	
	boolean isValidListAnnotationProperty(Object prop);
	
}
