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
package org.pathwayeditor.businessobjects.repository;

import java.util.List;

/**
 * The repository that holds all the folders and maps (including the map
 * diagrams) used in EPE. It is essentially a storage location and would usually
 * correspond to a physical storage location. It is envisaged that in the future
 * EPE may have multiple repositories corresponding to other physical locations
 * (for example on central servers) of pathway maps. At present one would expect
 * only one repository to be present per schema, but there may be reasons to
 * store multiple repositories in a schema (at least temporarily) that we
 * haven't thought of yet.
 * 
 * The repository holds repository items (folders and maps) that are identified
 * uniquely within the repository by an inode. This is analogous to the UNIX
 * file system It also has the important benefit that the natural key (and
 * therefore hashCode and equals) of maps and folders does not vary if they are
 * moved into different sub-folders or renamed etc.
 * 
 * @author smoodie
 * 
 */
public interface IRepository {

	/**
	 * @param name
	 *            of desired folders
	 * @return a list of all subfolders of this folder (anywhere in its
	 *         subfolder tree) having the given name, returns an empty list if
	 *         no folders are matching
	 * @throws IllegalArgumentException if the name is null or empty String
	 */
	List<ISubFolder> getFoldersByName(String name);

	/**
	 * @param path
	 *            the path through the folder tree to this folder
	 * @return the requested folder,
	 * @throws IllegalArgumentException if the <code>pathExists(path) == false</code>.
	 */
	IFolder getFolderByPath(String path);

	/**
	 * Finds the repository item that matches the given path. 
	 * @param path the path in the repository to search for, which can be null.
	 * @return the repository item that matches the path, or null if it cannot be found.
	 */
	IRepositoryItem findRepositoryItemByPath(String path);
	
	/**
	 * Finds the repository item that matches the given inode. 
	 * @param iNode the iNode in the repository to search for.
	 * @return the repository item that has the given inode, or null if it cannot be found.
	 */
	IRepositoryItem findRepositoryItemByINode(int iNode);
	
	/**
	 * @param path
	 *            a path to look for in the subfolder tree of this folder
	 * @return true if the path exists, false if it does not
	 */
	boolean pathExists(String path);

	/**
	 * The name of the repository. Must be unique within an EPE application.
	 * 
	 * @return The name cannot be null, NOR can it be an empty string.
	 */
	String getName();

	/**
	 * A description of the repository.
	 * 
	 * @return The description cannot be null, but can be an empty string.
	 */
	String getDescription();

	void setDescription(String description);

	/**
	 * Get the root folder of the repository.
	 * 
	 * @return The root folder. Cannot be null.
	 */
	IRootFolder getRootFolder();

	/**
	 * The build number of the underlying schema used to store the repository.
	 * Required to enable compatibility checks between the business objects and
	 * the underlying schema.
	 * 
	 * @return the schema build number.
	 */
	int getSchemaBuildNum();

	/**
	 * Uses the standard equals contract. In addition the business key of the
	 * object is <code>name</code> so 2 repositories of the same name are
	 * equal. Hashcode should also be implemented to enforce this key.
	 * 
	 * @param other
	 *            The other object to compare.
	 * @return true if the objects are identical of have the same name, false
	 *         otherwise.
	 */
	boolean equals(Object other);
	
	void addChangeListener(IRepositoryChangeListener listener);
	
	void removeChangeListener(IRepositoryChangeListener listener);
	
	List<IRepositoryChangeListener> getListeners();
}
