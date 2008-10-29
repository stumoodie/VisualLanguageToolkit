/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public interface IRepositoryPersistenceManager extends IPersistenceManager {

	/**
	 * Tests if the repository is currently open.
	 * @return true if it is, false otherwise.
	 */
	boolean isRepositoryOpen();	
	
	/**
	 * Gets the repository for this instance that contains all the folders,
	 * and maps. It will load it from persistent storage if necessary. 
	 * @throws PersistenceManagerAlreadyOpenException if <code>isRepositoryOpen() == true</code> when this is called. 
	 */
	void openRepository() throws PersistenceManagerAlreadyOpenException;
	
	/**
	 * Gets the repository for this instance that contains all the folders,
	 * and maps. 
	 * @return the repository which is ready to be used, cannot be null.
	 * @throws PersistenceManagerNotOpenException if <code>isRepositoryOpen() == false</code>. 
	 */
	IRepository getRepository() throws PersistenceManagerNotOpenException;
	
	/**
	 * Synchronises the repository with persistent storage. 
	 * @throws PersistenceManagerNotOpenException if <code>isRepositoryOpen() == false</code>. 
	 */
	void synchroniseRepository() throws PersistenceManagerNotOpenException;
	
	/**
	 * Closes the repository. If the repository is already closed, then does nothing.
	 */
	void closeRepository();
	
	/**
	 * Tests if the map can be opened, i.e. the map is valid and it isn't already opened by this or another repository manager instance.
	 * Also the repository must also be open. 
	 * @param owningMap the map to be tested, can be null.
	 * @return <code>true</code> if the map can be opened, false otherwise.
	 */
	boolean canOpenMap(IMap owningMap);
	
	
	/**
	 * Returns an iterator to the set of maps that are currently open. Note that this may
	 * include maps, being used by other instances of this class.
	 * @return the iterator, which will not be null.
	 * @throws PersistenceManagerNotOpenException if <code>isRepositoryOpen() == false</code>. 
	 */
	Iterator<IMap> openMapIterator() throws PersistenceManagerNotOpenException;
	
	/**
	 * Tests if the given map is currently open by this or any other instance of this class (If the repository is stored on a server then
	 * it may not necessarily be opened by this client).
	 * @param map the map to test, which cannot be null.
	 * @return true if the map is open, false otherwise.
	 */
	boolean isMapOpen(IMap map);
	
	/**
	 * Open the maps and get a new content manager to manipulate it's content. Can only open
	 * a map that is not already opened by this or another instance of the repository manager.
	 * @param map the map to be opened. It must exists in this repository and not be open.
	 * @return a content manager for the contents of the specified map, cannot be null.
	 * @throws PersistenceManagerNotOpenException if <code>canOpenMap(map)</code>.
	 */
	IMapContentPersistenceManager openMap(IMap map) throws PersistenceManagerNotOpenException;
}
