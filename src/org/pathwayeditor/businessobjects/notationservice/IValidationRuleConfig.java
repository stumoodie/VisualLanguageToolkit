package org.pathwayeditor.businessobjects.notationservice;

import org.pathwayeditor.businessobjects.notationservice.IValidationRuleDefinition.RuleLevel;

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
	 * Determines whether the rule being configured should be included in the validation process.
	 * For any rule definition of type MANDATORY, this method should always return <code>true</code>.
	 * @return <code>true</code> if the rule is to be included in the validation, false otherwise.
	 * By default should return <code>true</code> if not explicitly set.
	 */
	boolean mustBeRun();
	
	/**
	 * Configures rules of type {@link RuleLevel#OPTIONAL} or {@link RuleLevel#GUIDELINE} to be included 
	 * in the validation.
	 * Validation  rules of type {@link RuleLevel#MANDATORY} are always run and are not configurable by this method.
	 * @param mustBeRun A <code>boolean</code> indicating that the rule should be tested during validation.
	 */
	void setMustBeRun(boolean mustBeRun);
	
	/**
	 * Reports whether the rule being configured should produce a validation error on failure.
	 * For mandatory rules, this method should always return <code>false</code>.
	 * @return A <code>boolean</code>.
	 * Implementations should return true for only one of isErrorRule() and isWarningRule().<br>
	 *  I.e.,<br>
	 * isWarningRule() && isErrorRule() should always be false. <br>
	 * Conversely,isWarningRule() || isErrorRule() should always be true.
	 */
	boolean isErrorRule();
	
	/**
	 * Reports whether the rule being configured should produce a validation warning on failure.
	 * Should never return <code>true</code> for Mandatory and Optional Rules.
	 * @return A <code>boolean</code>.
	 * @see isErrorRule()
	 */
	boolean isWarningRule();
	
	/**
	 * 
	 * @param isError. If <code>false</code>, then a subsequent call to isWarningRule() should be true.
	 * This method will have no effect on mandatory {@link IValidationRuleDefinition}s as these always generate errors.
	 * If <code>true</code>,  then a subsequent call to isErrorRule() should return true.
	 */
	void promoteToError(boolean isError);

}
