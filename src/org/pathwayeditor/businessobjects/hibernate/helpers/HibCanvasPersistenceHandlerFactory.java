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
