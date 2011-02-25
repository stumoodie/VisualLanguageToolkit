/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives;



public interface ILabelNode extends IDrawingNode {

	
	/**
	 * Gets the {@link ILabelAttribute} assosiated with the current label node.
	 * @return the assosiated Label attribute.
	 */
	@Override
	ILabelAttribute getAttribute();
}
