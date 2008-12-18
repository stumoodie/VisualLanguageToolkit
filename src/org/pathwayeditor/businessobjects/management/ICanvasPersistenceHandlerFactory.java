/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public interface ICanvasPersistenceHandlerFactory {

	void setMap(IMap map);
	
	IMap getMap();

	ICanvasPersistenceHandler createPersistenceHandler();
}
