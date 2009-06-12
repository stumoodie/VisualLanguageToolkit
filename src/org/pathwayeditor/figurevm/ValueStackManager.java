package org.pathwayeditor.figurevm;

import java.util.Stack;


public class ValueStackManager {
	private final Stack<Value> stack;
//	private final Map<String, Instruction> variablelookup;
//	private final Map<String, Instruction> bindinglookup;
	
	public ValueStackManager(){ //Map<String, Instruction> variableLookup, Map<String, Instruction> bindingLookup){
		this.stack = new Stack<Value>();
//		this.variablelookup = variableLookup;
//		this.bindinglookup = bindingLookup;
	}
	
//	public ValueStackManager(InstructionList instrList, Map<String, Instruction> variableLookup, Map<String, Instruction> bindingLookup){
//		this(variableLookup, bindingLookup);
//		Iterator<Instruction> listIter = instrList.iterator();
//		while(listIter.hasNext()){
//			Instruction inst = listIter.next();
//			this.stack.insertElementAt(inst, 0);
//		}
//	}
	
//	private Instruction getExpandedInstruction(Instruction retVal){
//		if(retVal.getType().equals(InstructionType.BOUND_VALUE)){
//			Instruction lookup = this.bindinglookup.get((String)retVal.getValue());
//			if(lookup == null) throw new IllegalStateException("No binding found for name: " + retVal.getValue());
//			retVal = lookup;
//		}
//		else if(retVal.getType().equals(InstructionType.NAME)){
//			Instruction lookup = this.variablelookup.get((String)retVal.getValue());
//			if(lookup == null) throw new IllegalStateException("No variable name found matching: " + retVal.getValue());
//			retVal = lookup;
//		}
//		return retVal;
//	}
	
	public Value pop(){
		return this.stack.pop();
		//return getExpandedInstruction(this.stack.pop());
//		Instruction retVal = this.stack.pop();
//		if(retVal.getType().equals(InstructionType.BOUND_VALUE)){
//			Instruction lookup = this.bindinglookup.get((String)retVal.getValue());
//			if(lookup == null) throw new IllegalStateException("No binding found for name: " + retVal.getValue());
//			retVal = lookup;
//		}
//		else if(retVal.getType().equals(InstructionType.NAME)){
//			Instruction lookup = this.variablelookup.get((String)retVal.getValue());
//			if(lookup == null) throw new IllegalStateException("No variable name found matching: " + retVal.getValue());
//			retVal = lookup;
//		}
//		return retVal;
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