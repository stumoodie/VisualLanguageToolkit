/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos.graph;

import java.util.Iterator;

/**
 * @author smoodie
 *
 */
public class IterationCaster<S, T> implements Iterator<S> {
	private final Iterator<T> origIterator;
	
	public IterationCaster(Iterator<T> origIterator){
		this.origIterator = origIterator;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return this.origIterator.hasNext();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@SuppressWarnings("unchecked")
	public S next() {
		T retVal = this.origIterator.next();
		return (S)retVal;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		this.origIterator.remove();
	}

}
