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

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * 
 * IShapeAttribute is an interface that defines a shape attribute. The shape attribute provides
 * the attributes and operations of the shape node in the model. It defines how the shape node
 * is visualised graphically. 
 *
 * @author Stuart Moodie
 *
 */
public interface IShapeAttribute extends  IAnnotatedObject, ITypedDrawingNodeAttribute, ICanvasAttributeChangeListenee {
	public static final int MIN_LINE_WIDTH = 1;
	
	/**
	 * Gets the object type that is related with this shape.
	 * @return the object type. Cannot be null.
	 */
	@Override
	IShapeObjectType getObjectType();
	

	/**
	 * Gets the shape definition string, which cannot be null.
	 * @return the shape definition.
	 */
	String getShapeDefinition();
	
	/**
	 * Returns a {@link Colour} representation for the background color of this Shape
	 * @return the background color. Cannot be null.
	 */	
	Colour getFillColour();

	/**
	 * Sets the fill color for this Shape.
	 * @param fillColour the new fill color. Cannot be null
	 * @throws IllegalArgumentException if fillColour is null.
	 */
	void setFillColour(Colour fillColour);

	/**
	 * Returns the numerical value representing the thickness of the border line of the shape. 
	 * @return the line width. Cannot be less than <code>MIN_LINE_WIDTH</code>.
	 */
	double getLineWidth();
	
	/**
	 * Sets the line width for this Shape.
	 * @param lineWidth the new fill color. Must be at least <code>MIN_LINE_WIDTH</code>.
	 * @throws IllegalArgumentException if lineWidth is less than MIN_LINE_WIDTH.
	 */
	void setLineWidth(double lineWidth);

	/**
	 * Returns a {@link Colour} representation for the color of this Shape's border line.
	 * @return the background color. Cannot be null.
	 */
	Colour getLineColour();
	
	/**
	 * Sets the line color for this Shape.
	 * @param lineColour the new line color. Cannot be null
	 * @throws IllegalArgumentException if lineColour is null.
	 */
	void setLineColour(Colour lineColour);
	
	/**
	 * Returns the line style of this shape. 
	 * @return the line style. Cannot be null.
	 */
	LineStyle getLineStyle();
	
	/**
	 * Sets the line style for this Shape.
	 * @param lineStyle the new line style. Cannot be null
	 * @throws IllegalArgumentException if lineStyle is null.
	 */
	void setLineStyle(LineStyle lineStyle);
	
}
