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
			listener.propertyChange(e);
		}
	}

}
