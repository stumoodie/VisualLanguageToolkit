package org.pathwayeditor.businessobjects.contectadapter;

import java.io.File;
import java.util.List;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;

public interface IContextAdapterImportService {

	IContext getContext();
	
	/**
	 * Get Service provider which was be used to instantiate this service. 
	 * This method could be used to get access to other services, which are registered for
	 * that context. 
	 * @return An non-null service provider which this service is registered with
	 */
	IContextAdapterServiceProvider getServiceProvider();

	String getDisplayName();
	
	String getCode();
	
	String getRecommendedSuffix();
	
	void importMap(File importFile, IFolder saveLocation);
	
	boolean wasImportSuccessful();
	
	List<String> getErrorReports();
	
	IMap getImportedMap();
}
