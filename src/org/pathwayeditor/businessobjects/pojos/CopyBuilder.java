/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import uk.ed.inf.graph.compound.archetypal.ArchetypalChildCompoundGraph;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdge;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;
import uk.ed.inf.graph.compound.archetypal.ArchetypalGraphCopyBuilder;

/**
 * @author smoodie
 *
 */
public class CopyBuilder extends ArchetypalGraphCopyBuilder {

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
	protected ArchetypalCompoundEdge createCopyOfEdge(ArchetypalCompoundEdge srcEdge,
			ArchetypalChildCompoundGraph edgeOwner, int newEdgeIndex,
			ArchetypalCompoundNode outNode, ArchetypalCompoundNode inNode) {
		return new Link((ShapeModel)edgeOwner, newEdgeIndex, (Shape)outNode, (Shape)inNode, (Link)srcEdge);
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.archetypal.ArchetypalGraphCopyBuilder#createCopyOfNode(uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode, uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode, int)
	 */
	@Override
	protected ArchetypalCompoundNode createCopyOfNode(ArchetypalCompoundNode srcNode, ArchetypalCompoundNode destParentNode,
														int newNodeIndex) {
		return new Shape((Shape)destParentNode, newNodeIndex, (Shape)srcNode);
	}
}
