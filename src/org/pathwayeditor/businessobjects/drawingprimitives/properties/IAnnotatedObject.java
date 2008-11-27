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

	int numProperties();
	
	boolean containsProperty(IAnnotationProperty propDefn);

	boolean containsProperty(IPropertyDefinition propDefn);
	
	boolean containsProperty(String propName);

	IAnnotationProperty getProperty(IPropertyDefinition propDefn);
	
	IAnnotationProperty getProperty(String propName);
	
}
