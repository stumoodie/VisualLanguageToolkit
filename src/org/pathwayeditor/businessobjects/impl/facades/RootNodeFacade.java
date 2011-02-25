/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootNode;

import uk.ac.ed.inf.graph.compound.IRootCompoundNode;

/**
 * @author Stuart Moodie
 *
 */
public class RootNodeFacade implements IRootNode {
	private final IRootCompoundNode compoundRootNode;
	
	public RootNodeFacade(IRootCompoundNode compoundRootNode){
		this.compoundRootNode = compoundRootNode;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getGraphElement()
	 */
	@Override
	public IRootCompoundNode getGraphElement() {
		return this.compoundRootNode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootNode#getAttribute()
	 */
	@Override
	public IRootAttribute getAttribute() {
		return (IRootAttribute)this.compoundRootNode.getAttribute();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.compoundRootNode == null) ? 0 : this.compoundRootNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IRootNode)) {
			return false;
		}
		IRootNode other = (IRootNode) obj;
		if (this.compoundRootNode == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.compoundRootNode.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.compoundRootNode.getLevel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.compoundRootNode.getIndex();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#isDescendent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement)
	 */
	@Override
	public boolean isDescendent(IDrawingElement testElement) {
		return this.compoundRootNode.isDescendent(testElement.getGraphElement());
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.compoundRootNode);
		buf.append(")");
		return buf.toString();
	}
}
