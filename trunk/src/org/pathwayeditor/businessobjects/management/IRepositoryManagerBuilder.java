/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.database.util.IConnectionInfo;

/**
 * @author smoodie
 *
 */
public interface IRepositoryManagerBuilder {

	void setConnectionInfo(IConnectionInfo connection);
	
	void setNotationSubsystemPool(INotationSubsystemPool notationSubsystemPool);
	
	/**
	 * Tests if the notation subsystem pool and connection information are correctly initialised,
	 * such that a new repository manager could be created.
	 * @return true if a new repository manager could be created, false otherwise.
	 */
	boolean canCreateRepoManager();
	
	/**
	 * Create a new repository manager.
	 * @return the new repository manager, which cannot be null.
	 * @throws IllegalStateException if <code>canCreateRepoManager()</code>.
	 */
	IRepositoryPersistenceManager createRepoManager();
	
}
