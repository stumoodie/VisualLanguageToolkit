package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;


class FallbackLayoutService implements INotationAutolayoutService {

	public FallbackLayoutService(INotationSubsystem provider) {
		this.serviceProvider=provider;
	}
	private INotationSubsystem serviceProvider;

	public INotationSubsystem getNotationSubsystem() {
		return serviceProvider;
	}

	public INotation getNotation() {
		return serviceProvider.getNotation();
	}

	public boolean isImplemented() {
		return false;
	}


}