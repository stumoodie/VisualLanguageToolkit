package org.pathwayeditor.businessobjects.drawingprimitives;


public interface ILinkEdge extends IZOrderedObject {

	ICompoundGraph getGraph();
	
	int getIndex();
	
	/**
	 * Gets the source shape of this link.
	 * @return the shape instance, guaranteed to be non-null.
	 */
	IShapeNode getSourceShape();
	
	/**
	 * Tests if the source of the link can be changed the the new shape. To succeed the newShape must
	 * belong to the same map as the current source and it must have the same shape object type.    
	 * @param newShape the new shape to test. This can be null.
	 * @return true if the above criteria are met, false otherwise.
	 */
	boolean canChangeSource(IShapeNode newShape);
	
	/**
	 * Changes the current source to a new one.
	 * @param newShape The new source to be set to.
	 * @throws IllegalArgumentException if <code>canChangeSource(newShape) == false</code>
	 */
	void changeSource(IShapeAttribute newShape);
	
	/**
	 * Gets the target shape of this link.
	 * @return the shape instance, guaranteed to be non-null.
	 */
	IShapeNode getTargetShape();
	
	/**
	 * Tests if the target of the link can be changed the the new shape. To succeed the newShape must
	 * belong to the same map as the current target and it must have the same shape object type.    
	 * @param newShape the new shape to test. This can be null.
	 * @return true if the above criteria are met, false otherwise.
	 */
	boolean canChangeTarget(IShapeNode newShape);
	
	/**
	 * Changes the current target to a new one.
	 * @param newShape The new target to be set to.
	 * @throws IllegalArgumentException if <code>canChangeTarget(newShape) == false</code>
	 */
	void changeTarget(IShapeNode newShape);
	
	ILinkAttribute getAttribute();
	
	IChildCompoundGraph getOwningChildGraph();
}
