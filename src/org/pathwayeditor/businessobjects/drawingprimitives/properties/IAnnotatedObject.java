/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.graphics.ILabelLocationPolicy;


/**
 * @author smoodie
 *
 */
public interface IAnnotatedObject {

	Iterator<IAnnotationProperty> propertyIterator();

	int numProperties();
	
	boolean containsProperty(IAnnotationProperty prop);

	boolean containsProperty(IPropertyDefinition propDefn);
	
	boolean containsProperty(String propName);

	IAnnotationProperty getProperty(IPropertyDefinition propDefn);
	
	IAnnotationProperty getProperty(String propName);
	
	ILabelLocationPolicy getLabelLocationPolicy();
	
	void setLabelLocationPolicy(ILabelLocationPolicy labelLocationPolicy);
	
}
