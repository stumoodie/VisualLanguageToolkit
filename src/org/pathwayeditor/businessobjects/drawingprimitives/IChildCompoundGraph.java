/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public interface IChildCompoundGraph {
	/**
	 * Get model that this is a subset of.
	 * @return
	 */
	ICompoundGraph getGraph();


	IChildCompoundGraph getChildCompoundGraph();
	
	/**
	 * Tests if a child shape of the given object type can be created. If strict patenting is used
	 * then the method checks that <code>newShapeType</code> complies with the parenting rules of
	 * this shapes object type rules.
	 * @param newShapeType The shape object type to be created. This can be null.
	 * @return true if the child shape can be created, false otherwise.
	 */
	boolean canCreateShape(IShapeObjectType newShapeType);

	/**
	 * Creates a new shape of a given shape type that is a child of this shape.
	 * @param shapeType The shape type of the new shae to be created. 
	 * @return The new shape that is a child of this shape.
	 * @throws IllegalArgumentException if <code>canCreateChildShape(shapeType) == false</code>.
	 */
	IShapeAttribute createShape(IShapeObjectType shapeType);
	
	/**
	 * Is the submodel a valid destination? This requires that the sub-model is
	 * empty and that it belongs to the same model as this one, or that the model
	 * uses the same context adapter.  
	 * @param destination
	 * @return
	 */
	boolean canMoveHere(ICanvasObjectSelection canvasObjectSelection);
	
	/**
	 * Copy this submodel to the submodel provided.
	 * @param destination
	 */
	void moveHere(ICanvasObjectSelection canvasObjectSelection); 
	
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
	Iterator<IShapeAttribute> shapeIterator();
	
	/**
	 * Traverses over the list of links in this sub-model.
	 * @return
	 */
	Iterator<ILinkAttribute> linkIterator();

	/**
	 * Traverses over the list of links in this sub-model.
	 * @return
	 */
	Iterator<ILabelAttribute> labelIterator();
}
