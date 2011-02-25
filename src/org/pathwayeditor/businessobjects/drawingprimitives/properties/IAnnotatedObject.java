/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import java.util.Iterator;

import uk.ac.ed.inf.graph.compound.IElementAttribute;


/**
 * @author Stuart Moodie
 *
 */
public interface IAnnotatedObject extends IElementAttribute {

	Iterator<IAnnotationProperty> propertyIterator();

	int numProperties();
	
	boolean containsProperty(IAnnotationProperty prop);

	boolean containsProperty(IPropertyDefinition propDefn);
	
	boolean containsProperty(String propName);

	IAnnotationProperty getProperty(IPropertyDefinition propDefn);
	
	IAnnotationProperty getProperty(String propName);
	
//	ILabelAttributeFactory labelAttributeFactory();
	
}
