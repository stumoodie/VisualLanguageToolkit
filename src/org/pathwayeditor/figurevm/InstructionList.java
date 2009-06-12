package org.pathwayeditor.figurevm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class InstructionList {
	private final List<Instruction> instlist;
	
	public InstructionList(List<Instruction> list){
		this.instlist = new ArrayList<Instruction>(list);
	}
	
	public Iterator<Instruction> iterator(){
		return this.instlist.iterator();
	}
	
	
	public int size(){
		return this.instlist.size();
	}
	
	public Instruction get(int index){
		return this.instlist.get(index);
	}
	
	@Override
	public String toString(){
		return this.instlist.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instlist == null) ? 0 : instlist.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof InstructionList))
			return false;
		InstructionList other = (InstructionList) obj;
		if (instlist == null) {
			if (other.instlist != null)
				return false;
		} else if (!instlist.equals(other.instlist))
			return false;
		return true;
	}
}
