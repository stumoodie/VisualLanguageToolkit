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
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.management.ICanvasPersistenceHandler;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener;
import org.pathwayeditor.businessobjects.management.IPersistenceManagerStatusListener.StateChange;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 * 
 */
public class MapPersistenceManager implements IMapPersistenceManager {
	/**
	 * 
	 */
	private static final String MANAGER_ALREADY_OPEN = "Manager already open";
	/**
	 * 
	 */
	private static final String MANAGER_IS_NOT_OPEN = "Manager is not open";
	private static final boolean INITIAL_STATE = false;
	private final ICanvasPersistenceHandler canvasPersistenceHandler;
	private final AtomicBoolean open;
	private final List<IPersistenceManagerStatusListener> listeners;

	public MapPersistenceManager(ICanvasPersistenceHandler canvasPersistenceHandler) {
		if(canvasPersistenceHandler == null) throw new IllegalArgumentException("parameter cannot be null");
		
		this.canvasPersistenceHandler = canvasPersistenceHandler;
		this.open = new AtomicBoolean(INITIAL_STATE);
		this.listeners = new LinkedList<IPersistenceManagerStatusListener>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#close()
	 */
	public void close(boolean force) {
		if(force || !requestCancelStateChange(StateChange.CLOSED)) {
			this.open.set(false);
			this.canvasPersistenceHandler.reset();
			this.fireStateChange(StateChange.CLOSED);
		}
	}

	public void open() {
		if (this.isOpen())
			throw new IllegalStateException(MANAGER_ALREADY_OPEN);
		
		if (this.canvasPersistenceHandler.doesCanvasExist()) {
			this.canvasPersistenceHandler.loadCanvas();
		}
		this.open.set(true);
		this.fireStateChange(StateChange.OPENED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#getCanvas()
	 */
	public ICanvas getCanvas() {
		if (!this.isOpen())
			throw new IllegalStateException(MANAGER_IS_NOT_OPEN);

		ICanvas retVal = this.canvasPersistenceHandler.getLoadedCanvas();
		if (retVal == null) {
			throw new IllegalStateException("canvas does not exists or was not loaded");
		}
		return retVal;
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
	public void synchronise() {
		if (!this.isOpen())
			throw new IllegalStateException(MANAGER_IS_NOT_OPEN);
		
		this.canvasPersistenceHandler.synchroniseCanvas();
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
	public void createCanvas(INotationSubsystem notationSubsystem) {
		if (notationSubsystem == null)
			throw new IllegalArgumentException("notationSubsystem cannot be null");
		
		if (!this.isOpen())
				throw new IllegalStateException(MANAGER_IS_NOT_OPEN);
		
		this.canvasPersistenceHandler.createCanvas(notationSubsystem);
		this.fireStateChange(StateChange.CANVAS_CREATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.management.IMapPersistenceManager#doesCanvasExit()
	 */
	public boolean doesCanvasExist() {
		if (!this.isOpen())
				throw new IllegalStateException(MANAGER_IS_NOT_OPEN);
		
		return this.canvasPersistenceHandler.doesCanvasExist();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.management.IMapPersistenceManager#deleteCanvas()
	 */
	public void deleteCanvas() {
		if(!this.isOpen())
			throw new IllegalStateException(MANAGER_IS_NOT_OPEN);
			
		this.canvasPersistenceHandler.deleteCanvas();
		this.fireStateChange(StateChange.CANVAS_DESTROYED);
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
