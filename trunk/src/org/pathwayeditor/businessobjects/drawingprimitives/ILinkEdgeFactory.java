/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

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
}
