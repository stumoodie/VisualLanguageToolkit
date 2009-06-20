package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;

import org.pathwayeditor.figure.geometry.Dimension;

public class GenericFont implements IFont {
	private static final int DEFAULT_SIZE = 12;
	private static final EnumSet<Style> DEFAULT_STYLE = EnumSet.of(Style.NORMAL);
	private static final int DEFAULT_WIDTH = 6;
	private final int size;
	private final int width;
	private final EnumSet<Style> styles;
	
	public GenericFont(){
		this.size = DEFAULT_SIZE;
		this.width = DEFAULT_WIDTH;
		this.styles = EnumSet.copyOf(DEFAULT_STYLE);
	}
	
	public GenericFont(int size, int width, EnumSet<Style> styles){
		this.size = size;
		this.width = width;
		this.styles = EnumSet.copyOf(styles);
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

	public IFont newSize(int fontSize) {
		return new GenericFont(fontSize, this.width, this.styles);
	}

	public IFont newWidth(int width) {
		return new GenericFont(this.size, width, this.styles);
	}

	public IFont newStyle(EnumSet<Style> style) {
		return new GenericFont(this.size, this.width, styles);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.size;
		result = prime * result + ((this.styles == null) ? 0 : this.styles.hashCode());
		result = prime * result + this.width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GenericFont))
			return false;
		GenericFont other = (GenericFont) obj;
		if (this.size != other.size)
			return false;
		if (this.styles == null) {
			if (other.styles != null)
				return false;
		} else if (!this.styles.equals(other.styles))
			return false;
		if (this.width != other.width)
			return false;
		return true;
	}
}
