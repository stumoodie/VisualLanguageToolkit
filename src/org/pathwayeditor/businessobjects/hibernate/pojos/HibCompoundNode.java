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
	private HibShapeModel owningChildModel ;
	private Set<HibCompoundNode> nodes ; 
	private Set<HibCompoundEdge> outEdges ;
	
	
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

	public HibShapeModel getOwningChildModel() {
		return this.owningChildModel;
	}

	public void setOwningChildModel(HibShapeModel owningChildModel) {
		this.owningChildModel = owningChildModel;
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

	

}

