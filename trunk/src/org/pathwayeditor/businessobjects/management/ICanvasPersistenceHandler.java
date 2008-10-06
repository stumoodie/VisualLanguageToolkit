/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
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
	 * Sets the hibernate notation factory, the owning map and loaded canvas to null. This helps avoid memeory leaks
	 * since this object will not hold on to state object references and puts the object in a pristine state as if it
	 * were just initialised.
	 */
	void reset();

}