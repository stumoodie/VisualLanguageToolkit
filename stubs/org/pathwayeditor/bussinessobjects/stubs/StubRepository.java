/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs;

import java.util.List;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author smoodie
 *
 */
public class StubRepository implements IRepository {
	public static final int BUILD_NUM = 0;
	public static final String NAME = "StubRepo";
	public static final String DESCRIPTION = "Stub Repo Descn";

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#addChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener)
	 */
	public void addChangeListener(IRepositoryChangeListener listener) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#findRepositoryItemByPath(java.lang.String)
	 */
	public IRepositoryItem findRepositoryItemByPath(String path) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getDescription()
	 */
	public String getDescription() {
		return DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getFolderByPath(java.lang.String)
	 */
	public IFolder getFolderByPath(String path) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getFoldersByName(java.lang.String)
	 */
	public List<ISubFolder> getFoldersByName(String name) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getListeners()
	 */
	public List<IRepositoryChangeListener> getListeners() {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getRootFolder()
	 */
	public IRootFolder getRootFolder() {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#getSchemaBuildNum()
	 */
	public int getSchemaBuildNum() {
		return BUILD_NUM;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#pathExists(java.lang.String)
	 */
	public boolean pathExists(String path) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#removeChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryChangeListener)
	 */
	public void removeChangeListener(IRepositoryChangeListener listener) {
		throw new IllegalArgumentException("Not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepository#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		throw new IllegalArgumentException("Not implemented");
	}

}
