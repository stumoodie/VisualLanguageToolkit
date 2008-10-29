package org.pathwayeditor.businessobjects.notationsubsystem;

import java.io.File;
import java.util.List;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;

public interface INotationImportService extends INotationService {
	String getDisplayName();
	
	String getCode();
	
	String getRecommendedSuffix();
	
	void importMap(File importFile, IFolder saveLocation);
	
	boolean wasImportSuccessful();
	
	List<String> getErrorReports();
	
	IMap getImportedMap();
}
