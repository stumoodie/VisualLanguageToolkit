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
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.io.File;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

import uk.ac.ed.inf.graph.compound.ICompoundGraph;

/**
 * @author nhanlon
 *
 */
public class StubSBGNImportService implements INotationImportService {

	/**
	 * 
	 */
	public static final String DISPLAY_NAME = "Import from SGBN file.";

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#getCode()
	 */
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#getRecommendedSuffix()
	 */
	@Override
	public String getRecommendedSuffix() {
		return "xml";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService#importMap(java.io.File, org.pathwayeditor.businessobjects.repository.IFolder)
	 */
	@Override
	public void importToCanvas(File importFile, ICompoundGraph saveLocation) {
		System.out.println("File " + importFile.getAbsolutePath() + " was imported");
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
