/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import java.util.List;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener;

/**
 * @author smoodie
 *
 */
public class StubMap implements IMap {

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getDescription()
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getOwner()
	 */
	public IFolder getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getINode()
	 */
	public int getINode() {
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getPath()
	 */
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#addChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public void addChangeListener(IRepositoryItemChangeListener changeListener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getChangeListeners()
	 */
	public List<IRepositoryItemChangeListener> getChangeListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#removeChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public void removeChangeListener(
			IRepositoryItemChangeListener changeListener) {
		// TODO Auto-generated method stub
		
	}

}
