/**
 * 
 */
package org.pathwayeditor.businessobjects.repository;

/**
 * An item held in the repository. It is uniquely identified by its inode within the repository.
 * @author smoodie
 *
 */
public interface IRepositoryItem {

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
}
