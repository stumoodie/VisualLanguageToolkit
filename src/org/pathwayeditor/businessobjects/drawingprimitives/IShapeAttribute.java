/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
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
	 * Returns a {@link RGB} representation for the background color of this Shape
	 * @return the background color. Cannot be null.
	 */	
	RGB getFillColour();

	/**
	 * Sets the fill color for this Shape.
	 * @param fillColour the new fill color. Cannot be null
	 * @throws IllegalArgumentException if fillColour is null.
	 */
	void setFillColour(RGB fillColour);

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
	 * Returns a {@link RGB} representation for the color of this Shape's border line.
	 * @return the background color. Cannot be null.
	 */
	RGB getLineColour();
	
	/**
	 * Sets the line color for this Shape.
	 * @param lineColour the new line color. Cannot be null
	 * @throws IllegalArgumentException if lineColour is null.
	 */
	void setLineColour(RGB lineColour);
	
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
