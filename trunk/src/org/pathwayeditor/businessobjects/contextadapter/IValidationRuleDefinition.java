package org.pathwayeditor.businessobjects.contextadapter;


/**
 * Encapsulates a validation rule.
 * @author Richard Adams
 *
 */
public interface IValidationRuleDefinition {
	 /** <em>Mandatory</em> means the rule must be satisfied and it will always generate validation errors on failure.<p>
	 *  <em>Optional</em> is a rule can be ignored, set to a warning or raised as an error.
	 */
	public enum RuleLevel {
          OPTIONAL, MANDATORY
	}
	public enum RuleEnforcement {
		ERROR, WARNING, IGNORE
	}

	INotationValidationService getValidationService();
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
	 * Returns a string representing the category of the rule. The category is defined by the rule and does
	 * not comply with a controlled vocabulary. It is essentially a way of categorising the type of rule, such as
	 * "Syntactic", "Semantic" etc.   
	 * @return A <code>String</code>.
	 */
	String getRuleCategory();

	
	/**
	 * Tests if the given ruleEnforcement is valid and appropriate for the current
	 * rule level. Ensures MANDATORY rules can only be set as an ERROR. 
	 * @param ruleEnforcement the enforcement to be tested.
	 * @return
	 */
	boolean isValidEnforcement(RuleEnforcement ruleEnforecement);
	
	/**
	 * Gets the default enforcement level for this rule.
	 * @return the default enforcement level, which must comply with the
	 *  postcondition: <code>isValidEnforcement(getDefaultEnforcementLevel())</code>.
	 */
	RuleEnforcement getDefaultEnforcementLevel(); 
	
	/**
	 * Gets the level of this rule. 
	 * 
	 * @return A {@link RuleLevel} enum.
	 */
    RuleLevel getRuleLevel();

}
