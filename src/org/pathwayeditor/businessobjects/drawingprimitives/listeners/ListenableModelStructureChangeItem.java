/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
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

	public final void firePropertyChange(IModelNodeChangeEvent evt){
		for(IModelChangeListener listener : this.getListeners()){
			listener.nodeStructureChange(evt);
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
		firePropertyChange(event);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#addModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeListener)
	 */
	public void addModelNodeChangeListener(IModelChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#modelNodeChangeListenerIterator()
	 */
	public Iterator<IModelChangeListener> modelNodeChangeListenerIterator() {
		return this.listeners.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelChangeListenee#removeModelNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IModelNodeChangeListener)
	 */
	public void removeModelNodeChangeListener(IModelChangeListener listener) {
		this.listeners.remove(listener);
	}
}
