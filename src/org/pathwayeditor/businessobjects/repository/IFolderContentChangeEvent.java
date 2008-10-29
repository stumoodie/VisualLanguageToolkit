/**
 * 
 */
package org.pathwayeditor.businessobjects.repository;

/**
 * @author smoodie
 *
 */
public interface IFolderContentChangeEvent {
	enum ChangeType { REMOVED, ADDED };
	
	/**
	 * Determines what the change to the folder's content was. 
	 * @return the change type.
	 */
	ChangeType getChangeType();
	
	/**
	 * Returns the item that the change occurred to. If the item
	 * was removed then this will return null 
	 * @return the repositoty item that was changed, which will be null if it has been removed.
	 */
	IRepositoryItem getChangedItem();
	
	/**
	 * Gets the folder whose content has been changed by this event.
	 * @return the folder whose content was changed, which cannot be null
	 */
	IFolder getChangedFolder();
}
