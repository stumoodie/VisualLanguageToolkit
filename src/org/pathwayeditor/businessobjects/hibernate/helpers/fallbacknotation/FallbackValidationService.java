package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.Collections;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;

class FallbackValidationService implements INotationValidationService {
	private INotationSubsystem serviceProvider;
	
	public FallbackValidationService(INotationSubsystem provider) {
		this.serviceProvider=provider;
	}

	public ICanvas getCanvasBeingValidated() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public IValidationReport getValidationReport() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public boolean hasBeenValidated() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public boolean hasWarnings() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public boolean isImplemented() {
		return false;
	}

	public boolean isMapValid() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public boolean isReadyToValidate() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public void setCanvasToValidate(ICanvas mapToValidate) {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public void validate() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}


	public Set<IValidationRuleConfig> getDefaultRuleConfigurations() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public Set<IValidationRuleConfig> getRuleConfigurations() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService#getRules()
	 */
	public Set<IValidationRuleDefinition> getRules() {
		return Collections.emptySet();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
	 */
	public INotation getNotation() {
		return this.serviceProvider.getNotation();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.serviceProvider;
	}
}
