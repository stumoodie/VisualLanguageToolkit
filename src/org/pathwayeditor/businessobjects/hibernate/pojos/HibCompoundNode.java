/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Set;

/**
 * @author smoodie
 *
 */

public class HibCompoundNode {

	private Long id;
	private HibModel model ;
	private int nodeIndex ;
	private HibShapeModel owningShapeModel;
	private Set<HibCompoundNode> nodes; 
	private Set<HibCompoundEdge> outEdges;
	private Set<HibCompoundEdge> inEdges;
	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HibModel getModel() {
		return this.model;
	}

	public void setModel(HibModel model) {
		this.model = model;
	}

	public int getNodeIndex() {
		return this.nodeIndex;
	}

	public void setNodeIndex(int nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public HibShapeModel getOwningShapeModel() {
		return this.owningShapeModel;
	}

	public void setOwningShapeModel(HibShapeModel owningChildModel) {
		this.owningShapeModel = owningChildModel;
	}

	public Set<HibCompoundNode> getNodes() {
		return this.nodes;
	}

	public void setNodes(Set<HibCompoundNode> nodes) {
		this.nodes = nodes;
	}

	public Set<HibCompoundEdge> getOutEdges() {
		return this.outEdges;
	}

	public void setOutEdges(Set<HibCompoundEdge> outEdges) {
		this.outEdges = outEdges;
	}

	protected Set<HibCompoundEdge> getInEdges() {
		return this.inEdges;
	}

	protected void setInEdges(Set<HibCompoundEdge> inEdges) {
		this.inEdges = inEdges;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibCompoundNode))
			return false;
		final HibCompoundNode other = (HibCompoundNode) obj;
		if(this.nodeIndex!=other.nodeIndex)
			return false;
		 if (!this.model.equals(other.getModel()))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result+nodeIndex
				+ ((this.model == null) ? 0 : this.model.hashCode());
		return result;
	}

}

