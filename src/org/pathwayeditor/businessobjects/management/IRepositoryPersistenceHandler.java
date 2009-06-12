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
