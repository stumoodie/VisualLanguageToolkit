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

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public interface IShapeNodeFactory {
	
	/**
	 * Get the sub-canvas that this shape node will be draw on.
	 * @return the owning subcanvas.
	 */
	ISubModel getOwningSubCanvas();
	
	/**
	 * Sets the object type to be used when creating the shape node. It must be a valid
	 * object type for this sub-canvas.
	 * @param shapeObjectType the object type to use, which cannot be null and must be valid.
	 * @throws NullPointerException if shapeObjectType is null.
	 */
	void setObjectType(IShapeObjectType shapeObjectType);
	
	/**
	 * Get the currently set object type that will be used when the shape node is created.
	 * @return the object type set, which can be null if no object type has been set.
	 */
	IShapeObjectType getCurrentObjectType();

	/**
	 * Tests if a new shape node of the current object type can be created in the specified sub canvas
 	 * (specified by <code>getOwningSubCanvas()</code>);
	 * @return true if a new shape node can be created by this factory, false otherwise.
	 */
	boolean canCreateShapeNode();
	
	/**
	 * Creates a new shape node using the specified object type that is added to the owning sub-canvas.
	 * @return the newly created shape node. Cannot return null.
	 * @throws IllegalStateException if <code>canCreateShapeNode() == false</code>.
	 */
	IShapeNode createShapeNode();

}
