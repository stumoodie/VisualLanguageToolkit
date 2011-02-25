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

import java.util.Iterator;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.ISubCompoundGraph;

/**
 * @author smoodie
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
	
	ISubCompoundGraph getSubgraph();
}
