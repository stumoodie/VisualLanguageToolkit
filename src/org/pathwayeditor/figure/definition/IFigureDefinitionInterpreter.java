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
 * IFigureDefinitionInterpreter is an interface that defines an interpreter of compiled figure definition opCodes.
 * The interpreter converts the opCodes into actions using an implementation of {@link IOpCodeHandler}. 
 *
 * @author Stuart Moodie
 *
 */
public interface IFigureDefinitionInterpreter {

	/**
	 * Gets the compiled figure definition that is being interpreted.
	 * @return the compiled figure definition that cannot be null.
	 */
	ICompiledFigureDefinition getCompiledFigureDefinition();

	/**
	 * Get the error handler used by this interpreter.
	 * @return the error handler which cannot be null.
	 */
	IInterpreterErrorHandler getErrorHandler();

	/**
	 * Sets a value for the given bind variable.
	 * @param name the bind variable name.
	 * @param value the value to set the bind variable to.
	 */
	void setBindInteger(String name, Integer value);

	/**
	 * Sets a value for the given bind variable.
	 * @param name the bind variable name.
	 * @param value the value to set the bind variable to.
	 */
	void setBindBoolean(String name, Boolean value);

	/**
	 * Sets a value for the given bind variable.
	 * @param name the bind variable name.
	 * @param value the value to set the bind variable to.
	 */
	void setBindDouble(String name, Double value);

	/**
	 * Sets a value for the given bind variable.
	 * @param name the bind variable name.
	 * @param value the value to set the bind variable to.
	 */
	void setBindString(String name, String value);

	/**
	 * Get the opCode handler that is being used to carry out actions based on the
	 * interpreted opCodes.
	 * @return the opCode handler that cannot be null.
	 */
	IOpCodeHandler getOpCodeHandler();

	/**
	 * Executes the interpreter. 
	 */
	void execute();

	/**
	 * Gets the current value that the given bind variable is set to.
	 * @param name the bind variable name, which should not be null.
	 * @return the current bind variable value which will be null if the bind variable is not defined by the figure definition being interpreted.
	 */
	Boolean getBindBooleanValue(String name);

	/**
	 * Gets the current value that the given bind variable is set to.
	 * @param name the bind variable name, which should not be null.
	 * @return the current bind variable value which will be null if the bind variable is not defined by the figure definition being interpreted.
	 */
	Integer getBindIntegerValue(String name);

	/**
	 * Gets the current value that the given bind variable is set to.
	 * @param name the bind variable name, which should not be null.
	 * @return the current bind variable value which will be null if the bind variable is not defined by the figure definition being interpreted.
	 */
	Double getBindDoubleValue(String name);

	/**
	 * Gets the current value that the given bind variable is set to.
	 * @param name the bind variable name, which should not be null.
	 * @return the current bind variable value which will be null if the bind variable is not defined by the figure definition being interpreted.
	 */
	String getBindStringValue(String name);

}