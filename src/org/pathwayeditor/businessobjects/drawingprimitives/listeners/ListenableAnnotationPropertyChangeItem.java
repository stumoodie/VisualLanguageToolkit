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
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author smoodie
 *
 */
public final class ListenableAnnotationPropertyChangeItem	implements IAnnotationPropertyChangeListenee, ISuppressableChangeListenee {
	private final List<IAnnotationPropertyChangeListener> listeners;
	private boolean enabled = true; 
	
	public ListenableAnnotationPropertyChangeItem(){
		this.listeners = new CopyOnWriteArrayList<IAnnotationPropertyChangeListener>();
	}

	/**
	 * 
	 */
	public final List<IAnnotationPropertyChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IAnnotationPropertyChangeEvent evt){
		if(enabled){
			for(IAnnotationPropertyChangeListener listener : this.getListeners()){
				listener.propertyChange(evt);
			}
		}
	}
	
	public final void notifyPropertyChange(final IPropertyDefinition propDefn, final Object oldValue, final Object newValue){
		IAnnotationPropertyChangeEvent event = new IAnnotationPropertyChangeEvent(){

			public Object getNewValue() {
				return newValue;
			}

			public Object getOldValue() {
				return oldValue;
			}

			public IPropertyDefinition getPropertyDefinition() {
				return propDefn;
			}
			
		};
		firePropertyChange(event);
	}
	
	public final Iterator<IAnnotationPropertyChangeListener> listenerIterator(){
		return this.listeners.iterator();
	}
	
	public final void addChangeListener(IAnnotationPropertyChangeListener listener){
		this.listeners.add(listener);
	}

	public final void removeChangeListener(IAnnotationPropertyChangeListener listener){
		this.listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#areListenersEnabled()
	 */
	public boolean areListenersEnabled() {
		return this.enabled;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#setListenersEnabled(boolean)
	 */
	public void setListenersEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
