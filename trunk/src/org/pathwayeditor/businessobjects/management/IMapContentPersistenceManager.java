/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public interface IMapContentPersistenceManager extends IPersistenceManager {
	/**
	 * Tests if the map is open so that its content can be access.
	 * @return true if the map is open, false otherwise.
	 */
	boolean isOpen();
	
	/**
	 * Loads the content from the persistent storage.
	 * @throws PersistenceManagerAlreadyOpenException
	 */
	void loadContent() throws PersistenceManagerAlreadyOpenException;

	/**
	 * Get the canvas associated with the map.
	 * @return a canvas belonging to the owning map, cannot be null.
	 * @throws PersistenceManagerNotOpenException if <code>isOpen() == false</code>. 
	 */
	ICanvas getCanvas() throws PersistenceManagerNotOpenException;
	
	/**
	 * Gets the map that owns the content managed here.
	 * @return the map that owns this content, which cannot be null.
	 */
	IMap getOwningMap();
	
	/**
	 * Synchronised the canvas with persistent storage. Note that the canvas must be open.
	 * @param canvas the canvas to be synchronised, which cannot be null.
	 * @throws PersistenceManagerNotOpenException if <code>isOpen() == false</code>. 
	 */
	void synchronise() throws PersistenceManagerNotOpenException;
	
	/**
	 * Close the canvas. If the canvas or repository is already closed, then do nothing.
	 * @param canvas the canvas to be closed.
	 */
	void close();
	
	/**
	 * Adds a new listener to this manager 
	 * @param listener 
	 */
	void addListener(IMapContentManagerStatusListener listener);
}
