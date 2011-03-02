/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * 
 * INotationValidationService is an interface that defines the notation subsystem's
 * validation service. This service validates rules for a notation over and above the
 * syntax rules applied by the syntax service.
 *
 * @author Stuart Moodie
 *
 */
public interface INotationValidationService extends INotationService {

	/**
	 * The Notation Subsystem does not need to implement as validation service. This method tests is one is implemented.
	 * @return true if service is implemented, false otherwise.
	 */
	boolean isImplemented();
	
	/**
	 * Tells the validator what model is to be validated. It should not be used if no validation service
	 * is implemented. Will overwrite the previous map and reset validation status of the instance
	 * so that the validation report is empty.
	 * <p>
	 * Postconditions:
	 * <p>
	 *  <code>hasBeenValidated() == false</code>
	 *  <code>getValidationReport().isEmpty()</code>
	 *  <code>getCanvasBeingValidated().equals(canvasToValidate)</code>
	 *  
	 * @param modelToValidate the canvas to be validate, which cannot be null.
	 * @throws UnsupportedOperationException if <code>isImplemented() == false</code>.
	 * @throws IllegalArgumentException if <code>mapToValidate == null</code>.
	 */
	void setModelToValidate(IModel modelToValidate);
	
	/**
	 * Provides the model that is being validated. Will return null if no map is currently being validated.
	 * <p>
	 * Postconditions:
	 * <code>(isImplemented() == false) implies (getCanvasBeingValidated() == null)</code>
	 * @return the model being validated or null if none set.
	 * @throws UnsupportedOperationException if <code>isImplemented() == false</code>.
	 */
	IModel getModelBeingValidated();
	
	/**
	 * Tests if the validator is ready to perform a validation. This requires that a validator has been implemented
	 * and that a map has been provided for validation.
	 * @return true if ready, false otherwise.
	 * @throws UnsupportedOperationException if <code>isImplemented() == false</code>.
	 */
	boolean isReadyToValidate();
	
	/**
	 * Validates the map provided to check that it complies with the additional syntactic and semantic rules associated
	 * with a graphical notation.
	 * <p>
	 * Precondition:
	 * <p>
	 * <code>isReadyToValidate() == true</code>
	 * @throws UnsupportedOperationException if <code>isImplemented() == false</code>.
	 */
	void validate();
	
	/**
	 * Reports if the currently set canvas has been validated. This implies that a validation report should be available. 
	 * @return true if the map has been validated, false otherwise.
	 * @throws UnsupportedOperationException if <code>isImplemented() == false</code>.
	 */
	boolean hasBeenValidated();
	
	/**
	 * Provides a human readable report of the outcome of the validation. Error messages and warnings should be obtained here. Each separate
	 * error and warning message should be provided as a new item in the message list. The message should be read once validation has completed
	 * so NO status messages should be provided.
	 * If validation was successful with no warnings then the report should contain no items.
	 * <p>
	 * Preconditions:
	 * <p>
	 * <code>hasBeenValidated() == true</code>
	 * <code>isImplemented() == true</code>
	 * <p>

	 * @return an {@link IValidationReport} that is read-only and is guaranteed to be non-null. 
	 *  @throws UnsupportedOperationException if <code>isImplemented() == false</code>
	 *  @throws IllegalStateException if <code>hasMapBeenValidated() == false</code>  
	 */
	IValidationReport getValidationReport();
	
	/**
	 * Returns an immutable <code>Set</code> of <em>all </em> rules defined for this context.
	 * @return  An immutable  <code>Set</code> of {@link IValidationRuleDefinition}. If no rules are defined
	 * will return an empty list.
	 */
	Set<IValidationRuleDefinition> getRules();
}
