package org.pathwayeditor.businessobjects.contextadapter;

public interface INotation extends Comparable<INotation> {
	
	String getDisplayName();

	String getName();
	
	String getDescription () ;
	
	String getVersionString();
	
	int getMajorVersion();
	
	int getMinorVersion();
	
	int getPatchVersion();
	
	boolean equals(Object other);
	
	int hashCode();

	/**
	 * @return
	 */
	String getGlobalId();
}
