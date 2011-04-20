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

import java.util.Date;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * 
 * IValidationReport is an interface that defines a validation report provided by the validation
 * service define by: {@link INotationValidationService}.
 *
 * @author Stuart Moodie
 *
 */
public interface IValidationReport {
	
	/**
	 * Get the date and time that this report was generated.
	 * @return The date the report was generated.
	 */
	Date getValidationTime();
	
	/**
	 * Get the IModel which has been validated. Note that the model may be in a different state
	 * to the one in which this validation report was created.
	 * @return the model, which cannot be null.
	 */
	IModel getModel();
	
	/**
	 * Gets a the report items created during validation.  
	 * @return The list of report items, that should be a copy or an immutable.
	 */
	List<IValidationReportItem> getValidationReportItems();
	
	/**
	 * Reports if any warnings were issued during the validation checks.
	 * @return true if warnings were issued, false otherwise.
	 */
	boolean hasWarnings();
	
	/**
	 * Tests if the report found that the model was valid.
	 * @return true if the model was valid, false otherwise.
	 */
	boolean isValid();
}
