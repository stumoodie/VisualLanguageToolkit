/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public interface IBusinessObjectFactory {

	/**
	 * Gets the repository by name that contains all the folders,
	 * and maps. It will load it from persistent storage if necessary.  
	 * @return the repository
	 * @Param name the name of the repository which is guaranteed unique
	 */
	IRepository getRepository(String name);
	
	/**
	 * Synchronises the repository with persistent storage. 
	 * @param repository
	 */
	void synchroniseRepository(IRepository repository);
	
	/**
	 * Get the canvas associated with the given map
	 * @param map
	 * @return
	 */
	ICanvas getCanvas(IMap map);
	
	/**
	 * Synchronised the canvas with persistent storage. Note that the canvas cannot be transient.
	 * @param canvas
	 * @throws IllegalArgumentException if the canvas is transient i.e., <code>canvas.isTransient() == true</code>.
	 */
	void synchroniseCanvas(ICanvas canvas);
	
	/**
	 * Creates a new canvas that is not attached to a map and so can be used as a "clipboard"
	 * to manipulate diagrams. The canvas created here cannot be persisted.
	 * @return
	 */
	ICanvas createTransientCanvas();
}
