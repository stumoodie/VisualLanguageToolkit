package org.pathwayeditor.businessobjects.compoundgraph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibChildCompoundGraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundGraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;

public class HibCompoundEdgeFactory extends BaseCompoundEdgeFactory {

	public HibCompoundEdgeFactory(HibCompoundGraph graph) {
		super(graph);
	}

	@Override
	protected BaseCompoundEdge newEdge(BaseChildCompoundGraph owningGraph,
			int edgeIndex, BaseCompoundNode outNode, BaseCompoundNode inNode) {
		return new HibLinkEdge((HibChildCompoundGraph)owningGraph, edgeIndex, (HibCompoundNode)outNode,
				(HibCompoundNode)inNode);
	}
	
	public HibLinkEdge createEdge(){
		return (HibLinkEdge)super.createEdge();
	}

}
