/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;

/**
 * @author smoodie
 *
 */
public final class ListenableSubModelStructureChangeItem implements ISubModelChangeListenee {
	private final List<ISubModelChangeListener> listeners;
	private final ISubModel model;
	
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
		for(ISubModelChangeListener listener : this.getListeners()){
			listener.nodeStructureChange(evt);
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
}
