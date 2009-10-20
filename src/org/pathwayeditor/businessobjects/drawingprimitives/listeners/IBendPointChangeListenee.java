/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public interface IBendPointChangeListenee {

	/**
	 * Add the property change listener.
	 * @param listener the listener to be added, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void addChangeListener(IBendPointChangeListener listener);
	
	/**
	 * Remove the property change listener.
	 * @param listener the listener to be removed, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void removeChangeListener(IBendPointChangeListener listener);
	
	/**
	 * Gets an iterator of listeners.
	 * @return The list of listeners which can be modified without affecting this instance.
	 */
	Iterator<IBendPointChangeListener> bendPointListenerIterator();

}
