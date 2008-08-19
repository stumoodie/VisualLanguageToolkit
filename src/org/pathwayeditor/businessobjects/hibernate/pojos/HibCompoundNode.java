package org.pathwayeditor.businessobjects.hibernate.pojos;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ed.inf.graph.util.INodeSet;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ed.inf.graph.util.impl.NodeSet;


public class HibCompoundNode extends BaseCompoundNode {
	private Long id = null;
	private HibCompoundGraph graph = null;
	private int index;
	private HibChildCompoundGraph childCompoundGraph = null;
	private HibCompoundNode parentNode = null;
	private INodeSet<BaseCompoundNode, BaseCompoundEdge> children = new NodeSet<BaseCompoundNode, BaseCompoundEdge>();
	private IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> outEdges = new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();
	private IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> inEdges = new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();
	
	HibCompoundNode() {
		super();
		createInEdgeSet(this.inEdges);
		createOutEdgeSet(this.outEdges);
	}
	
	public HibCompoundNode(HibCompoundNode parentNode, int nodeIndex) {
		this(parentNode.getGraph(), parentNode, nodeIndex);
	}

	
	public HibCompoundNode(HibCompoundGraph graph, int nodeIndex) {
		this(graph, null, nodeIndex);
	}
		
	private HibCompoundNode(HibCompoundGraph graph, HibCompoundNode parentNode, int nodeIndex) {
		this.graph = graph;
		this.index = nodeIndex;
		this.parentNode = parentNode;
		this.childCompoundGraph = new HibChildCompoundGraph(this);
		super.createInEdgeSet(this.inEdges);
		super.createOutEdgeSet(this.outEdges);
	}
	
	void setOwningChildGraph(HibChildCompoundGraph childCompoundGraph){
		this.childCompoundGraph = childCompoundGraph;
	}
	
	HibChildCompoundGraph getOwningChildGraph(){
		return this.childCompoundGraph;
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
	
	void setChildCompoundGraph(HibChildCompoundGraph value) {
		this.childCompoundGraph = value;
	}
	
	void setChildren(INodeSet<BaseCompoundNode, BaseCompoundEdge> value) {
		this.children = value;
		if(this.childCompoundGraph != null){
			this.childCompoundGraph.setRootNode(this);
		}
	}

	INodeSet<BaseCompoundNode, BaseCompoundEdge> getChildren() {
		return this.children;
	}

	void setOutEdges(IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> edgeSet) {
		this.outEdges = edgeSet;
		this.createOutEdgeSet(this.outEdges);
	}
	
	IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> getOutEdges() {
		return this.outEdges;
	}
	
//    void addOutEdge(HibCompoundEdge newOutEdge) {
//		if (newOutEdge == null)
//			throw new IllegalArgumentException("newOutEdge cannot be null");
//
//		HibCompoundNode oldOutNode = newOutEdge.getInNode();
//		if (oldOutNode != null) {
//			oldOutNode.inEdges.remove(newOutEdge);
//		}
//		this.inEdges.add(newOutEdge);
//		newOutEdge.setInNode(this);
//	}

	void setInEdges(IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> edgeSet) {
		this.inEdges = edgeSet;
		this.createInEdgeSet(this.inEdges);
	}
	
	IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> getInEdges() {
		return this.inEdges;
	}
	
//    void addInEdge(HibCompoundEdge newInEdge) {
//		if (newInEdge == null)
//			throw new IllegalArgumentException("newInEdge cannot be null");
//
//		HibCompoundNode oldInNode = newInEdge.getInNode();
//		if (oldInNode != null) {
//			oldInNode.inEdges.remove(newInEdge);
//		}
//		this.inEdges.add(newInEdge);
//		newInEdge.setInNode(this);
//	}

	void setParent(HibCompoundNode parentNode) {
		this.parentNode = parentNode;
	}

//	public Set<HibCompoundNode> getChildren() {
//		return children;
//	}
//
//	void setChildren(Set<HibCompoundNode> children) {
//		this.children = children;
//	}
	
//	void addChild(HibCompoundNode newChild) {
//		if (newChild == null)
//			throw new IllegalArgumentException("newChild cannot be null");
//
//		HibCompoundNode oldParentNode = newChild.getParent();
//		if (oldParentNode != null) {
//			oldParentNode.children.remove(newChild);
//		}
//		this.children.add(newChild);
//		newChild.setParent(this);
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graph == null) ? 0 : graph.hashCode());
		result = prime * result + index;
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
		if (graph == null) {
			if (other.graph != null)
				return false;
		} else if (!graph.equals(other.graph))
			return false;
		if (getIndex() != other.getIndex())
			return false;
		return true;
	}

	@Override
	public HibChildCompoundGraph getChildCompoundGraph() {
		return this.childCompoundGraph;
	}

	@Override
	public HibCompoundNode getParent() {
		return this.parentNode;
	}

	@Override
	public HibCompoundNode getRoot(){
		return (HibCompoundNode)super.getRoot();
	}	
}
