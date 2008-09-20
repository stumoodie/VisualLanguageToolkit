package org.pathwayeditor.businessobjects.contextadapter;

import org.pathwayeditor.businessobjects.contextadapter.IValidationRuleDefinition.RuleEnforcement;


/**
 * This interface provide method for clients to manipulate the runtime behaviour of
 * validation rules.
 * @author Richard Adams
 *
 */
public interface IValidationRuleConfig {
	
	/**
	 * Returns the definition of the rule that is being configured.
	 * @return An {@link IValidationRuleDefinition} object, must not be null.
	 */
	IValidationRuleDefinition getValidationRuleDefinition();
	
	/**
	 * Get the current rule enforcement level for this rule. 
	 * @return the rule enforcement level, will comply with
	 * the postcondition <code>isValidEnforcement(getCurrentRuleEnforecement())</code>. 
	 */
	RuleEnforcement getCurrentRuleEnforcement();

	/**
	 * Sets the rule enforcement level to use for this rule. 
	 * @param ruleEnforcement the rule enforcement which must be valid for rule level.
	 * @throws IllegalArgumentException if <code>isValidEnforcement(ruleEnforcement) == false</code>.
	 */
	void setRuleEnforcement(RuleEnforcement ruleEnforcement);

}
