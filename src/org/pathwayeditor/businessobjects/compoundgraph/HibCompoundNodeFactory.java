package org.pathwayeditor.businessobjects.compoundgraph;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibCompoundNode;

import uk.ed.inf.graph.compound.base.BaseCompoundNode;
import uk.ed.inf.graph.compound.base.BaseCompoundNodeFactory;

public class HibCompoundNodeFactory extends BaseCompoundNodeFactory {

	public HibCompoundNodeFactory(HibCompoundNode parent) {
		super(parent);
	}

	public HibCompoundNode createNode(){
		return (HibCompoundNode)super.createNode();
	}
	
	@Override
	protected HibCompoundNode newNode(BaseCompoundNode parent, int nodeIndex) {
		return new HibCompoundNode((HibCompoundNode)parent, nodeIndex);
	}

}
