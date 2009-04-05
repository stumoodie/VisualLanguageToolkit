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
package org.pathwayeditor.businessobjects.management.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandlerFactory;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceHandler;
import org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager;
import org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener.StateChange;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;

/**
 * @author smoodie
 * 
 */
public class RepositoryPersistenceManager implements IRepositoryPersistenceManager {
	/**
	 * 
	 */
	private static final String MANAGER_IS_ALREADY_OPEN = "Manager is already open";
	/**
	 * 
	 */
	private static final String MANAGER_NOT_OPEN = "Manager not open";
	private final Map<IMap, IMapPersistenceManager> openMaps;
//	private final Map<Integer, IMapPersistenceManager> openPersistenceManagers;
	private final ICanvasPersistenceHandlerFactory canvasPersistenceHandlerFactory;
	private final IRepositoryPersistenceHandler repoPersistenceHandler;
	private final AtomicBoolean open;
	private final List<IPersistenceManagerStatusListener> listeners;

	public RepositoryPersistenceManager(IRepositoryPersistenceHandler repoPersistenceHandler, ICanvasPersistenceHandlerFactory canvasPersistenceHandlerFactory) {
		if(repoPersistenceHandler == null || canvasPersistenceHandlerFactory == null) throw new IllegalArgumentException("parameters cannot be null");
		
		this.canvasPersistenceHandlerFactory = canvasPersistenceHandlerFactory;
		this.repoPersistenceHandler = repoPersistenceHandler;
		this.openMaps = new ConcurrentHashMap<IMap, IMapPersistenceManager>();
//		this.openPersistenceManagers = new ConcurrentHashMap<Integer, IMapPersistenceManager>();
		this.open = new AtomicBoolean(false);
		this.listeners = new LinkedList<IPersistenceManagerStatusListener>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public IRepository getRepository() {
		if(!this.isOpen()) throw new IllegalStateException(MANAGER_NOT_OPEN);
		
		return this.repoPersistenceHandler.getLoadedRepository();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isRepositoryOpen()
	 */
	public boolean isOpen() {
		return this.open.get();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#openRepository()
	 */
	public void open()  {
		if(isOpen()) throw new IllegalStateException(MANAGER_IS_ALREADY_OPEN);
	
		this.repoPersistenceHandler.loadRepository();
		this.open.set(true);
		this.fireStateChange(StateChange.OPENED);
	}

	private void fireStateChange(StateChange status) {
		for(IPersistenceManagerStatusListener listener : this.listeners) {
			listener.stateChanged(status, this);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#closeRepository()
	 */
	public void close(boolean forceClose) {
		if(forceClose || !requestCancelStateChange(StateChange.CLOSED)) {
			this.repoPersistenceHandler.reset();
			this.open.set(false);
			this.fireStateChange(StateChange.CLOSED);
		}
	}

	private boolean requestCancelStateChange(StateChange stateChange) {
		boolean retVal = false;
		for (IPersistenceManagerStatusListener listener : this.listeners) {
			if(listener.requestCancelStateChange(stateChange, this) && retVal == false) {
				retVal = true;
			}
		}
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseRepository(org.pathwayeditor.businessobjects.repository.IRepository)
	 */
	public void synchronise() {
		if(!this.isOpen()) throw new IllegalStateException(MANAGER_NOT_OPEN);

		this.repoPersistenceHandler.synchroniseRepository();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#canOpenCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public boolean isValidMap(IMap map)  {
		return map != null && map.getRepository().equals(this.getRepository());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IRepositoryManager#openMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public IMapPersistenceManager getMapPersistenceManager(IMap map) {
		if (!this.isOpen())
			throw new IllegalStateException(MANAGER_NOT_OPEN);

		if (!this.isValidMap(map))
			throw new IllegalArgumentException("Invalid map provided as an argument");

		IMapPersistenceManager retVal = null;
		// if this manager has already created a map manager then return that one. This ensures that in
		// the absence of a lock manager on the DB that clients of this repo will always see the same
		// state information about the map and see the same map contents.
		if (this.openMaps.containsKey(map)) {
			retVal = this.openMaps.get(map);
		} else {
			this.canvasPersistenceHandlerFactory.setMap(map);
			ICanvasPersistenceHandler persistenceHandler = this.canvasPersistenceHandlerFactory.createPersistenceHandler();
			retVal = new MapPersistenceManager(persistenceHandler);
			this.openMaps.put(map, retVal);
//			this.openPersistenceManagers.put(map.getINode(), retVal);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IPersistenceManager#addListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)
	 */
	public void addListener(IPersistenceManagerStatusListener listener) {
		if(listener == null) throw new IllegalArgumentException("listener cannot be null");
		
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IPersistenceManager#listenerIterator()
	 */
	public Iterator<IPersistenceManagerStatusListener> listenerIterator() {
		return this.listeners.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IPersistenceManager#removeListener(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener)
	 */
	public void removeListener(IPersistenceManagerStatusListener listener) {
		if(listener == null) throw new IllegalArgumentException("listener cannot be null");

		this.listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager#getMapPersistenceManager(java.lang.Long)
	 */
	public IMapPersistenceManager getMapPersistenceManager(int inode) {
		IRepository repo = this.repoPersistenceHandler.getLoadedRepository();
		IRepositoryItem item = repo.findRepositoryItemByINode(inode);
		if(item == null || !(item instanceof IMap)){
			throw new IllegalArgumentException("The inode must be of a map help within this repository");
		}
		return this.getMapPersistenceManager((IMap)item);
	}
}
