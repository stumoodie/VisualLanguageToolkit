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

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public final class ListenableBendPointLocationChangeItem implements IBendPointLocationChangeListenee, ISuppressableChangeListenee {
	private final List<IBendPointLocationChangeListener> listeners;
	private final IBendPoint bendPoint;
	private boolean enabled = true; 
	
	public ListenableBendPointLocationChangeItem(IBendPoint bendpoint){
		this.listeners = new CopyOnWriteArrayList<IBendPointLocationChangeListener>();
		this.bendPoint = bendpoint;
	}

	/**
	 * 
	 */
	public final List<IBendPointLocationChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IBendPointLocationChangeEvent evt){
		if(enabled){
			for(IBendPointLocationChangeListener listener : this.getListeners()){
				listener.propertyChange(evt);
			}
		}
	}
	
	public final void notifyPropertyChange(final Point oldLocation, final Point newLocation){
		IBendPointLocationChangeEvent event = new IBendPointLocationChangeEvent(){

			public IBendPoint getBendPoint() {
				return bendPoint;
			}

			public Point getNewPosition() {
				return newLocation;
			}

			public Point getOldPosition() {
				return oldLocation;
			}

		};
		firePropertyChange(event);
	}
	
	public final Iterator<IBendPointLocationChangeListener> listenerIterator(){
		return this.listeners.iterator();
	}
	
	public final void addChangeListener(IBendPointLocationChangeListener listener){
		this.listeners.add(listener);
	}

	public final void removeChangeListener(IBendPointLocationChangeListener listener){
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
