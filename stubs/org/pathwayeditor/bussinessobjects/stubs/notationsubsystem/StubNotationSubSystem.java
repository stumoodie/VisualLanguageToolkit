/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Collections;
import java.util.Set;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationConversionService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationPluginService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;

/**
 * @author smoodie
 *
 */
public class StubNotationSubSystem implements INotationSubsystem {
	private final INotation notation;
	private final INotationAutolayoutService autolayoutService;
	private INotationSyntaxService syntaxService;
	private INotationValidationService validationService;
	
	public StubNotationSubSystem(){
		this.notation = new StubNotation();
		this.autolayoutService = new StubAutoLayoutService();
		this.syntaxService = new StubNotationSyntaxService(this);
		this.validationService = new StubNotationValidationService(this);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getAutolayoutService()
	 */
	public INotationAutolayoutService getAutolayoutService() {
		return this.autolayoutService;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getConversionServices()
	 */
	public Set<INotationConversionService> getConversionServices() {
		return Collections.emptySet();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getExportServices()
	 */
	public Set<INotationExportService> getExportServices() {
		return Collections.emptySet();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getImportServices()
	 */
	public Set<INotationImportService> getImportServices() {
		return Collections.emptySet();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getNotation()
	 */
	public INotation getNotation() {
		return this.notation;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getPluginServices()
	 */
	public Set<INotationPluginService> getPluginServices() {
		return Collections.emptySet();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getSyntaxService()
	 */
	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getValidationService()
	 */
	public INotationValidationService getValidationService() {
		return this.validationService;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#isFallback()
	 */
	public boolean isFallback() {
		return false;
	}

}
