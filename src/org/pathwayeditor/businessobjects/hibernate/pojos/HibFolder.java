/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
import org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener;
import org.pathwayeditor.businessobjects.repository.ISubFolder;
import org.pathwayeditor.businessobjects.repository.ListenableFolder;
import org.pathwayeditor.businessobjects.repository.IFolderContentChangeEvent.ChangeType;

public abstract class HibFolder implements Serializable, IFolder {
	private static final long serialVersionUID = 8668639813872187460L;
	public static final String ILLEGAL_SUBFOLDERNAME = "Subfolder names must be unique and cannot be null or contain a slashdot";
	public static final String ILLEGAL_SUBFOLDER = "Subfolder cannot be added";
	public static final String NOT_CHILD = "Subfolder not child of this folder";
	public static final String ILLEGAL_MAPNAME = "Map names must be unique and cannot be null or contain a slashdot";
	public static final String MAP_ALREADY_EXISTS = "Map already existed in the folder.";
	private Long id;
	private Set<HibMap> hibMaps = new HashSet<HibMap>(0);
	private Set<HibSubFolder> subFolders = new HashSet<HibSubFolder>(0);
	private HibRepository repository;
	private int iNode;
	private final ListenableFolder listenable = new ListenableFolder();

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	protected HibFolder() {
	}
	
	protected HibFolder(HibRepository repository){
		this();
		this.repository = repository;
		this.iNode = repository.getINodeCounter().nextIndex();
		this.repository.getFolders().add(this);
	}
	
	protected HibFolder(HibRepository repository, HibFolder other) {
		this.repository = repository;
		this.iNode = repository.getINodeCounter().nextIndex();
		this.repository.getFolders().add(this);
		for (HibMap diagram : other.getMapDiagrams()) {
			this.hibMaps.add(new HibMap(this, diagram));
		}
		for (HibSubFolder subFolder : other.getSubFolders()) {
			this.subFolders.add(new HibSubFolder(this, subFolder));
		}
	}

	
//	protected HibFolder(HibRepository repository, HibFolder other,boolean isCompleteCopy) {
//		this.repository = repository;
//		if(isCompleteCopy)
//			this.iNode=other.iNode;
//		for (HibMap diagram : other.getMapDiagrams()) {
//			this.hibMaps.add(new HibMap(this, diagram,isCompleteCopy));
//		}
//		for (HibSubFolder subFolder : other.getSubFolders()) {
//			this.subFolders.add(new HibSubFolder(this, subFolder,isCompleteCopy));
//		}
//	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	void setRepository(HibRepository repository) {
		this.repository = repository;
	}

	void changeRepository(HibRepository repository){
		if(this.repository != null){
			this.repository.getFolders().remove(this);
		}
		if(repository != null){
			repository.getFolders().add(this);
		}
		this.repository = repository;
	}
	
	public HibRepository getRepository() {
		return repository;
	}

	public Set<HibMap> getMapDiagrams() {
		return this.hibMaps;
	}

	void setMapDiagrams(Set<HibMap> hibMaps) {
		this.hibMaps = hibMaps;
	}

	public Set<HibSubFolder> getSubFolders() {
		return this.subFolders;
	}

	void setSubFolders(Set<HibSubFolder> subFolders) {
		this.subFolders = subFolders;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getName()).append("@").append(
				Integer.toHexString(hashCode())).append(" [");
		buffer.append("id").append("='").append(getId()).append("' ");
		buffer.append("]");
		return buffer.toString();
	}

	void addSubFolder(HibSubFolder newSubFolder) {
		if (newSubFolder == null)
			throw new IllegalArgumentException("newSubFolder cannot be null");
		HibFolder oldParentFolder = newSubFolder.getParentFolder();
		if (oldParentFolder != null) {
			oldParentFolder.subFolders.remove(newSubFolder);
			// oldParentFolder.removeHibSubFolder(newSubFolder); //FIX ME
		}
		this.subFolders.add(newSubFolder);
		newSubFolder.setParentFolder(this);
		newSubFolder.setRepository(getRepository());
	}

	void removeHibSubFolder(HibSubFolder subFolder) {
		if (subFolder == null)
			throw new IllegalArgumentException("subFolder cannot be null");
		if (!subFolder.getParentFolder().equals(this))
			throw new IllegalArgumentException(
					"subFolder must be a child of this folder");
		this.subFolders.remove(subFolder);
		subFolder.setParentFolder(null);
	}

	void addMapDiagram(HibMap newMapDiagram) {
		if (newMapDiagram == null)
			throw new IllegalArgumentException("newMapDiagram cannot be null");
		HibFolder oldParentFolder = newMapDiagram.getFolder();
		if (oldParentFolder != null) {
			oldParentFolder.getMapDiagrams().remove(newMapDiagram);
		}
		this.hibMaps.add(newMapDiagram);
		newMapDiagram.setFolder(this);
	}

	void removeMapDiagram(HibMap mapDiagram) {
		if (mapDiagram == null)
			throw new IllegalArgumentException("mapDiagram cannot be null");
		if (mapDiagram.getFolder()==null||!mapDiagram.getFolder().equals(this))
			throw new IllegalArgumentException(
					"mapDiagram must belong to this folder");
		this.hibMaps.remove(mapDiagram);
		mapDiagram.setFolder(null);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.iNode;
		result = prime * result
				+ ((this.getRepository() == null) ? 0 : this.getRepository().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibFolder))
			return false;
		HibFolder other = (HibFolder) obj;
		if (this.iNode != other.getINode())
			return false;
		if (this.getRepository() == null) {
			if (other.getRepository() != null)
				return false;
		} else if (!this.getRepository().equals(other.getRepository()))
			return false;
		return true;
	}

	public int getINode() {
		return this.iNode;
	}

	public int getiNode() {
		return this.iNode;
	}

	protected void setINode(int node) {
		this.iNode = node;
	}

	// /////////////////////////BUSINESS LOGIC
	// METHODS/////////////////////////////////////////////


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#containsSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public boolean containsSubfolder(ISubFolder subFolder) {
		boolean contains = subFolder != null && this.equals(subFolder.getParent());
		return contains;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canUseSubfolderName(java.lang.String)
	 */
	public boolean canUseSubfolderName(String name) {
		if (nameMalFormed(name))
			throw new IllegalArgumentException(ILLEGAL_SUBFOLDERNAME);
		boolean canUseName = true;
//		Set<String> subnames = new HashSet<String>();
		for (HibSubFolder f : getSubFolders()) {
			if (f.getName().equalsIgnoreCase(name)) {
				canUseName = false;
				break;
			}
		}
		;
		return canUseName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canRenameMap(org.pathwayeditor.businessobjects.repository.IMap,
	 *      java.lang.String)
	 */
	public boolean canRenameMap(IMap map, String newMapName) {
		return containsMap(map) && canUseMapName(newMapName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canRenameSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder,
	 *      java.lang.String)
	 */
	public boolean canRenameSubfolder(ISubFolder subFolder, String newFolderName) {
		if (subFolder.getParent() == null
				|| !subFolder.getParent().equals(this)) {
			throw new IllegalArgumentException(NOT_CHILD);
		}
		if (canUseSubfolderName(newFolderName))
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canUseMapName(java.lang.String)
	 */
	public boolean canUseMapName(String name) {
		if (nameMalFormed(name))
			return false;
		boolean canuse = true;
		for (HibMap d : hibMaps) {
			if (d.getName().equalsIgnoreCase(name))
				canuse = false;
		}
		return canuse;
	}

	private boolean nameMalFormed(String name) {
		return name == null 
				|| name.indexOf(".") != -1 || name.indexOf("\\") != -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#containsMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public boolean containsMap(IMap newMap) {
		boolean iscontained = newMap != null && this.equals(newMap.getOwner());
		return iscontained;
	}

	public boolean containsMap(String newMapName){
		boolean retVal = false;
		if(newMapName != null){
			for(IMap map : this.hibMaps){
				if(map.getName().equals(newMapName)){
					retVal = true;
					break;
				}
			}
		}
		return retVal;
	}
	
	public boolean containsSubfolder(String newFolderName){
		boolean retVal = false;
		if(newFolderName != null){
			for(ISubFolder folder : this.subFolders){
				if(folder.getName().equals(newFolderName)){
					retVal = true;
					break;
				}
			}
		}
		return retVal;
	}
	
	public boolean canCopyMap(IMap origMap){
		return origMap != null && !this.containsMap(origMap) && canUseMapName(origMap.getName());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createCopyOfMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public IMap createCopyOfMap(IMap origMap) {
		if (!canCopyMap(origMap))
			throw new IllegalArgumentException(ILLEGAL_MAPNAME);

		HibMap map = new HibMap(this, (HibMap) origMap);
		hibMaps.add(map);
		this.listenable.notifyDescendentChange(ChangeType.ADDED, map, this);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createCopyOfSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public ISubFolder createCopyOfSubfolder(ISubFolder origSubfolder) {
		if (!canUseSubfolderName(origSubfolder.getName()))
			throw new IllegalArgumentException(ILLEGAL_SUBFOLDER);
		HibSubFolder copy = new HibSubFolder(this, (HibSubFolder) origSubfolder);
		addSubFolder(copy);
		this.listenable.notifyDescendentChange(ChangeType.ADDED, copy, this);
		return copy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createMap(java.lang.String)
	 */
	public IMap createMap(String newMapName) {
		if (!canUseMapName(newMapName))
			throw new IllegalArgumentException(ILLEGAL_MAPNAME);
		HibMap map = new HibMap(this, newMapName);
		hibMaps.add(map);
		this.listenable.notifyDescendentChange(ChangeType.ADDED, map, this);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#createSubfolder(java.lang.String)
	 */
	public ISubFolder createSubfolder(String newSubfolderName) {
		if (!canUseSubfolderName(newSubfolderName))
			throw new IllegalArgumentException(ILLEGAL_SUBFOLDERNAME);
		HibSubFolder folder = new HibSubFolder(this, newSubfolderName);
		subFolders.add(folder);
		this.listenable.notifyDescendentChange(ChangeType.ADDED, folder, this);
		return folder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#getMapIterator()
	 */
	public Iterator<IMap> mapIterator() {
		final SortedSet<IMap> set = new TreeSet<IMap>();
		for(HibMap map : this.hibMaps){
			set.add(map);
		}
		return set.iterator();
	}

	public boolean canMoveMap(IMap origMap){
		return origMap != null && this.canUseMapName(origMap.getName()) && !origMap.getOwner().equals(this) && this.getRepository().equals(origMap.getRepository());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#moveMap(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public IMap moveMap(IMap newMap) {
		if (canMoveMap(newMap) == false)
			throw new IllegalArgumentException(ILLEGAL_MAPNAME);

		IFolder oldParent = newMap.getOwner();
		HibMap m = (HibMap) newMap;
		m.changeFolder(this);
		this.listenable.notifyDescendentChange(ChangeType.REMOVED, null, oldParent);
		this.listenable.notifyDescendentChange(ChangeType.ADDED, newMap, this);
		return m;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#moveSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public ISubFolder moveSubfolder(ISubFolder subFolder) {
		if (!canMoveSubfolder(subFolder))
			throw new IllegalArgumentException(ILLEGAL_SUBFOLDER);
//		HibFolder copy = new HibSubFolder(this, (HibSubFolder) subFolder, true);
//		addSubFolder((HibSubFolder) copy);
//		subFolder.getParent().removeSubfolder(subFolder);
//		return (ISubFolder) copy;
		IFolder oldParent = subFolder.getParent();
		HibSubFolder hibSubFolder = (HibSubFolder)subFolder;
		hibSubFolder.changeParentFolder(this);
		this.listenable.notifyDescendentChange(ChangeType.REMOVED, null, oldParent);
		this.listenable.notifyDescendentChange(ChangeType.ADDED, subFolder, this);
		return hibSubFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#numMaps()
	 */
	public int numMaps() {
		return hibMaps.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#numSubFolders()
	 */
	public int numSubFolders() {
		return this.subFolders.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#removeSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public void removeSubfolder(ISubFolder subFolder) {
		if (!subFolders.contains(subFolder))
			throw new IllegalArgumentException(NOT_CHILD);
		
		HibSubFolder hibSubFolder = (HibSubFolder) subFolder;
		// subfolder and all descendents in tree must be removed from repository
		// so that hibernate knows to remove them from the DB.  
		removeHibSubFolder(hibSubFolder);
		markChildrenRemoved(hibSubFolder);
		this.listenable.notifyDescendentChange(ChangeType.REMOVED, null, this);
	}

	private void markChildrenRemoved(HibSubFolder currFolder){
		currFolder.changeRepository(null);
		for(HibMap childMap : currFolder.getMapDiagrams()){
			childMap.changeRepository(null);
		}
		for(HibSubFolder subFolder : currFolder.getSubFolders()){
			markChildrenRemoved(subFolder);
		}
	}
	
	public void removeMap(IMap map){
		HibMap hibMap = (HibMap) map; 
		removeMapDiagram(hibMap);
		hibMap.changeRepository(null);
		this.listenable.notifyDescendentChange(ChangeType.REMOVED, null, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#renameMap(org.pathwayeditor.businessobjects.repository.IMap,
	 *      java.lang.String)
	 */
	public void renameMap(IMap map, String newMapName) {
		if (canUseMapName(newMapName)) {
			HibMap m = (HibMap) map;
			m.setName(newMapName);
		} else
			throw new IllegalArgumentException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#renameSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder,
	 *      java.lang.String)
	 */
	public void renameSubfolder(ISubFolder subFolder, String newFolderName) {
		if (!canRenameSubfolder(subFolder, newFolderName))
			throw new IllegalArgumentException(ILLEGAL_SUBFOLDERNAME);
		((HibSubFolder) subFolder).setName(newFolderName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#getSubFolderIterator()
	 */
	public Iterator<ISubFolder> subFolderIterator() {
		final SortedSet<ISubFolder> set = new TreeSet<ISubFolder>();
		for(ISubFolder subFolder : subFolders){
			set.add(subFolder);
		}
		return set.iterator();
	}

	protected final ListenableFolder getListenable(){
		return this.listenable;
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#addChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public final void addChangeListener(IRepositoryItemChangeListener changeListener) {
		this.listenable.addListener(changeListener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getChangeListeners()
	 */
	public final List<IRepositoryItemChangeListener> getChangeListeners() {
		return this.listenable.exportListenerList();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#removeChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public final void removeChangeListener(IRepositoryItemChangeListener changeListener) {
		this.listenable.removeListener(changeListener);
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compareTo(IRepositoryItem o2) {
		return Integer.valueOf(this.getINode()).compareTo(Integer.valueOf(o2.getINode())); 
	}
	
	public Iterator<IRepositoryItem> levelOrderIterator(){
		return new LevelOrderTreeIterator(this);
	}
}
