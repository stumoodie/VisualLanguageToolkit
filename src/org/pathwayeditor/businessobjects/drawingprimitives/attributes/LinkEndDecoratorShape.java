/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * Definition of arrowhead constants, derived from EPE1 LinkEDitPArt
 * definitions.
 * 
 * @author Richard Adams
 * 
 */
public enum LinkEndDecoratorShape {

	DIAMOND(1), ARROW(2), DOUBLE_ARROW(3), TRIANGLE(4), EMPTY_DIAMOND(5), BAR(6), DOUBLE_BAR(
			7), EMPTY_CIRCLE(8), NONE(9), SQUARE(10), EMPTY_SQUARE(11), EMPTY_TRIANGLE(
			12), TRIANGLE_BAR(13);
	/**
	 * The maximum height or width of an arrowhead decorator
	 */
	public static final int MAX_SCALE_FACTOR = 25;
	private int code;

	public int getCode() {
		return code;
	}

	private LinkEndDecoratorShape(int code) {
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
	public LinkEndDecoratorShape fromInt(int value) {
		switch (value) {
		case 1:
			return DIAMOND;
		case 2:
			return ARROW;
		case 3:
			return DOUBLE_ARROW;
		case 4:
			return TRIANGLE;
		case 5:
			return EMPTY_DIAMOND;
		case 6:
			return BAR;
		case 7:
			return DOUBLE_BAR;
		case 8:
			return EMPTY_CIRCLE;
		case 9:
			return NONE;
		case 10:
			return SQUARE;
		case 11:
			return EMPTY_SQUARE;
		case 12:
			return EMPTY_TRIANGLE;
		case 13:
			return TRIANGLE_BAR;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns an array of toString() representations of all PortTypes
	 * 
	 * @return A <code>String []</code>.
	 */
	public static String[] getStringArray() {
		String[] toReturn = new String[values().length];
		int i = 0;
		for (LinkEndDecoratorShape type : values()) {
			toReturn[i] = type.toString();
			i++;
		}
		return toReturn;
	}

}
