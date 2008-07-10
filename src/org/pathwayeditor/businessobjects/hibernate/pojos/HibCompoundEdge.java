/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

/**
 * @author smoodie
 *
 */
public class HibCompoundEdge {

	private Long id;
	private HibModel model ;
	private int edgeIndex ;
	private HibShapeModel owningChildGraph ;
	private HibCompoundNode outNode ;
	private HibCompoundNode inNode ;
	
	
	
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

	public HibShapeModel getOwningChildGraph() {
		return this.owningChildGraph;
	}

	public void setOwningChildGraph(HibShapeModel owningChildGraph) {
		this.owningChildGraph = owningChildGraph;
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

