/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;

import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * @author Stuart Moodie
 *
 */
public class LinkEdgeFacade implements ILinkEdge {
	private ICompoundEdge linkEdge;
	
	public LinkEdgeFacade(ICompoundEdge linkEdge){
		this.linkEdge = linkEdge;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getGraphElement()
	 */
	@Override
	public ICompoundEdge getGraphElement() {
		return this.linkEdge;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getAttribute()
	 */
	@Override
	public ILinkAttribute getAttribute() {
		return (ILinkAttribute)this.linkEdge.getAttribute();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getSourceShape()
	 */
	@Override
	public ICompoundNode getSourceShape() {
		return this.linkEdge.getConnectedNodes().getOutNode();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge#getTargetShape()
	 */
	@Override
	public ICompoundNode getTargetShape() {
		return this.linkEdge.getConnectedNodes().getInNode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.linkEdge == null) ? 0 : this.linkEdge.hashCode());
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
		if (!(obj instanceof ILinkEdge)) {
			return false;
		}
		ILinkEdge other = (ILinkEdge) obj;
		if (this.linkEdge == null) {
			if (other.getGraphElement() != null) {
				return false;
			}
		} else if (!this.linkEdge.equals(other.getGraphElement())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.linkEdge.getLevel();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#getUniqueIndex()
	 */
	@Override
	public long getUniqueIndex() {
		return this.linkEdge.getIndex();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement#isDescendent(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement)
	 */
	@Override
	public boolean isDescendent(IDrawingElement testElement) {
		return this.linkEdge.isDescendent(testElement.getGraphElement());
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.linkEdge);
		buf.append(")");
		return buf.toString();
	}
}