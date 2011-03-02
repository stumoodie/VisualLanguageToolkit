/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * LinkEndDecoratorShape is an enumerated type that defines a set of
 * link end decorators.
 * 
 * @author Stuart Moodie
 * 
 */
public enum LinkEndDecoratorShape {

	DIAMOND(1), ARROW(2), DOUBLE_ARROW(3), TRIANGLE(4), EMPTY_DIAMOND(5), BAR(6),
	DOUBLE_BAR(7), EMPTY_CIRCLE(8), NONE(9), SQUARE(10), EMPTY_SQUARE(11),
	EMPTY_TRIANGLE(12), TRIANGLE_BAR(13);

	private int code;

	private LinkEndDecoratorShape(int code) {
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
}
