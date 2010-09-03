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
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

public interface ILinkAttribute extends ICanvasElementAttribute, IAnnotatedObject, ICanvasAttributePropertyChangeListenee,
	ISuppressableChangeListenee  {
	/**
	 * Gets the Object type related to this Link.
	 * @return the object type.
	 */
	@Override
	ILinkObjectType getObjectType();
	
	
	/**
	 * Get the Link Decorator for the source of the link. Cannot be null.
	 * @return the source link Decorator.
	 */		
	ILinkTerminus getSourceTerminus();
	
	/**
	 * Get the Link Decorator for the target of the link. Cannot be null.
	 * @return the target link Decorator.
	 */		
	ILinkTerminus getTargetTerminus();

	/**
	 * Gets the RGB representation of the color of the link. Cannot be null.
	 * @return the RGB color.
	 */
	RGB getLineColour () ;
	
	/**
	 * Sets a new Color for this link.
	 * @param newColor the new colour for the line, which cannot be null.
	 * @throws IllegalArgumentException if newColor is null.
	 */
	void setLineColour ( RGB newColor ) ;
	
	/**
	 * Gets the line style of this link.
	 * @return the line style. Cannot be null.
	 */	
	LineStyle getLineStyle () ;
	
	/**
	 * Sets a new line style for this link.
	 * @param style the new line style, which cannot be null.
	 * @throws IllegalArgumentException if style is null.
	 */
	void setLineStyle ( LineStyle style );
	
	/**
	 * Returns the width of the line.
	 * @return the width.
	 */
	double getLineWidth() ;
	
	/**
	 * Sets a new width for the line of this link.
	 * @param lineWidth the new line width, which must be a positive integer greater than zero.
	 * @throws IllegalArgumentException if the line width is invalid.
	 */
	void setLineWidth ( double lineWidth) ;
	
	IBendPointContainer getBendPointContainer();
}
