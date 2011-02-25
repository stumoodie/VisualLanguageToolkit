/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * Define constants corresponding to SWT line style constants.
 * @author Richard Adams
 *
 */
public enum LineStyle {
	SOLID(1), DASHED(2), DASH_DOT(3), DASH_DOT_DOT(4), DOT(5);
	
	private int code;
 
	public int getCode() {
		return code;
	}
	
	
	private LineStyle(int code) {
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
