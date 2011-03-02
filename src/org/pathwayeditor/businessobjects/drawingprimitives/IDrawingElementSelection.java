/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ISubCompoundGraph;

/**
 * IDrawingElementSelection is an interface that defines how a selection of drawing elements is accessed. This interface
 * is a facade that is convenient to use if one wants to distinguish between different types of node. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IDrawingElementSelection {

	/**
	 * Get the number of nodes that form the head of a tree of the selected nodes in the compound graph.
	 * @return the number of top nodes.
	 */
	int numTopDrawingNodes();
	
	/**
	 * Iterator for the top nodes in the selection tree.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> topDrawingNodeIterator();

	/**
	 * Get the number of edges that for the head of a tree of the selected nodes in the compound graph.
	 * @return the number of top nodes.
	 */
	int numTopLinkEdges();
	
	/**
	 * Iterator for the top edges in the selection tree.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundEdge> topLinkEdgesIterator();

	/**
	 * Test if this selection is a consistent snapshot of the model. The selection may become inconsistent if any of the nodes
	 * are removed from the model. 
	 * @return true it is consistent, false otherwise.
	 */
	boolean isConsistentSnapShot();

	/**
	 * Get the number of nodes in the graph.
	 * @return The number of nodes.
	 */
	int getNumNodes();
	
	/**
	 * Iterates over all the drawing nodes in this selection.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> drawingNodeIterator();
	
	/**
	 * Iterates over all the shape nodes in this selection.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> shapeNodeIterator();
	
	/**
	 * Iterates over all the label nodes in this selection.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundNode> labelNodeIterator();
	
	/**
	 * Get the number of edges in the graph.
	 * @return The number of edges.
	 */
	int getNumEdges();
	
	/**
	 * Iterates over all the drawing nodes in this selection.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ICompoundEdge> linkEdgeIterator();

	/**
	 * Tests if this selection contains the given node.
	 * @param node the node to test, which can be null.
	 * @return true if this selection contains the node.
	 */
	boolean containsNode(IDrawingNode node);
	
	/**
	 * Tests if this selection contains the given linkEdge
	 * @param linkEdge the link edge to test, which can be null.
	 * @return true if this selection contains the link edge.
	 */
	boolean containsEdge(ILinkEdge linkEdge);
	
	/**
	 * Tests if this selection has dangling links, that is links that are not connected to nodes that are included in
	 * this selection. Note that some operations such as copy and move do not permit dangling edges in a selection.
	 * @return true if dangling edges are present, false otherwise.
	 */
	boolean hasDanglingEdges();
	
	/**
	 * Gets the sub-compound graph that is the object wrapped by an implementation of this interface. 
	 * @return the wrapped subgraph which cannot be null.
	 */
	ISubCompoundGraph getSubgraph();
}
