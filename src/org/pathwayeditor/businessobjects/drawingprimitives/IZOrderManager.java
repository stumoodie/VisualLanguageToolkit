/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

/**
 * IZOrderManager
 *
 * @author Stuart Moodie
 *
 */
public interface IZOrderManager {

	ICanvasElementAttribute getOwningAttribute();
	
	void toFront(ICanvasElementAttribute att);
	
	void toBack(ICanvasElementAttribute att);
	
	void moveForwardOne(ICanvasElementAttribute att);
	
	void moveBackwardOne(ICanvasElementAttribute att);
	
	void addToFront(ICanvasElementAttribute att);
	
	boolean canMoveBack(ICanvasElementAttribute att);
	
	boolean canMoveForward(ICanvasElementAttribute att);
	
	/**
	 * Returns the attributes that belong to the child graph of this attribute in z-order, back to front.
	 * @return the z-ordered attributes. 
	 */
	Iterator<ICanvasElementAttribute> orderedIterator();

	int numAttributes();

	/**
	 * @param attribute
	 */
	void remove(ICanvasElementAttribute attribute);
	
	IZOrderState getCurrentState();
	
	void restoreToState(IZOrderState state);

	int zOrderCompare(ICanvasElementAttribute associatedAttribute, ICanvasElementAttribute associatedAttribute2);
}
