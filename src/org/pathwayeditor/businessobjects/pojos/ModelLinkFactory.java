/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.compound.archetypal.ArchetypalChildCompoundGraph;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdge;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundEdgeFactory;
import uk.ed.inf.graph.compound.archetypal.ArchetypalCompoundNode;

/**
 * @author smoodie
 *
 */
public class ModelLinkFactory extends ArchetypalCompoundEdgeFactory {
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
	 * @see uk.ed.inf.graph.compound.impl.ArchetypalCompoundEdgeFactory#newEdge(uk.ed.inf.graph.compound.impl.ArchetypalChildCompoundGraph, int, uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode, uk.ed.inf.graph.compound.impl.ArchetypalCompoundNode)
	 */
	@Override
	protected ArchetypalCompoundEdge newEdge(
			ArchetypalChildCompoundGraph owningGraph, int edgeIndex,
			ArchetypalCompoundNode outNode, ArchetypalCompoundNode inNode) {
		return new Link(linkObjectType, (ShapeModel)owningGraph, edgeIndex, (Shape)outNode, (Shape)inNode);
	}

}
