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


package org.pathwayeditor.figure.geometry;


/**
 * 
 * Dimension is a class that defines the size of something in terms of its height and width. The class is immutable
 * so it can be passed safely by reference. It provides an number of methods to change the dimension, but these all
 * return a new instance of this class. Just like <code>String</code> be aware of the cost of using these methods. 
 *
 * @author Stuart Moodie
 *
 */
public class Dimension {
	private final double width;
	private final double height;
	
	/**
	 * Constructor taking a width and height.
	 * @param width the width
	 * @param height the height
	 */
	public Dimension(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Resizes this dimension by the specified amount.
	 * @param widthOffset the amount to offset the width.
	 * @param heightOffset the amount to offset the height.
	 * @return a new instance with the resized dimension.
	 */
	public Dimension resize(double widthOffset, double heightOffset){
		return new Dimension(this.width + widthOffset, this.height + heightOffset);
	}
	
	/**
	 * Resizes this dimension by the specified amount.
	 * @param sizeDelta the change in size.
	 * @return a new instance with the resized dimension.
	 */
	public Dimension resize(Dimension sizeDelta){
		return resize(sizeDelta.width, sizeDelta.height);
	}

	/**
	 * Negates each of the width and height dimensions. 
	 * @return a new instance with the dimensions negated.
	 */
	public Dimension negate(){
		return new Dimension(-this.width, -this.height);
	}
	
	/**
	 * Gets the width of this dimension.
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Gets the height of this dimension.
	 * @return the height.
	 */
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

	/**
	 * Creates a new dimension with the new width and the same height as this one.
	 * @param newWidth the new width to use.
	 * @return the new dimension with the new width.
	 */
	public Dimension newWidth(double newWidth) {
		return new Dimension(newWidth, this.height);
	}

	/**
	 * Creates a new dimension with the new height and the same width as this one.
	 * @param newHeight the new height to use.
	 * @return the new dimension with the new height.
	 */
	public Dimension newHeight(double newHeight) {
		return new Dimension(this.width, newHeight);
	}

	/**
	 * Calculate the scaling factor to convert the reference dimension into this dimension.
	 * @param referenceDimension the reference dimension to scale from.
	 * @return the scaling factor required to scale the reference dimension to this one.
	 */
	public Scale calcScalingFactors(Dimension referenceDimension) {
		return new Scale(referenceDimension.width/this.width, referenceDimension.height/this.height);
	}

	/**
	 * Scale this dimension and return the result.
	 * @param scale the scaling factor to use. 
	 * @return a new instance containing the scaled dimensions.
	 */
	public Dimension scale(Scale scale) {
		return new Dimension(this.width * scale.getXfactor(), this.height * scale.getYFactor());
	}
}
