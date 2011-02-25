package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;

public class GenericFont implements IFont {
	private static final double DEFAULT_SIZE = 12;
	private static final EnumSet<Style> DEFAULT_STYLE = EnumSet.of(Style.NORMAL);
	private final double size;
	private final EnumSet<Style> styles;
	
	public GenericFont(){
		this.size = DEFAULT_SIZE;
		this.styles = EnumSet.copyOf(DEFAULT_STYLE);
	}
	
	public GenericFont(double size, EnumSet<Style> styles){
		this.size = size;
		this.styles = EnumSet.copyOf(styles);
	}
	
	public GenericFont(IFont other) {
		this.size = other.getFontSize();
		this.styles = EnumSet.copyOf(other.getStyle());
	}

	@Override
	public double getFontSize() {
		return this.size;
	}

	@Override
	public EnumSet<Style> getStyle() {
		return EnumSet.copyOf(this.styles);
	}

	@Override
	public IFont newSize(double fontSize) {
		return new GenericFont(fontSize, this.styles);
	}

	@Override
	public IFont newStyle(EnumSet<Style> style) {
		return new GenericFont(this.size, styles);
	}

	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(size=");
		buf.append(this.size);
		buf.append(",styles=");
		buf.append(this.styles);
		buf.append(")");
		return buf.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((this.styles == null) ? 0 : this.styles.hashCode());
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
		if (Double.doubleToLongBits(this.size) != Double.doubleToLongBits(other.size))
			return false;
		if (this.styles == null) {
			if (other.styles != null)
				return false;
		} else if (!this.styles.equals(other.styles))
			return false;
		return true;
	}

}
