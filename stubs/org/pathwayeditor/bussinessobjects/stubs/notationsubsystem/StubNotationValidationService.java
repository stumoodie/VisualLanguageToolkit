/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

/**
 * @author Stuart Moodie
 *
 */
public class StubNotationValidationService implements INotationValidationService {
	private final INotationSubsystem notationSubsystem;
	
	/**
	 * @param notationSubsystem
	 */
	public StubNotationValidationService(INotationSubsystem notationSubsystem) {
		this.notationSubsystem = notationSubsystem;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#getMapBeingValidated()
	 */
	@Override
	public IModel getModelBeingValidated() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#getRules()
	 */
	@Override
	public Set<IValidationRuleDefinition> getRules() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#getValidationReport()
	 */
	@Override
	public IValidationReport getValidationReport() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#hasMapBeenValidated()
	 */
	@Override
	public boolean hasBeenValidated() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#isImplemented()
	 */
	@Override
	public boolean isImplemented() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#isReadyToValidate()
	 */
	@Override
	public boolean isReadyToValidate() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#setMapToValidate(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	@Override
	public void setModelToValidate(IModel mapToValidate) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#validateMap()
	 */
	@Override
	public void validate() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
	 */
	@Override
	public INotation getNotation() {
		return this.notationSubsystem.getNotation();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
	 */
	@Override
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

}
