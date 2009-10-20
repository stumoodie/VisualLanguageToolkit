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
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;

/**
 * @author smoodie
 *
 */
public final class ListenableBendPointChangeItem implements IBendPointChangeListenee, ISuppressableChangeListenee {
	private final List<IBendPointChangeListener> listeners;
	private final ILinkAttribute linkAttribute;
	private boolean enabled = true; 
	
	public ListenableBendPointChangeItem(ILinkAttribute linkAttribute){
		this.listeners = new CopyOnWriteArrayList<IBendPointChangeListener>();
		this.linkAttribute = linkAttribute;
	}

	/**
	 * 
	 */
	public final List<IBendPointChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IBendPointChangeEvent evt){
		if(enabled){
			for(IBendPointChangeListener listener : this.getListeners()){
				listener.propertyChange(evt);
			}
		}
	}
	
	public final void notifyPropertyChange(final BendPointChange type, final IBendPoint bp, final int oldIdxPos, final int newIdxPos){
		IBendPointChangeEvent event = new IBendPointChangeEvent(){

			public BendPointChange getChangeType() {
				return type;
			}

			public int getNewIndexPos() {
				return newIdxPos;
			}

			public int getOldIndexPos() {
				return oldIdxPos;
			}

			public ILinkAttribute getLink() {
				return linkAttribute;
			}

			public IBendPoint getBendPoint() {
				return bp;
			}

		};
		firePropertyChange(event);
	}
	
	public final Iterator<IBendPointChangeListener> bendPointListenerIterator(){
		return this.listeners.iterator();
	}
	
	public final void addChangeListener(IBendPointChangeListener listener){
		this.listeners.add(listener);
	}

	public final void removeChangeListener(IBendPointChangeListener listener){
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
