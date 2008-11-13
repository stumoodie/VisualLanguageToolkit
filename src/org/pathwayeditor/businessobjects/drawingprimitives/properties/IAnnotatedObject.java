/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.Iterator;


/**
 * @author smoodie
 *
 */
public interface IAnnotatedObject {

	Iterator<IAnnotationProperty> propertyIterator();
	
	IAnnotationProperty getProperty(IPropertyDefinition propDefn);
	
	IAnnotationProperty getProperty(String propName);
	
}
