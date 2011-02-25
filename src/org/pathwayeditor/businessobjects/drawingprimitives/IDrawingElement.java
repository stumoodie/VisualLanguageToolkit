/*
Copyright 2009-2011, Court of the University of Edinburgh

*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

/**
 * IDrawingElement is the definition of any graphical element drawn on the canvas. It serves as a facade for a 
 * ICompoundGraphElement.
 *  
 * @author Stuart Moodie
 *
 */
public interface IDrawingElement {

	/**
	 * Gets the level on the element in the compound graph ``tree''. The root of the tree is 0. 
	 * @return the level, which should be a positive integer.
	 */
	int getLevel();
	
	/**
	 * Gets a unique index for this element within the graph.
	 * @return the index number.
	 */
	long getUniqueIndex();
	
	/**
	 * Get the attribute of the drawing element.
	 * @return the attribute, which cannot be null.
	 */
	ICanvasElementAttribute getAttribute();

	/**
	 * Get the underlying ICompoundGraphElement that is wrapped by the implementation of this interface. 
	 * @return the wrapped instance, which cannot be null.
	 */
	ICompoundGraphElement getGraphElement();

	/**
	 * Tests if the element is a descendent of this instance.
	 * @param testElement the element to test.
	 * @return true if it is a descendent, false otherwise.
	 */
	boolean isDescendent(IDrawingElement testElement);
	
}
