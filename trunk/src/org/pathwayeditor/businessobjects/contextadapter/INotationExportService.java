package org.pathwayeditor.businessobjects.contextadapter;

import java.io.File;

import org.pathwayeditor.businessobjects.repository.IMap;

public interface INotationExportService extends INotationService {
    
	/**
	 * A method to return a human-readable form of the 
	 * @return A <code>String</code>. Must not return null.
	 */
	String getDisplayName();
	
	/**
	 * Returns the unique code for this export service. M
	 * @return A <code>String</code>, must not be null.
	 */
	String getCode();
	
	/**
	 * Returns the recommended suffix. This  information is for the client so that it knows
     * what extension might be preferred and so it can display it in a file dialogue. The
     * export(IMap, File ) method will take the file as it comes and need not append the suffix.
	 * @return A <code>String</code>. Must not be null.
	 */
	String getRecommendedSuffix();
	
	/**
	 * Exports a map to file. It is the client's responsibility to ensure the
	 * <code>exportFile</code> parameter is a valid writable file. Inclusion of a suffix is optional.
	 * Implementations do not have to enforce that  getRecommendedSuffix() is appended.
	 * @param map An {@link IMap} object
	 * @param exportFile A {@link File}
	 * @throws ExportServiceException
	 */
	void exportMap(IMap map, File exportFile) throws ExportServiceException;
	
}
