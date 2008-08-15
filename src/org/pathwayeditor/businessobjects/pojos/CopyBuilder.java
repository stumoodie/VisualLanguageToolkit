/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;


import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseGraphCopyBuilder;

/**
 * @author smoodie
 *
 */
public class CopyBuilder extends BaseGraphCopyBuilder {

	/**
	 * 
	 */
	public CopyBuilder() {
		super();
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.archetypal.ArchetypalGraphCopyBuilder#createCopyOfEdge(uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdge, uk.ed.inf.graph.compound.archetypal.ArchetypalChildCompoundGraph, int, uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode, uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge createCopyOfEdge(BaseCompoundEdge srcEdge,
			BaseChildCompoundGraph edgeOwner, int newEdgeIndex,
			BaseCompoundNode outNode, BaseCompoundNode inNode) {
		return new Link((ShapeModel)edgeOwner, newEdgeIndex, (Shape)outNode, (Shape)inNode, (Link)srcEdge);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.archetypal.BaseGraphCopyBuilder#createCopyOfNode(uk.ed.inf.graph.compound.archetypal.BaseCompoundNode, uk.ed.inf.graph.compound.archetypal.BaseCompoundNode, int)
	 */
	@Override
	protected BaseCompoundNode createCopyOfNode(BaseCompoundNode srcNode, BaseCompoundNode destParentNode,
														int newNodeIndex) {
		return new Shape((Shape)destParentNode, newNodeIndex, (Shape)srcNode);
	}
}
