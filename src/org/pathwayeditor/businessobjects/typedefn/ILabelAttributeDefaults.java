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

package org.pathwayeditor.businessobjects.typedefn;

import java.text.Format;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LabelLocationPolicy;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * 
 * ILabelAttributeDefaults is an interface that provides default values for a
 * {@link ILabelAttribute}. It is provides the default values when a new instance
 * of <code>ILabelAttribute</code> is constructed and is usually associated with
 * an {@link ILabelObjectType}.
 *
 * @author Stuart Moodie
 *
 */
public interface ILabelAttributeDefaults {
	/**
	 * Gets the label location policy for a label, which provides an indication of 
	 * the approach or policy any viewer should use when positioning a new label.
	 * @return the label location policy, which cannot be null.
	 */
	LabelLocationPolicy getLabelLocationPolicy();
	
	Format getDisplayFormat();
	
	/**
	 * Get the line colour of the label border.
	 * @return the line colour.
	 */
	RGB getLineColour();

	/**
	 * Get the fill (background) colour of the label.
	 * @return the label's background colour.
	 */
	RGB getFillColour();
	
	/**
	 * Should the label be transparent or opaque? 
	 * @return true if the label should be transparent, false if opaque.
	 */
	boolean hasNoFill();
	
	
	/**
	 * Should the label's border be shown?
	 * @return true if the label border should be hidden, false if shown.
	 */
	boolean hasNoBorder();
	
	/**
	 * Get the initial minimum size of the label. 
	 * @return the initial minimum size.
	 */
	Dimension getMinimumSize();

	/**
	 * Get the initial line style of the label border.
	 * @return the initial border line style, which cannot be null.
	 */
	LineStyle getLineStyle();

	/**
	 * Get the initial line width of the label border.
	 * @return the initial border line width, which should be a positive value.
	 */
	double getLineWidth();
}
