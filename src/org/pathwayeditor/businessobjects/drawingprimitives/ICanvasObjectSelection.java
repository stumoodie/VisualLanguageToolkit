/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * @author smoodie
 *
 */
public interface ICanvasObjectSelection {

	/** 
	 * Add a shape to the selection 
	 * @param selectedShape the shape to be selected. Cannot be null.
	 * @throws NullPointerException if selectedShape is null.
	 */
	void addShape(IShapeNode selectedShape);

	/**
	 * Add a link to the selection
	 * @param selectedLink the link to be selected. Cannot be null.
	 * @throws NullPointerException if selectedLink is null.
	 */
	void addLink(ILinkEdge selectedLink);
	
	
	/**
	 * Tests if anything has been added to the selection.
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * Gets the model that this selection refers to.
	 * @return
	 */
	IModel getModel();
}
