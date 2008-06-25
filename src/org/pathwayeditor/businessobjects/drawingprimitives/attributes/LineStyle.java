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
