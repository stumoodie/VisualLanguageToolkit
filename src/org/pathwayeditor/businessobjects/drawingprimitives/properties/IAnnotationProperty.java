/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IAnnotationPropertyChangeListenee;


public interface IAnnotationProperty extends IAnnotationPropertyChangeListenee {
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
	 * @return An object representing the value of the property. Cannot be null. The value must satisfy
	 * <code>this.isValid(this.getValue))</code>
	 */
	Object getValue();

//	/**
//	 * Tests if the property can be visualised. This means it must be a visualisable property.
//	 * @return true if it can false otherwise.
//	 */
//	boolean canVisualiseProperty();
	
//	/**
//	 * Reports the recorded displayed state of the visualisable property. Not this does not affect the
//	 * display of the property directly, but simply records whether it is displayed.
//	 * @return true is the property is being displayed, false otherwise.
//	 */
//	boolean isDisplayed();
//	
//	/**
//	 * Record that the property is displayed or not. This need not provide the location and size of the label.
//	 * @param displayed true for displayed, false for not.
//	 * @throws IllegalStateException if <code>canVisualiseProperty() == false</code>.  
//	 */
//	void setDisplayed(boolean displayed);
	
//	/**
//	 * Get the label that is being used to display this property.
//	 * @return the displayed label, which must be not null if <code>isDisplayed() == true</code>.
//	 * @throws IllegalStateException if <code>canVisualiseProperty() == false</code>.  
//	 */
//	ILabelAttribute getLabel();
	
//	void setLabel(ILabelAttribute label);
	
	void visit(IAnnotationPropertyVisitor visitor);
}
