/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public interface ICanvasPersistenceHandler {

	void setOwningMap(IMap owningMap);

	IMap getOwningMap();

	void loadCanvas();
	
	void synchroniseCanvas();

	public ICanvas getLoadedCanvas();
	
	/**
	 * Creates a copy ( persisted in the database ) of the currently loaded canvas (which must not be null) and attaches the copy map as its owner
	 * 
	 * @param newMapForCopy a copy of the owning map of the loaded canvas - will be set as owning map of the loaded canvas' copy
	 * newMapForCopy must not be null
	 * 
	 * @return a copy of the loaded canvas
	 */
	public ICanvas createCopyOfCurrentlyLoadedCanvas(IMap newMapForCopy);
	
	/**
	 * Sets the hibernate notation factory, the owning map and loaded canvas to null. This helps avoid memeory leaks
	 * since this object will not hold on to state object references and puts the object in a pristine state as if it
	 * were just initialised.
	 */
	void reset();

	/**
	 * Creates a new canvas for the map specified by <code>getOwningMap()</code>. This creates the canvas, but does not
	 * load it.
	 * @param notationSubsystem the notation subsystem that the new canvas will use.
	 * @throws IllegalStateException if <code>getOwningMap() == null || doesCanvasExist() == true</code>.
	 */
	void createCanvas(INotationSubsystem notationSubsystem);

	/**
	 * Tests if a canvas exists for the current map.
	 * @return true if it does, false otherwise.
	 */
	boolean doesCanvasExist();

}