/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * @author smoodie
 *
 */
public enum LinkTermType {
	SOURCE(1), TARGET(2);

	private static final int SOURCE_INT = 1;
	private static final int TARGET_INT = 2;
	
	private int code;
	
	private LinkTermType(int code){
		this.code = code;
	}
	
	public int toInt(){
		return this.code;
	}
	
	public LinkTermType fromInt(int code){
		switch(code){
			case SOURCE_INT:
				return SOURCE;
			case TARGET_INT:
				return TARGET;
			default:
				throw new IllegalArgumentException("Unknown integer code (" + code + ") cannot be mapped to enum LinkEndType");
		}
	}
}
