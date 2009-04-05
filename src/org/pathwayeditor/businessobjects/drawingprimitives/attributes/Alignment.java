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
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * @author ntsorman
 *
 */
public enum Alignment {
	TOP(1), BOTTOM(2), CENTER(3), LEFT(4), RIGHT(5);
	
	private int code;
 
	public int getCode() {
		return code;
	}
	
	
	private Alignment(int code) {
		this.code = code;
	}
	

	/**
	 * @return an int for persistence - used by the Hibernate Custom  Type for this enum
	 */
	public int toInt() {
		return code;
	}

	/**
	 * @param value an int from the database 
	 * @return the enum constant which stored a code matching this int. Used by the Hibernate Custom Type for this enum
	 */
	public Alignment fromInt(int value) {
		switch (value) {
		case 1:
			return TOP;
		case 2:
			return BOTTOM;
		case 3:
			return CENTER;
		case 4:
			return LEFT;
		case 5:
			return RIGHT;
		default:
			throw new IllegalArgumentException();
		}
	}
}
