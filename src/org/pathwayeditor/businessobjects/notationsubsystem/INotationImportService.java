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

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;

public interface INotationImportService extends INotationService {
	/**
	 * The display name for this service that may be used by the user interface.
	 * @return the display name, which cannot be null.
	 */
	String getDisplayName();
	
	/**
	 * Gets the unique code for this import. Can be used to look up this service byt a client.
	 * @return the import service code, which must be unique within the owning NotationSubsystem.
	 */
	String getCode();
	
	/**
	 * The recommended suffix for this type of import file. Can be used by clients when searching
	 * for files or in file dialogs.
	 * @return the suffix which cannot be null, but can be empty. It should not contain a period character.
	 */
	String getRecommendedSuffix();
	
	/**
	 * Imports the contents of a file in a particular file format into an empty canvas.
	 * @param importFile the file to be imported, which cannot be null and must be readable.
	 * @param canvas the canvas, which cannot be null and should be empty.
	 * @throws ImportServiceException if an error occurs importing the file
	 * @throws IllegalArgumentException if <code>canvas == null || canvas.isEmpty() == false</code>
	 * @throws IllegalArgumentException if <code>importFile == null || importFile.canRead() == false</code>
	 */
	void importToCanvas(File importFile, IModel canvas) throws ImportServiceException;
}
