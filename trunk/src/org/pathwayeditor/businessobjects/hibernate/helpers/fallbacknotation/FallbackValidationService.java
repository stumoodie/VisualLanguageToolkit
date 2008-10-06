package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.repository.IMap;

class FallbackValidationService implements INotationValidationService {
	private INotationSubsystem serviceProvider;
	
	public FallbackValidationService(INotationSubsystem provider) {
		this.serviceProvider=provider;
	}

	public IMap getMapBeingValidated() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public IValidationReport getValidationReport() {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public boolean hasMapBeenValidated() {
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

	public void setMapToValidate(IMap mapToValidate) {
		throw new UnsupportedOperationException("This validation service is not validated");
	}

	public void validateMap() {
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
	public List<IValidationRuleDefinition> getRules() {
		return Collections.emptyList();
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
