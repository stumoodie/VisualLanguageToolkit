/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;

/**
 * ILinkAttributeDefaults is an interface that defines the initial/default
 * values for a {@link ILinkAttribute}. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ILinkAttributeDefaults extends IAnnotatedCanvasAttributeDefaults, IPropertyDefinitionContainer  {
	/**
	 * Get the line style of the object type.
	 * @return a non-null LineStyle.
	 */
	LineStyle getLineStyle();

	/**
	 * Get the width of the line drawing the link. 
	 * @return the line width.
	 */
	double getLineWidth();

	/**
	 * The red component of the RGB fill colour. The number can be between 0 and 255.
	 * @return a red colour value.
	 */
	RGB getLineColour();
}
