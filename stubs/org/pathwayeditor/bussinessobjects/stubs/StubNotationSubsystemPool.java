/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;

/**
 * @author smoodie
 *
 */
public class StubNotationSubsystemPool implements INotationSubsystemPool {
	
	private Set <INotationSubsystem> subSystemIterator = new HashSet<INotationSubsystem> ();
	private INotationSubsystem notationSubSystem= new StubNotationSubSystem(); 

	
	public StubNotationSubsystemPool () 
	{
		subSystemIterator.add(notationSubSystem) ;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#getSubsystem(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public INotationSubsystem getSubsystem(INotation notation) {
		return notationSubSystem ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#hasNotationSubsystem(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public boolean hasNotationSubsystem(INotation notation) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#subsystemIterator()
	 */
	public Iterator<INotationSubsystem> subsystemIterator() {
		
		return this.subSystemIterator.iterator();
	}

}
