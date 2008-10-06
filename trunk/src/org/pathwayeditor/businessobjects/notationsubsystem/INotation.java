package org.pathwayeditor.businessobjects.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;

public interface INotation extends Comparable<INotation> {
	
	String getDisplayName();

	String getName();
	
	String getDescription () ;
	
	Version getVersion();
	
	boolean equals(Object other);
	
	int hashCode();

	/**
	 * Gets the global identifier for this version of the notation. This uniquely identifies the notation.
	 * @return the global id that globally uniquely identifies this version of the notation.
	 */
	String getGlobalId();
}
