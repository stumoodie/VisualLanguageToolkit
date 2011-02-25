/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.List;

import uk.ac.ed.inf.graph.compound.ICompoundGraphElement;

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
	ICompoundGraphElement getInvalidObject();
	
	
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
