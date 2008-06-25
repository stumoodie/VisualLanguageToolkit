package org.pathwayeditor.businessobjects.notationservice;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * Encapsulates information about a single rule validation problem.
 * @author Richard Adams
 *
 */
public interface IValidationReportItem {
	
	enum Severity{ERROR, WARNING};
	
	/**
	 * Gets the map object that causes the validation error or warning.
	 * Can return null if no single identifiable map object is responsible for the validation problem.
	 * @return An {@link IMapObject}, or null.
	 */
	IModel getMapObject();
	
	
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
