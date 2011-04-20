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

import java.util.List;

/**
 * 
 * IInstructionFactory is an interface that defines a factory for the creation of new instruction types.
 *
 * @author Stuart Moodie
 *
 */
public interface IInstructionFactory {

	Instruction createReal(String value);
	
	Instruction createInteger(String value);
	
	Instruction createInteger(Integer value);
	
	Instruction createString(String value);
	
	Instruction createExpandedVarName(String value);
	
	Instruction createUnexpandedVarName(String value);
	
	Instruction createBoundValue(String value);
	
	Instruction createOpCode(String value);
	
	Instruction createArray(List<Instruction> instArray);
	
	Instruction createNull();
	
	Instruction createBoolean(String value);

	Instruction createProcedure(List<Instruction> procList);
}
