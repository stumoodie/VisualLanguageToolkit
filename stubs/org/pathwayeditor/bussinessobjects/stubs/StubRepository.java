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
package org.pathwayeditor.bussinessobjects.stubs;

import java.util.List;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author smoodie
 *
 */
public class StubRepository implements IRepository {
	public static final int BUILD_NUM = 0;
	public static final String NAME = "StubRepo";
	public static final String DESCRIPTION = "Stub Repo Descn";

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#addChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener)
	 */
	public void addChangeListener(IRepositoryChangeListener listener) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#findRepositoryItemByPath(java.lang.String)
	 */
	public IRepositoryItem findRepositoryItemByPath(String path) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getDescription()
	 */
	public String getDescription() {
		return DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getFolderByPath(java.lang.String)
	 */
	public IFolder getFolderByPath(String path) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getFoldersByName(java.lang.String)
	 */
	public List<ISubFolder> getFoldersByName(String name) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getListeners()
	 */
	public List<IRepositoryChangeListener> getListeners() {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getRootFolder()
	 */
	public IRootFolder getRootFolder() {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getSchemaBuildNum()
	 */
	public int getSchemaBuildNum() {
		return BUILD_NUM;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#pathExists(java.lang.String)
	 */
	public boolean pathExists(String path) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#removeChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener)
	 */
	public void removeChangeListener(IRepositoryChangeListener listener) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#findRepositoryItemByINode(int)
	 */
	public IRepositoryItem findRepositoryItemByINode(int node) {
		throw new UnsupportedOperationException("Not implemented");
	}

}
