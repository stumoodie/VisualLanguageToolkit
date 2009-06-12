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
package org.pathwayeditor.businessobjects.repository;

import java.util.Iterator;
import java.util.List;

/**
 * An item held in the repository. It is uniquely identified by its inode within the repository.
 * @author smoodie
 *
 */
public interface IRepositoryItem extends Comparable<IRepositoryItem> {

	/**
	 * The repository that owns this item.
	 * @return the owning repository, which cannot be null. 
	 */
	IRepository getRepository();
	
	/**
	 * The inode of the repository item. Equivalent to the inode of a file system. Each inode is a
	 * number that is unique within the repository.
	 * @return the number identifying the inode.
	 */
	int getINode();
	
	/**
	 * Implementers should provide an equals that enforces the business key of this interface, namely that instances of the same repository and
	 * with the same inode are equal.  
	 * @param object the other object to compare.
	 * @return true if the repositories are equal and the inodes are identical, false otherwise.
	 */
	boolean equals(Object object);
	
	/**
	 * Gets the path of the repository object. A path leading to a folder
	 * should end with a '/' character, maps do not.
	 * @return the path, which cannot be null.
	 */
	String getPath();

	/**
	 * Adds a listener to the repository item.
	 * @param changeListener the listener, which cannot be null.
	 * @throws IllegalArgumentException if <code>changeListener == null</code>.
	 */
	void addChangeListener(IRepositoryItemChangeListener changeListener);
	
	/**
	 * Removes a listener to the repository item. If the listener is not found
	 * then this is ignored.
	 * @param changeListener the listener, which cannot be null.
	 * @throws IllegalArgumentException if <code>changeListener == null</code>.
	 */
	void removeChangeListener(IRepositoryItemChangeListener changeListener);
	
	/**
	 * Gets a list of listeners added to this repository item. This will be
	 * empty if there are none.
	 * @return the list of listeners.
	 */
	List<IRepositoryItemChangeListener> getChangeListeners();
	
	/**
	 * Provides a level order iterator for the tree potentially represented by this 
	 * repository item. The first node in the tree is this node, so the iterator will
	 * always have 1 node.
	 * @return the iterator, which starts with this node.
	 */
	Iterator<IRepositoryItem> levelOrderIterator();
}
