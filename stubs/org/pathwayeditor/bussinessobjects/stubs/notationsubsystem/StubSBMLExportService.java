/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.io.File;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.ExportServiceException;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author nhanlon
 *
 */
public class StubSBMLExportService implements INotationExportService {

	public static final String DISPLAY_NAME = "SBML Export";

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService#exportMap(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas, java.io.File)
	 */
	@Override
	public void exportMap(IModel map, File exportFile) throws ExportServiceException {
		System.out.println("Map " + map + " was exported to " + exportFile.getAbsolutePath());

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService#getCode()
	 */
	@Override
	public String getCode() {
		return "stub";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		
		return DISPLAY_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService#getRecommendedSuffix()
	 */
	@Override
	public String getRecommendedSuffix() {
		return "xml";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
	 */
	@Override
	public INotation getNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
	 */
	@Override
	public INotationSubsystem getNotationSubsystem() {
		// TODO Auto-generated method stub
		return null;
	}

}
