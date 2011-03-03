/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
tree grammar TreeDrawingVm;

options {
	tokenVocab=DrawingVm;
	ASTLabelType=CommonTree;
}

@header{

package org.pathwayeditor.figurevm;

import java.util.LinkedList;

}

@members {

private static final String JUMP_OP_CODE_NAME = "jump";
private IInstructionFactory instFact;
private List<Instruction> allInstructions = new LinkedList<Instruction>();

public void setInstructionFactory(IInstructionFactory fact){
    this.instFact = fact;
}

public IInstructionFactory getInstructionFactory(){
    return this.instFact;
}

public ICompiledFigureDefinition getInstructions(){
    return new CompiledFigureDefinition(this.allInstructions);
}

//private void addInstructions(List<Instruction> insts){
//    this.allInstructions.addAll(insts);
//}

private void addInstruction(Instruction inst){
    this.allInstructions.add(inst);
}

}


shapeDefn
	:	( d=object { addInstruction($d.inst); } )+
	{ addInstruction(instFact.createOpCode("exit")); }
	;
	
object returns [ Instruction inst ]
	:	^(UNEXPANDED_VAR_NAME NAME_VALUE)
	{ $inst = instFact.createUnexpandedVarName($NAME_VALUE.text); }
	|	^(EXPANDED_VAR_NAME NAME_VALUE)
	{ $inst = instFact.createExpandedVarName($NAME_VALUE.text); }
	|	^(BOUND_VALUE NAME_VALUE)
	{ $inst = instFact.createBoundValue($NAME_VALUE.text); }
	|	REAL
	{ $inst = instFact.createReal($REAL.text); }
	|	INTEGER
	{ $inst = instFact.createInteger($INTEGER.text); }
	|	BOOLEAN
	{ $inst = instFact.createBoolean($BOOLEAN.text); }
	|	NULL
	{ $inst = instFact.createNull(); }
	|	^(ARRAY array)
	{ $inst = $array.inst; }
	|	proc
	{ $inst = $proc.inst; }
	|	STRING_LITERAL
	{ 
		String text = $STRING_LITERAL.text; text = text.substring(1, text.length()-1);
	   	$inst = instFact.createString(text);
	}
	|	OPCODE
	{
		$inst = instFact.createOpCode($OPCODE.text);
	}
	;

array returns [ Instruction inst ]
	scope {
		List<Instruction> arrayList;
	}
	@init { 
		$array::arrayList = new LinkedList<Instruction>();
	}
	:	( e=object { $array::arrayList.add($e.inst); } )*
	{ $inst = instFact.createArray($array::arrayList); }
	;


proc returns [ Instruction inst ]
	scope {
		List<Instruction> procList;
	}
	@init { $proc::procList = new LinkedList<Instruction>(); }
	:	^(PROCEDURE ( e=object { $proc::procList.add($e.inst); } )* )
	{ $inst = instFact.createProcedure($proc::procList); }
	;
