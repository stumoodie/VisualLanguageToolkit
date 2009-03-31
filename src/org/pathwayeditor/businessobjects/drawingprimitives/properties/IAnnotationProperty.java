package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.graphics.ILabelLocationPolicy;


public interface IAnnotationProperty {
	
	/**
	 * Get the definition associated with this PlainTextProperty.
	 * @return The property definition instance. Cannot be null.
	 */
	IPropertyDefinition getDefinition();
	
	/**
	 * Gets the owner of this property
	 * @return the owner of this property, which cannot be null. 
	 */
	IAnnotatedObject getOwner();
	
	/**
	 * Get the an object representation of the value of this property
	 * @return An object representing the value of the property. Cannot be null.
	 */
	Object getValue();

	/**
	 * Tests if the property can be visualised. This means it must be a visualisable property.
	 * @return true if it can false otherwise.
	 */
	boolean canVisualiseProperty();
	
	/**
	 * Reports the recorded displayed state of the visualisable property. Not this does not affect the
	 * display of the property directly, but simply records whether it is displayed.
	 * @return true is the property is being displayed, false otherwise.
	 */
	boolean isDisplayed();
	
	/**
	 * Record that the property is displayed or not. Requires that size and location are defined if set to true.
	 * @param displayed true for displayed, false for not.
	 * @throws IllegalStateException if <code>canVisualiseProperty() == false</code>.  
	 */
	void setDisplayed(boolean displayed);
	
	/**
	 * Get the label that is being used to display this property.
	 * @return the displayed label, which must be not null if <code>isDisplayed() == true</code>.
	 * @throws IllegalStateException if <code>canVisualiseProperty() == false</code>.  
	 */
	ILabelAttribute getDisplayedLabel();
	
	/**
	 * Gets a location policy that is used to layout any labels associated with this property.
	 * @return the label location policy, which cannot be null.
	 * @throws IllegalStateException if <code>canVisualiseProperty() == false</code>.  
	 */
	ILabelLocationPolicy getLabelLocationPolicy();
}
