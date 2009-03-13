/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public interface IRepositoryPersistenceManager extends IPersistenceManager {

	/**
	 * Gets the repository for this instance that contains all the folders,
	 * and maps. 
	 * @return the repository which is ready to be used, cannot be null.
	 * @throws IllegalStateException if <code>isRepositoryOpen() == false</code>. 
	 */
	IRepository getRepository();
	
	/**
	 * Tests if the map can be opened. To pass the map must not be null and belong to this repository).
	 * Also the repository must also be open. 
	 * @param owningMap the map to be tested, can be null.
	 * @return <code>true</code> if the map can be opened, false otherwise.
	 * @throws IllegalStateException if <code>isRepositoryOpen()==false</code>.
	 */
	boolean isValidMap(IMap owningMap);
	
	
	/**
	 * Open the maps and get a new content manager to manipulate it's content. Can only open
	 * a map that is not already opened by this or another instance of the repository manager.
	 * @param map the map to be opened. It must exists in this repository and not be open.
	 * @return a content manager for the contents of the specified map, cannot be null.
	 * @throws IllegalStateException if <code>isRepositoryOpen()==false</code>.
	 * @throws IllegalArgumentException if <code>isValidMap(map) == false</code>.
	 */
	IMapPersistenceManager getMapPersistenceManager(IMap map);
	
	
	/**
	 * @param iNode the inode which matches that given to both the map and the canvas managed by this MapPersistenceManager
	 * @return a MapPersistenceManager from the Map of open MapPersistenceManagers. Never returns null
	 * @throws IllegalArgumentException if <code>getMapPersistenceManager(Long iNode)==null</code>.
	 */
	IMapPersistenceManager getMapPersistenceManager(int iNode);


	/**
	 * Given a map, its being deleted from the repository. In order for this to be allowed it means that
	 * the map has no ties with the repository whatsoever and there is no Canvas associated with the map.
	 * @param mapToDelete
	 * @throws IllegalArgumentException if the map is associated with a folder or a repository and a canvas exists.
	 */
	void removeMapFromRepository ( IMap mapToDelete);
}
