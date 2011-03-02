/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * 
 * LabelLocationPolicy is an enumeration describing layout policies used by labels
 * associated with a shape.
 * 
 * 
 * @author Stuart Moodie
 *
 */

public enum LabelLocationPolicy {
	/**
	 * Position label in the centre of the node.
	 */
	CENTRE(0),
	/**
	 * Position label at the edge of the shape centred on a compass point: N, NNW, NW, etc.
	 */
	COMPASS(1); 

	private static final int CENTRE_INT = 0;
	private static final int COMPASS_INT = 1;

	private int code;
	
	private LabelLocationPolicy(int code){
		this.code = code;
	}
	
	/**
	 * Convert the enum into a numerical code.
	 * @return the numercial code.
	 */
	public int toInt(){
		return this.code;
	}

	/**
	 * Converts a numerical value into an enumerated type.
	 * @param code the integer value. 
	 * @return the enum type corresponding to the code.
	 * @throws IllegalArgumentException if <code>code</code> does not match an enum type. 
	 */
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