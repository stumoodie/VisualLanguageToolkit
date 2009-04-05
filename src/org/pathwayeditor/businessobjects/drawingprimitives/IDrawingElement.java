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
	 * @return the model, which cannot be null.
	 */
	IModel getModel();
	
//	/**
//	 * Get the model that a label should be added to for this drawing element.
//	 * @return the appropriate sub-model, which cannot be null.
//	 */
//	ISubModel getLabelSubModel();
	
	/**
	 * Get the attribute of the drawing element.
	 * @return the attribute, which cannot be null.
	 */
	ICanvasAttribute getAttribute();

	/**
	 * identity based on the canvas and the attribute combination. 
	 * @param other
	 * @return true if objects are equal, false otherwise.
	 */
	boolean equals(Object other);
	
	/**
	 * See equals for identity rules.
	 * @return the hash code.
	 */
	int hashCode();
}
