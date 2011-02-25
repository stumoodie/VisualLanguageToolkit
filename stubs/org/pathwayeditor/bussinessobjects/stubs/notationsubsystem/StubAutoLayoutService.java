/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author Stuart Moodie
 *
 */
public class StubAutoLayoutService implements INotationAutolayoutService {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService#isImplemented()
	 */
	@Override
	public boolean isImplemented() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
	 */
	@Override
	public INotation getNotation() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
	 */
	@Override
	public INotationSubsystem getNotationSubsystem() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService#layout(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	@Override
	public void layout(IModel canvas) {
		System.out.println("Canvas " + canvas + " laid out.");
	}

}
