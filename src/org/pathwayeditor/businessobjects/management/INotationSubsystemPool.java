/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
