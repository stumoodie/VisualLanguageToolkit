/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;

public class HibLinkEdge extends BaseCompoundEdge implements ILinkEdge {
	private Long id;
	private HibModel graph;
	private int index;
	private HibShapeNode outNode;
	private HibShapeNode inNode;
	private HibSubModel owningChildGraph;
	private HibLinkAttribute attribute;
	
	HibLinkEdge() {
		super();
	}
	
	public HibLinkEdge(HibSubModel child, int edgeIndex, HibShapeNode outNode, HibShapeNode inNode,
						ILinkObjectType objectType, HibObjectType hibObjectType) {
		super();
		this.graph = child.getSuperGraph();
		this.owningChildGraph = child;
		HibCanvas hibCanvas = this.graph.getCanvas();
		this.attribute = new HibLinkAttribute(hibCanvas, hibCanvas.getAttributeSerialCounter().nextIndex(),
											this, objectType, hibObjectType);
		this.index = edgeIndex;
		this.outNode = outNode;
		this.inNode = inNode;
		this.changeInEdge();
		this.changeOutNode();
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
	
	void setGraph(HibModel value) {
		this.graph = value;
	}
	
	public HibModel getGraph() {
		return graph;
	}
	
	void setOutNode(IShapeNode value) {
		this.outNode = (HibShapeNode)value;
	}
	
	public HibShapeNode getOutNode() {
		return outNode;
	}
	
	void setInNode(IShapeNode value) {
		this.inNode = (HibShapeNode)value;
	}
	
	public HibShapeNode getInNode() {
		return inNode;
	}
	
	void setOwningChildGraph(HibSubModel value) {
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
	public HibSubModel getOwningSubCanvas() {
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

