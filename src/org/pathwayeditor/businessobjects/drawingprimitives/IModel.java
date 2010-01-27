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

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee;


/**
 * @author smoodie
 *
 */
public interface IModel extends IModelChangeListenee, ISuppressableChangeListenee {

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
	 * Returns a shape node iterator for all the shape nodes held in this model.
	 * @return the shape node iterator, which cannot be null.
	 */
	Iterator<IShapeNode> shapeNodeIterator();
	
	
	/**
	 * Returns an iterator for all the label nodes held in this model.
	 * @return the label node iterator, which cannot be null.
	 */
	Iterator<ILabelNode> labelNodeIterator();
	
	/**
	 * Returns an iterator for all the link edges in this model
	 * @return the link edge iterator.
	 */
	Iterator<ILinkEdge> linkEdgeIterator();
	
	/**
	 * Tests that the model is self-consistent and correctly initialised from persistent storage (if
	 * applicable). Once loaded from persistent storage or if created using the interface APIs, then
	 * a model should always be valid. Therefore this method should mainly be used testing and debugging
	 * purposes.   
	 * @return true if the model is self-consistent and maintains referential integrity, false otherwise.
	 */
	boolean isValid();
	
	/**
	 * Provides the number of drawing elements in this model.
	 * @return the number of drawing elements.
	 */
	int numDrawingElements();
	
	/**
	 * Provides the number of drawing nodes in this model.
	 * @return the number of drawing nodes.
	 */
	int numDrawingNodes();
	
	/**
	 * Provides the number of shape nodes in this model.
	 * @return the number of shape nodes.
	 */
	int numShapeNodes();
	
	/**
	 * Provides the number of label nodes in this model.
	 * @return the number of label nodes.
	 */
	int numLabelNodes();
	
	
	/**
	 * Provides the number of link edges in this model.
	 * @return the number of link edges.
	 */
	int numLinkEdges();
}
