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

import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * Implementors will provide a factory that creates a new CanvasPersistenceHandler
 * that uses the given map to obtain the canvas. 
 * 
 * @author smoodie
 * 
 */
public interface ICanvasPersistenceHandlerFactory {

	/**
	 * Sets the map to be used to create the persistence handler
	 * @param map the map to be used, which can be null.
	 */
	void setMap(IMap map);
	
	/**
	 * Get the map that will be used to create the canvas persistence handler. 
	 * @return the map, which can be null.
	 */
	IMap getMap();

	/**
	 * Creates and returns a new canvas persistence handler using the currently set
	 * map, which should not be null. 
	 * @return the newly created persistence handler.
	 * @throws IllegalStateException if <code>getMap() == null</code>.
	 */
	ICanvasPersistenceHandler createPersistenceHandler();
}
