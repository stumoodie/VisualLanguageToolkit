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

			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.SELCTION_COPIED;
			}

			public IDrawingElementSelection getChangedSelection() {
				return copiedSelection;
			}

			public IDrawingElementSelection getOriginalSelection() {
				return source;
			}
		};
		fireModelStructureChange(event);
	}
	
	
	public final void notifyMoveOperationCompleted(final IDrawingElementSelection source,
			final IDrawingElementSelection movedSelection){
		IModelStructureChangeEvent event = new IModelStructureChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.SELECTION_MOVED;
			}

			public IDrawingElementSelection getChangedSelection() {
				return movedSelection;
			}

			public IDrawingElementSelection getOriginalSelection() {
				return source;
			}
		};
		fireModelStructureChange(event);
	}
	
	public final void notifyRemovalOperationCompleted(final IDrawingElementSelection removedSelection){
		IModelStructureChangeEvent event = new IModelStructureChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return ModelStructureChangeType.SELECTION_REMOVED;
			}

			public IDrawingElementSelection getChangedSelection() {
				return null;
			}

			public IDrawingElementSelection getOriginalSelection() {
				return removedSelection;
			}
		};
		fireModelStructureChange(event);
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
