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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;

/**
 * @author smoodie
 *
 */
public final class ListenableSubModelStructureChangeItem implements ISubModelChangeListenee, ISuppressableChangeListenee {
	private final List<ISubModelChangeListener> listeners;
	private final ISubModel model;
	private boolean enabled = true;
	
	public ListenableSubModelStructureChangeItem(ISubModel model){
		this.listeners = new CopyOnWriteArrayList<ISubModelChangeListener>();
		this.model = model;
	}

	/**
	 * 
	 */
	public final List<ISubModelChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(ISubModelNodeChangeEvent evt){
		if(this.enabled){
			for(ISubModelChangeListener listener : this.getListeners()){
				listener.nodeStructureChange(evt);
			}
		}
	}
	
	public final void fireEdgeChange(ISubModelEdgeChangeEvent evt){
		if(this.enabled){
			for(ISubModelChangeListener listener : this.getListeners()){
				listener.edgeStructureChange(evt);
			}
		}
	}
	
	public final void notifyNodeStructureChange(final ModelStructureChangeType type, final IDrawingNode changedNode){
		ISubModelNodeChangeEvent event = new ISubModelNodeChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return type;
			}

			public IDrawingNode getChangedItem() {
				return changedNode;
			}

			public ISubModel getChangedModel() {
				return model;
			}
			
		};
		firePropertyChange(event);
	}

	public final void notifyEdgeStructureChange(final ModelStructureChangeType type, final ILinkEdge changedEdge){
		ISubModelEdgeChangeEvent event = new ISubModelEdgeChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return type;
			}

			public ILinkEdge getChangedItem() {
				return changedEdge;
			}

			public ISubModel getChangedModel() {
				return model;
			}
			
		};
		fireEdgeChange(event);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListenee#addModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelNodeChangeListener)
	 */
	public void addSubModelNodeChangeListener(ISubModelChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListenee#modelNodeChangeListenerIterator()
	 */
	public Iterator<ISubModelChangeListener> subModelNodeChangeListenerIterator() {
		return this.listeners.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelChangeListenee#removeModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISubModelNodeChangeListener)
	 */
	public void removeSubModelNodeChangeListener(ISubModelChangeListener listener) {
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
