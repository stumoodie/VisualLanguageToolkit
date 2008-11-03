/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 * 
 */
public class RepositoryPersistenceManager implements IRepositoryPersistenceManager {
	private final Object myLock = new Object();
	private final Map<IMap, IMapContentPersistenceManager> openMaps;
	private final ICanvasPersistenceHandler canvasPersistenceHandler;
	private final IRepositoryPersistenceHandler repoPersistenceHandler;
	private final AtomicBoolean open;

	public RepositoryPersistenceManager(IRepositoryPersistenceHandler repoPersistenceHandler, ICanvasPersistenceHandler canvasPersistenceHandler) {
		this.canvasPersistenceHandler = canvasPersistenceHandler;
		this.repoPersistenceHandler = repoPersistenceHandler;
		this.openMaps = new ConcurrentHashMap<IMap, IMapContentPersistenceManager>();
		this.open = new AtomicBoolean(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public IRepository getRepository() throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(!this.isRepositoryOpen()) throw new PersistenceManagerNotOpenException(this);
			return this.repoPersistenceHandler.getLoadedRepository();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isRepositoryOpen()
	 */
	public boolean isRepositoryOpen() {
		return this.open.get();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#openRepository()
	 */
	public void openRepository() throws PersistenceManagerAlreadyOpenException {
		synchronized(myLock){
			if(isRepositoryOpen()) throw new PersistenceManagerAlreadyOpenException(this);
			this.repoPersistenceHandler.loadRepository();
			this.open.set(true);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#closeRepository()
	 */
	public void closeRepository() {
		synchronized(myLock){
			this.repoPersistenceHandler.reset();
			this.open.set(false);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseRepository(org.pathwayeditor.businessobjects.repository.IRepository)
	 */
	public void synchroniseRepository() throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(!this.isRepositoryOpen()) throw new PersistenceManagerNotOpenException(this);

			this.repoPersistenceHandler.synchroniseRepository();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#canOpenCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public boolean canOpenMap(IMap map) throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			return map != null && map.getRepository().equals(this.getRepository())
				&& this.isRepositoryOpen() && !this.openMaps.containsKey(map);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#openCanvasIterator()
	 */
	public Iterator<IMap> openMapIterator() throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(this.isRepositoryOpen() == false) throw new PersistenceManagerNotOpenException(this);
			return this.openMaps.keySet().iterator();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isCanvasOpen(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public boolean isMapOpen(IMap map) {
		synchronized(myLock){
			return this.isRepositoryOpen() && this.openMaps.containsKey(map);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IRepositoryManager#openMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public IMapContentPersistenceManager openMap(IMap map) throws PersistenceManagerNotOpenException {
		if(map == null) throw new IllegalArgumentException("map cannot be null");
		synchronized(myLock){
			if(!this.isRepositoryOpen()) throw new PersistenceManagerNotOpenException(this);
			IMapContentPersistenceManager retVal = new MapContentPersistenceManager(map, this.canvasPersistenceHandler);
			retVal.addListener(new MapContentListener());
			return retVal;
		}
	}

	/**
	 * @param mapContentPersistenceManager
	 */
	private void updateStatus(IMapContentPersistenceManager mapContentPersistenceManager) {
		IMap map = mapContentPersistenceManager.getOwningMap();
		synchronized (myLock) {
			if (this.openMaps.containsKey(map)) {
				// we have the map stored so we think it is open.
				if (!mapContentPersistenceManager.isOpen()) {
					// if the map is not open then remove it.
					this.openMaps.remove(map);
				}
			} else {
				// it is not stored so we think it is closed
				if (mapContentPersistenceManager.isOpen()) {
					// it is not closed so we should added to the collection of
					// open maps
					this.openMaps.put(map, mapContentPersistenceManager);
				}
			}
		}
	}
	
	private class MapContentListener implements IMapContentManagerStatusListener {

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.bolayer.IPersistenceManagerListener#stateChanged(org.pathwayeditor.businessobjects.bolayer.IMapContentManager)
		 */
		public void stateChanged(IMapContentPersistenceManager changedManager) {
			updateStatus(changedManager);
		}
		
	}
}
