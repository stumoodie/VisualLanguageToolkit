/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.Set;

/**
 * @author smoodie
 *
 */
public interface IAnnotatedObject {

	Set<IAnnotationProperty> propertyIterator();
	
	IAnnotationProperty getProperty(IPropertyDefinition propDefn);
	
	IAnnotationProperty getProperty(String propName);
	
}
