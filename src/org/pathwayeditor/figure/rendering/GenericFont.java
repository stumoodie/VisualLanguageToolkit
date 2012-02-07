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

package org.pathwayeditor.figure.rendering;

import java.util.EnumSet;

/**
 * GenericFont is a class the provides an implementation of a device independent font. It is immutable so can be
 * passed by reference without breaking encapsulation. 
 * 
 * @author Stuart Moodie
 *
 */
public class GenericFont implements IFont {
	private static final double DEFAULT_SIZE = 12;
	private static final EnumSet<Style> DEFAULT_STYLE = EnumSet.of(Style.NORMAL);
	private final double size;
	private final EnumSet<Style> styles;
	
	public GenericFont(){
		this.size = DEFAULT_SIZE;
		this.styles = EnumSet.copyOf(DEFAULT_STYLE);
	}
	
	public GenericFont(double size, EnumSet<Style> styles){
		this.size = size;
		this.styles = EnumSet.copyOf(styles);
	}
	
	public GenericFont(IFont other) {
		this.size = other.getFontSize();
		this.styles = EnumSet.copyOf(other.getStyle());
	}

	@Override
	public double getFontSize() {
		return this.size;
	}

	@Override
	public EnumSet<Style> getStyle() {
		return EnumSet.copyOf(this.styles);
	}

	@Override
	public GenericFont newSize(double fontSize) {
		return new GenericFont(fontSize, this.styles);
	}

	@Override
	public GenericFont newStyle(EnumSet<Style> styles) {
		return new GenericFont(this.size, styles);
	}

	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(size=");
		buf.append(this.size);
		buf.append(",styles=");
		buf.append(this.styles);
		buf.append(")");
		return buf.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((this.styles == null) ? 0 : this.styles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GenericFont))
			return false;
		GenericFont other = (GenericFont) obj;
		if (Double.doubleToLongBits(this.size) != Double.doubleToLongBits(other.size))
			return false;
		if (this.styles == null) {
			if (other.styles != null)
				return false;
		} else if (!this.styles.equals(other.styles))
			return false;
		return true;
	}

}
