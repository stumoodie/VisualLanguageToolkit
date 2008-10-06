/**
 * 
 */
package org.pathwaueditor.bussinessobjects.stubs;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author smoodie
 *
 */
public class StubNotationSubsystemPool implements INotationSubsystemPool {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#getSubsystem(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public INotationSubsystem getSubsystem(INotation notation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#hasNotationSubsystem(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public boolean hasNotationSubsystem(INotation notation) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#subsystemIterator()
	 */
	public Iterator<INotationSubsystem> subsystemIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
