/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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

	/**
	 * Gets the map that contains this canvas. 
	 * @return the owning map.
	 */
	IMap getOwningMap();

	/**
	 * Loads the canvas from persistent storage. The canvas must exists for this to succeed.
	 * @throws IllegalStateException if <code>doesCanvasExist() == false</code>. 
	 */
	void loadCanvas();
	
	/**
	 * Synchronises the canvas held in persistent storage with the canvas help by this handler.
//	 * @throws IllegalStateException if <code>getLoadedCanvas() == null</code>.
	 */
	void synchroniseCanvas();

	/**
	 * Gets the currently loaded canvas, which can be null if no canvas has been loaded by this handler.
	 * @return the loaded canvas or null if none has been loaded or created.
	 */
	ICanvas getLoadedCanvas();
	
	/**
	 * Sets the hibernate notation factory, the owning map and loaded canvas to null. This helps avoid memory leaks
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

	/**
	 * Deletes the current canvas. This assumes that a canvas has already been loaded or
	 * created.
	 * @throws IllegalStateException if <code>getLoadedCanvas() == null</code>.
	 * @throws IllegalStateException if <code>doesCanvasExist() == false</code>.
	 */
	void deleteCanvas();

}