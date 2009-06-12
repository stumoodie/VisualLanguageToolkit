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

/**
 * @author smoodie
 *
 */
public final class ListenableNodeStructureChangeItem implements INodeChangeListenee {
	private final List<INodeChangeListener> listeners;
	private final IDrawingNode model;
	
	public ListenableNodeStructureChangeItem(IDrawingNode model){
		this.listeners = new CopyOnWriteArrayList<INodeChangeListener>();
		this.model = model;
	}

	/**
	 * 
	 */
	public final List<INodeChangeListener> getListeners() {
		return this.listeners;
	}

	public final void fireSourceNodeChange(INodeChangeEvent evt){
		for(INodeChangeListener listener : this.getListeners()){
			listener.sourceNodeStructureChange(evt);
		}
	}
	
	public final void fireTargetNodeChange(INodeChangeEvent evt){
		for(INodeChangeListener listener : this.getListeners()){
			listener.targetNodeStructureChange(evt);
		}
	}
	
	public final void notifyNodeStructureChange(final ModelStructureChangeType type, final ILinkEdge changedNode){
		INodeChangeEvent event = new INodeChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return type;
			}

			public ILinkEdge getChangedItem() {
				return changedNode;
			}

			public IDrawingNode getChangedModel() {
				return model;
			}
			
		};
		if(changedNode.getSourceShape().equals(model)){
			fireSourceNodeChange(event);
		}
		else if(changedNode.getTargetShape().equals(model)){
			fireTargetNodeChange(event);
		}
		else{
			throw new IllegalArgumentException("the changedEdge does not contain this node");
		}
	}

	public final void notifySourceNodeStructureChange(final ModelStructureChangeType type, final ILinkEdge changedNode){
		INodeChangeEvent event = new INodeChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return type;
			}

			public ILinkEdge getChangedItem() {
				return changedNode;
			}

			public IDrawingNode getChangedModel() {
				return model;
			}
			
		};
		if(changedNode.getSourceShape().equals(model)){
			fireSourceNodeChange(event);
		}
		else{
			throw new IllegalArgumentException("this node is not the source of changedEdge");
		}
	}

	public final void notifyTargetNodeStructureChange(final ModelStructureChangeType type, final ILinkEdge changedNode){
		INodeChangeEvent event = new INodeChangeEvent(){

			public ModelStructureChangeType getChangeType() {
				return type;
			}

			public ILinkEdge getChangedItem() {
				return changedNode;
			}

			public IDrawingNode getChangedModel() {
				return model;
			}
			
		};
		if(changedNode.getTargetShape().equals(model)){
			fireTargetNodeChange(event);
		}
		else{
			throw new IllegalArgumentException("this node is not the target of changedEdge");
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#addNodeNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeNodeChangeListener)
	 */
	public void addNodeChangeListener(INodeChangeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#modelNodeChangeListenerIterator()
	 */
	public Iterator<INodeChangeListener> nodeChangeListenerIterator() {
		return this.listeners.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeChangeListenee#removeNodeNodeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.INodeNodeChangeListener)
	 */
	public void removeNodeChangeListener(INodeChangeListener listener) {
		this.listeners.remove(listener);
	}
}
