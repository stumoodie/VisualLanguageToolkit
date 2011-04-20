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

package org.pathwayeditor.figure.definition;


/**
 * 
 * InterpreterException
 *
 * @author Stuart Moodie
 *
 */
public class InterpreterException extends RuntimeException {
	private final OpCodes opCode;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7828676909079228740L;

	
	public InterpreterException(OpCodes opCode){
		super("Cannot process instructions for opcode: " + opCode);
		this.opCode = opCode;
	}
	
	public OpCodes getInstruction(){
		return this.opCode;
	}
}
