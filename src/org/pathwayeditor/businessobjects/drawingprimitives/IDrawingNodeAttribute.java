/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * An interface defining an attribute associated with a drawing node. This defines attributes
 * and operations common to all nodes.
 * 
 * @author Stuart Moodie
 *
 */
public interface IDrawingNodeAttribute extends ICanvasElementAttribute {

	/**
	 * Gets the current rectangular bounds of the node as drawn. 
	 * @return the drawing bounds, which cannot be null.
	 */
	Envelope getBounds();
	
	/**
	 * Set the current rectangular bounds of the node as drawn.
	 * @param newBounds the new bounds envelope, which cannot be null.
	 */
	void setBounds(Envelope newBounds);
	
	/**
	 * Resize the shape, relative to the current bounds. Resize operations can change both
	 * the origin of the current bound and the height and width of the bounding rectangle. 
	 * @param locationDelta the change in the origin of this shapes bounding envelope, it cannot be null. 
	 * @param sizeDelta the change in the height and width of the node's bounding rectangle, it cannot be null.
	 */
	void resize(Point locationDelta, Dimension sizeDelta);
	
	
	@Override
	ICompoundNode getCurrentElement();
}
