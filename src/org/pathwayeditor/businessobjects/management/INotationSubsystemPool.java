/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author smoodie
 *
 */
public interface INotationSubsystemPool {

	/**
	 * Tests this pool contains a given notation subsystem. 
	 * @param notation the notation to find a subsystem for, can be null.
	 * @return true if this pool contains a subsystem for <code>notation</code>, false otherwise.
	 */
	boolean hasNotationSubsystem(INotation notation);
	
	/**
	 * Provides an iterator to provide access to all the Notation subsystems available from this pool.
	 * @return an iterator, which cannot be null.
	 */
	Iterator<INotationSubsystem> subsystemIterator();
	
	/**
	 * Gets the subsystem for the given notation.
	 * @param notation the notation to find a subsystem for, cannot be null.
	 * @return the subsystem corresponding to the given notation.
	 * @throws IllegalArgumentException if <code>hasNotationSubsystem(notation)==false</code>.
	 */
	INotationSubsystem getSubsystem(INotation notation);
	
}
