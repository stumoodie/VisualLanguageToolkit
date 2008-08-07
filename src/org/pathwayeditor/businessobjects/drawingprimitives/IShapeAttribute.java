package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public interface IShapeAttribute extends  IZOrderedObject, IAnnotatedObject , ICanvasObject {

	IShapeObjectType getObjectType();
	
	String getName();
	void setName(String name);
	
	String getDescription();
	void setDescription(String description);
	
	void setDetailedDescription(String detailedDescription);
	String getDetailedDescription();
	
	void setUrl(String url);
	String getUrl();
	
	void setLocation(Location newLocation);
	Location getLocation();
	
	void setSize(Size size);
	Size getSize();
	
	void setPrimitiveShape(IPrimitiveShape primitiveShape);
	IPrimitiveShape getPrimitiveShape();
	
	RGB getFillColour();
	void setFillColour(RGB fillColour);
	
	int getLineWidth();
	void setLineWidth(int lineWidth);
	
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
