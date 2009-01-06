/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener.StateChange;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 * 
 */
public class MapPersistenceManager implements IMapPersistenceManager {
	private static final boolean INITIAL_STATE = false;
	private final Object myLock = new Object();
	private final ICanvasPersistenceHandler canvasPersistenceHandler;
	private final AtomicBoolean open;
	private final List<IPersistenceManagerStatusListener> listeners;

	public MapPersistenceManager(ICanvasPersistenceHandler canvasPersistenceHandler) {
		if(canvasPersistenceHandler == null) throw new IllegalArgumentException("parameter cannot be null");
		
		this.canvasPersistenceHandler = canvasPersistenceHandler;
		this.open = new AtomicBoolean(INITIAL_STATE);
		this.listeners = new CopyOnWriteArrayList<IPersistenceManagerStatusListener>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#close()
	 */
	public void close(boolean force) {
		synchronized (myLock) {
			if(force || !requestCancelStateChange(StateChange.CLOSED)) {
				this.open.set(false);
				this.canvasPersistenceHandler.reset();
				this.fireStateChange(StateChange.CLOSED);
			}
		}
	}

	public void open() throws PersistenceManagerAlreadyOpenException {
		synchronized (myLock) {
			if (this.isOpen())
				throw new PersistenceManagerAlreadyOpenException(this);
			if (this.canvasPersistenceHandler.doesCanvasExist()) {
				this.canvasPersistenceHandler.loadCanvas();
			}
			this.open.set(true);
			this.fireStateChange(StateChange.OPENED);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#getCanvas()
	 */
	public ICanvas getCanvas() throws PersistenceManagerNotOpenException {
		synchronized (myLock) {
			if (!this.isOpen())
				throw new PersistenceManagerNotOpenException(this);

			ICanvas retVal = this.canvasPersistenceHandler.getLoadedCanvas();
			if (retVal == null) {
				throw new IllegalStateException("canvas does not exists or was not loaded");
			}
			return retVal;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#getOwningMap()
	 */
	public IMap getMap() {
		return this.canvasPersistenceHandler.getOwningMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#isOpen()
	 */
	public boolean isOpen() {
		return this.open.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#synchronise()
	 */
	public void synchronise() throws PersistenceManagerNotOpenException {
		synchronized (myLock) {
			if (!this.isOpen())
				throw new PersistenceManagerNotOpenException(this);
			this.canvasPersistenceHandler.synchroniseCanvas();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#addListener(org.pathwayeditor.businessobjects.bolayer.IPersistenceManagerListener)
	 */
	public void addListener(IPersistenceManagerStatusListener listener) {
		if(listener == null) throw new IllegalArgumentException("listener cannot be null");
		
		this.listeners.add(listener);
	}

	private void fireStateChange(StateChange stateChange) {
		for (IPersistenceManagerStatusListener listener : this.listeners) {
			listener.stateChanged(stateChange, this);
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
	 * @seeorg.pathwayeditor.businessobjects.management.IMapContentPersistenceManager#createCanvas(org.pathwayeditor.businessobjects.notationsubsystem.
	 * INotationSubsystem)
	 */
	public void createCanvas(INotationSubsystem notationSubsystem) throws PersistenceManagerNotOpenException {
		if (notationSubsystem == null)
			throw new IllegalArgumentException("notationSubsystem cannot be null");
		synchronized (myLock) {
			if (!this.isOpen())
				throw new PersistenceManagerNotOpenException(this);
			this.canvasPersistenceHandler.createCanvas(notationSubsystem);
			this.fireStateChange(StateChange.CANVAS_CREATED);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.management.IMapPersistenceManager#doesCanvasExit()
	 */
	public boolean doesCanvasExist() throws PersistenceManagerNotOpenException {
		synchronized (myLock) {
			if (!this.isOpen())
				throw new PersistenceManagerNotOpenException(this);
			return this.canvasPersistenceHandler.doesCanvasExist();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IMapPersistenceManager#deleteCanvas()
	 */
	public void deleteCanvas() throws PersistenceManagerNotOpenException {
		synchronized (myLock) {
			if(!this.isOpen())
				throw new PersistenceManagerNotOpenException(this);
			
			this.canvasPersistenceHandler.deleteCanvas();
			this.fireStateChange(StateChange.CANVAS_DESTROYED);
		}
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
