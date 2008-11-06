package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

public final class Size {
	private final int width;
	private final int height;

	public Size(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Size newWidth(int newWidth){
		return new Size(newWidth, this.height);
	}
	
	public Size newHeight(int newHeight){
		return new Size(this.width, newHeight);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Size other = (Size) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder retVal = new StringBuilder("Size(w=");
		retVal.append(width);
		retVal.append(", h=");
		retVal.append(height);
		retVal.append(")");
		return retVal.toString();
	}
}
