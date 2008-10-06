package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

public final class RGB {
	public static final RGB BLACK = new RGB(0, 0, 0);
	public static final RGB WHITE = new RGB(255, 255, 255);
	public static final RGB RED = new RGB(255, 0, 0);
	public static final RGB GREEN = new RGB(0, 255, 0);
	public static final RGB BLUE = new RGB(0, 0, 255);
	
	private final int red;
	private final int green;
	private final int blue;
	
	public RGB(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public RGB newRed(int newRed){
		return new RGB(newRed, this.green, this.blue);
	}
	
	public RGB newGreen(int newGreen){
		return new RGB(this.red, newGreen, this.blue);
	}
	
	public RGB newBlue(int newBlue){
		return new RGB(this.blue, this.green, newBlue);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blue;
		result = prime * result + green;
		result = prime * result + red;
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
		final RGB other = (RGB) obj;
		if (blue != other.blue)
			return false;
		if (green != other.green)
			return false;
		if (red != other.red)
			return false;
		return true;
	}
	
	
}
