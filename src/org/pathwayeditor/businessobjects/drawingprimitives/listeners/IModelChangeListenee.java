/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IModelChangeListenee {

	/**
	 * Add the property change listener.
	 * @param listener the listener to be added, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void addModelNodeChangeListener(IModelChangeListener listener);
	
	/**
	 * Remove the property change listener.
	 * @param listener the listener to be removed, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void removeModelNodeChangeListener(IModelChangeListener listener);
	
	/**
	 * Gets an iterator of listeners.
	 * @return The list of listeners which can be modified without affecting this instance.
	 */
	Iterator<IModelChangeListener> modelNodeChangeListenerIterator();
	
}
