/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class MapContentPersistenceManager implements IMapContentPersistenceManager {
	private static final boolean INITIAL_STATE = false;
	private final Object myLock = new Object();
	private final ICanvasPersistenceHandler canvasPersistenceHandler;
	private final IMap owningMap;
	private final AtomicBoolean open;
	private final List<IMapContentManagerStatusListener> listeners;

	public MapContentPersistenceManager(IMap owningMap, ICanvasPersistenceHandler canvasPersistenceHandler){
//		this.repoManager = repoManager;
		this.owningMap = owningMap;
		this.canvasPersistenceHandler = canvasPersistenceHandler;
		this.open = new AtomicBoolean(INITIAL_STATE);
		this.listeners = new CopyOnWriteArrayList<IMapContentManagerStatusListener>();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#close()
	 */
	public void close() {
		synchronized(myLock){
			this.open.set(false);
			this.canvasPersistenceHandler.reset();
		}
		this.fireStatusChange();
//		this.repoManager.updateStatus(this);
	}
	
	public void loadContent() throws PersistenceManagerAlreadyOpenException {
		synchronized(myLock){
			if(this.isOpen()) throw new PersistenceManagerAlreadyOpenException(this);
			
			this.canvasPersistenceHandler.setOwningMap(this.owningMap);
			this.canvasPersistenceHandler.loadCanvas();
			this.open.set(true);
		}
		this.fireStatusChange();
//		this.repoManager.updateStatus(this);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#getCanvas()
	 */
	public ICanvas getCanvas() throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(!this.isOpen()) throw new PersistenceManagerNotOpenException(this);
			return this.canvasPersistenceHandler.getLoadedCanvas();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#getOwningMap()
	 */
	public IMap getOwningMap() {
		return this.owningMap;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#isOpen()
	 */
	public boolean isOpen() {
		return this.open.get();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#synchronise()
	 */
	public void synchronise() throws PersistenceManagerNotOpenException {
		synchronized(myLock){
			if(!this.isOpen()) throw new PersistenceManagerNotOpenException(this);
			this.canvasPersistenceHandler.synchroniseCanvas();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#addListener(org.pathwayeditor.businessobjects.bolayer.IPersistenceManagerListener)
	 */
	public void addListener(IMapContentManagerStatusListener listener) {
		this.listeners.add(listener);
	}
	
	void fireStatusChange(){
		for(IMapContentManagerStatusListener listener : this.listeners){
			listener.stateChanged(this);
		}
	}

}
