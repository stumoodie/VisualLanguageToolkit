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
 * Scale is a class that represents scale factors. The class is immutable so can be passed by reference without
 * fear of breaking encapsulation. 
 * 
 * @author Stuart Moodie
 *
 */
public class Scale {
	private final double xfactor;
	private final double yFactor;
	
	/**
	 * Constructs a new instance with the given scale factors.
	 * @param xFactor the x scale factor.
	 * @param yFactor the y scale factor.
	 */
	public Scale(double xFactor, double yFactor){
		this.xfactor = xFactor;
		this.yFactor = yFactor;
	}

	/**
	 * Get the x scale factor. 
	 * @return the x scale factor.
	 */
	public double getXfactor() {
		return this.xfactor;
	}

	/**
	 * Get the y scale factor. 
	 * @return the y scale factor.
	 */
	public double getYFactor() {
		return this.yFactor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.xfactor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.yFactor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Scale))
			return false;
		Scale other = (Scale) obj;
		if (Double.doubleToLongBits(this.xfactor) != Double.doubleToLongBits(other.xfactor))
			return false;
		if (Double.doubleToLongBits(this.yFactor) != Double.doubleToLongBits(other.yFactor))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(xFactor=");
		buf.append(this.xfactor);
		buf.append(",yfactor=");
		buf.append(this.yFactor);
		buf.append(")");
		return buf.toString();
	}
}
