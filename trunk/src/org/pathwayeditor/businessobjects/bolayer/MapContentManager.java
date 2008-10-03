/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * @author smoodie
 *
 */
public class MapContentManager implements IMapContentManager {
	private final Object myLock = new Object();
	private static final boolean INITIAL_STATE = false;
	private static final String ILLEGAL_STATE_ALREADY_OPEN = "manager is already open";
	private static final String ILLEGAL_STATE_NOT_OPEN = "manager is not open";
	private final ICanvasLoader canvasLoader;
	private final IHibNotationFactory hibNotationFactory;
//	private final RepositoryManager repoManager;
	private final IMap owningMap;
	private final AtomicBoolean open;
	private final List<IPersistenceManagerListener> listeners;

	public MapContentManager(IMap owningMap, ICanvasLoader canvasLoader, IHibNotationFactory  hibNotationFactory){
//		this.repoManager = repoManager;
		this.owningMap = owningMap;
		this.canvasLoader = canvasLoader;
		this.hibNotationFactory = hibNotationFactory;
		this.open = new AtomicBoolean(INITIAL_STATE);
		this.listeners = new CopyOnWriteArrayList<IPersistenceManagerListener>();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#close()
	 */
	public void close() {
		synchronized(myLock){
			this.open.set(false);
			this.canvasLoader.reset();
		}
		this.fireStatusChange();
//		this.repoManager.updateStatus(this);
	}
	
	public void loadContent(){
		synchronized(myLock){
			if(this.isOpen()) throw new IllegalStateException(ILLEGAL_STATE_ALREADY_OPEN);
			
			this.canvasLoader.setHibNotationFactory(this.hibNotationFactory);
			this.canvasLoader.setOwningMap(this.owningMap);
			this.canvasLoader.loadCanvas();
			this.open.set(true);
		}
		this.fireStatusChange();
//		this.repoManager.updateStatus(this);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#getCanvas()
	 */
	public ICanvas getCanvas() {
		synchronized(myLock){
			if(!this.isOpen()) throw new IllegalStateException(ILLEGAL_STATE_NOT_OPEN);
			return this.canvasLoader.getLoadedCanvas();
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
	public void synchronise() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IMapContentManager#addListener(org.pathwayeditor.businessobjects.bolayer.IPersistenceManagerListener)
	 */
	public void addListener(IPersistenceManagerListener listener) {
		this.listeners.add(listener);
	}
	
	private void fireStatusChange(){
		for(IPersistenceManagerListener listener : this.listeners){
			listener.stateChanged(this);
		}
	}

}
