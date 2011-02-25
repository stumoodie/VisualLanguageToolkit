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
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.text.Format;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.figure.geometry.Dimension;

public interface ILabelAttribute extends IDrawingNodeAttribute, ICanvasAttributeChangeListenee {
	
	/**
	 * Get the property associated with the current Label
	 * @return The associated property. Cannot be null.
	 */
	IAnnotationProperty getProperty();

	@Override
	ILabelObjectType getObjectType();
	
	/**
	 * Get the background colour of this Label.
	 * @return the RGB representation of the colour of the Label.
	 */	
	RGB getBackgroundColor ();
	
	/**
	 * Set the background color of this Label.
	 * @throws IllegalArgumentException if value is null.
	 */
	void setBackgroundColor (RGB color);
	
	/**
	 * Get the foreground colour of this Label.
	 * @return the RGB representation of the colour of the Label.
	 */	
	RGB getForegroundColor ();
	
	/**
	 * Set the foreground color of this Label.
	 * @throws IllegalArgumentException if value is null.
	 */
	void setForegroundColor (RGB color);
	
	void setNoFill(boolean noFill);
	
	boolean hasNoFill();
	
	void setNoBorder(boolean noBorder);
	
	boolean hasNoBorder();
	
	double getLineWidth();
	
	void setLineWidth(double lineWidth);
	
	LineStyle getLineStyle();
	
	void setLineStyle(LineStyle lineStyle);
	
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
