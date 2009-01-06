/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener.StateChange;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 * 
 */
public class RepositoryPersistenceManager implements IRepositoryPersistenceManager {
	private final Object myLock = new Object();
	private final Map<IMap, IMapPersistenceManager> openMaps;
	private final ICanvasPersistenceHandlerFactory canvasPersistenceHandlerFactory;
	private final IRepositoryPersistenceHandler repoPersistenceHandler;
	private final AtomicBoolean open;
	private final List<IPersistenceManagerStatusListener> listeners;

	public RepositoryPersistenceManager(IRepositoryPersistenceHandler repoPersistenceHandler, ICanvasPersistenceHandlerFactory canvasPersistenceHandlerFactory) {
		if(repoPersistenceHandler == null || canvasPersistenceHandlerFactory == null) throw new IllegalArgumentException("parameters cannot be null");
		
		this.canvasPersistenceHandlerFactory = canvasPersistenceHandlerFactory;
		this.repoPersistenceHandler = repoPersistenceHandler;
		this.openMaps = new ConcurrentHashMap<IMap, IMapPersistenceManager>();
		this.open = new AtomicBoolean(false);
		this.listeners = new CopyOnWriteArrayList<IPersistenceManagerStatusListener>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public IRepository getRepository() throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(!this.isOpen()) throw new PersistenceManagerNotOpenException(this);
			return this.repoPersistenceHandler.getLoadedRepository();
		}
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
	public void open() throws PersistenceManagerAlreadyOpenException {
		synchronized(myLock){
			if(isOpen()) throw new PersistenceManagerAlreadyOpenException(this);
			this.repoPersistenceHandler.loadRepository();
			this.open.set(true);
			this.fireStateChange(StateChange.OPENED);
		}
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
		synchronized(myLock){
			if(forceClose || !requestCancelStateChange(StateChange.CLOSED)) {
				this.repoPersistenceHandler.reset();
				this.open.set(false);
				this.fireStateChange(StateChange.CLOSED);
			}
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
	public void synchronise() throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(!this.isOpen()) throw new PersistenceManagerNotOpenException(this);

			this.repoPersistenceHandler.synchroniseRepository();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#canOpenCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public boolean isValidMap(IMap map) throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			return map != null && map.getRepository().equals(this.getRepository()) && this.isOpen();
		}
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#openCanvasIterator()
//	 */
//	public Iterator<IMap> openMapIterator() throws PersistenceManagerNotOpenException {
//		synchronized(myLock){
//			if(this.isOpen() == false) throw new PersistenceManagerNotOpenException(this);
//			return this.openMaps.keySet().iterator();
//		}
//	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isCanvasOpen(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
//	 */
//	public boolean isMapOpen(IMap map) {
//		synchronized(myLock){
//			return this.isOpen() && this.openMaps.containsKey(map);
//		}
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IRepositoryManager#openMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public IMapPersistenceManager getMapPersistenceManager(IMap map) throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(!this.isOpen()) throw new PersistenceManagerNotOpenException(this);
			if(!this.isValidMap(map)) throw new IllegalArgumentException("Invalid map provided as an argument");
			
			IMapPersistenceManager retVal = null;
			// if this manager has already created a map manager then return that one. This ensure that in
			// the absence of a lock manager on the DB that clients of this repo will always see the same
			// state information about the map and see the same map contents.
			if(this.openMaps.containsKey(map)) {
				retVal = this.openMaps.get(map);
			}
			else {
				this.canvasPersistenceHandlerFactory.setMap(map);
				ICanvasPersistenceHandler persistenceHandler = this.canvasPersistenceHandlerFactory.createPersistenceHandler();
				retVal = new MapPersistenceManager(persistenceHandler);
//				retVal.addListener(new MapContentListener());
				this.openMaps.put(map, retVal);
			}
			return retVal;
		}
	}

//	private void updateStatus(IMapPersistenceManager mapContentPersistenceManager) {
//		IMap map = mapContentPersistenceManager.getMap();
//		synchronized (myLock) {
//			if (this.openMaps.containsKey(map)) {
//				// we have the map stored so we think it is open.
//				if (!mapContentPersistenceManager.isOpen()) {
//					// if the map is not open then remove it.
//					this.openMaps.remove(map);
//				}
//			} else {
//				// it is not stored so we think it is closed
//				if (mapContentPersistenceManager.isOpen()) {
//					// it is not closed so we should added to the collection of
//					// open maps
//					this.openMaps.put(map, mapContentPersistenceManager);
//				}
//			}
//		}
//	}
	
//	private class MapContentListener implements IPersistenceManagerStatusListener {
//
//		/* (non-Javadoc)
//		 * @see org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener#requestCancelStateChange(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener.StateChange, org.pathwayeditor.businessobjects.management.IPersistenceManager)
//		 */
//		public boolean requestCancelStateChange(StateChange stateChange, IPersistenceManager changeManager) {
//			return false;
//		}
//
//		/* (non-Javadoc)
//		 * @see org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener#stateChanged(org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener.StateChange, org.pathwayeditor.businessobjects.management.IPersistenceManager)
//		 */
//		public void stateChanged(StateChange stateChange, IPersistenceManager changedManager) {
////			updateStatus((IMapPersistenceManager)changedManager);
//		}
//		
//	}

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
}
