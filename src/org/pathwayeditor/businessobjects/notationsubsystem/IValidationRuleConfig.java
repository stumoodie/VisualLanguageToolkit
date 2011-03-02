/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;


/**
 * IValidationRuleConfig is an interface that define the configuration of a 
 * rule specified by {@link IValidationRuleDefinition}.
 * 
 * @author Stuart Moodie
 *
 */
public interface IValidationRuleConfig extends Comparable <IValidationRuleConfig>{
	
	/**
	 * Gets the rule definition that is being configured.
	 * @return The rule definition, which must not be null.
	 */
	IValidationRuleDefinition getValidationRuleDefinition();
	
	/**
	 * Gets the current rule enforcement level for this rule. 
	 * @return the rule enforcement level, which cannot be null. 
	 */
	RuleEnforcement getCurrentRuleEnforcement();

	/**
	 * Sets the rule enforcement level to use for this rule, which must be valid for the rule definition, i.e.
	 * <code>IValidRuleDefinition.isValidEnforcement(ruleEnforcement)</code>. 
	 * @param ruleEnforcement the rule enforcement to set.
	 * @throws IllegalArgumentException if <code>ruleEnforcement</code> is not valid for the rule definition.
	 */
	void setRuleEnforcement(RuleEnforcement ruleEnforcement);

}
