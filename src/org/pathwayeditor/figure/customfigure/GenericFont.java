package org.pathwayeditor.figure.customfigure;

import java.util.EnumSet;

import org.pathwayeditor.figure.geometry.Dimension;

public class GenericFont implements IFont {
	private static final int DEFAULT_SIZE = 12;
	private static final EnumSet<Style> DEFAULT_STYLE = EnumSet.of(Style.NORMAL);
	private static final int DEFAULT_WIDTH = 6;
	private int size;
	private int width;
	private EnumSet<Style> styles;
	
	public GenericFont(){
		this.size = DEFAULT_SIZE;
		this.width = DEFAULT_WIDTH;
		this.styles = EnumSet.copyOf(DEFAULT_STYLE);
	}
	
	public GenericFont(IFont other) {
		this.size = other.getFontSize();
		this.width = other.getFontWidth();
		this.styles = EnumSet.copyOf(other.getStyle());
	}

	public int getFontSize() {
		return this.size;
	}

	public Dimension getStringExtends(String str) {
		return new Dimension(width * str.length(), size);
	}

	public EnumSet<Style> getStyle() {
		return EnumSet.copyOf(this.styles);
	}

	public void setSize(int fontSize) {
		this.size = fontSize;
	}

	public void setStyle(EnumSet<Style> style) {
		this.styles = EnumSet.copyOf(styles);
	}

	public int getFontWidth() {
		return this.width;
	}

	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(size=");
		buf.append(this.size);
		buf.append(",width=");
		buf.append(this.width);
		buf.append(",styles=");
		buf.append(this.styles);
		buf.append(")");
		return buf.toString();
	}
}
