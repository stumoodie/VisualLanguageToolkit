/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public interface IRepositoryPersistenceHandler {

	/**
	 * Gets the name of the repository to be loaded.
	 * @return the name of the repository, which cannot be null.
	 */
	String getName();

	/**
	 * Loads the repository from persistent storage. Each call will load a new repository into
	 * this handler. 
	 */
	void loadRepository();
	
	/**
	 * Synchronises the persistent storage with the current repository held by this persistence handler. To do
	 * this, this handler must have a repository loaded.
	 * @throws IllegalStateException if <code>getLoadedRepository() == null</code>.
	 */
	void synchroniseRepository();

	/**
	 * Gets the currently loaded repository.
	 * @return the currently loaded repository or null if no repository has been loaded yet.
	 */
	IRepository getLoadedRepository();

	/**
	 * Unloads the repository so that <code>getLoadedRepository() == null</code>.
	 */
	void reset();
}
