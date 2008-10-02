/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;

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
	private IRepository rep;
	private final SessionFactory fact;
	private final String repositoryName;

	public BusinessObjectFactory(SessionFactory fact, String repositoryName) {
		this.fact = fact;
		this.repositoryName = repositoryName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public synchronized IRepository getRepository() {
		if (rep == null) {
			loadRepository();
		}
		return rep;
	}

	private void loadRepository(){
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		HibRepository hibRep = (HibRepository) s.createQuery(
				"from HibRepository r  where r.name = :name").setString(
				"name",repositoryName).uniqueResult();
//		if (rep == null)
//			rep = makeAndSavedefaultRepository();
		hibRep.getMaps().size();
		hibRep.getFolders().size();
		HibRootFolder root = hibRep.getRootFolder();
		loadSubFoldersAndMaps(root);
//		HibernateUtil.commit();
		s.getTransaction().commit();
		rep = hibRep;
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
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#createTransientCanvas()
	 */
	public synchronized ICanvas createTransientCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public synchronized ICanvas getCanvas(IMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseCanvas(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public synchronized void synchroniseCanvas(ICanvas canvas) {
		//TODO: implement me!
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseRepository(org.pathwayeditor.businessobjects.repository.IRepository)
	 */
	public synchronized void synchroniseRepository() {
		Session s = this.fact.getCurrentSession();
		s.getTransaction().begin();
		s.saveOrUpdate(rep);
		s.getTransaction().commit();
	}

}
