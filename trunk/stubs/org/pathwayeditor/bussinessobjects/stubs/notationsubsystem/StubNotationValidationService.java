/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.List;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
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
	public IMap getMapBeingValidated() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#getRules()
	 */
	public List<IValidationRuleDefinition> getRules() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#getValidationReport()
	 */
	public IValidationReport getValidationReport() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#hasMapBeenValidated()
	 */
	public boolean hasMapBeenValidated() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#isImplemented()
	 */
	public boolean isImplemented() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#isReadyToValidate()
	 */
	public boolean isReadyToValidate() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#setMapToValidate(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void setMapToValidate(IMap mapToValidate) {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#validateMap()
	 */
	public void validateMap() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
	 */
	public INotation getNotation() {
		return this.notationSubsystem.getNotation();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

}
