package org.pathwayeditor.businessobjects.notationsubsystem;

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
	 * Gets the global identifier for this version of the notation. This uniquely identifies the notation.
	 * @return the global id that globally uniquely identifies this version of the notation.
	 */
	String getGlobalId();
}
