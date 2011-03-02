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
 * IAnnotatedObject is an interface that should be implemented by classes using
 * annotation properties: see {@link IAnnotationProperty}.  Each annotation property is
 * defined by an {@link IPropertyDefinition}, which has a name. The name and <code>IPropertyDefinition</code>
 * are unique within a given instance of this interface.  
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnnotatedObject extends IElementAttribute {

	/**
	 * Gets a new iterator that will iterate over the annotation properties contained by this annotated object.
	 * @return the new iterator.
	 */
	Iterator<IAnnotationProperty> propertyIterator();

	/**
	 * Gets the number of annotated objects held by this annotated object.
	 * @return the number of annotated properties, which cannot be negative.
	 */
	int numProperties();
	
	/**
	 * Test is the annotation property is help by this annotated object.
	 * @param prop the annotation property to test.
	 * @return true if <code>prop</code> is help by this object, false otherwise. 
	 */
	boolean containsProperty(IAnnotationProperty prop);

	/**
	 * Test if an annotation property with the specified <code>propDefn</code> is help by this annotated object.
	 * @param propDefn the property definition to test.
	 * @return true if a property with the <code>propDefn</code> is help by this object, false otherwise. 
	 */
	boolean containsProperty(IPropertyDefinition propDefn);
	
	/**
	 * Test if an annotation property with the specified name is help by this annotated object.
	 * @param propName the property name to test.
	 * @return true if a property with the specified name is help by this object, false otherwise. 
	 */
	boolean containsProperty(String propName);

	/**
	 * Gets a property containing the specified property definition.
	 * @param propDefn the property definition, which should not be null.
	 * @return the property with the specified definition
	 * @throws IllegalArgumentException of a property containing <code>propDefn</code> cannot be found.
	 */
	IAnnotationProperty getProperty(IPropertyDefinition propDefn);
	
	/**
	 * Gets a property with the specified property name.
	 * @param propName the property name.
	 * @return the property with the specified name.
	 * @throws IllegalArgumentException of a property containing <code>propName</code> cannot be found.
	 */
	IAnnotationProperty getProperty(String propName);
	
}
