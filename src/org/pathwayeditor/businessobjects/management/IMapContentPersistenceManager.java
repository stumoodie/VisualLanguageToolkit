/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
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
	 * Tests if this map has a canvas.
	 * @return true if it does, false otherwise.
	 * @throws PersistenceManagerNotOpenException
	 */
	boolean doesCanvasExist() throws PersistenceManagerNotOpenException;
	
	/**
	 * Creates a new canvas using the given notation subsystem. The newly created canvas
	 * is then available via <code>getCanvas()</code>.
	 * @param notationSubsystem the notation subsystem, which cannot be null.
	 * @throws PersistenceManagerNotOpenException 
	 * @throws IllegalArgumentException if notationSubsystem is null.
	 */
	void createCanvas(INotationSubsystem notationSubsystem) throws PersistenceManagerNotOpenException;
	
	/**
	 * Loads the content from the persistent storage and opens this manager.
	 * @throws PersistenceManagerAlreadyOpenException if this manager has already been opened. 
	 */
	void loadContent() throws PersistenceManagerAlreadyOpenException;

	/**
	 * Get the canvas associated with the map.
	 * @return a canvas belonging to the owning map, cannot be null.
	 * @throws PersistenceManagerNotOpenException if <code>isOpen() == false</code>.
	 * @throws IllegalStateException if <code>doesCanvasExist()==false</code>. 
	 */
	ICanvas getCanvas() throws PersistenceManagerNotOpenException;
	
	/**
	 * Gets the map that owns the content managed here.
	 * @return the map that owns this content, which cannot be null.
	 */
	IMap getOwningMap();
	
	/**
	 * Synchronised the canvas with persistent storage. Note that the canvas must be open.
	 * @throws PersistenceManagerNotOpenException if <code>isOpen() == false</code>. 
	 */
	void synchronise() throws PersistenceManagerNotOpenException;
	
	/**
	 * Close the canvas. If the canvas or repository is already closed, then do nothing.
	 */
	void close();
	
	/**
	 * Adds a new listener to this manager 
	 * @param listener 
	 */
	void addListener(IMapContentManagerStatusListener listener);
}
