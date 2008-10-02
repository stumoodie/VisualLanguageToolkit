/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
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
public class BusinessObjectFactory implements IBusinessObjectFactory {
	private static final String ILLEGAL_STATE_REPO_NOT_OPEN = "The repository is must be open for this operation to be performed";
	private final Object myLock = new Object();
	private final SessionFactory fact;
	private final String repositoryName;
	private final AtomicReference<IRepository> rep;
	private final Set<ICanvas> openCanvases;

	public BusinessObjectFactory(SessionFactory fact, String repositoryName) {
		this.fact = fact;
		this.repositoryName = repositoryName;
		this.rep = new AtomicReference<IRepository>(null);
		this.openCanvases = new CopyOnWriteArraySet<ICanvas>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public IRepository getRepository() {
		if(this.isRepositoryOpen() == false) throw new IllegalArgumentException(ILLEGAL_STATE_REPO_NOT_OPEN);
		return rep.get();
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
		this.rep.set(null);
	}


	private void loadRepository(){
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibRepository hibRep = (HibRepository) s.createQuery(
				"from HibRepository r  where r.name = :name").setString(
				"name",repositoryName).uniqueResult();
		hibRep.getMaps().size();
		hibRep.getFolders().size();
		HibRootFolder root = hibRep.getRootFolder();
		loadSubFoldersAndMaps(root);
		s.getTransaction().commit();
		rep.set(hibRep);
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
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public ICanvas openCanvas(IMap map) {
		if(this.isRepositoryOpen() == false) throw new IllegalArgumentException(ILLEGAL_STATE_REPO_NOT_OPEN);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseCanvas(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public void synchroniseCanvas(ICanvas canvas) {
		if(this.isRepositoryOpen() == false) throw new IllegalArgumentException(ILLEGAL_STATE_REPO_NOT_OPEN);
		//TODO: implement me!
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseRepository(org.pathwayeditor.businessobjects.repository.IRepository)
	 */
	public void synchroniseRepository() {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		s.saveOrUpdate(rep);
		s.getTransaction().commit();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#canOpenCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public boolean canOpenCanvas(IMap owningMap) {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		int count = (Integer)s.getNamedQuery("canvasExistsForMap").setInteger("inode", owningMap.getINode())
						.setEntity("repo", owningMap.getRepository()).uniqueResult();
		s.getTransaction().commit();
		return count > 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#closeCanvas(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public void closeCanvas(ICanvas canvas) {
		this.openCanvases.remove(canvas);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#openCanvasIterator()
	 */
	public Iterator<ICanvas> openCanvasIterator() {
		if(this.isRepositoryOpen() == false) throw new IllegalArgumentException(ILLEGAL_STATE_REPO_NOT_OPEN);
		return this.openCanvases.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isCanvasOpen(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public boolean isCanvasOpen(ICanvas canvas) {
		return this.openCanvases.contains(canvas);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#isCanvasOwnedHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public boolean isCanvasOwnedHere(ICanvas canvas) {
		boolean retVal = false;
		if(canvas != null){
			retVal = this.rep.get().equals(canvas.getOwningMap().getRepository());
		}
		return retVal;
	}

}
