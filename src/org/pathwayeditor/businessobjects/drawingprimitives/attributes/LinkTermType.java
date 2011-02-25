/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * @author Stuart Moodie
 *
 */
public enum LinkTermType {
	SOURCE(0), TARGET(1);

	private static final int SOURCE_INT = 0;
	private static final int TARGET_INT = 1;
	
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
