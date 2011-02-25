/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author Stuart Moodie
 *
 */
public interface IPropertyDefinitionContainer {
	/**
	 * Provides an iterator for the set of all property definitions.
	 * @return an iterator, that cannot be null, but may iterator over an empry collection.
	 */
	Iterator<IPropertyDefinition> propertyDefinitionIterator();

	/**
	 * Test if this contains a property definition of this name.
	 * @param name the property name, which can be null.
	 * @return true if it exists, false otherwise.
	 */
	boolean containsPropertyDefinition(String name);
	
	/**
	 * Test if this contains a property definition of this name.
	 * @param name the property name, which can be null.
	 * @return true if it exists, false otherwise.
	 */
	boolean containsPropertyDefinition(IPropertyDefinition name);
	
	/**
	 * Gets the property definition with the given name.
	 * @param name the name which must be contained.
	 * @return the property definition with the given name, which cannot be null.
	 * @throws IllegalArgumentException if <code>containsPropertyDefinition(name) == false</code.
	 */
	IPropertyDefinition getPropertyDefinition(String name);
	
	/**
	 * gets the number of property definitions held by this conbtainer.
	 * @return the number of properties, which cannot be negative.
	 */
	int numPropertyDefinitions();
}
