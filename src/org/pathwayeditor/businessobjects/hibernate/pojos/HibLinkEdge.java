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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ModelStructureChangeType;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;

public class HibLinkEdge extends BaseCompoundEdge implements ILinkEdge {
	private Long id;
	private HibModel graph;
	private int index;
	private HibCompoundNode outNode;
	private HibCompoundNode inNode;
	private HibSubModel owningChildGraph;
	private HibLinkAttribute attribute;
	private boolean removed;
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibLinkEdge() {
		super();
	}
	
	public HibLinkEdge(HibSubModel owningSubmodel, int edgeIndex, HibShapeNode outNode, HibShapeNode inNode,
						HibLinkAttribute linkAttribute) {
		super();
		this.graph = owningSubmodel.getSuperGraph();
		this.owningChildGraph = owningSubmodel;
		this.index = edgeIndex;
		this.outNode = outNode;
		this.inNode = inNode;
		this.changeInEdge();
		this.changeOutNode();
		this.attribute = linkAttribute;
		this.attribute.setLinkEdge(this);
		this.owningChildGraph.addNewEdge(this);
	}
	
//	/**
//	 * @param linkAttribute
//	 */
//	public void changeAttribute(HibLinkAttribute linkAttribute) {
//		if(this.attribute != null){
//			this.attribute.setLinkEdge(null);
//		}
//		if(linkAttribute != null){
//			linkAttribute.setLinkEdge(this);
//		}
//		this.attribute = linkAttribute;
//	}

	@SuppressWarnings("unused")
	private void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return id;
	}
	
	void setIndex(int value) {
		this.index = value;
	}
	
	@Override
	public int getIndex() {
		return index;
	}
	
	void setModel(HibModel value) {
		this.graph = value;
	}
	
	public HibModel getGraph() {
		return graph;
	}
	
	void setOutNode(HibCompoundNode value) {
		this.outNode = value;
	}
	
	public HibCompoundNode getOutNode() {
		return outNode;
	}
	
	void setInNode(HibCompoundNode value) {
		this.inNode = value;
	}
	
	public HibCompoundNode getInNode() {
		return inNode;
	}
	
	void setOwningSubModel(HibSubModel value) {
		this.owningChildGraph = value;
	}
	
	public HibSubModel getOwningChildGraph() {
		return owningChildGraph;
	}
	
	public HibLinkAttribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(HibLinkAttribute linkAttribute) {
		this.attribute = linkAttribute;
		this.attribute.setLinkEdge(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((graph == null) ? 0 : graph.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibLinkEdge))
			return false;
		final HibLinkEdge other = (HibLinkEdge) obj;
		if (this.getIndex() != other.getIndex())
			return false;
		if (this.getGraph() == null) {
			if (other.getGraph() != null)
				return false;
		} else if (!this.getGraph().equals(other.getGraph()))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#canChangeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public boolean canChangeSource(IShapeNode newShape) {
		// TODO implement this
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#canChangeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public boolean canChangeTarget(IShapeNode newShape) {
		// TODO implement this
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#changeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public void changeSource(IShapeNode newShape) {
		// TODO implement this
		throw new UnsupportedOperationException("this functionmality has not been implemented yet");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#changeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public void changeTarget(IShapeNode newShape) {
		// TODO implement this
		throw new UnsupportedOperationException("this functionmality has not been implemented yet");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getOwningSubCanvas()
	 */
	public HibSubModel getOwningSubModel() {
		return this.getOwningChildGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getSourceShape()
	 */
	public HibShapeNode getSourceShape() {
		return (HibShapeNode) this.getOutNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getTargetShape()
	 */
	public HibShapeNode getTargetShape() {
		return (HibShapeNode) this.getInNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getCanvas()
	 */
	public HibModel getModel() {
		return this.graph;
	}


	void setIsRemoved(boolean removed){
		this.removed = removed;
	}
	
	boolean getIsRemoved(){
		return this.removed;
	}

	@Override
	protected void setRemoved(boolean removed){
		this.removed = removed;
	}

	@Override
	public boolean isRemoved() {
		return this.removed;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("[model=");
		builder.append(this.getModel());
		builder.append(", index=");
		builder.append(this.getIndex());
		builder.append(", removed=");
		builder.append(this.isRemoved());
		builder.append(", attribute=");
		builder.append(this.getAttribute());
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundEdge#removalAction()
	 */
	@Override
	protected void removalAction(boolean removed) {
		ModelStructureChangeType type;
		if(removed){
			type = ModelStructureChangeType.DELETED;
		}
		else{
			type = ModelStructureChangeType.ADDED;
		}
		this.owningChildGraph.notifyEdgeStructureChange(type, this);
		this.getSourceShape().notifySourceEdgeChange(type, this);
		this.getTargetShape().notifyTargetEdgeChange(type, this);
	}

	public boolean isValid() {
		return this.attribute != null && this.attribute.isValid();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLabelSubModel()
	 */
	public ISubModel getLabelSubModel() {
		return this.getOwningSubModel();
	}
}

