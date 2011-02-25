/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author Stuart Moodie
 *
 */
public class DrawingNodeFacade implements IDrawingNode {
	private final ICompoundNode graphNode;
	
	public DrawingNodeFacade(ICompoundNode graphNode){
		this.graphNode = graphNode;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getGraphElement()
	 */
	@Override
	public ICompoundNode getGraphElement() {
		return this.graphNode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getAttribute()
	 */
	@Override
	public IDrawingNodeAttribute getAttribute() {
		return (IDrawingNodeAttribute)this.graphNode.getAttribute();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.graphNode == null) ? 0 : this.graphNode.hashCode());
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
		if (!(obj instanceof IDrawingNode)) {
			return false;
		}
		IDrawingNode other = (IDrawingNode) obj;
		if (this.graphNode == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.graphNode.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.graphNode.getLevel();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.graphNode.getIndex();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#isDescendent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement)
	 */
	@Override
	public boolean isDescendent(IDrawingElement testElement) {
		return this.graphNode.isDescendent(testElement.getGraphElement());
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.graphNode);
		buf.append(")");
		return buf.toString();
	}
}
