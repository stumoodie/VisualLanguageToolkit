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
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * @author smoodie
 *
 */
public final class ListenableModelStructureChangeItem implements IModelChangeListenee {
	private final List<IModelChangeListener> listeners;
	private final IModel model;
	
	public ListenableModelStructureChangeItem(IModel model){
		this.listeners = new CopyOnWriteArrayList<IModelChangeListener>();
		this.model = model;
	}

	/**
	 * 
	 */
	public final List<IModelChangeListener> getListeners() {
		return this.listeners;
	}

	public final void fireNodeChange(IModelNodeChangeEvent evt){
		for(IModelChangeListener listener : this.getListeners()){
			listener.nodeStructureChange(evt);
		}
	}
	
	public final void fireEdgeChange(IModelEdgeChangeEvent evt){
		for(IModelChangeListener listener : this.getListeners()){
			listener.edgeStructureChange(evt);
		}
	}
	
	public final void notifyNodeStructureChange(final ModelStructureChangeType type, final IDrawingNode changedNode){
		IModelNodeChangeEvent event = new IModelNodeChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return type;
			}

			public IDrawingNode getChangedItem() {
				return changedNode;
			}

			public IModel getChangedModel() {
				return model;
			}
			
		};
		fireNodeChange(event);
	}

	public final void notifyEdgeStructureChange(final ModelStructureChangeType type, final ILinkEdge changedEdge){
		IModelEdgeChangeEvent event = new IModelEdgeChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return type;
			}

			public ILinkEdge getChangedItem() {
				return changedEdge;
			}

			public IModel getChangedModel() {
				return model;
			}
			
		};
		fireEdgeChange(event);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#addModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeListener)
	 */
	public void addModelChangeListener(IModelChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#modelNodeChangeListenerIterator()
	 */
	public Iterator<IModelChangeListener> modelChangeListenerIterator() {
		return this.listeners.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#removeModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeListener)
	 */
	public void removeModelChangeListener(IModelChangeListener listener) {
		this.listeners.remove(listener);
	}
}
