/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.directed.IDirectedPair#reversedNodes()
	 */
	public ShapeNodePair reversedNodes() {
		return new ShapeNodePair(this.inNode, this.outNode);
	}	
}
