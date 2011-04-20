/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
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
