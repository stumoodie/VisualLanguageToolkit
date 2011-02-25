/**
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * 
 * @author Stuart Moodie
 *
 */

public enum LabelLocationPolicy { 
	CENTRE(0), COMPASS(1); 

	private static final int CENTRE_INT = 0;
	private static final int COMPASS_INT = 1;

	private int code;
	
	private LabelLocationPolicy(int code){
		this.code = code;
	}
	
	public int toInt(){
		return this.code;
	}
	
	public LabelLocationPolicy fromInt(int code){
		switch(code){
			case CENTRE_INT:
				return CENTRE;
			case COMPASS_INT:
				return COMPASS;
			default:
				throw new IllegalArgumentException("Unknown integer code (" + code + ") cannot be mapped to " + this.getClass().getSimpleName());
		}
	}
}