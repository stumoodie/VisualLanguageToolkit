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

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListenee;




/**
 * @author smoodie
 *
 */
public interface ICanvasAttribute extends ICanvasAttributePropertyChangeListenee, Comparable<ICanvasAttribute> {
	
	/**
	 * Get the Canvas related with this attribute. Cannot be null.
	 * @return the Canvas. Cannot be null.
	 */	
	ICanvas getCanvas();

	/**
	 * Get the Creation serial of this attribute. Cannot be null.
	 * @return the serial number, greater that 0.
	 */	
	int getCreationSerial();	
	
//	/**
//	 * The object type associated with the drawing node
//	 * @return the object type, which cannot be null.
//	 */
//	IObjectType getObjectType();
	
	/**
	 * Gets the drawing elements associated with the attribute.
	 * @return the drawing element, which cannot be null.
	 */
	IDrawingElement getCurrentDrawingElement();
	
	/**
	 * The business key is the combination of canvas and creation serial.
	 * @param other
	 * @return true if equal, false otherwise.
	 */
	boolean equals(Object other); 

	/**
	 * See <code>equals</code> for definition of identity.
	 * @return the hash code.
	 */
	int hashCode();
}
