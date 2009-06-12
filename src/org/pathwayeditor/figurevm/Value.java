package org.pathwayeditor.figurevm;


public final class Value {
	public static enum ValueType { REAL, INTEGER, STRING_LITERAL, LITERAL_VAR_NAME, BOOLEAN, ARRAY, NULL, PROCEDURE }

	public static final Value NULL = new Value();
	
	private final Object value;
	private final ValueType type;
	
	public static Value createStringLiteral(String value){
		return new Value(ValueType.STRING_LITERAL, value);
	}
	
	public static Value createLiteralVariableName(String value){
		return new Value(ValueType.LITERAL_VAR_NAME, value);
	}
	
	public Value(){
		this.type = ValueType.NULL;
		this.value = null;
	}
	
	public Value(ValueType type, String stringValue){
		this.type = type;
		this.value = stringValue;
	}
	
	public Value(Integer value){
		this.type = ValueType.INTEGER;
		this.value = value;
	}
	
	public Value(Boolean value){
		this.type = ValueType.BOOLEAN;
		this.value = value;
	}
	
	public Value(Double value){
		this.type = ValueType.REAL;
		this.value = value;
	}
	
	public Value(InstructionList value){
		this.type = ValueType.PROCEDURE;
		this.value = value;
	}
	
	public Value(ValueList value){
		this.type = ValueType.ARRAY;
		this.value = value;
	}
	
	public Value(Value otherVal){
		this.type = otherVal.type;
		// all other types are immutable so straight copy will do
		this.value = otherVal.value;
	}
	
	public ValueType getType(){
		return this.type;
	}
	
	public Object getValue(){
		return this.value;
	}
	
	public Integer getInteger(){
		return (Integer)this.value;
	}
	
	public Double getDouble(){
		Object popVal = this.value;
		Double retVal = null;
		if(popVal instanceof Integer){
			retVal = ((Integer)popVal).doubleValue();
		}
		else{
			retVal = (Double)popVal;
		}
		return retVal;
	}
	
	public Boolean getBoolean(){
		return (Boolean)this.value;
	}
	
	public String getStringLiteral(){
		return (String)this.value;
	}
	
	public String getLiteralVariableName(){
		return (String)this.value;
	}
	
	public ValueList getArray(){
		return (ValueList)this.value;
	}
	
	public InstructionList getPackedArray(){
		return (InstructionList)this.value;
	}
	
	public boolean isNull(){
		return this.value == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Value))
			return false;
		Value other = (Value) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(type=");
		buf.append(this.type);
		buf.append(", value=");
		buf.append(this.value);
		buf.append(")");
		return buf.toString();
	}
}
