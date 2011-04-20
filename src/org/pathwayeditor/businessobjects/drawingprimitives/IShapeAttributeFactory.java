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


package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * IShapeAttributeFactory is an interface that defines a factory used to
 * create shape attributes. The factory must have an object type associated
 * with it before {@link IElementAttributeFactory#canCreateAttribute} can be true.
 *  
 * @author Stuart Moodie
 *
 */
public interface IShapeAttributeFactory extends ICanvasAttributeFactory {

	/**
	 * Get the object type of the new shape attribute to be created.
	 * @return the object type 
	 */
	IShapeObjectType getObjectType();

	/**
	 * Get the attribute that will be the parent of the newly created attribute
	 * @return the attribute, which cannot be null.
	 */
	@Override
	ICanvasElementAttribute getDestinationAttribute();

	/**
	 * Sets the object type to be used when creating the next attribute.
	 * @param objectType the object type to use.
	 */
	void setObjectType(IShapeObjectType objectType);
	
	@Override
	IShapeAttribute createAttribute();

}
