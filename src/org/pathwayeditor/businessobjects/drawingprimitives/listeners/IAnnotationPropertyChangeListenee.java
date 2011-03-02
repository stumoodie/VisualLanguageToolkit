/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;

/**
 * IAnnotationPropertyChangeListenee is a interface that defines methods required by
 * any class using an {@link IAnnotationPropertyChangeListener}.
 * 
 * @author Stuart Moodie
 *
 */
public interface IAnnotationPropertyChangeListenee {

	/**
	 * Add the property change listener.
	 * @param listener the listener to be added, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void addChangeListener(IAnnotationPropertyChangeListener listener);
	
	/**
	 * Remove the property change listener.
	 * @param listener the listener to be removed, which cannot be null.
	 * @throws IllegalArgumentException if <code>listener</code> is null.
	 */
	void removeChangeListener(IAnnotationPropertyChangeListener listener);
	
	/**
	 * Gets an iterator of listeners.
	 * @return The list of listeners which can be modified without affecting this instance.
	 */
	Iterator<IAnnotationPropertyChangeListener> listenerIterator();
	
	
	
}
