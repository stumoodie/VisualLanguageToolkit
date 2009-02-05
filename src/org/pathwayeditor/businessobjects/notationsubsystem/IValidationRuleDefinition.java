package org.pathwayeditor.businessobjects.notationsubsystem;


/**
 * <p>Defines a validation rule. A validation rule can have a &quot;Level&quot; of either mandatory or optional. A validation
 * rule has an &quot;Enforcement&quot; which defines the consequence of the rule being invalid.</p>
 * 
 * <p>If a rule is MANDATORY then it must always be validated and if the rule is invalid then this should be enforced as an error.
 *  A rule level of MANDATORY must always have an enforcement level of ERROR.</p>
 * 
 * <p>If a rule is OPTIONAL then it will be validated <b>unless</b> enforcement is set to IGNORE. If the rule is invalid
 * then this will be enforced as an error or warning depending on the <code>RuleEnforecement</code> setting. The default enforcement
 * is set in the definition, but the application may choose to change this via <code>IValidationRuleConfig</code>.
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
	 * A Numerical value that uniquely identifies a rule definition.
	 * @return the id, which should be a number > 0.
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
	 * @return true if the the rule enforcement is valid, false otherwise.
	 */
	boolean isValidEnforcement(RuleEnforcement ruleEnforcement);
	
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

    /**
     * Equivalence is based on the rule number, otherwise follows the standard contract of equals.
     * @param other the other object to compare, which can be null.
     * @return true if the objects are equal with the same id, false otherwise.
     */
    boolean equals(Object other);
    
    /**
     * Provides a hash code that matches the object equivalence defined in equals(). 
     * @return the hash code.
     */
    int hashCode();
}
