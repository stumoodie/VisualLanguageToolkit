/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;


/**
 * IValidationRuleDefinition is an interface that defines a validation rule.
 * A validation rule can have a &quot;Level&quot; of either mandatory or optional. A validation
 * rule has an &quot;Enforcement&quot; which defines the consequence of the rule being invalid.</p>
 * 
 * <p>If a rule is MANDATORY then it must always be validated and if the rule is invalid then this should be enforced as an error.
 *  A rule level of MANDATORY must always have an enforcement level of ERROR.</p>
 * 
 * <p>If a rule is OPTIONAL then it will be validated <b>unless</b> enforcement is set to IGNORE. If the rule is invalid
 * then this will be enforced as an error or warning depending on the <code>RuleEnforecement</code> setting. The default enforcement
 * is set in the definition, but the application may choose to change this via <code>IValidationRuleConfig</code>.
 * 
 * @author Stuart Moodie
 * 
 */
public interface IValidationRuleDefinition extends Comparable <IValidationRuleDefinition>{
	 /** <em>Mandatory</em> means the rule must be satisfied and it will always generate validation errors on failure.<p>
	 *  <em>Optional</em> is a rule can be ignored, set to a warning or raised as an error.
	 */
	public enum RuleLevel {
          OPTIONAL, MANDATORY
	}
	public enum RuleEnforcement {
		ERROR, WARNING, IGNORE
	}

	/**
	 * Get the rule number that uniquely identifies this rule definition.
	 * @return the rule number, which should be a number > 0.
	 */
	int getRuleNumber();
	
	/**
	 * A user-readable name for this rule.
	 * @return the rule name, which is not null.
	 */
	String getName();
	
	/**
	 * Get a brief user readable description giving a short summary of this rule. 
	 * @return The description, which is not null
	 */
	String getDescription();
	
	/**
	 * Get a detailed explanation of the rule. This should be user readable and help the user understand
	 * the rule and how it is validated.
	 * @return The detailed description, which should not be null.
	 */
	String getDetailedDescription();
	
	/**
	 * Returns a string representing the category of the rule. The category is defined by the rule and does
	 * not comply with a controlled vocabulary. It is essentially a way of categorising the type of rule, such as
	 * "Syntactic", "Semantic" etc.   
	 * @return the rule category, which cannot be null.
	 */
	String getRuleCategory();

	
	/**
	 * Tests if the given ruleEnforcement is valid and appropriate for the current
	 * rule level. Ensures MANDATORY rules can only be set as an ERROR. 
	 * @param ruleEnforcement the enforcement to be tested.
	 * @return true if the the rule enforcement is valid, false otherwise.
	 */
	boolean isValidEnforcement(RuleEnforcement ruleEnforcement);
	
	/**
	 * Gets the default enforcement for this rule.
	 * @return the default enforcement level, which must comply with the
	 *  postcondition: <code>isValidEnforcement(getDefaultEnforcementLevel())</code>.
	 */
	RuleEnforcement getDefaultEnforcement(); 
	
	/**
	 * Gets the level of this rule. 
	 * 
	 * @return the level of this rule, which canot be null.
	 */
    RuleLevel getRuleLevel();

}
