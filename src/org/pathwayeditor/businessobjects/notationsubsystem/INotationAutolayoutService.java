package org.pathwayeditor.businessobjects.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;

public interface INotationAutolayoutService extends INotationService {

	/**
	 * The CA does not need to implement the auto-layout service. This method tests is it is implemented.
	 * @return true if service is implemented, false otherwise.
	 */
	boolean isImplemented();
	
	/**
	 * @param canvas which will be laid out by this service
	 */
	void layout(ICanvas canvas);
	
}
