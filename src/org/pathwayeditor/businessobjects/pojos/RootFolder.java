/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibRootFolder;
import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.businessobjects.repository.IRootFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * @author smoodie
 *
 */
public class RootFolder implements IRootFolder, IHibernateFacade<HibRootFolder> {
	private final HibRootFolder hibRootFolder;
	
	public RootFolder(Repository repository){
		this.hibRootFolder = new HibRootFolder(this, repository.getHibObject());
	}
	
	public RootFolder(HibRootFolder hibRootFolder){
		this.hibRootFolder = hibRootFolder;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRootFolder#getDataSource()
	 */
	public IRepository getRepository() {
		return this.hibRootFolder.getHibRepository().getBusinessObject();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canMoveSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public boolean canMoveSubfolder(ISubFolder subFolder) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canRenameMap(org.pathwayeditor.businessobjects.repository.IMap, java.lang.String)
	 */
	public boolean canRenameMap(IMap map, String newMapName) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canRenameSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder, java.lang.String)
	 */
	public boolean canRenameSubfolder(ISubFolder subFolder, String newFolderName) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canUseMapName(java.lang.String)
	 */
	public boolean canUseMapName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canUseSubfolderName(java.lang.String)
	 */
	public boolean canUseSubfolderName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#containsMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public boolean containsMap(IMap newMap) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#containsSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public boolean containsSubfolder(ISubFolder subFolder) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createCopyOfMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public IMap createCopyOfMap(IMap origMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createCopyOfSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public IFolder createCopyOfSubfolder(ISubFolder origSubfolder) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createMap(java.lang.String)
	 */
	public IMap createMap(String newMapName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createSubfolder(java.lang.String)
	 */
	public ISubFolder createSubfolder(String newSubfolderName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#getMapIterator()
	 */
	public Iterator<IMap> getMapIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#getOwner()
	 */
	public IFolder getOwner() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#getSubfolderIterator()
	 */
	public Iterator<SubFolder> getSubfolderIterator() {
		return new BusinessObjectSetIterator<SubFolder>(this.hibRootFolder.getSubFolders());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#isRootFolder()
	 */
	public boolean isRootFolder() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#levelOrderFolderIterator()
	 */
	public Iterator<ISubFolder> levelOrderFolderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#moveMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void moveMap(IMap newMap) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#moveSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public void moveSubfolder(ISubFolder subFolder) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#numMaps()
	 */
	public int numMaps() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#numSubFolders()
	 */
	public int numSubFolders() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#removeSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public void removeSubfolder(ISubFolder subFolder) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#renameMap(org.pathwayeditor.businessobjects.repository.IMap, java.lang.String)
	 */
	public void renameMap(IMap map, String newMapName) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#renameSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder, java.lang.String)
	 */
	public void renameSubfolder(ISubFolder subFolder, String newFolderName) {
		// TODO Auto-generated method stub

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

	public HibRootFolder getHibObject(){
		return this.hibRootFolder;
	}
}
