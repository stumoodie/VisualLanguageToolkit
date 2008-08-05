package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public interface IShape extends  IZOrderedObject, IAnnotatedObject , ICanvasObject {

	IShapeObjectType getObjectType();
	
	ICanvasObject getParent();

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
	
	IShapeModel getShapeModel();
	
	int getNumLinks();
	/**
	 * Provides and iterator for all the links associated with this shape.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILink> linkIterator();

	int getNumOutLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a source.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILink> outLinkIterator();

	int getNumInLinks();
	
	/**
	 * Provides and iterator for all the links where this shape is a target.
	 * @return An new iterator to the collection of links. 
	 */
	Iterator<ILink> inLinkIterator();

	int getNumChildren();
	
	/**
	 * Provides and iterator for all the children of this shape.
	 * @return An new iterator to the collection of child shapes. 
	 */
	Iterator<IShape> childShapeIterator();
	
	int getNumLabels();
	
	Iterator<ILabel> labelIterator();
	
}
