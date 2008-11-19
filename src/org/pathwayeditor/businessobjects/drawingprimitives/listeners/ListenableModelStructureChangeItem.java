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
