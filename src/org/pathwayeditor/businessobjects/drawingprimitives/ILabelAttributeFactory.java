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

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;

/**
 * ILabelAttributeFactory is an interface defining a factory for label attributes @{link ILabelAttribute}.
 * The factory requires an annotation property and an object type to create a label attribute.
 * The method {@link #canCreateAttribute()} should return false if either of these is not set.    
 * 
 * @author Stuart Moodie
 *
 */
public interface ILabelAttributeFactory extends ICanvasAttributeFactory {

	/**
	 * The property to associated with this label.
	 * @param annotationProperty the annotation property.
	 */
	void setProperty(IAnnotationProperty annotationProperty);

	/**
	 * The property to be associated with this label.
	 * @return the associated annotation property, this can be null if not set.
	 */
	IAnnotationProperty getProperty();
	
	/**
	 * Sets the object type to be associated with the next label attribute to be created.
	 * @param labelObjectType the object type.
	 */
	void setLabelObjectType(ILabelObjectType labelObjectType);

	/**
	 * Get the current object type associated with the next label.
	 * @return the label object type to be used for the next label attribute.
	 */
	ILabelObjectType getLabelObjectType();
	
	@Override
	ILabelAttribute createAttribute();
	
}
