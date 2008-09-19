package org.pathwayeditor.businessobjects.contextadapter;

public interface IContext {
	
	String getGlobalId();
	
	String getDisplayName();

	String getName();
	
	String getDescription () ;
	
	String getVersionString();
	
	int getMajorVersion();
	
	int getMinorVersion();
	
	int getPatchVersion();
}
