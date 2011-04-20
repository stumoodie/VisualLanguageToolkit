/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * Defines the notation susbsystem syntax service. Implementations of this interfaces should define the visual
 * look of the glyphs in the notation and its syntactic rules. This is done by providing implementations 
 * of IShapeObjectType and ILinkObjectType and an IRootMapObject for the map.
 * <p>
 * The API provides methods for accessing the object types so created, which are then used to define new
 * map objects and the notation's syntactic rules.
 * <p>
 * The object type also controls which properties of its associated map object are editable. It also
 * defines additional annotation properties, which again may be editable and also displayed as a label 
 * associated with the map object (visualisable).
 * 
 * @author Stuart Moodie
 *
 */
public interface INotationSyntaxService extends INotationService {

	/**
	 * Returns an iterator of shape object types defined by this syntax service.
	 * @return An iterator of shape object types cannot be null.
	 */
	Iterator<IShapeObjectType> shapeTypeIterator();

	/**
	 * Returns an iterator of link object types defined by this syntax service.
	 * @return An iterator of link object types cannot be null.
	 */
	Iterator<ILinkObjectType> linkTypeIterator();

	/**
	 * Returns an iterator over the object types help by this syntax service. 
	 * @return The iterator, which may iterate over an empty collection.
	 */
	Iterator<IObjectType> objectTypeIterator();

	/**
	 * Returns an object type defining the map attribute defaults and syntax rules for shapes that can be
	 * placed on it.
	 * @return An instance of the root map object. This cannot be null and the same object will always be
	 * returned for a given instance of this interface.
	 */
	IRootObjectType getRootObjectType();

	/**
	 * Tests if an object type with the given uniqueId exists in this syntax service.
	 * @param uniqueId the unique id to be tested.
	 * @return true if is contains the object type, false otherwise.
	 */
	boolean containsShapeObjectType(int uniqueId); 
	
	/**
	 * Retrieve the object type based on it's object id.
	 * @param uniqueId the unique identifier of the object type.
	 * @return the object type, which cannot be null.
	 * @throws IllegalArgumentException if the objectType cannot be found.
	 */
	IShapeObjectType getShapeObjectType(int uniqueId);

	/**
	 * Tests if a link object type with the given uniqueId exists in this syntax service.
	 * @param uniqueId the unique id to be tested.
	 * @return true if is contains the link object type, false otherwise.
	 */
	boolean containsLinkObjectType(int uniqueId); 
	
	/**
	 * Retrieve the link object type based on it's object id.
	 * @param uniqueId the unique identifier of the object type.
	 * @return the object type, which cannot be null.
	 * @throws IllegalArgumentException if the objectType cannot be found.
	 */
	ILinkObjectType getLinkObjectType(int uniqueId);

	/**
	 * Tests if a object type with the given uniqueId exists in this syntax service.
	 * @param uniqueId the unique id to be tested.
	 * @return true if is contains the object type, false otherwise.
	 */
	boolean containsObjectType(int uniqueId); 
	
	/**
	 * Retrieve the object type based on it's unique id.
	 * @param uniqueId the unique identifier of the object type.
	 * @return the object type, which cannot be null.
	 * @throws IllegalArgumentException if the objectType cannot be found.
	 */
	IObjectType getObjectType(int uniqueId);
	
	/**
	 * Get the label object type corresponding to the provided id.
	 * @param uniqueId the unique id of the object type 
	 * @return the label object type with the <code>uniqueId</code> or null if it cannot be found.
	 */
	ILabelObjectType getLabelObjectType(int uniqueId);

	/**
	 * Get the label object type corresponding to the given property definition.
	 * @param propDefn the property definition to search with, which should not be null. 
	 * @return the label object type for the given property definition or null if one cannot be found.
	 */
	ILabelObjectType getLabelObjectTypeByProperty(IPropertyDefinition propDefn);

	/**
	 * Tests if the specified property has an associated label object type, i.e. can the property value
	 * be visualised with an associated label.  
	 * @param propDefn the property definition to test.
	 * @return true is there is an associated label object type, false otherwise.
	 */
	boolean isVisualisableProperty(IPropertyDefinition propDefn); 
	
	/**
	 * Get the number of shape object types in this notation syntax service.
	 * @return the number of shape object types.
	 */
	int numShapeObjectTypes();
	
	/**
	 * Get the number of link object types in this notation syntax service.
	 * @return the number of link object types.
	 */
	int numLinkObjectTypes();
	
	/**
	 * Get the number of object types in this notation syntax service.
	 * @return the number of object types.
	 */
	int numObjectTypes();
	
	/**
	 * Find the shape object type by its unique name.
	 * @param name true if the name can be found, false otherwise.
	 * @return the shape object type matching the name, or null if it cannot be found. 
	 */
	IShapeObjectType findShapeObjectTypeByName(String name);
	
	/**
	 * Find the link object type by its unique name.
	 * @param name true if the name can be found, false otherwise.
	 * @return the link object type matching the name, or null if it cannot be found. 
	 */
	ILinkObjectType findLinkObjectTypeByName(String name);
}
