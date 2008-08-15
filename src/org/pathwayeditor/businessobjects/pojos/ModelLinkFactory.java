/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.base.BaseChildCompoundGraph;
import uk.ed.inf.graph.compound.base.BaseCompoundEdge;
import uk.ed.inf.graph.compound.base.BaseCompoundEdgeFactory;
import uk.ed.inf.graph.compound.base.BaseCompoundNode;

/**
 * @author smoodie
 *
 */
public class ModelLinkFactory extends BaseCompoundEdgeFactory {
	private ILinkObjectType linkObjectType;
	
	/**
	 * @param graph
	 */
	public ModelLinkFactory(Model graph) {
		super(graph);
	}
	
	public void setObjectType(ILinkObjectType objectType){
		this.linkObjectType = objectType;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.impl.BaseCompoundEdgeFactory#newEdge(uk.ed.inf.graph.compound.impl.BaseChildCompoundGraph, int, uk.ed.inf.graph.compound.impl.BaseCompoundNode, uk.ed.inf.graph.compound.impl.BaseCompoundNode)
	 */
	@Override
	protected BaseCompoundEdge newEdge(
			BaseChildCompoundGraph owningGraph, int edgeIndex,
			BaseCompoundNode outNode, BaseCompoundNode inNode) {
		return new Link(linkObjectType, (ShapeModel)owningGraph, edgeIndex, (Shape)outNode, (Shape)inNode);
	}

}
