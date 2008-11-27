package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

public interface ILabelAttribute extends IZOrderedObject, ICanvasAttribute, IPropertyChangeListenee {
	
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
	 * Get the property assosiated with the current Label
	 * @return The assosiated property. Cannot be null.
	 */
	IAnnotationProperty getProperty();

	/**
	 * Get the LabelNode of the current Label
	 * @return The LabelNode. Cannot be null.
	 */
	ILabelNode getCurrentDrawingElement();
	
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
}
