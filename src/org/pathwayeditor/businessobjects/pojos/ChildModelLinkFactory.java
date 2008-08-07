/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalChildCompoundEdgeFactory;
import uk.ed.inf.graph.compound.archetypal.ArchetypalChildCompoundGraph;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdge;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;

/**
 * @author smoodie
 *
 */
public class ChildModelLinkFactory extends ArchetypalChildCompoundEdgeFactory {
	private ILinkObjectType linkObjectType;


	/**
	 * @param parentNode
	 */
	public ChildModelLinkFactory(CommonModelNode parentNode) {
		super(parentNode);
	}

	public void setObjectType(ILinkObjectType objectType){
		this.linkObjectType = objectType;
	}

	
	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalChildCompoundEdgeFactory#newEdge(uk.ed.inf.graph.compound.impl.ArchetypalChildCompoundGraph, int, uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode, uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode)
	 */
	@Override
	protected ArchetypalCompoundEdge newEdge(
			ArchetypalChildCompoundGraph owningChildGraph, int edgeIndex,
			ArchetypalCompoundNode outNode, ArchetypalCompoundNode inNode) {
		return new Link(linkObjectType, (ShapeModel)owningChildGraph, edgeIndex, (Shape)outNode, (Shape)inNode);
	}

}
