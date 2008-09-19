/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface ISubModel {

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
	
	/**
	 * A new instance of the label node factory for this submodel.
	 * @return a new shape node instance, which cannot be null.
	 */
	ILabelNodeFactory labelNodeFactory();
	
	/**
	 * A new instance of the link edge factory for this submodel.
	 * @return a new shape node instance, which cannot be null.
	 */
	ILinkEdgeFactory linkEdgeFactory();
	
	/**
	 * Is the submodel a valid destination? This requires that the sub-model is
	 * empty and that it belongs to the same model as this one, or that the model
	 * uses the same context adapter.  
	 * @param destination
	 * @return
	 */
	boolean canCopyHere(ICanvasObjectSelection canvasObjectSelection);
	
	/**
	 * Copy this submodel to the submodel provided.
	 * @param destination
	 */
	void copyHere(ICanvasObjectSelection canvasObjectSelection); 
	
	/**
	 * Tests if the selection can be moved to this SubModel. To no this non of the
	 * selected objects can be owned by this subModel or its children. This will return
	 * true only if the move will succeed.  
	 * @param canvasObjectSelection
	 * @return
	 */
	boolean canMoveHere(ICanvasObjectSelection canvasObjectSelection);
	
	/**
	 * Move this selection to the submodel provided.
	 * @param canvasObjectSelection
	 */
	void moveHere(ICanvasObjectSelection canvasObjectSelection); 
	
	/**
	 * Number of shapes.
	 * @return
	 */
	int getNumShapes();
	
	/**
	 * Num links owned by this shape model. 
	 * @return
	 */
	int getNumLinks();
	
	/**
	 * Number of labels
	 * @return
	 */
	int getNumLabels();
	
	/**
	 * Traverses over all the shapes in the sub-model, using depth-first
	 * traversal. This means that each root node will be taken in turn and 
	 * traversal will proceed down the tree until all nodes are traversed.
	 * @return
	 */
	Iterator<IShapeNode> shapeIterator();
	
	/**
	 * Traverses over the list of links in this sub-model.
	 * @return
	 */
	Iterator<ILinkEdge> linkIterator();

	/**
	 * Traverses over the list of links in this sub-model.
	 * @return
	 */
	Iterator<ILabelNode> labelIterator();
}
