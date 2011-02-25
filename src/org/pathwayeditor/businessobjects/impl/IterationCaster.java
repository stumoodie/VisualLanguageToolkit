/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;

/**
 * @author Stuart Moodie
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
	@Override
	public boolean hasNext() {
		return this.origIterator.hasNext();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public S next() {
		T retVal = this.origIterator.next();
		return (S)retVal;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		this.origIterator.remove();
	}

}
