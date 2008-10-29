package org.pathwayeditor.businessobjects.drawingprimitives.properties;



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
}
