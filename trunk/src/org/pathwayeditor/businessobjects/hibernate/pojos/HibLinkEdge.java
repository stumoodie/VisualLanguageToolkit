/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;

public class HibLinkEdge extends BaseCompoundEdge implements ILinkEdge {
	private Long id;
	private HibModel graph;
	private int index;
	private HibShapeNode outNode;
	private HibShapeNode inNode;
	private HibSubModel owningChildGraph;
	private HibLinkAttribute attribute;
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibLinkEdge() {
		super();
	}
	
	public HibLinkEdge(HibSubModel child, int edgeIndex, HibShapeNode outNode, HibShapeNode inNode,
						HibLinkAttribute linkAttribute) {
		super();
		this.graph = child.getSuperGraph();
		this.owningChildGraph = child;
		this.index = edgeIndex;
		this.outNode = outNode;
		this.inNode = inNode;
		this.changeInEdge();
		this.changeOutNode();
		this.changeAttribute(linkAttribute);
	}
	
	/**
	 * @param linkAttribute
	 */
	public void changeAttribute(HibLinkAttribute linkAttribute) {
		if(this.attribute != null){
			this.attribute.setLinkEdge(null);
		}
		if(linkAttribute != null){
			linkAttribute.setLinkEdge(this);
		}
		this.attribute = linkAttribute;
	}

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
	
	void setOutNode(HibShapeNode value) {
		this.outNode = value;
	}
	
	public HibShapeNode getOutNode() {
		return outNode;
	}
	
	void setInNode(HibShapeNode value) {
		this.inNode = value;
	}
	
	public HibShapeNode getInNode() {
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
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#canChangeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public boolean canChangeTarget(IShapeNode newShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#changeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public void changeSource(IShapeNode newShape) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#changeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode)
	 */
	public void changeTarget(IShapeNode newShape) {
		// TODO Auto-generated method stub
		
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
		return this.getOutNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getTargetShape()
	 */
	public HibShapeNode getTargetShape() {
		return this.getInNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getCanvas()
	 */
	public HibModel getModel() {
		return this.graph;
	}


}

