/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;
import org.pathwayeditor.businessobjects.database.util.IConnectionInfo;
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
	private HibRepository rep;
	private IConnectionInfo conn;

	public  BusinessObjectFactory(IConnectionInfo conn) {
		this.conn=conn;
		HibernateUtil.setConnectionInfo(conn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public synchronized IRepository getRepository() {
		if (rep == null) {
			Session s = HibernateUtil.getSession();
			rep = (HibRepository) s.createQuery(
					"from HibRepository r  where r.name = :name").setString(
					"name", conn.getRepositoryName()).uniqueResult();
			HibRootFolder root = rep.getHibRootFolder();
			loadSubFoldersAndMaps(root);
			HibernateUtil.commit();
		}
		Session s = HibernateUtil.getSession();
		s.lock(rep, LockMode.NONE);
		return rep;
	}

	public synchronized IRepository getFreshRepository(String name) {
		rep = null;
		return getRepository();
	}

	/**
	 * @param root
	 */
	private void loadSubFoldersAndMaps(HibFolder folder) {
		folder.getMapDiagrams().size(); // note - its the call to size() that
		// forces Hibernate to initialise the
		// collection - so do not remove!
		for (Iterator<? extends ISubFolder> i = folder.getSubFolderIterator(); i
				.hasNext();) {
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseRepository(org.pathwayeditor.businessobjects.repository.IRepository)
	 */
	public synchronized void synchroniseRepository(IRepository repository) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			s.saveOrUpdate(rep);
			HibernateUtil.commit();
		} catch (Exception e) {
			if (s != null)
				s.close();
			throw new RuntimeException(e);
		}

	}

}
