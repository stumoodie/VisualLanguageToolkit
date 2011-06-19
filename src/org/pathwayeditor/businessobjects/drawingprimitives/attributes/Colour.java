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
 * Colour is a class that represent colours complete with support for an alpha component that
 * described the transparency of the colour.
 *
 * @author Stuart Moodie
 *
 */
public class Colour {
	public static final int OPAQUE = 255; 
	public static final int TRANSPARENT = 0;
	public static final Colour WHITE = new Colour(RGB.WHITE, OPAQUE);
	public static final Colour BLACK = new Colour(RGB.BLACK, OPAQUE);
	public static final Colour BLUE = new Colour(RGB.BLUE, OPAQUE);
	public static final Colour RED = new Colour(RGB.RED, OPAQUE);
	public static final Colour GREEN = new Colour(RGB.GREEN, OPAQUE);
	
	private final RGB rgb;
	private final int alpha;
	
	public Colour(RGB rgb){
		this.rgb = rgb;
		this.alpha = TRANSPARENT;
	}

	public Colour(RGB rgb, int alpha){
		this.rgb = rgb;
		this.alpha = alpha;
	}

	public Colour(int red, int green, int blue, int alpha){
		this.rgb = new RGB(red, green, blue);
		this.alpha = alpha;
	}

	public Colour(int red, int green, int blue){
		this.rgb = new RGB(red, green, blue);
		this.alpha = OPAQUE;
	}

	/**
	 * @return the rgb
	 */
	public RGB getRgb() {
		return this.rgb;
	}

	/**
	 * @return the alpha
	 */
	public int getAlpha() {
		return this.alpha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.alpha;
		result = prime * result + ((this.rgb == null) ? 0 : this.rgb.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colour other = (Colour) obj;
		if (this.alpha != other.alpha)
			return false;
		if (this.rgb == null) {
			if (other.rgb != null)
				return false;
		} else if (!this.rgb.equals(other.rgb))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(rgb=");
		buf.append(this.rgb);
		buf.append(", alpha=");
		buf.append(this.alpha);
		buf.append(")");
		return buf.toString();
	}

}
