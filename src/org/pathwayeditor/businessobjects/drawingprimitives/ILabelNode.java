/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;


/**
 * 
 * ILabelNode is an interface defining a label node. This is a facade that wraps an
 * instance of @{link ICompoundNode} and makes manipulation of label node more convenient. 
 *
 * @author Stuart Moodie
 *
 */
public interface ILabelNode extends IDrawingNode {

	/**
	 * Gets the {@link ILabelAttribute} associated with the current label node.
	 * @return the associated Label attribute.
	 */
	@Override
	ILabelAttribute getAttribute();
}
