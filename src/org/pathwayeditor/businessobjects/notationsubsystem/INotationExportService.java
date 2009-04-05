/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import java.io.File;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
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
	 * @return A <code>String</code>. Must not be null and should not contain a period character.
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
	void exportMap(ICanvas canvas, File exportFile) throws ExportServiceException;
	
}
