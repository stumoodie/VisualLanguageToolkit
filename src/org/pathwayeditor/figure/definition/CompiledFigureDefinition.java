/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.definition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * 
 * CompiledFigureDefinition is a class that implements a compiled figure definition. The class is immutable so
 * can be passed by reference without breaking an objects encapsulation.
 *
 * @author Stuart Moodie
 *
 */
public final class CompiledFigureDefinition implements ICompiledFigureDefinition {
	private final List<Instruction> instlist;
	
	public CompiledFigureDefinition(List<Instruction> list){
		this.instlist = new ArrayList<Instruction>(list);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinition#iterator()
	 */
	@Override
	public Iterator<Instruction> iterator(){
		return this.instlist.iterator();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinition#getBindVariableNames()
	 */
	@Override
	public Set<String> getBindVariableNames(){
		Set<String> retVal = new TreeSet<String>();
		for(Instruction inst : this.instlist){
			if(inst.getType().equals(InstructionType.BOUND_VALUE)){
				String name = inst.getTypedValue(); 
				retVal.add(name);
			}
			else if(inst.getType().equals(InstructionType.RAW_ARRAY)){
				ICompiledFigureDefinition procList = inst.getTypedValue();
				retVal.addAll(procList.getBindVariableNames());
			}
			else if(inst.getType().equals(InstructionType.VALUE)){
				Value val = inst.getTypedValue();
				if(val.getType().equals(ValueType.PROCEDURE)){
					ICompiledFigureDefinition procList = val.getPackedArray();
					retVal.addAll(procList.getBindVariableNames());
				}
			}
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinition#size()
	 */
	@Override
	public int numInstructions(){
		return this.instlist.size();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinition#get(int)
	 */
	@Override
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
		if (!(obj instanceof CompiledFigureDefinition))
			return false;
		CompiledFigureDefinition other = (CompiledFigureDefinition) obj;
		if (instlist == null) {
			if (other.instlist != null)
				return false;
		} else if (!instlist.equals(other.instlist))
			return false;
		return true;
	}
}
