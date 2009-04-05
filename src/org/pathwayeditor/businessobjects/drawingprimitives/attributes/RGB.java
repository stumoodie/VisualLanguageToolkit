/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

public final class RGB {
	public static final int MAX_COLOUR_VAL = 255;
	public static final int MIN_COLOUR_VAL = 0;
	public static final RGB BLACK = new RGB(0, 0, 0);
	public static final RGB WHITE = new RGB(255, 255, 255);
	public static final RGB RED = new RGB(255, 0, 0);
	public static final RGB GREEN = new RGB(0, 255, 0);
	public static final RGB BLUE = new RGB(0, 0, 255);
	
	private final int red;
	private final int green;
	private final int blue;
	
	public RGB(int red, int green, int blue){
		if(!isValidColourComponent(red)
				|| !isValidColourComponent(green)
				|| !isValidColourComponent(blue)){
			throw new IllegalArgumentException("One or more colour component(s) invalid: red=" + red + ", green=" + green + ", blue=" + blue + ".");
		}
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public static boolean isValidColourComponent(int colour){
		return colour >= MIN_COLOUR_VAL && colour <= MAX_COLOUR_VAL;
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
		return new RGB(this.red, this.green, newBlue);
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(");
		builder.append(this.getRed());
		builder.append(',');
		builder.append(this.getGreen());
		builder.append(',');
		builder.append(this.getBlue());
		builder.append(")");
		return builder.toString();
	}
}
