/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

/**
 * @author smoodie
 *
 */
public interface ILabelNodeFactory {
	/**
	 * The sub-canvas that this label will be drawn on.
	 * @return the owning sub-canvas, which cannot be null.
	 */
	ISubModel getOwningSubCanvas();

	/**
	 * The property that this label will be displaying.
	 * @return the currently set owning property.
	 */
	IAnnotationProperty getOwningProperty();

	/**
	 * Tests if the annotation property can be used to create a label in this subcanvas.
	 * To be valid then the property must belong to the root node of the owning SubCanvas (returned
	 * by <code>getOwningSubCanvas()</code>) or a Link within the owning canvas.
	 * @param annotationProperty the property to test, which can be null. 
	 * @return true if the conditions described above are true, false otherwise.
	 */
	boolean isValidProperty(IAnnotationProperty annotationProperty);
	
	/**
	 * Sets the property that the label is to visualise.
	 * @param annotationProperty the annotation property, which must be valid for this SubCanvas.
	 * @throws NullPointerException if <code>annotationProperty</code> is null.
	 * @throws IllegalArgumentException if <code>isValidProperty(annotationProperty) == false</code>.
	 */
	void setProperty(IAnnotationProperty annotationProperty);
	
	/**
	 * Creates the label.
	 * @return the newly created label which is associated
	 *  with the owning SubCanvas returned by <code>getOwningSubCanvas()</code>.
	 *  Cannot be null.
	 * @throws IllegalStateException if <code>getOwningProperty() == null</code>.  
	 */
	ILabelNode createLabel();
}
