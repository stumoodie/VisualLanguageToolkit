/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;

public class HibLinkEdge extends BaseCompoundEdge {
	private Long id;
	private HibCompoundGraph graph;
	private int index;
	private HibCompoundNode outNode;
	private HibCompoundNode inNode;
	private HibChildCompoundGraph owningChildGraph;
	private HibLinkAttribute linkAttribute ;
	
	HibLinkEdge() {
		super();
	}
	
	public HibLinkEdge(HibChildCompoundGraph child, int edgeIndex, HibCompoundNode outNode, HibCompoundNode inNode) {
		super();
		this.graph = child.getSuperGraph();
		this.owningChildGraph = child;
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
	
	void setGraph(HibCompoundGraph value) {
		this.graph = value;
	}
	
	public HibCompoundGraph getGraph() {
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
	
	void setOwningChildGraph(HibChildCompoundGraph value) {
		this.owningChildGraph = value;
	}
	
	public HibChildCompoundGraph getOwningChildGraph() {
		return owningChildGraph;
	}
	
	public HibLinkAttribute getLinkAttribute() {
		return this.linkAttribute;
	}

	public void setLinkAttribute(HibLinkAttribute linkAttribute) {
		this.linkAttribute = linkAttribute;
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
		if (index != other.index)
			return false;
		if (graph == null) {
			if (other.graph != null)
				return false;
		} else if (!graph.equals(other.graph))
			return false;
		return true;
	}


}

