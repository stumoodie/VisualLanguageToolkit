package org.pathwayeditor.businessobjects.contectadapter;

public interface IContext {
	
	String getGlobalId();
	
	String getDisplayName();

	String getName();
	
	String getVersionString();
	
	int getMajorVersion();
	
	int getMinorVersion();
	
	int getPatchVersion();
}
