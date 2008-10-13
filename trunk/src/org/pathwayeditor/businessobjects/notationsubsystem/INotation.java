package org.pathwayeditor.businessobjects.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;

/**
 * Identifies a notation. It provides a name and description together with mechanisms for uniquely identifying it.
 * The notation has two business keys. The first is its name and version. The other is the global id. Both combinations
 * should be globally unique.  
 * @author smoodie
 *
 */
public interface INotation extends Comparable<INotation> {

	/**
	 * The name used to globally identify this class of notation. A notation of the same class can have
	 * multiple versions.
	 * @return the name
	 */
	String getName();
	
	/**
	 * Gets the name of the notation. This name can be used by clients to display to users. 
	 * @return the name, which can contain spaces and punctuation characters and cannot be null.
	 */
	String getDisplayName();
	
	/**
	 * Gets a description of this notation. This should be a concise description of what the notation is and it's purpose.
	 * @return the description, which cannot be null.
	 */
	String getDescription () ;
	
	/**
	 * Gets the version of the notation. 
	 * @return  the notation version, cannot be null.
	 */
	Version getVersion();
	
	/**
	 * Tests the equality of this notation with another notation base on their globals identifiers. Otherwise follows the
	 * standard equals contract.  
	 * @param other the other object to test.
	 * @return true if the global ids of both notations are the same, false otherwise.
	 */
	boolean equals(Object other);
	
	/**
	 * Return the hash code using the identiy rules that applies to {@link #equals(Object)}
	 * @return the hascode.
	 */
	int hashCode();

	/**
	 * Gets the global identifier for this version of the notation. This uniquely identifies the notation.
	 * @return the global id that globally uniquely identifies this version of the notation.
	 */
	String getGlobalId();
}
