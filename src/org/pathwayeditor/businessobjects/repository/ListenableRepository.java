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

/**
 * @author smoodie
 *
 */
public class ListenableRepository {
	private final List<IRepositoryChangeListener> listeners = new CopyOnWriteArrayList<IRepositoryChangeListener>();
	
	public ListenableRepository(){
		
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#addChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener)
	 */
	public void addChangeListener(IRepositoryChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getListeners()
	 */
	public List<IRepositoryChangeListener> getListeners() {
		return new ArrayList<IRepositoryChangeListener>(this.listeners);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#removeChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener)
	 */
	public void removeChangeListener(IRepositoryChangeListener listener) {
		this.listeners.remove(listener);
	}

	public void notifyPropertyChangeEvent(final IRepositoryPropertyChangeEvent.PropertyType type, final Object oldValue, final String newValue){
		for(IRepositoryChangeListener listener : this.listeners){
			IRepositoryPropertyChangeEvent e = new IRepositoryPropertyChangeEvent(){

				@Override
				public Object getNewValue() {
					return newValue;
				}

				@Override
				public Object getOldValue() {
					return oldValue;
				}

				@Override
				public PropertyType getPropertyName() {
					return type;
				}
			};
			listener.propertyChange(e);
		}
	}

}
