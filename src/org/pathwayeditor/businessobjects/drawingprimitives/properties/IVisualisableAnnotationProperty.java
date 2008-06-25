package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;


/**
 * Records a visualisable property's display status and its display attributes
 * such as location and size. Not that a property that is not displayed can still have a defined
 * size and location, but not vice versa.
 * <p>
 * Invariants:
 * <p>
 * <code>isDisplayed() == true && !(getSize() != null && getLocation() != null)</code>
 * <code>getDefintion().isVisualisable() == true</code>  
 * @author smoodie
 *
 */
public interface IVisualisableAnnotationProperty {
	
	/**
	 * Get the definition associated with this property.
	 * @return The property definition instance. Cannot be null.
	 */
	IAnnotationProperty getProperty();
	
	/**
	 * Reports the recorded displayed state of the visualisable property. Not this does not affect the
	 * display of the property directly, but simply records whether it is displayed.
	 * @return true is the property s being displayed, false otherwise.
	 */
	boolean isDisplayed();
	
	/**
	 * Record that the property is displayed or not. Requires that size and location are defined if set to true.
	 * @param displayed true for displayed, false for not.
	 * @throws IllegalStateException if <code>displayed == true && (getSize() == null || getLocation() == null)</code>.  
	 */
	void setDisplayed(boolean displayed);

	/**
	 * Sets the size of the displayed property label.
	 * @param newSize the new size of the label. Can be null.
	 * @throws IllegalStateException if <code>newSize == null && isDisplayed() == true</code>.
	 */
	void setSize(Size newSize);
	
	/**
	 * Gets the size of the displayed property label.
	 * @return the size, which can be null.
	 */
	Size getSize();
	
	/**
	 * Sets the location of the top left-hand corner of the property label.
	 * @param location the new location, which can be null.
	 * @throws IllegalStateException if <code>location == null && isDisplayed() == true</code>.
	 */
	void setLocation(Location location);
	
	/**
	 * Get the location of the property label.
	 * @return the location, which can be null.
	 */
	Location getLocation();
}
