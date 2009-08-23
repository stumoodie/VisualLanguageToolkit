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

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListenee;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee;

/**
 * @author smoodie
 *
 */
public interface ISubModel extends ISubModelChangeListenee, ISuppressableChangeListenee {

	/**
	 * Get model that this belongs to.
	 * @return the owning model, which cannot be null.
	 */
	IModel getModel();

	/**
	 * Get the root node that owns this sub model. 
	 * @return the root node, which cannot be null.
	 */
	IDrawingNode getRootNode();
	
	/**
	 * A new instance of the shape node factory for this submodel.
	 * @return a new shape node instance, which cannot be null.
	 */
	IShapeNodeFactory shapeNodeFactory();
	
//	/**
//	 * A new instance of the label node factory for this submodel.
//	 * @return a new shape node instance, which cannot be null.
//	 */
//	ILabelNodeFactory labelNodeFactory();
	
	/**
	 * A new instance of the link edge factory for this submodel.
	 * @return a new shape node instance, which cannot be null.
	 */
	ILinkEdgeFactory linkEdgeFactory();
	
	/**
	 * Is this submodel a valid destination for the copy? This requires that the sub-model is
	 * empty and that it belongs to the same model as this one, or that the model
	 * uses the same notation subsystem. Also the selection cannot have any dangling links.
	 * @param canvasObjectSelection the selection of nodes and links that is to be copied.
	 * @return true if the selection can be copied here, false otherwise.
	 */
	boolean canCopyHere(IDrawingElementSelection canvasObjectSelection);
	
	/**
	 * Copy the selected objects to this.
	 * @param canvasObjectSelection the selection of nodes and links that is to be copied.
	 * @throws IllegalArgumentException if <code>canCopyHere(canvasObjectSelection)==false</code>.
	 */
	void copyHere(IDrawingElementSelection canvasObjectSelection); 
	
	/**
	 * Gets a selection of the elements that were created by the copy operation. 
	 * @return the drawing selection, which will be empty if no copy operation has taken place. 
	 */
	IDrawingElementSelection getCopiedElements();
	
	/**
	 * Tests if the selection can be moved to this SubModel. To no this non of the
	 * selected objects can be owned by this subModel or its children. This will return
	 * true only if the move will succeed. Also the selection cannot have any dangling links.
	 * @param canvasObjectSelection
	 * @return true if the selection can be moved here, false otherwise.
	 */
	boolean canMoveHere(IDrawingElementSelection canvasObjectSelection);
	
	/**
	 * Move this selection to the submodel provided.
	 * @param canvasObjectSelection
	 * @throws IllegalArgumentException if <code>canMoveHere(canvasObjectSelection)==false</code>.
	 */
	void moveHere(IDrawingElementSelection canvasObjectSelection); 
	
	/**
	 * Gets a selection of the elements that were created by the move operation. 
	 * @return the drawing selection, which will be empty if no move operation has taken place. 
	 */
	IDrawingElementSelection getMovedElements();
	
	/**
	 * Returns the number of drawing elements that are owned by this submodel.
	 * @return the number of drawing elements.
	 */
	int numDrawingElements();
	
	/**
	 * Returns the number of drawing nodes that are owned by this submode.
	 * @return the number of drawing nodes.
	 */
	int numDrawingNodes();
	
	/**
	 * Number of shapes owned by this submodel.
	 * @return the number of shapes.
	 */
	int numShapeNodes();
	
	/**
	 * Num links owned by this shape model. 
	 * @return the number of links.
	 */
	int numLinkEdges();
	
	/**
	 * Number of labels owned by the shape that owns this submodel.
	 * @return number of labels.
	 */
	int numLabelNodes();
	
	
	Iterator<IDrawingNode> drawingNodeIterator();
	
	/**
	 * Traverses over all the shapes in the sub-model, using depth-first
	 * traversal. This means that each root node will be taken in turn and 
	 * traversal will proceed down the tree until all nodes are traversed.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<IShapeNode> shapeNodeIterator();
	
	/**
	 * Traverses over the list of links in this sub-model.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ILinkEdge> linkIterator();

	/**
	 * Traverses over the labels in this sub-model.
	 * @return the iterator, which cannot be null.
	 */
	Iterator<ILabelNode> labelIterator();

}
