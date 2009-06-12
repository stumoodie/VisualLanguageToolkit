package org.pathwayeditor.figurevm;

import java.util.List;

public interface IInstructionFactory {

	Instruction createReal(String value);
	
	Instruction createInteger(String value);
	
	Instruction createInteger(Integer value);
	
	Instruction createString(String value);
	
	Instruction createExpandedVarName(String value);
	
	Instruction createUnexpandedVarName(String value);
	
	Instruction createBoundValue(String value);
	
	Instruction createOpCode(String value);
	
	Instruction createArray(List<Instruction> instArray);
	
	Instruction createNull();
	
	Instruction createBoolean(String value);

	Instruction createProcedure(List<Instruction> procList);
}
