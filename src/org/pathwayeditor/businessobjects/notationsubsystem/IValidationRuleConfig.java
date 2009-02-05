package org.pathwayeditor.businessobjects.notationsubsystem;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;


/**
 * This interface provide method for clients to configure the runtime behaviour of
 * validation rules.
 * @author Richard Adams/Stuart Moodie
 *
 */
public interface IValidationRuleConfig extends Comparable <IValidationRuleConfig>{
	
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

	/**
	 * The validation rules specifies the business key of the object. Objects with the same rule are equivalent.
	 * Follows the standard equals contract. 
	 * @param other the other object to compare, which can be null.
	 * @return true if the objects are the same, with equal rule definitions, false otherwise.
	 */
	boolean equals(Object other);
	
	/**
	 * Gets a hashcode that is consistent with equals behaviour.
	 * @return the has code.
	 */
	int hashCode();
}
