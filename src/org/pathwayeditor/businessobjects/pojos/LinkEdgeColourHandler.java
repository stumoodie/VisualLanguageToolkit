/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import uk.ed.inf.graph.colour.IEdgeColourHandler;
import uk.ed.inf.graph.compound.impl.CompoundEdge;
import uk.ed.inf.graph.compound.impl.CompoundNode;

/**
 * @author smoodie
 *
 */
public class LinkEdgeColourHandler implements IEdgeColourHandler<CompoundNode, CompoundEdge> {
//	private CompoundEdge edge;
	private Link link;
	
	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.colour.IEdgeColourHandler#copyColour(uk.ed.inf.graph.basic.IBasicEdge)
	 */
	public Link copyColour(CompoundEdge newEdge) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.colour.IEdgeColourHandler#createCopy()
	 */
	public IEdgeColourHandler<CompoundNode, CompoundEdge> createCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.colour.IEdgeColourHandler#getColour()
	 */
	public Link getColour() {
		return this.link;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.colour.IEdgeColourHandler#getEdge()
	 */
	public CompoundEdge getEdge() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.colour.IEdgeColourHandler#setColour(java.lang.Object)
	 */
	public void setColour(Object colour) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see uk.ed.inf.graph.colour.IEdgeColourHandler#setEdge(uk.ed.inf.graph.basic.IBasicEdge)
	 */
	public void setEdge(CompoundEdge edge) {
		// TODO Auto-generated method stub
		
	}

}
