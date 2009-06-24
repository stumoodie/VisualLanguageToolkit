package org.pathwayeditor.figurevm;


public class Instruction {
	public enum InstructionType { VALUE, OPCODE, VARIABLE_NAME, BOUND_VALUE, RAW_ARRAY };
	
	public enum OpCodes { RRECT, RECT, POINT, PLINE, PGON, OVAL, ARC, LINE,
		CUR_FONT_SIZE, SET_FONT_SIZE, SET_FONT_STYLE, CUR_FONT_STYLE, TEXT,
		IFELSE, EQ, NE, LT, GT, LE, GE, SET_FILL_COL, DEF, CUR_FILL_COL,
		CUR_LINE_COL, SET_LINE_COL, EXIT, GPOP, GPUSH, DUP, EXCH,
		ADD, SUB, MUL, DIV, AND, OR, NOT, GETTEXTLENGTH, GETTEXTHEIGHT, CURR_LINE_WIDTH,
		SET_LINE_WIDTH, IF, FOR, FORALL, REPEAT, GET, PUT, LENGTH, ARRAY, SIN, COS, ATAN,
		SQRT, LN, LOG, EXP, ROUND, CEIL, FLOOR, NEG, ABS, CUR_BOUNDS, ANCHOR }

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

	public Instruction(IFigureDefinition rawArrayList){
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
