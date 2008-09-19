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

	protected BaseCompoundEdge createCopyOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, BaseCompoundNode outNode, BaseCompoundNode inNode) {
		BaseChildCompoundEdgeFactory fact =  edgeOwner.edgeFactory();
		fact.setPair(outNode, inNode);
		return fact.createEdge();
	}

	protected BaseCompoundNode createCopyOfNode(BaseCompoundNode srcNode,
			BaseCompoundNode destParentNode) {
		BaseCompoundNodeFactory fact = destParentNode.getChildCompoundGraph().nodeFactory();
		return fact.createNode();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfEdge(uk.ed.inf.graph.compound.base.BaseCompoundEdge, uk.ed.inf.graph.compound.base.BaseChildCompoundGraph, int, uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge createCopyOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, int newEdgeIndex,
			BaseCompoundNode outNode, BaseCompoundNode inNode) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode, int)
	 */
	@Override
	protected BaseCompoundNode createCopyOfNode(BaseCompoundNode srcNode,
			BaseCompoundNode destParentNode, int newNodeIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
