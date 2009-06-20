/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.drawingprimitives.properties;

import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee;


public interface IAnnotationProperty extends IPropertyChangeListenee {
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
	 * Record that the property is displayed or not. This need not provide the location and size of the label.
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
}
