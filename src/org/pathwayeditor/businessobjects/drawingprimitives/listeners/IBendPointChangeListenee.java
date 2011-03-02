/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.List;


/**
 * IBendPointChangeListenee is an interface to be implemented by classes providing access to a 
 * bend point change listener. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IBendPointChangeListenee {

	/**
	 * Add the property change listener.
	 * @param listener the listener to be added, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void addChangeListener(IBendPointContainerListener listener);
	
	/**
	 * Remove the property change listener.
	 * @param listener the listener to be removed, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void removeChangeListener(IBendPointContainerListener listener);
	
	/**
	 * Gets the list of listeners.
	 * @return The list of listeners which can be modified without affecting this instance.
	 */
	List<IBendPointContainerListener> bendPointListeners();

}
