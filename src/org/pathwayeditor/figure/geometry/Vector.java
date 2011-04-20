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
 * Vector is a class that represents a vector. In addition to accessor methods it provides simple vector
 * geometric operations such as the cross and scalar products of 2 vectors. The class is immutable.  
 * 
 * @author Stuart Moodie
 *
 */
public class Vector {
	private final double i;
	private final double j;
	private final double k;
	
	
	/**
	 * Create a new vector with the specified components.
	 * @param i the i component.
	 * @param j the j component.
	 * @param k the k component.
	 */
	public Vector(double i, double j, double k){
		this.i = i;
		this.j = j;
		this.k = k;
	}
	
	/**
	 * Creates a new vector that is the cross product of this vector and another vector.
	 * @param other the other vector to use in the calculation.
	 * @return a new vector that is the cross product result.
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(this.j * other.getKMagnitude() - this.k * other.getJMagnitude(),
							this.k * other.getIMagnitude() - this.i * other.getKMagnitude(),
							this.i * other.getJMagnitude() - this.j * other.getIMagnitude());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(this.i) != Double.doubleToLongBits(other.i))
			return false;
		if (Double.doubleToLongBits(this.j) != Double.doubleToLongBits(other.j))
			return false;
		if (Double.doubleToLongBits(this.k) != Double.doubleToLongBits(other.k))
			return false;
		return true;
	}
	
	public double getIMagnitude(){
		return i;
	}
	
	public double getJMagnitude(){
		return j;
	}

	public double getKMagnitude(){
		return k;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.i);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.j);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.k);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Calculates the magnitude of the vector.
	 * @return the vector magnitude.
	 */
	public double magnitude() {
		return Math.sqrt(i*i + j*j + k*k);
	}

	/**
	 * Calculates the scalar product of this vector and another vector. 
	 * @param other the other vector in the calcualtion.
	 * @return the scalar product.
	 */
	public double scalarProduct(Vector other) {
		return this.i * other.getIMagnitude() + this.j * other.getJMagnitude() + this.k + other.getKMagnitude();
	}

	/**
	 * Creates a new vector scaled so it has a magnitude commensurate with the scale factor. 
	 * @param scaleFactor the scaleFactor which must be a positive nonzero number. 
	 * @return a new vector scaled relative to this one.
	 */
	public Vector scale(double scaleFactor) {
		return new Vector(scaleFactor * this.getIMagnitude(), scaleFactor * this.getJMagnitude(), 0.0);
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(");
		builder.append(this.i);
		builder.append(",");
		builder.append(this.j);
		builder.append(",");
		builder.append(this.k);
		builder.append(")");
		return builder.toString();
	}
	

	/**
	 * Creates a new vector that is the result of the translation of this vector by the specified displacement.
	 * @param translation the displacement to apply to this vector.
	 * @return a new vector translated relative to this one.
	 */
	public Vector translate(Vector translation) {
		return new Vector(this.i + translation.getIMagnitude(),
				this.j + translation.getJMagnitude(),
				this.k + translation.getKMagnitude());
	}

	/**
	 * Creates a new vector that is the unit vector of this one, i.e. it is scaled to have a magnitude of 1. 
	 * @return a new unit vector of this vector. 
	 */
	public Vector unitVector() {
		double magnitude = this.magnitude();
		return new Vector(this.i/magnitude, this.j/magnitude, this.k/magnitude);
	}
}
