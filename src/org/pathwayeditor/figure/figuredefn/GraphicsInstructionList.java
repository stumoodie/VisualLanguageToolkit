package org.pathwayeditor.figure.figuredefn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GraphicsInstructionList {
	private final List<GraphicsInstruction> list;
	
	public GraphicsInstructionList(List<GraphicsInstruction> list){
		this.list = new ArrayList<GraphicsInstruction>(list);
	}
	
	public boolean contains(Object o) {
		return list.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public GraphicsInstruction get(int index) {
		return list.get(index);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Iterator<GraphicsInstruction> iterator() {
		return list.iterator();
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public ListIterator<GraphicsInstruction> listIterator() {
		return list.listIterator();
	}

	public ListIterator<GraphicsInstruction> listIterator(int index) {
		return list.listIterator(index);
	}

	public int size() {
		return list.size();
	}

	public List<GraphicsInstruction> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GraphicsInstructionList))
			return false;
		GraphicsInstructionList other = (GraphicsInstructionList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return this.list.toString();
	}
}
