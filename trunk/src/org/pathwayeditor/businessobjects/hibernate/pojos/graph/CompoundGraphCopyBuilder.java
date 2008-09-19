/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder;

/**
 * @author smoodie
 *
 */
public class CompoundGraphCopyBuilder extends BaseGraphCopyBuilder {

	/**
	 * 
	 */
	public CompoundGraphCopyBuilder() {
			super();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfEdge(uk.ed.inf.graph.compound.base.BaseCompoundEdge, uk.ed.inf.graph.compound.base.BaseChildCompoundGraph, uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge createCopyOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, BaseCompoundNode outNode,
			BaseCompoundNode inNode) {
		BaseChildCompoundEdgeFactory edgeFact = edgeOwner.edgeFactory();
		edgeFact.setPair(outNode, inNode);
		return edgeFact.createEdge();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder#createCopyOfNode(uk.ed.inf.graph.compound.base.BaseCompoundNode, uk.ed.inf.graph.compound.base.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundNode createCopyOfNode(BaseCompoundNode srcNode,
			BaseCompoundNode destParentNode) {
		destParentNode.getChildCompoundGraph().nodeFactory();
		//TODO:
		return null;
	}

}
