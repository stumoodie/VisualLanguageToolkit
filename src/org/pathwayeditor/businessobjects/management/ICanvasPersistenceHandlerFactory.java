/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * Implementors will provide a factory that creates a new CanvasPersistenceHandler
 * that uses the given map to obtain the canvas. 
 * 
 * @author smoodie
 * 
 */
public interface ICanvasPersistenceHandlerFactory {

	/**
	 * Sets the map to be used to create the persistence handler
	 * @param map the map to be used, which can be null.
	 */
	void setMap(IMap map);
	
	/**
	 * Get the map that will be used to create the canvas persistence handler. 
	 * @return the map, which can be null.
	 */
	IMap getMap();

	/**
	 * Creates and returns a new canvas persistence handler using the currently set
	 * map, which should not be null. 
	 * @return the newly created persistence handler.
	 * @throws IllegalStateException if <code>getMap() == null</code>.
	 */
	ICanvasPersistenceHandler createPersistenceHandler();
}
