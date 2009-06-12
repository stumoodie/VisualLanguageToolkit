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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent.ChangeType;
import org.pathwayeditor.businessobjects.repository.IRepositoryPropertyChangeEvent.PropertyType;

/**
 * @author smoodie
 *
 */
public abstract class ListenableRepositoryItem {
	private final List<IRepositoryItemChangeListener> listeners;
	
	protected ListenableRepositoryItem(){
		this.listeners = new CopyOnWriteArrayList<IRepositoryItemChangeListener>();
	}

	protected final List<IRepositoryItemChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IRepositoryPropertyChangeEvent evt){
		for(IRepositoryItemChangeListener listener : this.getListeners()){
			listener.propertyChange(evt);
		}
	}
	
	public final void notifyProperyChange(final PropertyType type, final Object oldValue, final Object newValue){
		IRepositoryPropertyChangeEvent event = new IRepositoryPropertyChangeEvent(){

			public Object getNewValue() {
				return newValue;
			}

			public Object getOldValue() {
				return oldValue;
			}

			public PropertyType getPropertyName() {
				return type;
			}
		};
		firePropertyChange(event);
	}
	
	public abstract void fireDescendentChange(IFolderContentChangeEvent evt);

	public abstract void notifyDescendentChange(final ChangeType type, final IRepositoryItem changedItem, final IFolder changedFolder);
	
	public final List<IRepositoryItemChangeListener> exportListenerList(){
		return new ArrayList<IRepositoryItemChangeListener>(this.listeners);
	}
	
	public final void addListener(IRepositoryItemChangeListener listener){
		this.listeners.add(listener);
	}

	public final void removeListener(IRepositoryItemChangeListener listener){
		this.listeners.add(listener);
	}
}
