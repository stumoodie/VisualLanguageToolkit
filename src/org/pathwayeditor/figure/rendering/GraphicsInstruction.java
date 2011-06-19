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

package org.pathwayeditor.figure.rendering;

import java.util.EnumSet;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;

/**
 *
 * @author Stuart Moodie
 *
 */
public class GraphicsInstruction {
	private final GraphicsOpCode opCode;
	private final Object value;
	
	public GraphicsInstruction(GraphicsOpCode opCode, Object value){
		this.opCode = opCode;
		this.value = value;
	}
	
	public GraphicsOpCode getOpCode(){
		return this.opCode;
	}
	
	public Object getValue(){
		return this.value;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList() {
		return (List<T>)this.value;

	}
	public double[] getDoubleArray() {
		return (double[])this.value;
	}

	public Colour getColour() {
		return (Colour)this.value;
	}

	@SuppressWarnings("unchecked")
	public <E extends Enum<E>> EnumSet<E> getEnumSet() {
		return (EnumSet<E>)this.value;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(opCode=");
		buf.append(opCode);
		buf.append(",value=");
		buf.append(value);
		buf.append(")");
		return buf.toString();
	}

	@SuppressWarnings("unchecked")
	public <T> T getTypedValue() {
		return (T)this.value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.opCode == null) ? 0 : this.opCode.hashCode());
		result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GraphicsInstruction))
			return false;
		GraphicsInstruction other = (GraphicsInstruction) obj;
		if (this.opCode == null) {
			if (other.opCode != null)
				return false;
		} else if (!this.opCode.equals(other.opCode))
			return false;
		if (this.value == null) {
			if (other.value != null)
				return false;
		} else if (!this.value.equals(other.value))
			return false;
		return true;
	}
}
