/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.base.BaseChildCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;

/**
 * @author smoodie
 *
 */
public class ChildModelLinkFactory extends BaseChildCompoundEdgeFactory {
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
	protected BaseCompoundEdge newEdge(
			BaseChildCompoundGraph owningChildGraph, int edgeIndex,
			BaseCompoundNode outNode, BaseCompoundNode inNode) {
		return new Link(linkObjectType, (ShapeModel)owningChildGraph, edgeIndex, (Shape)outNode, (Shape)inNode);
	}

}
