package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public interface IShapeAttribute extends  IZOrderedObject, ICanvasAttribute, IAnnotatedObject, IPropertyChangeListenee {
	
	/**
	 * Gets the object type that is related with this shape.
	 * @return the object type. Cannot be null.
	 */
	IShapeObjectType getObjectType();
	
	/**
	 * Gets the name of this Shape.
	 * @return the name. Cannot be null.
	 */
	String getName();
	
	/**
	 * Sets the new name for this Shape.
	 * @param name the new name. Cannot be null
	 * @throws IllegalArgumentException if name is null.
	 */
	void setName(String name);
	
	/**
	 * Gets the description of this Shape
	 * @return the description. Cannot be null.
	 */
	String getDescription();
	
	/**
	 * Sets the new description for this Shape.
	 * @param description the new description. Cannot be null
	 * @throws IllegalArgumentException if description is null.
	 */
	void setDescription(String description);
	
	/**
	 * Sets the new detailed description for this Shape.
	 * @param detailedDescription the new detailed description. Cannot be null
	 * @throws IllegalArgumentException if detailed description is null.
	 */
	void setDetailedDescription(String detailedDescription);
	
	/**
	 * Gets the detailed description of this Shape
	 * @return the detailed description. Cannot be null.
	 */
	String getDetailedDescription();
	
	/**
	 * Sets the new URL for this Shape.
	 * @param url the new URL. Cannot be null
	 * @throws IllegalArgumentException if URL is null.
	 */
	void setUrl(String url);
	
	/**
	 * Gets the URL related to this Shape
	 * @return the URL. Cannot be null.
	 */
	String getUrl();

	/**
	 * Sets the new location for this Shape.
	 * @param newLocation the new location. Cannot be null
	 * @throws IllegalArgumentException if location is null.
	 */
	void setLocation(Location newLocation);
	
	/**
	 * Returns a {@link Location} representation of the coordinates of this Shape.
	 * @return the locatioln. Cannot be null.
	 */
	Location getLocation();
	
	/**
	 * Sets the new size for this Shape.
	 * @param size the new size. Cannot be null
	 * @throws IllegalArgumentException if URL is null.
	 */
	void setSize(Size size);
	
	/**
	 * Returns a {@link Size} representation for the size of this Shape
	 * @return the size. Cannot be null.
	 */
	Size getSize();
	
	/**
	 * Sets the primitive shape for this Shape.
	 * @param primitiveShape the new primitive shape. Cannot be null
	 * @throws IllegalArgumentException if primitiveShape is null.
	 */
	void setPrimitiveShape(PrimitiveShapeType primitiveShape);
	
	
	PrimitiveShapeType getPrimitiveShape();
	
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
	 * @return the line width. Cannot be less than zero.
	 */
	int getLineWidth();
	
	/**
	 * Sets the line width for this Shape.
	 * @param lineWidth the new fill color. Cannot be null
	 * @throws IllegalArgumentException if lineWidth is less than zero.
	 */
	void setLineWidth(int lineWidth);

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
	
	/**
	 * Returns the padding for this Shape.
	 * @return the padding of this Shape.
	 */
	int getPadding () ;
	
	/**
	 * Sets the padding for this Shape
	 * @param padding the new padding.
	 */
	void setPadding ( int padding ) ;

	IShapeNode getShapeNode();
	
//	Iterator<IAnnotationProperty> propertyIterator();
}
