/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;
import java.util.Set;

/**
 * @author smoodie
 *
 */
public class BusinessObjectSetIterator<T> implements Iterator<T> {
	private final Iterator<? extends IBusinessObjectData<T>> iterator;
	
	public BusinessObjectSetIterator(Set<? extends IBusinessObjectData<T>> boList){
		this.iterator = boList.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public T next() {
		IBusinessObjectData<T> nextVal = this.iterator.next();
		return nextVal.getBusinessObject();
	}

	public void remove() {
		this.iterator.remove();
	}

}
