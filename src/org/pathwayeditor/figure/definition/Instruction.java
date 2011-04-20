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

/**
 * 
 * Instruction is a class that defines an instruction used in the interpretation of a figure definition. The instruction can be an
 * opcode, a variable name, a bind variable value, or an array. The instruction is immutable so can be passed by reference without
 * fear of breaking encapsulation.
 *
 * @author Stuart Moodie
 *
 */
public class Instruction {
	private final InstructionType type;
	private final Object value;
	
	public Instruction(Value number){
		this.type = InstructionType.VALUE;
		this.value = number;
	}
	
	public Instruction(InstructionType type, String text){
		this.type = type;
		this.value = text;
	}
	
	public Instruction(OpCodes opCode){
		this.type = InstructionType.OPCODE;
		this.value = opCode;
	}

	public Instruction(ICompiledFigureDefinition rawArrayList){
		this.type = InstructionType.RAW_ARRAY;
		this.value = rawArrayList;
	}
	
	public Instruction(Instruction orig) {
		this.type = orig.type;
		// all types are immuatable so this is safe
		this.value = orig.value;
	}

	public InstructionType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	public <T> T getTypedValue(){
		return (T)value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Instruction))
			return false;
		Instruction other = (Instruction) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append('(');
		builder.append("type=");
		builder.append(this.type);
		builder.append(", value=");
		builder.append(this.value);
		builder.append(')');
		return builder.toString();
	}
}
