package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.Collections;
import java.util.Set;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationConversionService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationPluginService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;

public class FallbackNotationSubsystem implements INotationSubsystem {
	private final INotation notation;
	private final INotationAutolayoutService autoLayoutService;
	private final INotationSyntaxService syntaxService;
	private final INotationValidationService validationService;
	
	public FallbackNotationSubsystem(HibNotation hibNotation) {
		this.notation = hibNotation;
		this.autoLayoutService = new FallbackLayoutService(this);
		this.syntaxService = new FallbackSyntaxService(this, hibNotation);
		this.validationService = new FallbackValidationService(this);
	}
	
	public INotationAutolayoutService getAutolayoutService() {
		return this.autoLayoutService;
	}

	public INotation getNotation() {
		return this.notation;
	}

	public Set<INotationConversionService> getConversionServices() {
		return Collections.emptySet();
	}

	public Set<INotationExportService> getExportServices() {
		return Collections.emptySet();
	}

	public Set<INotationImportService> getImportServices() {
		return Collections.emptySet();
	}

	public Set<INotationPluginService> getPluginServices() {
		return Collections.emptySet();
	}

	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}

	public INotationValidationService getValidationService() {
		return this.validationService;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#isFallback()
	 */
	public boolean isFallback() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.notation == null) ? 0 : this.notation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FallbackNotationSubsystem))
			return false;
		FallbackNotationSubsystem other = (FallbackNotationSubsystem) obj;
		if (this.notation == null) {
			if (other.notation != null)
				return false;
		} else if (!this.notation.equals(other.notation))
			return false;
		return true;
	}
	
}
