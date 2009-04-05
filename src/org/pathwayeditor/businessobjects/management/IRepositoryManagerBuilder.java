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


/**
 * @author smoodie
 *
 */
public interface IRepositoryManagerBuilder {

	void setConnectionInfo(IConnectionInfo connection);
	
	IConnectionInfo getConnectionInfo();
	
	void setNotationSubsystemPool(INotationSubsystemPool notationSubsystemPool);
	
	INotationSubsystemPool getNotationSubsystemPool();
	
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
