/*
Copyright 2009-2011, Court of the University of Edinburgh
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
