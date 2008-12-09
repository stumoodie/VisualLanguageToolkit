/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author smoodie
 *
 */
public class StubNotationSubsystemPool implements INotationSubsystemPool {
	
	private Set <INotationSubsystem> subSystemIterator = new HashSet<INotationSubsystem> ();
	private INotationSubsystem notationSubSystem= new StubNotationSubSystem(); 
	private INotationSubsystem notationSubSystem1= new StubNotationSubSystem("a");
	private INotationSubsystem notationSubSystem2= new StubNotationSubSystem("b");
	private INotationSubsystem notationSubSystem3= new StubNotationSubSystem("c");
	private INotationSubsystem notationSubSystem4= new StubNotationSubSystem("d");
	private INotationSubsystem notationSubSystem5= new StubNotationSubSystem("e");

	
	public StubNotationSubsystemPool () 
	{
		subSystemIterator.add(notationSubSystem) ;
//		subSystemIterator.add(notationSubSystem1) ; //only added for testing scrolling of GUI boxes. Do not add back
//		subSystemIterator.add(notationSubSystem2) ;
//		subSystemIterator.add(notationSubSystem3) ;
//		subSystemIterator.add(notationSubSystem4) ;
//		subSystemIterator.add(notationSubSystem5) ;
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
