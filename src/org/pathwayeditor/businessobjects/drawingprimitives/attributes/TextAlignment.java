package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * Define constants corresponding to SWT line style constants.
 * @author Richard Adams
 *
 */
public enum TextAlignment {
	HORIZ_LEFT(1), HORIZ_CENTRE(2), HORIZ_RIGHT(3), VERT_TOP(4), VERT_CENTRE(5), VERT_BOTTOM(6);
	
	private int code;
 
	public int getCode() {
		return code;
	}
	
	
	private TextAlignment(int code) {
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
	public TextAlignment fromInt(int value) {
		switch (value) {
		case 1:
			return HORIZ_LEFT;
		case 2:
			return HORIZ_CENTRE;
		case 3:
			return HORIZ_RIGHT;
		case 4:
			return VERT_TOP;
		case 5:
			return VERT_CENTRE;
		case 6:
			return VERT_BOTTOM;
		default:
			throw new IllegalArgumentException();
		}
	}
}
