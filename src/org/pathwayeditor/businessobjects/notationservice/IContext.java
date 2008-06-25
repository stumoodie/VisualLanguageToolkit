package org.pathwayeditor.businessobjects.notationservice;

public interface IContext {
	
	String getGlobalId();
	
	String getDisplayName();

	String getName();
	
	String getVersionString();
	
	int getMajorVersion();
	
	int getMinorVersion();
	
	int getPatchVersion();
}
