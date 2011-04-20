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

/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.properties;


/**
 * IPropertyBuilder is an interface that defines a Visitor (see the Visitor Design Pattern) that is designed specifically to support
 * the creation of new subtypes of <code>IAnnotationProperty</code> and to copy existing instances. It should be used in conjunction with methods in
 * {@link IPropertyDefinition}.
 * 
 * @author Stuart Moodie
 *
 */
public interface IPropertyBuilder {

	/**
	 * The owner of objects that are to be created.
	 * @return the owner of the properties being built, which cannot be null.
	 */
	IAnnotatedObject getOwner();
	
	/**
	 * Creates a new plain text annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IPlainTextAnnotationProperty createPlainTextProperty(IPlainTextPropertyDefinition propDefn);

	/**
	 * Creates a new number annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	INumberAnnotationProperty createNumberProperty(INumberPropertyDefinition propDefn);

	/**
	 * Creates a new integer annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IIntegerAnnotationProperty createIntegerProperty(IIntegerPropertyDefinition propDefn);

	/**
	 * Creates a new boolean annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IBooleanAnnotationProperty createBooleanProperty(IBooleanPropertyDefinition propDefn);

	/**
	 * Creates a new list annotation property using the specified property definition. 
	 * @param propDefn the property definition to use for the new annotation property.
	 * @return the newly created annotation property.
	 */
	IListAnnotationProperty createListProperty(IListPropertyDefinition propDefn);

	/**
	 * Creates a new plain text annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IPlainTextAnnotationProperty copyPlainTextProperty(IPlainTextAnnotationProperty other);

	/**
	 * Creates a new number annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	INumberAnnotationProperty copyNumberProperty(INumberAnnotationProperty other);

	/**
	 * Creates a new integer annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IIntegerAnnotationProperty copyIntegerProperty(IIntegerAnnotationProperty other);

	/**
	 * Creates a new boolean annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IBooleanAnnotationProperty copyBooleanProperty(IBooleanAnnotationProperty other);

	/**
	 * Creates a new list annotation property that is a copy of another property. 
	 * @param other the other property to use as the basis for the copy, which cannot be null.
	 * @return the newly created copy.
	 */
	IListAnnotationProperty copyListProperty(IListAnnotationProperty other);
}
