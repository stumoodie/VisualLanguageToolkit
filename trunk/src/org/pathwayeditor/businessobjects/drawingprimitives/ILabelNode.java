package org.pathwayeditor.businessobjects.drawingprimitives;


public interface ILabelNode extends IZOrderedObject {

	
	/**
	 * Gets the graph this Label Node is assosiated with.
	 * @return the related graph.
	 */
	ICompoundGraph getGraph();
	
	/**
	 * Gets the index number that Label has on the graph.
	 * @return the number of links.
	 */
	int getNodeIndex();
	
	/**
	 * Gets the {@link ILabelAttribute} assosiated with the current label node.
	 * @return the assosiated Label attribute.
	 */
	ILabelAttribute getAttribute();
	
	/**
	 * Gets the {@link IShapeNode} that owns that Label Node.
	 * @return the owning shape node.
	 */
	IShapeNode getOwningShape();
}
