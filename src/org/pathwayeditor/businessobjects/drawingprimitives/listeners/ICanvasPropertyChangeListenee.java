/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface ICanvasPropertyChangeListenee {

	/**
	 * Add the property change listener.
	 * @param listener the listener to be added, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void addChangeListener(ICanvasPropertyChangeListener listener);
	
	/**
	 * Remove the property change listener.
	 * @param listener the listener to be removed, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void removeChangeListener(ICanvasPropertyChangeListener listener);
	
	/**
	 * Gets an iterator of listeners.
	 * @return The list of listeners which can be modified without affecting this instance.
	 */
	Iterator<ICanvasPropertyChangeListener> listenerIterator();

}
