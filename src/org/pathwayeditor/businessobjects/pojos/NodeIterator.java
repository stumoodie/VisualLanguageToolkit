/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import uk.ed.inf.graph.compound.impl.CompoundNode;

/**
 * @author smoodie
 *
 */
public class NodeIterator<F, D> implements Iterator<F> {
	private final Iterator<CompoundNode> iter;
	
	public NodeIterator(Iterator<CompoundNode> iter){
		this.iter = iter;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return iter.hasNext();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@SuppressWarnings("unchecked")
	public F next() {
		CompoundNode nextNode = iter.next();
		BoMapping<F, D> retVal = (BoMapping<F, D>)nextNode.getColourHandler().getColour();
		return retVal.getBoFacade();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new UnsupportedOperationException("remove not implemented in this iterator");
	}

}
