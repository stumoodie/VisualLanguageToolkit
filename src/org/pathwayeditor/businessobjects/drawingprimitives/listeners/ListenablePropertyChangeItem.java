/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author smoodie
 *
 */
public final class ListenablePropertyChangeItem	implements IPropertyChangeListenee {
	private final List<IPropertyChangeListener> listeners;
	
	public ListenablePropertyChangeItem(){
		this.listeners = new CopyOnWriteArrayList<IPropertyChangeListener>();
	}

	/**
	 * 
	 */
	public final List<IPropertyChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IPropertyChangeEvent evt){
		for(IPropertyChangeListener listener : this.getListeners()){
			listener.propertyChange(evt);
		}
	}
	
	public final void notifyProperyChange(final PropertyChange type, final Object oldValue, final Object newValue){
		IPropertyChangeEvent event = new IPropertyChangeEvent(){

			public Object getNewValue() {
				return newValue;
			}

			public Object getOldValue() {
				return oldValue;
			}

			public PropertyChange getPropertyChange() {
				return type;
			}
			
		};
		firePropertyChange(event);
	}
	
	public final Iterator<IPropertyChangeListener> listenerIterator(){
		return this.listeners.iterator();
	}
	
	public final void addChangeListener(IPropertyChangeListener listener){
		this.listeners.add(listener);
	}

	public final void removeChangeListener(IPropertyChangeListener listener){
		this.listeners.remove(listener);
	}
}
