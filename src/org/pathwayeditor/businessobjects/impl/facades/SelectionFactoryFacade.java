/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl.facades;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;

import uk.ac.ed.inf.graph.compound.ISubCompoundGraphFactory;

/**
 * @author Stuart Moodie
 *
 */
public class SelectionFactoryFacade implements ISelectionFactory {
	private ISubCompoundGraphFactory subCompoundGraphFactory;
	
	public SelectionFactoryFacade(ISubCompoundGraphFactory subCompoundGraphFactory){
		this.subCompoundGraphFactory = subCompoundGraphFactory;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#addDrawingNode(org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNode)
	 */
	@Override
	public void addDrawingNode(IDrawingNode selectedNode) {
		this.subCompoundGraphFactory.addElement(selectedNode.getGraphElement());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#addLink(org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge)
	 */
	@Override
	public void addLink(ILinkEdge selectedLink) {
		this.subCompoundGraphFactory.addElement(selectedLink.getGraphElement());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#createGeneralSelection()
	 */
	@Override
	public IDrawingElementSelection createGeneralSelection() {
		return new DrawingElementSelectionFacade(this.subCompoundGraphFactory.createPermissiveInducedSubgraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#createEdgeExcludedSelection()
	 */
	@Override
	public IDrawingElementSelection createEdgeExcludedSelection() {
		return new DrawingElementSelectionFacade(this.subCompoundGraphFactory.createInducedSubgraph());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory#getSubgraphFactory()
	 */
	@Override
	public ISubCompoundGraphFactory getSubgraphFactory() {
		return this.subCompoundGraphFactory;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.subCompoundGraphFactory == null) ? 0 : this.subCompoundGraphFactory.hashCode());
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
		if (!(obj instanceof ISelectionFactory)) {
			return false;
		}
		ISelectionFactory other = (ISelectionFactory) obj;
		if (this.subCompoundGraphFactory == null) {
			if (other.getSubgraphFactory() != null) {
				return false;
			}
		} else if (!this.subCompoundGraphFactory.equals(other.getSubgraphFactory())) {
			return false;
		}
		return true;
	}


	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append("element=");
		buf.append(this.subCompoundGraphFactory);
		buf.append(")");
		return buf.toString();
	}
}
