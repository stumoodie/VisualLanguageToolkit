/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IBendPointLocationChangeListenee {

	/**
	 * Add the property change listener.
	 * @param listener the listener to be added, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void addChangeListener(IBendPointLocationChangeListener listener);
	
	/**
	 * Remove the property change listener.
	 * @param listener the listener to be removed, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void removeChangeListener(IBendPointLocationChangeListener listener);
	
	/**
	 * Gets an iterator of listeners.
	 * @return The list of listeners which can be modified without affecting this instance.
	 */
	Iterator<IBendPointLocationChangeListener> listenerIterator();

}
