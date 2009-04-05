/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElement;

/**
 * Encapsulates information about a single rule validation problem.
 * @author Richard Adams
 *
 */
public interface IValidationReportItem {
	
	enum Severity{ERROR, WARNING};
	
	/**
	 * Gets the object that causes the validation error or warning.
	 * Can return null if no single identifiable map object is responsible for the validation problem.
	 * @return the object causing the validation error, or null if there is none or more than one.
	 */
	IDrawingElement getInvalidObject();
	
	
	/**
	 * Gets the broken rule definition
	 * @return A <code>IValidationRuleDefinition</code>, will not be null 
	 */
	IValidationRuleDefinition getBrokenRule();
	
	
	/**
	 * A String providing specific informatiion about the the context of the 
	 * broken rule.
	 * @return A <code>String</code>, not null.
	 */
	String getMessage();
	
	/**
	 * 
	 * @return An unmodifiable <code>List</code> of child report items, will not be null.
	 */
	List<IValidationReportItem> getChildReports();
	
	/**
	 * Returns the severity of the broken rule, will not be null.
	 * @return An {@link Enum} Severity
	 */
	Severity getSeverity();

}
