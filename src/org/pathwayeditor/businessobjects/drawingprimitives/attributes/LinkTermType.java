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

/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * LinkTermType is an enumerated type that defines the type of a link terminus.
 * 
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
	
	/**
	 * Converts the enumerated type to an integer.
	 * @return a positive integer.
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
