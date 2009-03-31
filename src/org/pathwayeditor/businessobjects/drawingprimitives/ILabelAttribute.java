package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;

public interface ILabelAttribute extends IZOrderedObject, IDrawingNodeAttribute, IPropertyChangeListenee {
	
	/**
	 * Get the location of this LabelAttribute.
	 * @return The location . Cannot be null.
	 */
	Location getLocation();

	/**
	 * Set the location for this LabelAttribute.
	 * @throws IllegalArgumentException if the location is null.
	 */
	void setLocation (Location location) ;
	
	/**
	 * Get the size of this LabelAttribute.
	 * @return The size. Cannot be null.
	 */
	Size getSize();
	
	/**
	 * Set the size for this LabelAttribute.
	 * @throws IllegalArgumentException if the size is null.
	 */
	void setSize (Size size);
	
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
	
	
	INodeObjectType getObjectType();
	
	/**
	 * Get the background color of this Label.
	 * @return the RGB representation of the color of the Label.
	 */	
	RGB getBackgroundColor ();
	
	/**
	 * Set the background color of this Label.
	 * @throws IllegalArgumentException if value is null.
	 */
	void setBackgroundColor (RGB color);
	
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
