/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import java.io.File;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

/**
 * 
 * INotationExportService is an interface that defines an export service for the
 * notation subsystem. A notation subsystem can have multiple export services that handle
 * different file formats. The export service takes a model and exports it into the given
 * format. This may be a data format or a graphical image of the model diagram.
 *
 * @author Stuart Moodie
 *
 */
public interface INotationExportService extends INotationService {
    
	/**
	 * A method to return a human-readable form of the 
	 * @return A <code>String</code>. Must not return null.
	 */
	String getDisplayName();
	
	/**
	 * Returns the unique code for this export service.
	 * @return A <code>String</code>, must not be null.
	 */
	String getCode();
	
	/**
	 * Returns the recommended suffix. This  information is for the client so that it knows
     * what extension might be preferred and so it can display it in a file dialogue. The
     * export(IMap, File ) method will take the file as it comes and need not append the suffix.
	 * @return A <code>String</code>. Must not be null and should not contain a period character.
	 */
	String getRecommendedSuffix();
	
	/**
	 * Exports a map to file. It is the client's responsibility to ensure the
	 * <code>exportFile</code> parameter is a valid writable file. Inclusion of a suffix is optional.
	 * Implementations do not have to enforce that  getRecommendedSuffix() is appended.
	 * @param model An {@link IModel} object
	 * @param exportFile A {@link File}
	 * @throws ExportServiceException if the export fails.
	 */
	void exportMap(IModel model, File exportFile) throws ExportServiceException;
	
}
