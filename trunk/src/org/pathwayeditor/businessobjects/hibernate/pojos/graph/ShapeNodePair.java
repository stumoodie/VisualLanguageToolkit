/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodePair;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibShapeNode;

import uk.ed.inf.graph.compound.base.BaseCompoundNodePair;

/**
 * @author smoodie
 *
 */
public class ShapeNodePair extends BaseCompoundNodePair implements IShapeNodePair {
	private final HibShapeNode outNode;
	private final HibShapeNode inNode;
	
	public ShapeNodePair(HibShapeNode outNode, HibShapeNode inNode){
		super();
		this.outNode = outNode;
		this.inNode = inNode;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodePair#getSource()
	 */
	public IShapeNode getSource() {
		return this.getOutNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeNodePair#getTarget()
	 */
	public IShapeNode getTarget() {
		return this.getInNode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.outNode == null) ? 0 : this.outNode.hashCode());
		result = prime * result
				+ ((this.inNode == null) ? 0 : this.inNode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ShapeNodePair))
			return false;
		ShapeNodePair other = (ShapeNodePair) obj;
		if (this.outNode == null) {
			if (other.outNode != null)
				return false;
		} else if (!this.outNode.equals(other.outNode))
			return false;
		if (this.inNode == null) {
			if (other.inNode != null)
				return false;
		} else if (!this.inNode.equals(other.inNode))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodePair#getInNode()
	 */
	@Override
	public HibShapeNode getInNode() {
		return this.inNode;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.compound.base.BaseCompoundNodePair#getOutNode()
	 */
	@Override
	public HibShapeNode getOutNode() {
		return this.outNode;
	}	
}
