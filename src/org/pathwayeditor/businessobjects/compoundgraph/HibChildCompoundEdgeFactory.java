package org.pathwayeditor.businessobjects.compoundgraph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibChildCompoundGraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;

import uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;

public class HibChildCompoundEdgeFactory extends BaseChildCompoundEdgeFactory {
	
	public HibChildCompoundEdgeFactory(HibCompoundNode parentNode) {
		super(parentNode);
	}

	@Override
	protected HibLinkEdge newEdge(BaseChildCompoundGraph owningChildGraph,
			int edgeIndex, BaseCompoundNode outNode, BaseCompoundNode inNode) {
		return new HibLinkEdge((HibChildCompoundGraph)owningChildGraph, edgeIndex, (HibCompoundNode)outNode,
				(HibCompoundNode)inNode);
	}

}
