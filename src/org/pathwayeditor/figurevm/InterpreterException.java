/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figurevm;

import org.pathwayeditor.figurevm.Instruction.OpCodes;

/**
 * 
 * InterpreterException
 *
 * @author Stuart Moodie
 *
 */
public class InterpreterException extends RuntimeException {
	private final OpCodes opCode;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7828676909079228740L;

	
	public InterpreterException(OpCodes opCode){
		super("Cannot process instructions for opcode: " + opCode);
		this.opCode = opCode;
	}
	
	public OpCodes getInstruction(){
		return this.opCode;
	}
}
