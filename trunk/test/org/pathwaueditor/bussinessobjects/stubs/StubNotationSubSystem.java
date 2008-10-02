/**
 * 
 */
package org.pathwaueditor.bussinessobjects.stubs;

import java.util.Set;

import org.pathwayeditor.businessobjects.contextadapter.INotation;
import org.pathwayeditor.businessobjects.contextadapter.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.contextadapter.INotationConversionService;
import org.pathwayeditor.businessobjects.contextadapter.INotationExportService;
import org.pathwayeditor.businessobjects.contextadapter.INotationImportService;
import org.pathwayeditor.businessobjects.contextadapter.INotationPluginService;
import org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem;
import org.pathwayeditor.businessobjects.contextadapter.INotationSyntaxService;
import org.pathwayeditor.businessobjects.contextadapter.INotationValidationService;

/**
 * @author smoodie
 *
 */
public class StubNotationSubSystem implements INotationSubsystem {

	private final INotation notation = new StubNotation();
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getAutolayoutService()
	 */
	public INotationAutolayoutService getAutolayoutService() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getConversionServices()
	 */
	public Set<INotationConversionService> getConversionServices() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getExportServices()
	 */
	public Set<INotationExportService> getExportServices() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getImportServices()
	 */
	public Set<INotationImportService> getImportServices() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getSyntaxService()
	 */
	public INotationSyntaxService getSyntaxService() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.contextadapter.INotationSubsystem#getValidationService()
	 */
	public INotationValidationService getValidationService() {
		// TODO Auto-generated method stub
		return null;
	}

}
