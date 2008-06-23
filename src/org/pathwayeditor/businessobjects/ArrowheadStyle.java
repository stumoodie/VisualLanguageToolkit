package org.pathwayeditor.businessobjects;


/**
 * Definition of arrowhead constants, derived from EPE1 LinkEDitPArt definitions.
 * 
 * @author Richard Adams
 *
 */
public enum ArrowheadStyle {
	
	
	DIAMOND(1), ARROW(2), DOUBLE_ARROW(3), TRIANGLE(4), EMPTY_DIAMOND(5), 
	BAR(6), DOUBLE_BAR(7), EMPTY_CIRCLE(8), NONE(9), SQUARE(10),EMPTY_SQUARE(11),EMPTY_TRIANGLE(12),TRIANGLE_BAR(13);
    
	/**
	 * The maximum height or width of an arrowhead decorator
	 */
	public static final int MAX_SCALE_FACTOR = 25;
	private int code;
    
	public int getCode() {
		return code;
	}

	private ArrowheadStyle(int code) {
		this.code = code;
	}
	
	/**
	 * Returns an array of toString() representations of all PortTypes
	 * @return A <code>String []</code>.
	 */
	public static String [] getStringArray () {
		String [] toReturn = new String [values().length];
		int i = 0;
		for (ArrowheadStyle type: values()){
			toReturn[i] = type.toString();
			i++;
		}
		return toReturn;
	}
	
	
}
