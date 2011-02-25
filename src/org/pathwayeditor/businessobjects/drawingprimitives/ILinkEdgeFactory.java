/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ac.ed.inf.graph.compound.ICompoundEdgeFactory;

/**
 * @author Stuart Moodie
 *
 */
public interface ILinkEdgeFactory {

	ICompoundEdgeFactory getGraphEdgeFactory();
	
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
