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
 * LineStyle is an enumeration defining  
 *
 * @author Stuart Moodie
 *
 */
public enum LineStyle {
	/**
	 * A solid line
	 */
	SOLID(1),
	/**
	 * A line of even dashes.
	 */
	DASHED(2),
	/**
	 * A line with a dash followed by a dot.
	 */
	DASH_DOT(3),
	/**
	 * A line with a dash and 2 dots following it.
	 */
	DASH_DOT_DOT(4),
	/**
	 * A line made up of dots
	 */
	DOT(5);
	
	private int code;
 
	private LineStyle(int code) {
		this.code = code;
	}
	

	/**
	 * Converts the enumerated type to an integer.
	 * @return a positive integer.
	 */
	public int toInt() {
		return code;
	}

	/**
	 * Converts a numerical value into an enumerated type.
	 * @param value the integer value. 
	 * @return the enum type corresponding to the code.
	 * @throws IllegalArgumentException if <code>value</code> does not match an enum type. 
	 */
	public LineStyle fromInt(int value) {
		switch (value) {
		case 1:
			return SOLID;
		case 2:
			return DASHED;
		case 3:
			return DASH_DOT;
		case 4:
			return DASH_DOT_DOT;
		case 5:
			return DOT;
		default:
			throw new IllegalArgumentException();
		}
	}
}
