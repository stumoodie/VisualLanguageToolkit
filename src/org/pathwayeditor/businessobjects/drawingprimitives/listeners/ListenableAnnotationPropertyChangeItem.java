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

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

/**
 * @author smoodie
 *
 */
public final class ListenableAnnotationPropertyChangeItem	implements IAnnotationPropertyChangeListenee, ISuppressableChangeListenee {
	private final List<IAnnotationPropertyChangeListener> listeners;
	private boolean enabled = true; 
	private IAnnotationProperty annot;
	
	public ListenableAnnotationPropertyChangeItem(IAnnotationProperty annot){
		this.listeners = new CopyOnWriteArrayList<IAnnotationPropertyChangeListener>();
		this.annot = annot;
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
	
	public final void notifyPropertyChange(final Object oldValue, final Object newValue){
		IAnnotationPropertyChangeEvent event = new IAnnotationPropertyChangeEvent(){

			@Override
			public Object getNewValue() {
				return newValue;
			}

			@Override
			public Object getOldValue() {
				return oldValue;
			}

			@Override
			public IAnnotationProperty getPropertyDefinition() {
				return annot;
			}
			
		};
		firePropertyChange(event);
	}
	
	@Override
	public final Iterator<IAnnotationPropertyChangeListener> listenerIterator(){
		return this.listeners.iterator();
	}
	
	@Override
	public final void addChangeListener(IAnnotationPropertyChangeListener listener){
		this.listeners.add(listener);
	}

	@Override
	public final void removeChangeListener(IAnnotationPropertyChangeListener listener){
		this.listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#areListenersEnabled()
	 */
	@Override
	public boolean areListenersEnabled() {
		return this.enabled;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#setListenersEnabled(boolean)
	 */
	@Override
	public void setListenersEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
