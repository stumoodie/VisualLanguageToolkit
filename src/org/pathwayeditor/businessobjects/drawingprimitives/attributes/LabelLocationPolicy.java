/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
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