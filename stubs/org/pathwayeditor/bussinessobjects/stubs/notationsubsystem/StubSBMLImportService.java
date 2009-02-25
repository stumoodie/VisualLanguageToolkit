/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.io.File;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author nhanlon
 *
 */
public class StubSBMLImportService implements INotationImportService {

	/**
	 * 
	 */
	public static final String DISPLAY_NAME = "Import from SBML file.";

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#getCode()
	 */
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#getDisplayName()
	 */
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#getRecommendedSuffix()
	 */
	public String getRecommendedSuffix() {
		return "txt";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#importMap(java.io.File, org.pathwayeditor.businessobjects.repository.IFolder)
	 */
	public void importToCanvas(File importFile, ICanvas saveLocation) {
		System.out.println("File " + importFile.getAbsolutePath() + " was imported");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
	 */
	public INotation getNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		// TODO Auto-generated method stub
		return null;
	}

}
