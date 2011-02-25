/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figurevm;

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