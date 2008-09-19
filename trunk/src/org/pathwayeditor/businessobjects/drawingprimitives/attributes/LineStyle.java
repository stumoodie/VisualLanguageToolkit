package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * Define constants corresponding to SWT line style constants.
 * @author Richard Adams
 *
 */
public enum LineStyle {
	SOLID(1, "Solid"), DASHED(2, "Dashed"), DASH_DOT(3, "Dash dot"), DASH_DOT_DOT(4, "Dash dot dot"), DOT(5, "Dot");
	
	public final static int MAX_LINE_WIDTH = 20;
	public final static int MIN_LINE_WIDTH = 0;
	
	private int code;
	private String name ;
 
	public int getCode() {
		return code;
	}
	
	
	private LineStyle(int code, String name) {
		this.code = code;
		this.name = name;
		
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
	
	public static String [] getString () 
	{
		String [] stringArray = new String [values().length];
		
		LineStyle [] temp = values() ;
		
		for ( int a = 0 ; a < values().length ; a++ )
		{
			stringArray[a] = new String ( temp[a].name );
		}
		
		return stringArray ;
	}


}
