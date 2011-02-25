/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
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

}
