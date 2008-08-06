/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

/**
 * @author smoodie
 *
 */
public class HibLinkEdge {
	private Long id;
	private HibModel model ;
	private int edgeIndex ;
	private HibCompoundNode owningNode ;
	private HibCompoundNode outNode ;
	private HibCompoundNode inNode ;

	public HibLinkEdge(){
		
	}
	
	
	public HibLinkEdge(HibModel model, HibCompoundNode owningNode, int edgeIndex, HibCompoundNode outNode, HibCompoundNode inNode){
		this.model = model;
		this.edgeIndex = edgeIndex;
		this.owningNode = owningNode;
		this.outNode = outNode;
		this.inNode = inNode;
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

	public int getEdgeIndex() {
		return this.edgeIndex;
	}

	public void setEdgeIndex(int nodeIndex) {
		this.edgeIndex = nodeIndex;
	}

	public HibCompoundNode getOwningNode() {
		return this.owningNode;
	}

	public void setOwningNode(HibCompoundNode owningNode) {
		this.owningNode = owningNode;
	}

	public HibCompoundNode getOutNode() {
		return this.outNode;
	}

	public void setOutNode(HibCompoundNode outNode) {
		this.outNode = outNode;
	}

	public HibCompoundNode getInNode() {
		return this.inNode;
	}

	public void setInNode(HibCompoundNode inNode) {
		this.inNode = inNode;
	}
	
	

}

