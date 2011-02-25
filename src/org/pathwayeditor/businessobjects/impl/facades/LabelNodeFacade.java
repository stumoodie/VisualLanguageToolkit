/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;

import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author Stuart Moodie
 *
 */
public class LabelNodeFacade implements ILabelNode {
	private final ICompoundNode graphElement;
	
	
	public LabelNodeFacade(ICompoundNode graphElement){
		this.graphElement = graphElement;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode#getGraphElement()
	 */
	@Override
	public ICompoundNode getGraphElement() {
		return this.graphElement;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.graphElement.getLevel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.graphElement.getIndex();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode#getAttribute()
	 */
	@Override
	public ILabelAttribute getAttribute() {
		return (ILabelAttribute)this.graphElement.getAttribute();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.graphElement == null) ? 0 : this.graphElement.hashCode());
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
		if (!(obj instanceof ILabelNode)) {
			return false;
		}
		ILabelNode other = (ILabelNode) obj;
		if (this.graphElement == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.graphElement.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#isDescendent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement)
	 */
	@Override
	public boolean isDescendent(IDrawingElement testElement) {
		return this.graphElement.isDescendent(testElement.getGraphElement());
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.graphElement);
		buf.append(")");
		return buf.toString();
	}
}
