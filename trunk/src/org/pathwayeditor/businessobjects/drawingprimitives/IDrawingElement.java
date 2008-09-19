/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * Any element drawn on the canvas. 
 * @author smoodie
 *
 */
public interface IDrawingElement {

	/**
	 * Get the model that owns this element.
	 * @return
	 */
	IModel getModel();
	
	/**
	 * Get the attribute of the drawing element.
	 * @return
	 */
	ICanvasAttribute getAttribute();

	/**
	 * identity based on the canvas and the attribute combination. 
	 * @param other
	 * @return
	 */
	boolean equals(Object other);
	
	/**
	 * See equals for identity rules.
	 * @return
	 */
	int hashCode();
}
