package org.pathwayeditor.businessobjects.notationservice;


/**
 * Encapsulates a validation rule.
 * @author Richard Adams
 *
 */
public interface IValidationRuleDefinition {
	 /** <em>Mandatory</em> means the rule must be run in a validation step in order to build a domain object model.
	 *  Mandatory rules always generate validation errors on failure.<p>
	 *  <em>Optional</em> rules can be enabled or disabled by clients; if enabled they will still generate validation errors on failure.
	 *  Rules of type <em>Guideline</em> are optional and generate validation warnings or  error as configured by a client.
	 */
	public enum RuleLevel {
          OPTIONAL, MANDATORY, GUIDELINE
	}


	/**
	 * Returns the context to which this rule applies
	 * @An {@link IContext} object. Must not return null
	 */
	IContext getContext();
	/**
	 * 
	 * @return A unique numerical identifier for this validation rule
	 */
	int getRuleNumber();
	
	/**
	 * A user-readable name of the rule.
	 * @return a <code>String</code>, not null
	 */
	String getName();
	
	/**
	 * Returns a short  String describing the intent of the validation rule
	 * for display in the UI
	 * @return A <code>String</code>, not null
	 */
	String getDescription();
	
	/**
	 * Returns a full description of the logic and meaning of the validation rule
	 * for display in user help documentation.
	 * @return A <code>String</code>, not null
	 */
	String getDetailedDescription();
	
	/**
	 * Returns a string representing the category of the rule.<br>
	 * @return A <code>String</code>.
	 */
	String getRuleCategory();

	
	/**
	 * Provides information as to whether the rule is <em>mandatory</em>, <em>optional</em> or a <em>guideline.</em><p>
	 * 
	 * @return A {@link RuleLevel} enum.
	 */
    RuleLevel getRuleLevel();

}
