/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * IShapeAttributeDefaults is an interface that defines the initial/default values for
 * a new {@link IShapeAttribute}. This is typically obtained from an {@link IShapeObjectType}. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IShapeAttributeDefaults extends IAnnotatedCanvasAttributeDefaults, IPropertyDefinitionContainer  {
	
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

	/**
	 * Get the initial line style of the shape.
	 * @return the shape initial line style.
	 */
	LineStyle getLineStyle();

	/**
	 * Get the initial colour of the lines in the shape.
	 * @return the shape's initial line colour - not null.
	 */
	RGB getLineColour();

	/**
	 * Get the initial colour of the shape background (fill).
	 * @return the shape's initial fill colour - not null.
	 */
	RGB getFillColour();

	/**
	 * Get the initial line width of the shape.
	 * @return the shape's initial line width, which should be a non-sero number.
	 */
	double getLineWidth();
}
