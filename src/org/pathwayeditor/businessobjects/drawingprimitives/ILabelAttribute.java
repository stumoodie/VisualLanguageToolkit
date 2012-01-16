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

import java.text.Format;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * 
 * ILabelAttribute is an interface defining the attributes and operations of a label node.
 * A label contains no child nodes, but is generally the child of a shape node and link.
 * Strictly it is a mechanism for visualising a property associated with these elements.
 * A label associated with a property of one of these elements must be a child of that element, so
 * conversely the property associated with a label must belong to its parent element. 
 * Currently the root attribute does not have properties that are visualisable. This may happen
 * in the future in which case this node would have labels as children. 
 *
 * @author Stuart Moodie
 *
 */
public interface ILabelAttribute extends IDrawingNodeAttribute, ICanvasAttributeChangeListenee {
	
	/**
	 * Get the property associated with the current Label
	 * @return The associated property. Cannot be null.
	 */
	IAnnotationProperty getProperty();

	@Override
	ILabelObjectType getObjectType();
	
	/**
	 * Gets the minimum size of the label. This is the minimum size of the bounding rectangle.
	 * This is useful, when no value, such as an empty string is defined for a label and ensures
	 * that the shape is still visible to be moved and resized etc. 
	 * @return the minimum size, which cannot be null.
	 */
	Dimension getMinimumSize();
	
	/**
	 * Provides a string that the label should display. If a format has been specified then this format
	 * is used to format the property value to be displayed. 
	 * @return the string which cannot be null.
	 */
	String getDisplayedContent();
	
	/**
	 * Sets a format to use when displaying a label
	 * @param displayFormat
	 */
	void setDisplayFormat(Format displayFormat);

	/**
	 * Gets the display format to use when displaying a label
	 * @return the display format
	 */
	Format getDisplayFormat();
}
