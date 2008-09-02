package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public interface IShapeAttribute extends  IZOrderedObject, IAnnotatedObject , ICanvasAttribute {

	IShapeObjectType getObjectType();
	
	/**
	 * Gets the name of this Shape.
	 * @return the name. Cannot be null.
	 */
	String getName();
	void setName(String name);
	
	/**
	 * Gets the description of this Shape
	 * @return the description. Cannot be null.
	 */
	String getDescription();
	void setDescription(String description);
	
	void setDetailedDescription(String detailedDescription);
	
	/**
	 * Gets the detailed description of this Shape
	 * @return the detailed description. Cannot be null.
	 */
	String getDetailedDescription();
	
	void setUrl(String url);
	
	/**
	 * Gets the URL related to this Shape
	 * @return the URL. Cannot be null.
	 */
	String getUrl();
	
	void setLocation(Location newLocation);
	
	/**
	 * Returns a {@link Location} representation of the coordinates of this Shape.
	 * @return the locatioln. Cannot be null.
	 */
	Location getLocation();
	
	void setSize(Size size);
	
	/**
	 * Returns a {@link Size} representation for the size of this Shape
	 * @return the size. Cannot be null.
	 */
	Size getSize();
	
	void setPrimitiveShape(IPrimitiveShape primitiveShape);
	IPrimitiveShape getPrimitiveShape();
	
	/**
	 * Returns a {@link RGB} representation for the background color of this Shape
	 * @return the background color. Cannot be null.
	 */	
	RGB getFillColour();
	void setFillColour(RGB fillColour);
	
	/**
	 * Returns the numerical value representing the thickness of the border line of the shape. 
	 * @return the line width. Cannot be less than zero.
	 */
	int getLineWidth();
	void setLineWidth(int lineWidth);

	/**
	 * Returns a {@link RGB} representation for the color of this Shape's border line.
	 * @return the background color. Cannot be null.
	 */
	RGB getLineColour();
	void setLineColour(RGB lineColour);
	
	
	LineStyle getLineStyle();
	void setLineStyle(LineStyle lineStyle);
	
	/**
	 * Tests is the property of the given name exists in this shape.
	 * @param propertyName The name of the property. Can be null.
	 * @return true of the shape contains the property, false otherwise.
	 */
	boolean hasProperty(String propertyName);
	
	/**
	 * Gets the property of the given name.
	 * @param propertyName the name of the property
	 * @throws IllegalArgumentException if <code>hasProperty(propertyName) == false</code>.
	 */
	IAnnotationProperty getProperty(String propertyName);
}
