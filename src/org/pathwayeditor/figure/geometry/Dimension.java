package org.pathwayeditor.figure.geometry;

public final class Dimension {
	private final double width;
	private final double height;
	
	public Dimension(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	public Dimension resize(double widthOffset, double heightOffset){
		return new Dimension(this.width + widthOffset, this.height + heightOffset);
	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Dimension))
			return false;
		Dimension other = (Dimension) obj;
		if (Double.doubleToLongBits(height) != Double
				.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(width) != Double
				.doubleToLongBits(other.width))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(w=");
		buf.append(this.width);
		buf.append(",h=");
		buf.append(this.height);
		buf.append(")");
		return buf.toString();
	}

	public Dimension expand(double currentLineWidth, double currentLineHeight) {
		double newWidth = this.width + currentLineWidth;
		double newHeight = this.height + currentLineHeight;
		return new Dimension(newWidth, newHeight);
	}

	public Dimension shrink(double currentLineWidth, double currentLineHeight) {
		double newWidth = this.width - currentLineWidth;
		double newHeight = this.height - currentLineHeight;
		if(newWidth < 0 || newHeight < 0) throw new IllegalArgumentException("Cannot make a diemntion nagative");
		return new Dimension(newWidth, newHeight);
	}
}
