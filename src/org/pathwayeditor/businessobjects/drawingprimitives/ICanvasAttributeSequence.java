/*
Copyright 2009-2011, Court of the University of Edinburgh

*/

package org.pathwayeditor.businessobjects.drawingprimitives;

/**
 * ICanvasAttributeSequence is an interface that provides access to a unique index sequence. It provides a sequence of integers
 * starting at the current index and incrementing by 1. 
 * 
 * @author Stuart Moodie
 *
 */
public interface ICanvasAttributeSequence {

	/**
	 * Gets the current index number
	 * @return the index number.
	 */
	int getCurrent();
	
	/**
	 * Gets the next index number in the sequence. Incrementing the current index by 1.
	 * @return the next index number that was just created.
	 */
	int next();
	
}
