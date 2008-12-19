/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

/**
 * @author smoodie
 *
 */
public class StubAutoLayoutService implements INotationAutolayoutService {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService#isImplemented()
	 */
	public boolean isImplemented() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
	 */
	public INotation getNotation() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
	 */
	public INotationSubsystem getNotationSubsystem() {
		throw new UnsupportedOperationException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService#layout(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public void layout(ICanvas canvas) {
		System.out.println("Canvas " + canvas + " laid out.");
	}

}
