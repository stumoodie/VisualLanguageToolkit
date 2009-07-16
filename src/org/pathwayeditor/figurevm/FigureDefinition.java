package org.pathwayeditor.figurevm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.pathwayeditor.figurevm.Instruction.InstructionType;

public final class FigureDefinition implements IFigureDefinition {
	private final List<Instruction> instlist;
	
	public FigureDefinition(List<Instruction> list){
		this.instlist = new ArrayList<Instruction>(list);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figurevm.IFigureDefinition#iterator()
	 */
	public Iterator<Instruction> iterator(){
		return this.instlist.iterator();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figurevm.IFigureDefinition#getBindVariableNames()
	 */
	public Set<String> getBindVariableNames(){
		Set<String> retVal = new TreeSet<String>();
		for(Instruction inst : this.instlist){
			if(inst.getType().equals(InstructionType.BOUND_VALUE)){
				String name = inst.getTypedValue(); 
				retVal.add(name);
			}
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figurevm.IFigureDefinition#size()
	 */
	public int size(){
		return this.instlist.size();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figurevm.IFigureDefinition#get(int)
	 */
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
		if (!(obj instanceof FigureDefinition))
			return false;
		FigureDefinition other = (FigureDefinition) obj;
		if (instlist == null) {
			if (other.instlist != null)
				return false;
		} else if (!instlist.equals(other.instlist))
			return false;
		return true;
	}
}