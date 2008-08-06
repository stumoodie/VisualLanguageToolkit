/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.HashSet;
import java.util.Set;

/**
 * @author smoodie
 * 
 */

public abstract class HibCompoundNode {
	private Long id;
	private HibModel model;
	private int nodeIndex;
	private HibCompoundNode parentNode;
	private Set<HibLinkEdge> outEdges = new HashSet<HibLinkEdge>();
	private Set<HibLinkEdge> inEdges = new HashSet<HibLinkEdge>();

	public HibCompoundNode() {

	}

	public HibCompoundNode(HibModel model, HibCompoundNode parentNode, int nodeIdx) {
		this.model = model;
		this.parentNode = parentNode;
		this.nodeIndex = nodeIdx;
	}

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

	public HibCompoundNode getParentNode() {
		return this.parentNode;
	}

	public void setParentNode(HibCompoundNode parentNode) {
		this.parentNode = parentNode;
	}

	public Set<HibLinkEdge> getOutEdges() {
		return this.outEdges;
	}

	void setOutEdges(Set<HibLinkEdge> outEdges) {
		this.outEdges = outEdges;
	}

	public void addOutEdge(HibLinkEdge newOutEdge) {
		if (newOutEdge == null)
			throw new IllegalArgumentException("newOutEdge cannot be null");

		HibCompoundNode oldOutNode = newOutEdge.getOutNode();
		if (oldOutNode != null) {
			oldOutNode.getOutEdges().remove(newOutEdge);
		}
		this.getOutEdges().add(newOutEdge);
		newOutEdge.setOutNode(this);
	}

    public void removeOutEdge(HibLinkEdge outEdge) {
		if (outEdge == null)
			throw new IllegalArgumentException("outEdge cannot be null");
		if (outEdge.getOutNode() != this)
			throw new IllegalArgumentException("outEdge must be a child of this folder");

		this.getOutEdges().remove(outEdge);
		outEdge.setOutNode(null);
	}
    
	public Set<HibLinkEdge> getInEdges() {
		return this.inEdges;
	}

	void setInEdges(Set<HibLinkEdge> inEdges) {
		this.inEdges = inEdges;
	}


	public void addInEdge(HibLinkEdge newInEdge) {
		if (newInEdge == null)
			throw new IllegalArgumentException("newInEdge cannot be null");

		HibCompoundNode oldInNode = newInEdge.getInNode();
		if (oldInNode != null) {
			oldInNode.getInEdges().remove(newInEdge);
		}
		this.getInEdges().add(newInEdge);
		newInEdge.setInNode(this);
	}

    public void removeInEdge(HibLinkEdge inEdge) {
		if (inEdge == null)
			throw new IllegalArgumentException("inEdge cannot be null");
		if (inEdge.getInNode() != this)
			throw new IllegalArgumentException("inEdge must be a child of this folder");

		this.getInEdges().remove(inEdge);
		inEdge.setInNode(null);
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.getModel() == null) ? 0 : this.getModel().hashCode());
		result = prime * result + this.getNodeIndex();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibCompoundNode))
			return false;
		final HibCompoundNode other = (HibCompoundNode) obj;
		if (this.getModel() == null) {
			if (other.getModel() != null)
				return false;
		} else if (!this.getModel().equals(other.getModel()))
			return false;
		if (this.getNodeIndex() != other.getNodeIndex())
			return false;
		return true;
	}

}
