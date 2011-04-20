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

import java.util.Iterator;
import java.util.Set;

/**
 * 
 * IFigureDefinition is an interface that defines a compiled figure definition. This contains instructions that can be efficiently interpreted
 * to render a figure.
 *
 * @author Stuart Moodie
 *
 */
public interface ICompiledFigureDefinition {

	/**
	 * Provide a new iterator to iterate over all the instructions.
	 * @return a new iterator of instructions.
	 */
	Iterator<Instruction> iterator();

	/**
	 * Gets the bind variable names that have been specified in the figure definition. 
	 * @return the set of bind variable names, which cannot be null.
	 */
	Set<String> getBindVariableNames();

	/**
	 * Gets the number of instructions in the figure definition.
	 * @return the number of instructions.
	 */
	int numInstructions();

	/**
	 * Gets the instruction at a given position in the list of instructions.
	 * @param index the index position (starting at 0 to N-1).
	 * @return the instruction, which cannot be null.
	 * @throws IndexOutOfBoundsException if the <code>index</code> is out of the lists bounds. 
	 */
	Instruction get(int index);

}