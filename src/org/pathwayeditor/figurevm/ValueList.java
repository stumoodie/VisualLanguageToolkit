package org.pathwayeditor.figurevm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class ValueList {
	private final List<Value> valueList;
	
	public ValueList(List<Value> valueList){
		this.valueList = new ArrayList<Value>(valueList);
	}
	
	public ValueList(int arrayLen) {
		this.valueList = new ArrayList<Value>(arrayLen);
		// fill with nulls
		for(int i = 0; i < arrayLen; i++){
			this.valueList.add(new Value());
		}
	}

	public Value get(int index){
		return this.valueList.get(index);
	}
	
	public Iterator<Value> iterator(){
		return this.valueList.iterator();
	}
	
	public List<Double> getDoubleList(){
		Iterator<Value> iter = valueList.iterator();
		List<Double> retVal = new ArrayList<Double>(valueList.size());
		while(iter.hasNext()){
			Value val = iter.next();
			retVal.add(val.getDouble());
		}
		return retVal;
	}
	
	public List<Integer> getIntegerList(){
		Iterator<Value> iter = valueList.iterator();
		List<Integer> retVal = new ArrayList<Integer>(valueList.size());
		while(iter.hasNext()){
			Value val = iter.next();
			retVal.add(val.getInteger());
		}
		return retVal;
	}
	
	public int size(){
		return this.valueList.size();
	}
	
	public boolean isEmpty(){
		return this.valueList.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((valueList == null) ? 0 : valueList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ValueList))
			return false;
		ValueList other = (ValueList) obj;
		if (valueList == null) {
			if (other.valueList != null)
				return false;
		} else if (!valueList.equals(other.valueList))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return this.valueList.toString();
	}

	public boolean contains(Object o) {
		return valueList.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return valueList.containsAll(c);
	}

	public int indexOf(Object o) {
		return valueList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return valueList.lastIndexOf(o);
	}

	public ListIterator<Value> listIterator() {
		return valueList.listIterator();
	}

	public ListIterator<Value> listIterator(int index) {
		return valueList.listIterator(index);
	}

	public List<Value> subList(int fromIndex, int toIndex) {
		return valueList.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return valueList.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return valueList.toArray(a);
	}

	public ValueList put(int idx, Value val) {
		ValueList retVal = new ValueList(this.valueList);
		retVal.valueList.set(idx, val);
		return retVal;
	}
}
