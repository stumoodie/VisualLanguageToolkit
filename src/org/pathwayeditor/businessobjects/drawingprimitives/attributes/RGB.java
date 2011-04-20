/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * RGB A utility class to handle an RGB colour representation. The class is immutable
 * so methods that appear mutable actually return a new instance of the class. This
 * means that you can pass value of this class without fear of breaking encapsulation.
 * The RGB model defines 3 colour component, red, green and blue and defines a value
 * in the range 0-256 for each. This gives a palette of approx 16M colours.  
 * 
 * @author Stuart Moodie
 *
 */

public final class RGB {
	/**
	 * The maximum colour value for an individual colour component, i.e. red, green or blue.
	 */
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
	
	/**
	 * Constructor.
	 * @param red the red component (0-255).
	 * @param green the green component (0-255).
	 * @param blue the blue component (0-255).
	 * @throws IllegalArgumentException if a colour component is out of range.
	 */
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
	
	/**
	 * Tests if a colour component value is within the correct range.
	 * @param colour the colour component value to test.
	 * @return true if its in range false otherwise.
	 */
	public static boolean isValidColourComponent(int colour){
		return colour >= MIN_COLOUR_VAL && colour <= MAX_COLOUR_VAL;
	}
	
	/**
	 * Get the red colour component.
	 * @return the red colour component.
	 */
	public int getRed() {
		return red;
	}
	
	/**
	 * Get the green colour component.
	 * @return the green colour component.
	 */
	public int getGreen() {
		return green;
	}
	
	/**
	 * Get the blue colour component.
	 * @return the blue colour component.
	 */
	public int getBlue() {
		return blue;
	}
	
	/**
	 * Create a new instance with a new value for the red component.
	 * @param newRed the new red component value.
	 * @return the new instance with the new red component. 
	 */
	public RGB newRed(int newRed){
		return new RGB(newRed, this.green, this.blue);
	}
	
	/**
	 * Create a new instance with a new value for the green component.
	 * @param newGreen the new green component value.
	 * @return the new instance with the new green component. 
	 */
	public RGB newGreen(int newGreen){
		return new RGB(this.red, newGreen, this.blue);
	}
	
	/**
	 * Create a new instance with a new value for the blue component.
	 * @param newBlue the new green component value.
	 * @return the new instance with the new blue component. 
	 */
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
