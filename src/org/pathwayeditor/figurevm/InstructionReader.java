package org.pathwayeditor.figurevm;

import java.util.Iterator;

public class InstructionReader implements Iterator<Instruction> {
	private final Iterator<Instruction> instIter;
	
	public InstructionReader(Iterator<Instruction> iter){
		this.instIter = iter;
	}
	
	public void skip(int numToSkip){
		while(numToSkip > 0){
			instIter.next();
			numToSkip--;
		}
	}
	
	@Override
	public boolean hasNext() {
		return instIter.hasNext();
	}

	@Override
	public Instruction next() {
		return instIter.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Removal not supported"); 
	}

	public void finish() {
		while(this.instIter.hasNext()){
			this.instIter.next();
		}
	}

}
