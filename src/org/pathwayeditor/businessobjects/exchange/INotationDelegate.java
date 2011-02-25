/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import org.pathwayeditor.businessobjects.notationsubsystem.INotation;

/**
 * @author Stuart Moodie
 *
 */
public interface INotationDelegate {

	INotation getNotation();
	
//	Iterator<IObjectInfo> objectTypeIterator();
	
	int numObjectTypes();
	
}
