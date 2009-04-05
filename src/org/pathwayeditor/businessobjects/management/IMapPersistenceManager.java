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
 * Defines a manager of map content persistence. It deals with the life-cycle of the 
 * map's contents, currently just a canvas.
 * @author smoodie
 *
 */
public interface IMapPersistenceManager extends IPersistenceManager {
	/**
	 * Gets the map that owns the content managed here.
	 * @return the map that owns this content, which cannot be null.
	 */
	IMap getMap();
		
	/**
	 * Tests if this map has a canvas.
	 * @return true if it does, false otherwise.
	 * @throws IllegalStateException if this manager is not open.
	 */
	boolean doesCanvasExist();
	
	/**
	 * Creates a new canvas using the given notation subsystem. The newly created canvas
	 * is then available via <code>getCanvas()</code>.
	 * @param notationSubsystem the notation subsystem, which cannot be null.
	 * @throws IllegalStateException if the manager is not open. 
	 * @throws IllegalArgumentException if notationSubsystem is null.
	 */
	void createCanvas(INotationSubsystem notationSubsystem);
	
	/**
	 * Get the canvas associated with the map.
	 * @return a canvas belonging to the owning map, cannot be null.
	 * @throws IllegalStateException if <code>isOpen() == false</code>.
	 * @throws IllegalStateException if <code>doesCanvasExist()==false</code>. 
	 */
	ICanvas getCanvas();
	
	/**
	 * Deletes the canvas from this map.
	 * @throws IllegalStateException if this manager is not open.
	 */
	void deleteCanvas();
}
