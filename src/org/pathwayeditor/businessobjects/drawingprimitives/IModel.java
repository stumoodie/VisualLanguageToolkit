/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee;


/**
 * @author smoodie
 *
 */
public interface IModel extends IModelChangeListenee {

	/**
	 * Gets the map that owns this model.
	 * @return the owning map, which cannot be null.
	 */
	ICanvas getCanvas();
	
	/**
	 * Returns the root node of the compound graph representation held by this model.
	 * @return the root node, which will always be the same object, cannot be deleted and will not be null.
	 */
	IRootNode getRootNode();
	
	/**
	 * Create a copy of this model and add it to the given map.
	 * @param canvas the canvas that will own the copied model. This cannot be the same canvas that owns this model. 
	 * @return The newly created model.
	 * @throws IllegalArgumentException if <code>getCanvas().equals(canvas)</code>.
	 */
	IModel createCopy(ICanvas canvas);
	
	/**
	 * Get a new linkEdge factory for this model. Can create a new linkEdge between any two shape nodes in
	 * the model. 
	 * @return a new instance of the factory, cannot be null.
	 */
	ILinkEdgeFactory linkEdgeFactory();
	
	/**
	 * Gets the current state of the model as a momento object that can be used to restore the state of the
	 *  graph.  
	 * @return the momento, which cannot be null.
	 */
	IGraphMomento getCurrentState();
	
	/**
	 * Restores the model to the state stored in the given momento object.
	 * @param stateToRestore the momento of the model state it is to be restored to.
	 */
	void restoreToState(IGraphMomento stateToRestore);
	
	/**
	 * Get a new instance of the shape node and link edge selection class that is used in
	 * removal and copy operations.
	 * @return the new instance, and which cannot be null.
	 */
	ISelectionFactory newSelectionFactory();
	
	/**
	 * Tests if the selection can be removed from the model.
	 * @param selection the selection, which can be null.
	 * @return true if the removal will succeed, false otherwise.
	 */
	boolean canRemoveSelection(IDrawingElementSelection selection);
	
	
	/**
	 * Remove the nodes and edges in the model specified in the given selection.
	 * In addition to the specified nodes and edges child nodes and edges of selected nodes and 
	 * incident edges between selected nodes and their children will also be removed.
	 * @param selection the selection of nodes and edges to be removed.
	 * @throws IllegalArgumentException if <code>canRemoveSelection(selection) == false</code>.
	 */
	void removeSubgraph(IDrawingElementSelection selection);
	
	/**
	 * Returns all the drawing nodes in this model.
	 * @return the drawing node iterator.
	 */
	Iterator<IDrawingNode> drawingNodeIterator();
	
	/**
	 * Returns all the link edges in this model
	 * @return the link edge iterator.
	 */
	Iterator<ILinkEdge> linkEdgeIterator();
}
