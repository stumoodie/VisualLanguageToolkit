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
