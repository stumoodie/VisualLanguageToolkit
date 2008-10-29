/**
 * 
 */
package org.pathwayeditor.businessobjects.repository;

import org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent.ChangeType;

/**
 * @author smoodie
 *
 */
public class ListenableMap extends ListenableRepositoryItem {

	public ListenableMap() {
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.ListenableRepositoryItem#fireDescendentChange(org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent)
	 */
	@Override
	public void fireDescendentChange(IFolderContentChangeEvent evt) {
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.ListenableRepositoryItem#notifyDescendentChange(org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent.ChangeType, org.pathwayeditor.businessobjects.repository.IRepositoryItem, org.pathwayeditor.businessobjects.repository.IFolder)
	 */
	@Override
	public void notifyDescendentChange(ChangeType type,
			IRepositoryItem changedItem, IFolder changedFolder) {
	}

}
