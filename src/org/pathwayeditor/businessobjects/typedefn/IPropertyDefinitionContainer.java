/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author smoodie
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
