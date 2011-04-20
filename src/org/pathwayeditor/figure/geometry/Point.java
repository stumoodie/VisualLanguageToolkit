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
 * Point is a class representing a point in 2D Cartesian space. It provides methods for querying and carrying out
 * simple geometric calculations. The class is immutable.
 *
 * @author Stuart Moodie
 *
 */
public class Point {
	/**
	 * The origin of Cartesian space.  
	 */
	public static final Point ORIGIN = new Point(0, 0);
	
	/**
	 * Create a point from a polar coordinate.
	 * @param r the radius, which should be non-negative.
	 * @param theta the angle of the point in radians.
	 * @return a new point corresponding to the polar coordinates.  
	 */
	public static Point createFromPolar(double r, double theta){
		double x = r * Math.cos(theta);
		double y = r * Math.sin(theta);
		return new Point(x, y);
	}
	private final double x;
	
	private final double y;

	/**
	 * Constructs a point from carterisan coordinates.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	/**
	 * The difference between this point another. This returns the displacement require to translate this
	 * point to the other point.
	 * @param t the other point that the difference is calculated from.
	 * @return a new point containing the displacement from this point to the refernece point.
	 */
	public Point difference(Point t){
		return new Point(t.getX()-x, t.getY()-y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	/**
	 * Get the distance from this point to another point.
	 * @param other the other point to calculate the distance to.
	 * @return the distance, which must be a non-zero number.
	 */
	public double getDistance(Point other) {
		return Math.sqrt(this.getSqrDistance(other));
	}
	
	/**
	 * Get the square distance from this point to the other point. This is a 
	 * quicker operation than {@link #getDistance(Point)} as it avoids an expensive
	 * square root calculation. Useful if you are comparing distances and don't care
	 * about the exact value.
	 * @param other the point to measure the distance to.
	 * @return the distance to the other point squared.
	 */
	public double getSqrDistance(Point other) {
		double xDist = this.x - other.getX();
		double yDist = this.getY() - other.getY();
		return xDist * xDist + yDist * yDist;
	}

	/**
	 * Get the x coordinate of the point.
	 * @return the x coordinate.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y coordinate of the point.
	 * @return the y coordinate.
	 */
	public double getY() {
		return y;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Creates a new point that is the negation of this one, i.e. the coordinates are multiplied by -1. 
	 * @return a new point that is the negation of this one. 
	 */
	public Point negate(){
		return new Point(-this.x, -this.y);
	}

	private Point newOrigin(double x, double y){
		return new Point(this.x - x, this.y - y);
	}

	/**
	 * Creates a new point with its position calculated relative to a new coordinate space specified by
	 * the given origin. This is equivalent to calculating the vector between the specified origin and this point.
	 * @param p the new origin, specified in the same coordinate space as used by this point.
	 * @return the new point with a position relative to the new origin.
	 */
	public Point newOrigin(Point p){
		return newOrigin(p.x, p.y);
	}
	
	/**
	 * Create a new point with the new x coordinate.
	 * @param x the new x coordinate.
	 * @return a new point with the new x coordinate.
	 */
	public Point newX(double x) {
		return new Point(x, this.y);
	}
	
	/**
	 * Create a new point with the new y coordinate.
	 * @param y the new y coordinate.
	 * @return a new point with the new y coordinate.
	 */
	public Point newY(double y) {
		return new Point(this.x, y);
	}

	/**
	 * Creates a new point with the position of the current point after it has been scales by the specified amount.
	 * The scaling process effectively scales the coordinate space of this point and then reports the new point position
	 * in the current (pre-scaled) coordinate space.  
	 * @param scale the scaling factor to apply.
	 * @return a new point containing the position of the point after scaling. 
	 */
	public Point scale(Scale scale) {
		return new Point(this.x * scale.getXfactor(), this.y * scale.getYFactor());
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append('(');
		builder.append(this.x);
		builder.append(",");
		builder.append(this.y);
		builder.append(')');
		return builder.toString();
	}

	/**
	 * Create a new point translated by the specified amount. 
	 * @param x the x displacement.
	 * @param y the y displacement.
	 * @return an new point displaced by the given amount.
	 */
	public Point translate(double x, double y) {
		return new Point(this.x + x, this.y + y);
	}

	/**
	 * Create a new point translated by the specified amount. 
	 * @param t the displacement expressed as a point.
	 * @return an new point displaced by the given amount.
	 */
	public Point translate(Point t) {
		return new Point(this.x + t.getX(), this.y + t.getY());
	}
}
