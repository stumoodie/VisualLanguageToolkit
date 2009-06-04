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

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * @author smoodie
 *
 */
public interface ILinkEdgeFactory {

	/**
	 * Get the sub-canvas that will own the newly created edge
	 * @return the owning sub-canvas, which cannot be null.
	 * @throws IllegalArgumentException 
	 */
	ISubModel getOwningSubCanvas();
	
	/**
	 * Tests if the source and target node pair are non-null and belong to the same canvas as this
	 * factory. 
	 * @return true if the node pair is valid, false otherwise.
	 */
	boolean isValidShapeNodePair(IShapeNode source, IShapeNode target);
	
	/**
	 * Sets the node pair to be used when creating the new link. 
	 * @param source the source node, which cannot be null.
	 * @param target the target node, which cannot be null.
	 * @throws IllegalArgumentException if <code>isValidNodePair(source, target) == false</code>.
	 */
	void setShapeNodePair(IShapeNode source, IShapeNode target);
	
	/**
	 * Sets the object type to use when creating the new node.
	 * @param objectType the object type, which can be null.
	 */
	void setObjectType(ILinkObjectType objectType);
	
	/**
	 * Returns the currently set object type.
	 * @return the current object type, which can be null.
	 */
	ILinkObjectType getCurrentObjectType();
	
	/**
	 * Returns the currently set node pair.
	 * @return the current node pair, or null of no node pair is set.
	 */
	IShapeNodePair getShapeNodePair();
	
	/**
	 * Tests if the link can be created given the nodes that are specified and the object type.
	 * @return true of the link can be created, false otherwise.
	 */
	boolean canCreateLink();
	
	/**
	 * Creates a new link edge using the node pair and object type set in this factory.
	 * @return the newly created edge added to the sub-canvas specified by <code>getOwningSubCanvas</code>
	 * @throws IllegalStateException if <code>canCreateLink() == false</code>.
	 */
	ILinkEdge createLinkEdge();
	
	
	void setSrcLinkTermLocation(Location location);

	Location getSrcLinkTermLocation();
	
	void setTgtLinkTermLocation(Location location);

	Location getTgtLinkTermLocation();
}
