package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ISubModel;

import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.util.IDirectedEdgeSet;
import uk.ed.inf.graph.util.INodeSet;
import uk.ed.inf.graph.util.impl.DirectedEdgeSet;
import uk.ed.inf.graph.util.impl.NodeSet;


public abstract class HibCompoundNode extends BaseCompoundNode implements IDrawingNode {
	private Long id = null;
	private HibModel graph = null;
	private int index;
	private HibSubModel childCompoundGraph = null;
	private HibCompoundNode parentNode = null;
	private INodeSet<BaseCompoundNode, BaseCompoundEdge> children = new NodeSet<BaseCompoundNode, BaseCompoundEdge>();
	private IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> outEdges = new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();
	private IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> inEdges = new DirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge>();
	
	protected HibCompoundNode() {
		super();
		createInEdgeSet(this.inEdges);
		createOutEdgeSet(this.outEdges);
	}
	
	protected HibCompoundNode(HibModel graph, HibCompoundNode parentNode, int nodeIndex) {
		this.graph = graph;
		this.index = nodeIndex;
		this.parentNode = parentNode;
		this.childCompoundGraph = new HibSubModel(this);
		super.createInEdgeSet(this.inEdges);
		super.createOutEdgeSet(this.outEdges);
	}
	
	void setOwningChildGraph(HibSubModel childCompoundGraph){
		this.childCompoundGraph = childCompoundGraph;
	}
	
	HibSubModel getOwningChildGraph(){
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
	
	void setModel(HibModel value) {
		this.graph = value;
	}
	
	public HibModel getGraph() {
		return graph;
	}
	
	void setChildCompoundGraph(HibSubModel value) {
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
	
	void setInEdges(IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> edgeSet) {
		this.inEdges = edgeSet;
		this.createInEdgeSet(this.inEdges);
	}
	
	IDirectedEdgeSet<BaseCompoundNode, BaseCompoundEdge> getInEdges() {
		return this.inEdges;
	}
	
	void setParent(HibCompoundNode parentNode) {
		this.parentNode = parentNode;
	}


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
	public HibSubModel getChildCompoundGraph() {
		return this.childCompoundGraph;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getCanvas()
	 */
	public HibModel getModel() {
		return this.getGraph();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getSubCanvas()
	 */
	public ISubModel getSubCanvas() {
		return this.getChildCompoundGraph();
	}

	@Override
	public HibCompoundNode getParent() {
		return this.parentNode;
	}

	@Override
	public HibRootNode getRoot(){
		return (HibRootNode)super.getRoot();
	}

	public HibCompoundNode getParentNode() {
		return this.parentNode;
	}

	public void setParentNode(HibCompoundNode parentNode) {
		this.parentNode = parentNode;
	}	
	
}
