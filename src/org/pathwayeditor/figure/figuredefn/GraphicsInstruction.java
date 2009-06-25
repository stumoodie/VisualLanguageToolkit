package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;


public class GraphicsInstruction {
	public enum GraphicsOpCode {
		DRAW_RECT, FILL_RECT, DRAW_RRECT, FILL_RRECT, DRAW_OVAL, FILL_OVAL, DRAW_ARC, FILL_ARC,
		DRAW_POLYGON, FILL_POLYGON, DRAW_POLYLINE, DRAW_LINE, DRAW_POINT, DRAW_TEXT, FILL_TEXT,
		FILL_COLOUR, LINE_COLOUR, FONT_STYLE, FONT_SIZE, LINE_WIDTH, LINE_STYLE
	};
	public enum GraphicalTextAlignment { N, NE, E, SE, S, SW, W, NW, C };
	
	private final GraphicsOpCode opCode;
	private final Object value;
	
	public GraphicsInstruction(GraphicsOpCode opCode, Object value){
		this.opCode = opCode;
		this.value = value;
	}
	
	public GraphicsOpCode getOpCode(){
		return this.opCode;
	}
	
	public Object getValue(){
		return this.value;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList() {
		return (List)this.value;

	}
	public double[] getDoubleArray() {
		return (double[])this.value;
	}

	public RGB getRGB() {
		return (RGB)this.value;
	}

	@SuppressWarnings("unchecked")
	public <E extends Enum<E>> EnumSet<E> getEnumSet() {
		return (EnumSet)this.value;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(opCode=");
		buf.append(opCode);
		buf.append(",value=");
		buf.append(value);
		buf.append(")");
		return buf.toString();
	}

	@SuppressWarnings("unchecked")
	public <T> T getTypedValue() {
		return (T)this.value;
	}
}
