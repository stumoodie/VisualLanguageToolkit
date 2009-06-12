/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
}
