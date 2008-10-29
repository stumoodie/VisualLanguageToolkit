package org.pathwayeditor.businessobjects.drawingprimitives;



public interface ILabelNode extends IDrawingNode, IZOrderedObject {

	
	/**
	 * Gets the {@link ILabelAttribute} assosiated with the current label node.
	 * @return the assosiated Label attribute.
	 */
	ILabelAttribute getAttribute();
}
