/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public class Map implements IMap {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getOwner()
	 */
	public IFolder getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getNodeId()
	 */
	public int getNodeId() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getRepository()
	 */
	public IRepository getRepository() {
		// TODO Auto-generated method stub
		return null;
	}

}
