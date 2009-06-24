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
package org.pathwayeditor.businessobjects.drawingprimitives;



public interface ILinkEdge extends IDrawingElement {
	
	/**
	 * Gets the index number that Label has on the graph.
	 * @return the number of links.
	 */
	int getIndex();
	
	/**
	 * Gets the source shape of this link.
	 * @return the shape instance, guaranteed to be non-null.
	 */
	IShapeNode getSourceShape();
	
	/**
	 * Tests if the source of the link can be changed the the new shape. To succeed the newShape must
	 * belong to the same map as the current source and it must have the same shape object type.    
	 * @param newShape the new shape to test. This can be null.
	 * @return true if the above criteria are met, false otherwise.
	 */
	boolean canChangeSource(IShapeNode newShape);
	
	/**
	 * Changes the current source to a new one.
	 * @param newShape The new source to be set to.
	 * @throws IllegalArgumentException if <code>canChangeSource(newShape) == false</code>
	 */
	void changeSource(IShapeNode newShape);
	
	/**
	 * Gets the target shape of this link.
	 * @return the shape instance, guaranteed to be non-null.
	 */
	IShapeNode getTargetShape();
	
	/**
	 * Tests if the target of the link can be changed the the new shape. To succeed the newShape must
	 * belong to the same map as the current target and it must have the same shape object type.    
	 * @param newShape the new shape to test. This can be null.
	 * @return true if the above criteria are met, false otherwise.
	 */
	boolean canChangeTarget(IShapeNode newShape);
	
	/**
	 * Changes the current target to a new one.
	 * @param newShape The new target to be set to.
	 * @throws IllegalArgumentException if <code>canChangeTarget(newShape) == false</code>
	 */
	void changeTarget(IShapeNode newShape);
	
	/**
	 * Gets the {@link ILinkAttribute} relates to this LinkEdge. 
	 * @return the ILinkAttribute. Cannot be null.
	 */
	ILinkAttribute getAttribute();
	
	/**
	 * Get submodel that owns this linkedge.
	 * @return the submodel, which cannot be null
	 */
	ISubModel getOwningSubModel();
	
	/**
	 * Does this link have the same node as its source and target?
	 * @return true if the source and target are the same, false otherwise.
	 */
	boolean isSelfEdge();
}
