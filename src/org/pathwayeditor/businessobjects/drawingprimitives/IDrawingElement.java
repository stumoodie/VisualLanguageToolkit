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

/**
 * Any element drawn on the canvas. 
 * @author smoodie
 *
 */
public interface IDrawingElement {

	/**
	 * Get the model that owns this element.
	 * @return the model, which cannot be null.
	 */
	IModel getModel();
	
//	/**
//	 * Get the model that a label should be added to for this drawing element.
//	 * @return the appropriate sub-model, which cannot be null.
//	 */
//	ISubModel getLabelSubModel();
	
	/**
	 * Get the attribute of the drawing element.
	 * @return the attribute, which cannot be null.
	 */
	ICanvasAttribute getAttribute();

	/**
	 * Tests if this element has been removed from the canvas.
	 * @return true if it has, false otherwise.
	 */
	boolean isRemoved();
	
	/**
	 * identity based on the canvas and the attribute combination. 
	 * @param other
	 * @return true if objects are equal, false otherwise.
	 */
	boolean equals(Object other);
	
	/**
	 * See equals for identity rules.
	 * @return the hash code.
	 */
	int hashCode();
}
