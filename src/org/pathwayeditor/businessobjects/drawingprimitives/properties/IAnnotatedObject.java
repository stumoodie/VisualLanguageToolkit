/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.Set;

import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

/**
 * @author smoodie
 *
 */
public interface IAnnotatedObject {

	Set<IAnnotationProperty> propertyIterator();
	
	IAnnotationProperty getProperty(IPropertyDefinition propDefn);
	
	IAnnotationProperty getProperty(String propName);
	
}
