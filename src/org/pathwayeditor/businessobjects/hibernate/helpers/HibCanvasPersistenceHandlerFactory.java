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
package org.pathwayeditor.businessobjects.hibernate.helpers;

import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandlerFactory;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class HibCanvasPersistenceHandlerFactory implements ICanvasPersistenceHandlerFactory {
	private final SessionFactory fact;
	private final INotationSubsystemPool subsystemPool;
	private IMap map;
	
	public HibCanvasPersistenceHandlerFactory(SessionFactory fact, INotationSubsystemPool subsystemPool) {
		this.fact = fact;
		this.subsystemPool = subsystemPool;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandlerFactory#getMap()
	 */
	public IMap getMap() {
		return this.map;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandlerFactory#getPersistenceHandler()
	 */
	public ICanvasPersistenceHandler createPersistenceHandler() {
		return new HibCanvasPersistenceHandler(this.fact, this.subsystemPool, this.map);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandlerFactory#setMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void setMap(IMap map) {
		this.map = map;
	}

}
