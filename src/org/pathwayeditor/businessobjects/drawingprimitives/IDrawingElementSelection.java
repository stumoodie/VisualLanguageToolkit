/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IDrawingElementSelection {

	/**
	 * Get the model that this is a snapshot of.
	 * @return the model, which cannot be null.
	 */
	IModel getModel();

	/**
	 * Get the number of nodes that form the head of a tree of the selected nodes in the compound graph.
	 * @return the number of top nodes.
	 */
	int numTopDrawingNodes();
	
	/**
	 * Iterator for the top nodes in the selection tree.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<IDrawingNode> topDrawingNodeIterator();

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
	Iterator<IDrawingNode> drawingNodeIterator();
	
	/**
	 * Get the number of edges in the graph.
	 * @return The number of edges.
	 */
	int getNumEdges();
	
	/**
	 * Iterates over all the drawing nodes in this selection.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ILinkEdge> linkEdgeIterator();

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
}
