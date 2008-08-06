/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import java.util.Iterator;

import org.hibernate.Session;
import org.pathwayeditor.businessobjects.database.util.HibernateUtil;
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
	private static BusinessObjectFactory anInstance;
	private HibRepository rep;

	public static IBusinessObjectFactory getInstance() {
		if (anInstance == null) {
			anInstance = new BusinessObjectFactory();
		}
		return anInstance;
	}

	private BusinessObjectFactory() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public synchronized IRepository getRepository(String name) {
		Session s = HibernateUtil.getSession();
		rep = (HibRepository) s
				.createQuery(
						"from HibRepository r  where r.name = :name")
				.setString("name", name).uniqueResult();
		HibRootFolder root = rep.getHibRootFolder();
		loadSubFoldersAndMaps(root);
		HibernateUtil.commit();
		return rep;
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
		Session s = HibernateUtil.getSession();
		try {
			s.merge(rep);
//			s.saveOrUpdate(rep);
			HibernateUtil.commit();
		} catch (Exception e) {
			s.close();
			throw new RuntimeException(e);
		}

	}

}
