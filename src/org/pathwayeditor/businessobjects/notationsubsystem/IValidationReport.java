/*
Copyright 2009-2011, Court of the University of Edinburgh
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
