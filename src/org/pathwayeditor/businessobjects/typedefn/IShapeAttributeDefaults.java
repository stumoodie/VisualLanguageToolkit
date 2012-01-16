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
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * IShapeAttributeDefaults is an interface that defines the initial/default values for
 * a new {@link IShapeAttribute}. This is typically obtained from an {@link IShapeObjectType}. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IShapeAttributeDefaults extends  IDrawingNodeAttributeDefaults, IAnnotatedCanvasAttributeDefaults, IPropertyDefinitionContainer  {
	
	/**
	 * Gets the initial shape definition.
	 * @return the initial shape defintion, which should not be null.
	 */
	String getShapeDefinition();

	/**
	 * Get the initial size of the shape.
	 * @return the shape's initial size.
	 */
	Dimension getSize();

}
