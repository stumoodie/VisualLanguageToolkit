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

import java.util.Stack;


/**
 * 
 * ValueStackManager
 *
 * @author Stuart Moodie
 *
 */
public class ValueStackManager {
	private final Stack<Value> stack;
	
	public ValueStackManager(){
		this.stack = new Stack<Value>();
	}
	
	public Value pop(){
		return this.stack.pop();
	}
	
	public Integer popInteger(){
		return (Integer)this.pop().getValue();
	}
	
	public Double popDouble(){
		Object popVal = this.pop().getValue();
		Double retVal = null;
		if(popVal instanceof Integer){
			retVal = ((Integer)popVal).doubleValue();
		}
		else{
			retVal = (Double)popVal;
		}
		return retVal;
	}
	
	public Boolean popBoolean(){
		return (Boolean)this.pop().getValue();
	}
	
	public String popString(){
		Value inst = this.pop();
		return (String)inst.getValue();
	}
	
	public ValueList popArray(){
		Value inst = this.pop();
		return (ValueList)inst.getValue();
	}

	
	public void push(Value value){
		this.stack.push(value);
	}

	public int size(){
		return this.stack.size();
	}


	public void empty() {
		this.stack.empty();
	}

	public Value peek() {
		return this.stack.peek();
	}

	
	@Override
	public String toString(){
		return this.stack.toString();
	}
}