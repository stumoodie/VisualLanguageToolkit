package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

public abstract class HibFolder implements Serializable, IFolder, IPropertyChangeSupport {
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
	private PropertyChangeSupport listenerManager; // stores all registered listeners for this class

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	protected HibFolder() {
		listenerManager = new PropertyChangeSupport(this);
	}
	
	protected HibFolder(HibRepository repository){
		this();
		this.repository = repository;
		this.iNode = repository.getINodeCounter().nextIndex();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		listenerManager.addPropertyChangeListener(listener);
	}
	
	public void firePropertyChange(String property,Object oldValue,Object newValue){
		listenerManager.firePropertyChange(property, oldValue, newValue);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		listenerManager.removePropertyChangeListener(listener);
	}

	protected HibFolder(HibRepository repository, HibFolder other) {
		this.repository = repository;
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

	protected void setINode(int node) {
		this.iNode = node;
	}

	// /////////////////////////BUSINESS LOGIC
	// METHODS/////////////////////////////////////////////

//	/**
//	 * @param testee
//	 *            folder which may be a child
//	 * @param testFolder
//	 *            folder which may be a parent
//	 * @return true if testee folder is a child anywhere in the child subfolder
//	 *         tree of given test folder
//	 */
//	private boolean testeeChildOf(IFolder testee, IFolder testFolder) {
//		Set<HibSubFolder> children = ((HibFolder) testFolder).getSubFolders();
//		if (children.contains(testee)) {
//			return true;
//		}
//		for (HibSubFolder sub : children) {
//			if (((HibFolder) sub).testeeChildOf(testee, sub))
//				return true;
//		}
//		return false;
//	}

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
		return folder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#getMapIterator()
	 */
	public Iterator<IMap> getMapIterator() {
		return new IterationCaster<IMap, HibMap>(hibMaps.iterator());
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

		HibMap m = (HibMap) newMap;
		m.changeFolder(this);
//		HibMap copy = new HibMap(this,m, true);
//		copy.changeFolder(this);
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
		HibSubFolder hibSubFolder = (HibSubFolder)subFolder;
		hibSubFolder.changeParentFolder(this);
		return hibSubFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#numMaps()
	 */
	public int getNumMaps() {
		if (hibMaps == null)
			return 0;
		int nummaps = hibMaps.size();
		return nummaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#numSubFolders()
	 */
	public int numSubFolders() {
		if (subFolders == null)
			return 0;
		int numsubfolders = getSubFolders().size();
		return numsubfolders;
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
		removeHibSubFolder(hibSubFolder);
		hibSubFolder.changeRepository(null);
	}
	
	public void removeMap(IMap map){
		HibMap hibMap = (HibMap) map; 
		removeMapDiagram(hibMap);
		hibMap.changeRepository(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#renameMap(org.pathwayeditor.businessobjects.repository.IMap,
	 *      java.lang.String)
	 */
	public void renameMap(IMap map, String newMapName) {
		if (canUseMapName(newMapName)) {
			HibMap m = (HibMap) map;// map needs to be
			// explicitly removed from
			// its owning collection and
			// added back after name
			// change
			hibMaps.remove(m); // order is important - rename of map
			// changes equals!
			m.setName(newMapName);
			hibMaps.add(m);
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
		((HibSubFolder) subFolder).setName(newFolderName); // this step is
		// necessary as
		// Hibernate
		// does not see
		// the subFolder
		// as = any
		// object in
		// this
		subFolders.remove((HibSubFolder) subFolder); // folders
		// collection of
		// subfolders - so
		// changes in the
		// name are not
		// propogated.
		subFolders.add((HibSubFolder) subFolder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#getSubFolderIterator()
	 */
	public Iterator<ISubFolder> getSubFolderIterator() {
		return new IterationCaster<ISubFolder, HibSubFolder>(subFolders.iterator());
	}

	public int getNumSubFolders(){
		return this.subFolders.size();
	}
	
}
