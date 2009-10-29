/**
 * 
 */
package org.pathwayeditor.businessobjects.hyperlink;

import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;

/**
 * @author smoodie
 *
 */
public interface IRepositoryServiceCallback {

	IRepositoryPersistenceManager getRepositoryByName(String name);

	void registerRepository(IRepositoryPersistenceManager repoManager);
	
}
