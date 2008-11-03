/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;

/**
 * @author smoodie
 *
 */
public class StubNotationSubsystemPool implements INotationSubsystemPool {
	private final Map<INotation, INotationSubsystem> lookup;
	
	public StubNotationSubsystemPool(){
		this.lookup = new HashMap<INotation, INotationSubsystem>();
		INotationSubsystem notationSubsystem = new StubNotationSubSystem();
		this.lookup.put(notationSubsystem.getNotation(), notationSubsystem);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#getSubsystem(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public INotationSubsystem getSubsystem(INotation notation) {
		return this.lookup.get(notation);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#hasNotationSubsystem(org.pathwayeditor.businessobjects.notationsubsystem.INotation)
	 */
	public boolean hasNotationSubsystem(INotation notation) {
		return this.lookup.containsKey(notation);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.INotationSubsystemPool#subsystemIterator()
	 */
	public Iterator<INotationSubsystem> subsystemIterator() {
		return this.lookup.values().iterator();
	}

}
