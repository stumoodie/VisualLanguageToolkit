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

import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
import org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener;

/**
 * @author smoodie
 *
 */
public class StubMap implements IMap {
	public static final int INODE = 77;
	public static final String MAP_NAME = "Test Map";

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getDescription()
	 */
	public String getDescription() {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getName()
	 */
	public String getName() {
		return MAP_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getOwner()
	 */
	public IFolder getOwner() {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getINode()
	 */
	public int getINode() {
		return INODE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getRepository()
	 */
	public IRepository getRepository() {
		return new StubRepository();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getPath()
	 */
	public String getPath() {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#addChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public void addChangeListener(IRepositoryItemChangeListener changeListener) {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getChangeListeners()
	 */
	public List<IRepositoryItemChangeListener> getChangeListeners() {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#removeChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public void removeChangeListener(IRepositoryItemChangeListener changeListener) {
		throw new UnsupportedOperationException("not implemented!");
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compareTo(IRepositoryItem o2) {
		return Integer.valueOf(this.getINode()).compareTo(Integer.valueOf(o2.getINode())); 
	}
	
	public Iterator<IRepositoryItem> levelOrderIterator(){
		throw new UnsupportedOperationException("Not implmented");
	}
}
