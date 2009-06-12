package org.pathwayeditor.figurevm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pathwayeditor.figurevm.Instruction.InstructionType;
import org.pathwayeditor.figurevm.Instruction.OpCodes;
import org.pathwayeditor.figurevm.Value.ValueType;

public class InstructionFactoryImpl implements IInstructionFactory {
	private final Map<String, OpCodes> opCodeLookup;
	private static final Object[][] opCodeTable = {
		{ "rect", OpCodes.RECT },
		{ "pgon", OpCodes.PGON },
		{ "pline", OpCodes.PLINE },
		{ "rrect", OpCodes.RRECT },
		{ "arc", OpCodes.ARC },
		{ "oval", OpCodes.OVAL },
		{ "line", OpCodes.LINE },
		{ "point", OpCodes.POINT },
		{ "curfontsize", OpCodes.CUR_FONT_SIZE },
		{ "setfontsize", OpCodes.SET_FONT_SIZE },
		{ "setfontstyle", OpCodes.SET_FONT_STYLE },
		{ "curfontstyle", OpCodes.CUR_FONT_STYLE },
		{ "text", OpCodes.TEXT },
		{  "ifelse", OpCodes.IFELSE },
		{ "exit", OpCodes.EXIT },
		{ "grestore", OpCodes.GPOP },
		{ "gsave", OpCodes.GPUSH },
		{ "dup", OpCodes.DUP },
		{ "exch", OpCodes.EXCH },
		{ "and", OpCodes.AND },
		{ "not", OpCodes.NOT },
		{ "or", OpCodes.OR },
		{ "add", OpCodes.ADD },
		{ "sub", OpCodes.SUB },
		{ "mul", OpCodes.MUL },
		{ "div", OpCodes.DIV },
		{ "eq", OpCodes.EQ },
		{ "ne", OpCodes.NE },
		{ "lt", OpCodes.LT },
		{ "gt", OpCodes.GT },
		{ "le", OpCodes.LE },
		{ "ge", OpCodes.GE },
		{ "def", OpCodes.DEF },
		{ "curfillcol", OpCodes.CUR_FILL_COL },
		{ "setfillcol", OpCodes.SET_FILL_COL },
		{ "curlinecol", OpCodes.CUR_LINE_COL },
		{ "setlinecol", OpCodes.SET_LINE_COL },
		{ "texthgt", OpCodes.GETTEXTHEIGHT },
		{ "textlen", OpCodes.GETTEXTLENGTH },
		{ "curlinewidth", OpCodes.CURR_LINE_WIDTH },
		{ "setlinewidth", OpCodes.SET_LINE_WIDTH },
		{ "exit", OpCodes.EXIT },
		{ "if", OpCodes.IF },
		{ "for", OpCodes.FOR },
		{ "forall", OpCodes.FORALL },
		{ "repeat", OpCodes.REPEAT },
		{ "get", OpCodes.GET },
		{ "put", OpCodes.PUT },
		{ "length", OpCodes.LENGTH },
		{ "array", OpCodes.ARRAY },
		{ "sin", OpCodes.SIN },
		{ "cos", OpCodes.COS },
		{ "atan", OpCodes.ATAN },
		{ "sqrt", OpCodes.SQRT },
		{ "ln", OpCodes.LN },
		{ "log", OpCodes.LOG },
		{ "exp", OpCodes.EXP },
		{ "round", OpCodes.ROUND },
		{ "ceil", OpCodes.CEIL },
		{ "floor", OpCodes.FLOOR },
		{ "neg", OpCodes.NEG },
		{ "abs", OpCodes.ABS },
	};
	
	public InstructionFactoryImpl(){
		this.opCodeLookup = new HashMap<String, OpCodes>();
		for(Object[] row : opCodeTable){
			this.opCodeLookup.put((String)row[0], (OpCodes)row[1]);
		}
	}
	
	public Instruction createArray(List<Instruction> instArray) {
		return new Instruction(new InstructionList(instArray));
	}

	public Instruction createBoolean(String value) {
		return new Instruction(new Value(Boolean.valueOf(value)));
	}

	public Instruction createBoundValue(String value) {
		return new Instruction(InstructionType.BOUND_VALUE, value);
	}

	public Instruction createExpandedVarName(String value) {
		return new Instruction(InstructionType.VARIABLE_NAME, value);
	}

	public Instruction createInteger(String value) {
		return new Instruction(new Value(Integer.valueOf(value)));
	}

	public Instruction createInteger(Integer value) {
		return new Instruction(new Value(value));
	}

	public Instruction createNull() {
		return new Instruction(Value.NULL);
	}

	public Instruction createOpCode(String value) {
		OpCodes opCode = this.opCodeLookup.get(value);
		if(opCode == null) throw new IllegalArgumentException("Unrecognised opcode name: " + value);
		return new Instruction(opCode);
	}

	public Instruction createReal(String value) {
		return new Instruction(new Value(Double.valueOf(value)));
	}

	public Instruction createString(String value) {
		return new Instruction(new Value(ValueType.STRING_LITERAL, value));
	}

	public Instruction createUnexpandedVarName(String value) {
		return new Instruction(Value.createLiteralVariableName(value));
	}

	public Instruction createProcedure(List<Instruction> procList) {
		return new Instruction(new Value(new InstructionList(procList)));
	}

}
