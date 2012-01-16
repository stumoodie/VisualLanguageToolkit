/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.figure.rendering.GenericFont;

/**
 * IDrawingNodeAttributeDefaults
 *
 * @author Stuart Moodie
 *
 */
public interface IDrawingNodeAttributeDefaults {

	/**
	 * Get the line colour of the label border.
	 * @return the line colour.
	 */
	Colour getLineColour();

	/**
	 * Get the fill (background) colour of the label.
	 * @return the label's background colour.
	 */
	Colour getFillColour();
	
	/**
	 * Get the font colour of the not.
	 * @return the font colour.
	 */
	Colour getFontColour();
	
	/**
	 * Get the font.
	 * @return the font.
	 */
	GenericFont getFont();
	
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
