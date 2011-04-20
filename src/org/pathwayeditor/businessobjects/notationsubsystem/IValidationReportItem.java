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

package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.List;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

/**
 * IValidationReportItem is an interface defining an issue or item in a validation report.
 * Typically this will contain an error or warning generated by the report item. 
 * 
 * @author Stuart Moodie
 *
 */
public interface IValidationReportItem {
	enum Severity{ERROR, WARNING};
	
	/**
	 * Gets the object that caused the validation error or warning.
	 * Can return null if no single identifiable map object is responsible for the validation problem.
	 * @return the object causing the validation error, or null if there is none or more than one.
	 */
	ICompoundGraphElement getInvalidObject();
	
	
	/**
	 * The rule that was this issue was related to.
	 * @return the rule that this issue was related to. 
	 */
	IValidationRuleDefinition getBrokenRule();
	
	
	/**
	 * Gets the error or warning messgae detailing the issue.
	 * @return the error message.
	 */
	String getMessage();
	
	/**
	 * Get a list of child reports for this item. 
	 * @return Returns a list of child report items, that is either immutable or copied.
	 */
	List<IValidationReportItem> getChildReports();
	
	/**
	 * Get the severity of the issue, typically this is whether it is an error or a warning.
	 * @return The severity of the issue.
	 */
	Severity getSeverity();

}
