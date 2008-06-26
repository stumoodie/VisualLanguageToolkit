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
public class ShapeNodeColourHandler implements
		INodeColourHandler<CompoundNode, CompoundEdge> {
	private Shape shape;
	private CompoundNode node;
	
	/**
	 * @param shape
	 */
	public ShapeNodeColourHandler(Shape shape) {
		this.shape = shape;
	}

	public Object copyColour(CompoundNode newNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public INodeColourHandler<CompoundNode, CompoundEdge> createCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	public Shape getColour() {
		return this.shape;
	}
	public CompoundNode getNode() {
		return this.node;
	}

	public void setColour(Object colour) {
		Shape shape = (Shape)colour;
		this.shape = shape;
	}

	public void setNode(CompoundNode node) {
		this.node = node;
	}

}
