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
