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
	private final Map<Integer, IMapPersistenceManager> openPersistenceManagers;
	private final ICanvasPersistenceHandlerFactory canvasPersistenceHandlerFactory;
	private final IRepositoryPersistenceHandler repoPersistenceHandler;
	private final AtomicBoolean open;
	private final List<IPersistenceManagerStatusListener> listeners;

	public RepositoryPersistenceManager(IRepositoryPersistenceHandler repoPersistenceHandler, ICanvasPersistenceHandlerFactory canvasPersistenceHandlerFactory) {
		if(repoPersistenceHandler == null || canvasPersistenceHandlerFactory == null) throw new IllegalArgumentException("parameters cannot be null");
		
		this.canvasPersistenceHandlerFactory = canvasPersistenceHandlerFactory;
		this.repoPersistenceHandler = repoPersistenceHandler;
		this.openMaps = new ConcurrentHashMap<IMap, IMapPersistenceManager>();
		this.openPersistenceManagers = new ConcurrentHashMap<Integer, IMapPersistenceManager>();
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
		// if this manager has already created a map manager then return that one. This ensure that in
		// the absence of a lock manager on the DB that clients of this repo will always see the same
		// state information about the map and see the same map contents.
		if (this.openMaps.containsKey(map)) {
			retVal = this.openMaps.get(map);
		} else {
			this.canvasPersistenceHandlerFactory.setMap(map);
			ICanvasPersistenceHandler persistenceHandler = this.canvasPersistenceHandlerFactory.createPersistenceHandler();
			retVal = new MapPersistenceManager(persistenceHandler);
			this.openMaps.put(map, retVal);
			this.openPersistenceManagers.put(map.getINode(), retVal);
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
		if(openPersistenceManagers.get(new Integer(inode))==null)
			throw new IllegalArgumentException("No Map Open with this ID");
		return openPersistenceManagers.get(new Integer(inode));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IRepositoryPersistenceManager#removeMapFromRepository(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void removeMapFromRepository(IMap mapToDelete) {
		if ( mapToDelete.getOwner()!= null || mapToDelete.getRepository()!=null)
			throw new IllegalArgumentException("This map is still assosiated with a folder or the repository.");
			
		//Looks like there is no way to open a map unassociated with a folder. I could not find one at least.
		//Given that this method is being called only from RepoDeletehandler which deletes the canvas first, 
		//and that this is a temporarily solution, I think for the time being this we can live without this check. NT
//		IMapPersistenceManager mapManager = this.getMapPersistenceManager(mapToDelete) ;
//		if ( mapManager.doesCanvasExist() )
//			throw new IllegalArgumentException("This map is still has a canvas associated with.");
		
		this.repoPersistenceHandler.deleteMap(mapToDelete);
		
			
	}
}
