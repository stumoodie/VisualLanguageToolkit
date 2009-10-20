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

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

public interface ILabelAttribute extends IZOrderedObject, IDrawingNodeAttribute, ICanvasAttributePropertyChangeListenee, ISuppressableChangeListenee {
	
	/**
	 * Get the location of this LabelAttribute.
	 * @return The location . Cannot be null.
	 */
	Point getLocation();

	/**
	 * Set the location for this LabelAttribute.
	 * @throws IllegalArgumentException if the location is null.
	 */
	void setLocation (Point location) ;
	
	/**
	 * Get the size of this LabelAttribute.
	 * @return The size. Cannot be null.
	 */
	Dimension getSize();
	
	/**
	 * Set the size for this LabelAttribute.
	 * @throws IllegalArgumentException if the size is null.
	 */
	void setSize (Dimension size);
	
	/**
	 * Get the property associated with the current Label
	 * @return The associated property. Cannot be null.
	 */
	IAnnotationProperty getProperty();

	/**
	 * Get the LabelNode of the current Label
	 * @return The LabelNode. Cannot be null.
	 */
	ILabelNode getCurrentDrawingElement();
	
	
//	INodeObjectType getObjectType();
	
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
	
	public double getLineWidth();
	
	public void setLineWidth(double lineWidth);
	
	public LineStyle getLineStyle();
	
	public void setLineStyle(LineStyle lineStyle);
	
	public Dimension getMinimumSize();
	
	/**
	 * Test if the label attribute is that same as the other label. Bases on its associated
	 * property, which is the business key of this object.
	 * @param other the other object to compare.
	 * @return true is the business keys of both objects are the same, false otherwise. 
	 */
	boolean equals(Object other);
	
	/**
	 * Gets a hash code based on the business key of this object. See {@link #equals(Object)}. 
	 * @return the hash code.
	 */
	int hashCode();
}
