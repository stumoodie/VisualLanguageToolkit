/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 * 
 */
public class MapContentPersistenceManager implements IMapContentPersistenceManager {
	private static final boolean INITIAL_STATE = false;
	private final Object myLock = new Object();
	private final ICanvasPersistenceHandler canvasPersistenceHandler;
	private final AtomicBoolean open;
	private final List<IMapContentManagerStatusListener> listeners;

	public MapContentPersistenceManager(ICanvasPersistenceHandler canvasPersistenceHandler) {
		// this.repoManager = repoManager;
		this.canvasPersistenceHandler = canvasPersistenceHandler;
		this.open = new AtomicBoolean(INITIAL_STATE);
		this.listeners = new CopyOnWriteArrayList<IMapContentManagerStatusListener>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#close()
	 */
	public void close() {
		synchronized (myLock) {
			this.open.set(false);
			this.canvasPersistenceHandler.reset();
		}
		this.fireStatusChange();
		// this.repoManager.updateStatus(this);
	}

	public void loadContent() throws PersistenceManagerAlreadyOpenException {
		synchronized (myLock) {
			if (this.isOpen())
				throw new PersistenceManagerAlreadyOpenException(this);
			if (this.canvasPersistenceHandler.doesCanvasExist()) {
				this.canvasPersistenceHandler.loadCanvas();
			}
			this.open.set(true);
		}
		this.fireStatusChange();
		// this.repoManager.updateStatus(this);
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
	public IMap getOwningMap() {
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
	public void addListener(IMapContentManagerStatusListener listener) {
		this.listeners.add(listener);
	}

	void fireStatusChange() {
		for (IMapContentManagerStatusListener listener : this.listeners) {
			listener.stateChanged(this);
		}
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
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.management.IMapContentPersistenceManager#doesCanvasExit()
	 */
	public boolean doesCanvasExist() throws PersistenceManagerNotOpenException {
		synchronized (myLock) {
			if (!this.isOpen())
				throw new PersistenceManagerNotOpenException(this);
			return this.canvasPersistenceHandler.doesCanvasExist();
		}
	}

}
