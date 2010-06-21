/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;

import uk.ed.inf.graph.compound.ICompoundEdge;
import uk.ed.inf.graph.compound.ICompoundNode;
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
	private HibCompoundNode hibParentNode = null;
	private boolean removed;
	private transient Integer level;
	private INodeSet<ICompoundNode, ICompoundEdge> children = new NodeSet<ICompoundNode, ICompoundEdge>();
	private IDirectedEdgeSet<ICompoundNode, ICompoundEdge> outEdges = new DirectedEdgeSet<ICompoundNode, ICompoundEdge>();
	private IDirectedEdgeSet<ICompoundNode, ICompoundEdge> inEdges = new DirectedEdgeSet<ICompoundNode, ICompoundEdge>();
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	protected HibCompoundNode() {
		super();
		createInEdgeSet(this.inEdges);
		createOutEdgeSet(this.outEdges);
	}
	
	protected HibCompoundNode(HibModel graph, HibCompoundNode parentNode, int nodeIndex) {
		super();
		this.graph = graph;
		this.index = nodeIndex;
		this.hibParentNode = parentNode;
		this.childCompoundGraph = new HibSubModel(this);
		super.createInEdgeSet(this.inEdges);
		super.createOutEdgeSet(this.outEdges);
		if(this.hibParentNode != null) {
			this.hibParentNode.getSubModel().addNewNode(this);
		}
		this.level = super.calcTreeLevel();
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
	
	void setChildren(INodeSet<ICompoundNode, ICompoundEdge> value) {
		this.children = value;
		if(this.childCompoundGraph != null){
			this.childCompoundGraph.setRootNode(this);
		}
	}

	public INodeSet<ICompoundNode, ICompoundEdge> getChildren() {
		return this.children;
	}

	void setOutEdges(IDirectedEdgeSet<ICompoundNode, ICompoundEdge> edgeSet) {
		this.outEdges = edgeSet;
		this.createOutEdgeSet(this.outEdges);
	}
	
	public IDirectedEdgeSet<ICompoundNode, ICompoundEdge> getOutEdges() {
		return this.outEdges;
	}
	
	void setInEdges(IDirectedEdgeSet<ICompoundNode, ICompoundEdge> edgeSet) {
		this.inEdges = edgeSet;
		this.createInEdgeSet(this.inEdges);
	}
	
	public IDirectedEdgeSet<ICompoundNode, ICompoundEdge> getInEdges() {
		return this.inEdges;
	}
	
	void setHibParentNode(HibCompoundNode parentNode) {
		this.hibParentNode = parentNode;
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
	public HibSubModel getSubModel() {
		return this.getChildCompoundGraph();
	}

	@Override
	public HibRootNode getRoot(){
		return (HibRootNode)super.getRoot();
	}

	public HibCompoundNode getHibParentNode() {
		return this.hibParentNode;
	}
	
//	public abstract HibCompoundNode getParentNode();

	public HibCompoundNode getParent() {
		return (HibCompoundNode)getParentNode();
	}

	void setIsRemoved(boolean removed){
		this.removed = removed;
	}

	boolean getIsRemoved(){
		return this.removed;
	}
	
	@Override
	protected void setRemoved(boolean removed){
		this.removed = removed;
	}

	@Override
	public boolean isRemoved() {
		return this.removed;
	}

	/**
	 * Validates aspects of the node not associated with graph connectivity (which is already validated by the graph library)
	 * It may delegate to associated objects, such as attribute.
	 * @return true if the node is valid, false otherwise.
	 */
	public abstract boolean isValid();
	
	
	public boolean isDescendent(IDrawingNode testNode) {
		boolean retVal = false;
		if(testNode != null && testNode instanceof HibCompoundNode) {
			retVal = super.isDescendent((HibCompoundNode)testNode);
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getLevel()
	 */
	public int getLevel() {
		if(this.level == null){
			this.level = Integer.valueOf(super.calcTreeLevel());
		}
		return this.level.intValue();
	}
	
	public long getUniqueIndex(){
		return this.getIndex();
	}

}
