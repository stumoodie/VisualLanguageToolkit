package org.pathwayeditor.businessobjects.compoundgraph;

import uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;
import uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder;

public class HibCompoundGraphCopyBuilder extends BaseGraphCopyBuilder {

	public HibCompoundGraphCopyBuilder() {
	}

	@Override
	protected BaseCompoundEdge createCopyOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, BaseCompoundNode outNode, BaseCompoundNode inNode) {
		BaseChildCompoundEdgeFactory fact =  edgeOwner.edgeFactory();
		fact.setPair(outNode, inNode);
		return fact.createEdge();
	}

	@Override
	protected BaseCompoundNode createCopyOfNode(BaseCompoundNode srcNode,
			BaseCompoundNode destParentNode) {
		BaseCompoundNodeFactory fact = destParentNode.getChildCompoundGraph().nodeFactory();
		return fact.createNode();
	}

}
