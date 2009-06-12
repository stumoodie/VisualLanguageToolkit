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
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

/**
 * @author smoodie
 *
 */
public interface ILabelNodeFactory {
	/**
	 * The sub-canvas that this label will be drawn on.
	 * @return the owning sub-canvas, which cannot be null.
	 */
	ISubModel getOwningSubCanvas();

	/**
	 * The property that this label will be displaying.
	 * @return the currently set owning property.
	 */
	IAnnotationProperty getOwningProperty();

	/**
	 * Tests if the annotation property can be used to create a label in this subcanvas.
	 * To be valid then the property must belong to the root node of the owning SubCanvas (returned
	 * by <code>getOwningSubCanvas()</code>) or a Link within the owning canvas.
	 * @param annotationProperty the property to test, which can be null. 
	 * @return true if the conditions described above are true, false otherwise.
	 */
	boolean isValidProperty(IAnnotationProperty annotationProperty);
	
	/**
	 * Sets the property that the label is to visualise.
	 * @param annotationProperty the annotation property, which must be valid for this SubCanvas.
	 * @throws NullPointerException if <code>annotationProperty</code> is null.
	 * @throws IllegalArgumentException if <code>isValidProperty(annotationProperty) == false</code>.
	 */
	void setProperty(IAnnotationProperty annotationProperty);
	
	/** Tests if this factory is correctly initialised to create a label node.
	 * @return true if it is, false otherwise.
	 */
	boolean canCreateLabelNode();
	
	/**
	 * Creates the label.
	 * @return the newly created label which is associated
	 *  with the owning SubCanvas returned by <code>getOwningSubCanvas()</code>.
	 *  Cannot be null.
	 * @throws IllegalStateException if <code>canCreateLabelNode() == null</code>.  
	 */
	ILabelNode createLabel();
}
