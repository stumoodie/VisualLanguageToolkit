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

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;

/**
 * @author smoodie
 *
 */
public final class ListenableModelStructureChangeItem implements IModelChangeListenee, ISuppressableChangeListenee {
	private final List<IModelChangeListener> listeners;
	private boolean enabled = true;
	
	public ListenableModelStructureChangeItem(){
		this.listeners = new CopyOnWriteArrayList<IModelChangeListener>();
	}

	/**
	 * 
	 */
	public final List<IModelChangeListener> getListeners() {
		return this.listeners;
	}

	public final void fireModelStructureChange(IModelStructureChangeEvent evt){
		if(this.enabled){
			for(IModelChangeListener listener : this.getListeners()){
				listener.modelStructureChange(evt);
			}
		}
	}
	
	public final void notifyCopyOperationCompleted(final IDrawingElementSelection source, final IDrawingElementSelection copiedSelection){
		IModelStructureChangeEvent event = new IModelStructureChangeEvent(){

			@Override
			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.SELECTION_COPIED;
			}

			@Override
			public IDrawingElementSelection getChangedSelection() {
				return copiedSelection;
			}

			@Override
			public IDrawingElementSelection getOriginalSelection() {
				return source;
			}
		};
		fireModelStructureChange(event);
	}
	
	
	public final void notifyMoveOperationCompleted(final IDrawingElementSelection source,
			final IDrawingElementSelection movedSelection){
		IModelStructureChangeEvent event = new IModelStructureChangeEvent(){

			@Override
			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.SELECTION_MOVED;
			}

			@Override
			public IDrawingElementSelection getChangedSelection() {
				return movedSelection;
			}

			@Override
			public IDrawingElementSelection getOriginalSelection() {
				return source;
			}
		};
		fireModelStructureChange(event);
	}
	
	public final void notifyRemovalOperationCompleted(final IDrawingElementSelection removedSelection){
		IModelStructureChangeEvent event = new IModelStructureChangeEvent(){

			@Override
			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.SELECTION_REMOVED;
			}

			@Override
			public IDrawingElementSelection getChangedSelection() {
				return null;
			}

			@Override
			public IDrawingElementSelection getOriginalSelection() {
				return removedSelection;
			}
		};
		fireModelStructureChange(event);
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#addModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeListener)
	 */
	@Override
	public void addModelChangeListener(IModelChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#modelNodeChangeListenerIterator()
	 */
	@Override
	public Iterator<IModelChangeListener> modelChangeListenerIterator() {
		return this.listeners.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#removeModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeListener)
	 */
	@Override
	public void removeModelChangeListener(IModelChangeListener listener) {
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

	public void notifyNewEdge(final IDrawingElementSelection selection) {
		IModelStructureChangeEvent event = new IModelStructureChangeEvent(){

			@Override
			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.LINK_EDGE_CREATED;
			}

			@Override
			public IDrawingElementSelection getChangedSelection() {
				return selection;
			}

			@Override
			public IDrawingElementSelection getOriginalSelection() {
				return null;
			}
		};
		fireModelStructureChange(event);
	}

	public void notifyNewNode(final IDrawingElementSelection selection) {
		IModelStructureChangeEvent event = new IModelStructureChangeEvent(){

			@Override
			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.DRAWING_NODE_CREATED;
			}

			@Override
			public IDrawingElementSelection getChangedSelection() {
				return selection;
			}

			@Override
			public IDrawingElementSelection getOriginalSelection() {
				return null;
			}
		};
		fireModelStructureChange(event);
	}
}
