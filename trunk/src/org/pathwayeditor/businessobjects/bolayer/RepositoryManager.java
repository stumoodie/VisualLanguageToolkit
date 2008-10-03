/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibFolder;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRepository;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author smoodie
 * 
 */
public class RepositoryManager implements IRepositoryManager {
	private static final String ILLEGAL_STATE_REPO_NOT_OPEN = "The repository is must be open for this operation to be performed";
	private static final String ILLEGAL_STATE_REPO_ALREADY_OPEN = "The repository cannot already be open for this operation to be performed";
	private final Object myLock = new Object();
	private final SessionFactory fact;
	private final String repositoryName;
	private final AtomicReference<IRepository> rep;
	private final Map<IMap, IMapContentManager> openMaps;
	private final ICanvasLoader canvasLoader;
	private final IHibNotationFactory hibNotatinFactory = null;

	public RepositoryManager(ICanvasLoader canvasLoader, SessionFactory fact, String repositoryName) {
		this.canvasLoader = canvasLoader;
		this.fact = fact;
		this.repositoryName = repositoryName;
		this.rep = new AtomicReference<IRepository>(null);
		this.openMaps = new ConcurrentHashMap<IMap, IMapContentManager>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public IRepository getRepository() {
		synchronized(myLock){
			if(this.isRepositoryOpen() == false) throw new IllegalArgumentException(ILLEGAL_STATE_REPO_NOT_OPEN);
			return rep.get();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isRepositoryOpen()
	 */
	public boolean isRepositoryOpen() {
		return this.rep.get() != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#openRepository()
	 */
	public void openRepository() {
		synchronized(myLock){
			if(isRepositoryOpen()) throw new IllegalStateException("Cannot open a repository that is already open");
			loadRepository();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#closeRepository()
	 */
	public void closeRepository() {
		synchronized(myLock){
			this.rep.set(null);
		}
	}


	private void loadRepository() {
		synchronized (myLock) {
			if (this.isRepositoryOpen())
				throw new IllegalArgumentException(ILLEGAL_STATE_REPO_ALREADY_OPEN);

			Session s = this.fact.getCurrentSession();
			s.getTransaction().begin();
			HibRepository hibRep = (HibRepository) s.createQuery(
					"from HibRepository r  where r.name = :name").setString(
					"name", repositoryName).uniqueResult();
			hibRep.getMaps().size();
			hibRep.getFolders().size();
			HibRootFolder root = hibRep.getRootFolder();
			loadSubFoldersAndMaps(root);
			s.getTransaction().commit();
			rep.set(hibRep);
		}
	}


	/**
	 * @param root
	 */
	private void loadSubFoldersAndMaps(HibFolder folder) {
		folder.getMapDiagrams().size(); // note - its the call to size() that
		for (Iterator<ISubFolder> i = folder.getSubFolderIterator(); i.hasNext();) {
			loadSubFoldersAndMaps((HibFolder) i.next());
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseRepository(org.pathwayeditor.businessobjects.repository.IRepository)
	 */
	public void synchroniseRepository() {
		synchronized(myLock){
			if(!this.isRepositoryOpen()) throw new IllegalArgumentException(ILLEGAL_STATE_REPO_NOT_OPEN);
			
			Session s = this.fact.getCurrentSession();
			s.getTransaction().begin();
			s.saveOrUpdate(rep);
			s.getTransaction().commit();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#canOpenCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public boolean canOpenMap(IMap map) {
		synchronized(myLock){
			return this.isRepositoryOpen() && !this.openMaps.containsKey(map);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#openCanvasIterator()
	 */
	public Iterator<IMap> openMapIterator() {
		synchronized(myLock){
			if(this.isRepositoryOpen() == false) throw new IllegalStateException(ILLEGAL_STATE_REPO_NOT_OPEN);
			return this.openMaps.keySet().iterator();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isCanvasOpen(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public boolean isMapOpen(IMap canvas) {
		return this.openMaps.containsKey(canvas);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IRepositoryManager#openMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public IMapContentManager openMap(IMap map) {
		synchronized(myLock){
			if(!this.isRepositoryOpen()) throw new IllegalStateException(ILLEGAL_STATE_REPO_NOT_OPEN);
			IMapContentManager retVal = new MapContentManager(map, this.canvasLoader, this.hibNotatinFactory);
			retVal.addListener(new MapContentListener());
			return retVal;
		}
	}

	/**
	 * @param mapContentManager
	 */
	private void updateStatus(IMapContentManager mapContentManager) {
		IMap map = mapContentManager.getOwningMap();
		synchronized (myLock) {
			if (this.openMaps.containsKey(map)) {
				// we have the map stored so we think it is open.
				if (!mapContentManager.isOpen()) {
					// if the map is not open then remove it.
					this.openMaps.remove(map);
				}
			} else {
				// it is not stored so we think it is closed
				if (mapContentManager.isOpen()) {
					// it is not closed so we should added to the collection of
					// open maps
					this.openMaps.put(map, mapContentManager);
				}
			}
		}
	}
	
	private class MapContentListener implements IPersistenceManagerListener {

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.bolayer.IPersistenceManagerListener#stateChanged(org.pathwayeditor.businessobjects.bolayer.IMapContentManager)
		 */
		public void stateChanged(IMapContentManager changedManager) {
			updateStatus(changedManager);
		}
		
	}
}
