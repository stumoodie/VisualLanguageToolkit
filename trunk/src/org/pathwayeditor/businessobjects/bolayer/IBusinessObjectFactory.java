/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public interface IBusinessObjectFactory {

	/**
	 * Tests if the repository is currently open.
	 * @return true if it is, false otherwise.
	 */
	boolean isRepositoryOpen();	
	
	/**
	 * Gets the repository for this instance that contains all the folders,
	 * and maps. It will load it from persistent storage if necessary. 
	 * @throws IllegalStateException if <code>isRepositoryOpen() == true</code> when this is called. 
	 */
	void openRepository();
	
	/**
	 * Gets the repository for this instance that contains all the folders,
	 * and maps. 
	 * @return the repository which is ready to be used, cannot be null.
	 * @throws IllegalStateException if <code>isRepositoryOpen() == false</code>. 
	 */
	IRepository getRepository();
	
	/**
	 * Synchronises the repository with persistent storage. 
	 * @throws IllegalStateException if <code>isRepositoryOpen() == false</code>. 
	 */
	void synchroniseRepository();
	
	/**
	 * Closes the repository. If the repository is already closed, then does nothing.
	 */
	void closeRepository();
	
	/**
	 * Tests if a canvas belonging to owning map can be opened. 
	 * @param owningMap the map to be tested, can be null.
	 * @return <code>true</code> if the canvas can be opened, false otherwise.
	 */
	boolean canOpenCanvas(IMap owningMap);
	
	
	/**
	 * Returns an iterator to the set of canvases that are currently open. Note that this may
	 * include Canvases, being used by other EPE clients, if the repository if stored on a server.
	 * @return the iterator, which will not be null.
	 * @throws IllegalStateException if <code>isRepositoryOpen() == false</code>. 
	 */
	Iterator<ICanvas> openCanvasIterator();
	
	/**
	 * Tests if the given canvas is currently open. (If the repository is stored on a server then
	 * it may not necessarily be opened by this client).
	 * @param canvas the canvas to test, which cannot be null.
	 * @return true if the canvas is open, false otherwise.
	 */
	boolean isCanvasOpen(ICanvas canvas);
	
	/**
	 * Tests if the canvas belongs to this repository.
	 * @param canvas the canvas to test, cannot be null.
	 * @return true if it belongs to this repository, false otherwise.
	 */
	boolean isCanvasOwnedHere(ICanvas canvas);
	
	/**
	 * Get the canvas associated with the given map.
	 * @param map the map to be loaded. It must exists in the repository and the canvas
	 * can be opened.
	 * @return a canvas belonging to the specified map, cannot be null.
	 * @throws IllegalArgumentException if <code>canOpenCanvas(map)</code>.
	 * @throws IllegalStateException if <code>isRepositoryOpen() == false</code>. 
	 */
	ICanvas openCanvas(IMap map);
	
	/**
	 * Synchronised the canvas with persistent storage. Note that the canvas must be open.
	 * @param canvas the canvas to be synchronised, which cannot be null.
	 * @throws IllegalArgumentException if <code>isCanvasOwnedHere(canvas) == false</code>.
	 * @throws IllegalStateException if <code>isRepositoryOpen() == false</code>. 
	 */
	void synchroniseCanvas(ICanvas canvas);
	
	/**
	 * Close the canvas. If the canvas or repository is already closed, then do nothing.
	 * @param canvas the canvas to be closed.
	 * @throws IllegalArgumentException if <code>isCanvasCanvasOwnedHere(canvas) == false</code>.
	 */
	void closeCanvas(ICanvas canvas);
	
//	/**
//	 * Creates a new canvas that is not attached to a map and so can be used as a "clipboard"
//	 * to manipulate diagrams. The canvas created here cannot be persisted.
//	 * @return
//	 */
//	ICanvas createTransientCanvas();
}
