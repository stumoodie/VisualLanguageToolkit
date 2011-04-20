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
 * IInterpreterErrorHandler is an interface that defines an error handler that is called by the figure definition
 * interpreter. It enables implementation of error handling to be hidden from the interpreter. 
 *
 * @author Stuart Moodie
 *
 */
public interface IInterpreterErrorHandler {
	public enum ErrorCode {	UNEXPECTED_TYPE  };
	
	
	/**
	 * Report that an error has occurred.
	 * @param msg the error message describing the error. 
	 */
	void reportError(String msg);

	/**
	 * Report that an error has occurred together with an error code.
	 * @param errorCode the error code. 
	 * @param value the value associated with the error code.
	 */
	void reportError(ErrorCode errorCode, Value value);
	
}
