/**
 * 
 */
package org.pathwayeditor.businessobjects.repository;

import org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent.ChangeType;

/**
 * @author smoodie
 *
 */
public class ListenableFolder extends ListenableRepositoryItem {

	public ListenableFolder(){
		super();
	}
	
	@Override
	public final void fireDescendentChange(IFolderContentChangeEvent evt){
		for(IRepositoryItemChangeListener listener : this.getListeners()){
			listener.descendentChange(evt);
		}
	}
	
	@Override
	public final void notifyDescendentChange(final ChangeType type, final IRepositoryItem changedItem, final IFolder changedFolder) {
		IFolderContentChangeEvent event = new IFolderContentChangeEvent(){

			public IRepositoryItem getChangedItem() {
				return changedItem;
			}

			public ChangeType getChangeType() {
				return type;
			}

			public IFolder getChangedFolder() {
				return changedFolder;
			}
		};
		fireDescendentChange(event);
	}
}
