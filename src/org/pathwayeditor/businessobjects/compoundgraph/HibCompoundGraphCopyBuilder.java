package org.pathwayeditor.businessobjects.compoundgraph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibChildCompoundGraph;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibLinkEdge;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder;

public class HibCompoundGraphCopyBuilder extends BaseGraphCopyBuilder {

	public HibCompoundGraphCopyBuilder() {
	}

	@Override
	protected HibLinkEdge createCopyOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, int newEdgeIndex,
			BaseCompoundNode outNode, BaseCompoundNode inNode) {
		return new HibLinkEdge((HibChildCompoundGraph)edgeOwner, newEdgeIndex, (HibCompoundNode)outNode, (HibCompoundNode)inNode);
	}

	@Override
	protected BaseCompoundNode createCopyOfNode(BaseCompoundNode srcNode,
			BaseCompoundNode destParentNode, int newNodeIndex) {
		return new HibCompoundNode((HibCompoundNode)destParentNode, newNodeIndex);
	}

}
