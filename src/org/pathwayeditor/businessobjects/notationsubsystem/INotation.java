/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;

/**
 * Identifies a notation. It provides a name and description together with mechanisms for uniquely identifying it.
 * The notation has two business keys. The first is its name and version. The other is the global id. Both combinations
 * should be globally unique.  
 * @author Stuart Moodie
 *
 */
public interface INotation extends Comparable<INotation> {

	/**
	 * The name used to globally identify this class of notation. A notation of the same class can have
	 * multiple versions. The recommendation is to use a package name as in Java that is based on an
	 * Internet domain name that you own. 
	 * @return a qualified name, which cannot be null.
	 */
	String getQualifiedName();
	
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
	 * @return the notation version, cannot be null.
	 */
	Version getVersion();
	
	/**
	 * Tests the equality of this notation with another notation base on the qualified name and the version number. Otherwise follows the
	 * standard equals contract.  
	 * @param other the other object to test.
	 * @return true if the qualified names and version numbers of both notations are the same, false otherwise.
	 */
}
