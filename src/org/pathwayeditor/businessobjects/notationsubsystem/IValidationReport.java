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

import java.util.Date;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;

import uk.ac.ed.inf.graph.compound.ICompoundGraph;

/**
 * Provides a snapshot of the validation status of a map.
 * Typically will be generated by a call {@link INotationValidationService#validate()}.
 * The validation report will not  automatically refresh by altering the map but can be refreshed by subsequent calls to
 * {@link INotationValidationService#validate()}.<br>
 * Typically this report will be read-only.<p>
 * Implementations should provide a toString() method that provides details of the validation report.
 * @see INotationValidationService
 * @author Richard Adams/Stuart Moodie
 *
 */
public interface IValidationReport {
	
	/**
	 * Returns the date and time that this report was generated.
	 * @return A {@link Date} object, never null
	 */
	Date getValidationTime();
	
	/**
	 * Returns the ICanvas which has been validated.<br>
	 * This is not a snapshot, the canvas can subsequently change after the report has been 
	 * generated.
	 * @return an {@link IRootAttribute}, never null
	 */
	ICompoundGraph getCanvas();
	
	/**
	 * Returns an immutable<code>List</code> of individual validation errors and warnings.
	 * If no validation items are present this method will return an empty List.
	 * @return A <code>List</code> of {@link IValidationReportItem}
	 */
	List<IValidationReportItem> getValidationReportItems();
	
	/**
	 * Reports if any warnings were issued during the validation checks. These are issues that may indicate potential problems with the map, or
	 * stylistic or presentational issues that the user may wish to address.<br>
	 * Specifically, will return true if getValidationReportItems() contains at least one item of status.
	 * @return true if warnings were issued, false otherwise.
	 */
	boolean hasWarnings();
	
	/**
	 * Convenience method reports if the canvas has passed validation checks that have been performed on it by this instance.<br>
	 * Will return <code>true</code> if getValidationReportItems() contains no items of status.
	 * @return true if it has, false otherwise.
	 */
	boolean isValid();
}
