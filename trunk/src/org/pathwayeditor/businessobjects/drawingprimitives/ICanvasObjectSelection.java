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
	 * @param selectedShape
	 */
	void addShape(IShapeAttribute selectedShape);

	void addLink(ILinkAttribute selectedLink);
	
}
