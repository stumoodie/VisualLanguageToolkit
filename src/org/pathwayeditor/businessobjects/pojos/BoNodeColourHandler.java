/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import uk.ed.inf.graph.colour.INodeColourHandler;
import uk.ed.inf.graph.compound.impl.CompoundEdge;
import uk.ed.inf.graph.compound.impl.CompoundNode;

/**
 * @author smoodie
 *
 */
public class BoNodeColourHandler<F, D> implements	INodeColourHandler<CompoundNode, CompoundEdge> {
	private BoMapping<F, D> boMapping;
	private CompoundNode node;
	
	public BoNodeColourHandler() {
		this.boMapping = null;
	}

	/**
	 * @param shape
	 */
	public BoNodeColourHandler(BoMapping<F, D> mapping) {
		this.boMapping = mapping;
	}

	public Object copyColour(CompoundNode newNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public INodeColourHandler<CompoundNode, CompoundEdge> createCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	public BoMapping<F, D> getColour() {
		return this.boMapping;
	}
	public CompoundNode getNode() {
		return this.node;
	}

	@SuppressWarnings("unchecked")
	public void setColour(Object boMapping) {
		BoMapping<F, D> castBoMapping = (BoMapping<F, D>)boMapping;
		this.boMapping = castBoMapping;
	}

	public void setNode(CompoundNode node) {
		this.node = node;
	}

}
