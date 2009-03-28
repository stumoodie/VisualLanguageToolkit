package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;
import org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener;
import org.pathwayeditor.businessobjects.repository.ListenableMap;
import org.pathwayeditor.businessobjects.repository.IRepositoryPropertyChangeEvent.PropertyType;

public class HibMap implements IMap, Serializable {
	private static final long serialVersionUID = -7566323206185334088L;

	private static final int LIST_SIZE = 1;

	private Long id;
	private HibFolder folder;
	private String name = "";
	private String description = "";
	private HibRepository repository;
	private int iNode;
	private final ListenableMap listenable = new ListenableMap();

	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibMap() {
	}

	public HibMap(HibFolder hibFolder, String name) {
		this();
		this.folder = hibFolder;
		this.name = name;
		this.repository = hibFolder.getRepository();
		this.iNode = this.repository.getINodeCounter().nextIndex();
		this.repository.getMaps().add(this);
	}

	public HibMap(HibFolder newParent, HibMap other) {
		this.folder = newParent;
		this.name = other.name;
		this.description = other.description;
		this.repository = newParent.getRepository();
		this.iNode = this.repository.getINodeCounter().nextIndex();
		this.repository.getMaps().add(this);
	}
	
	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) { 
		this.id = id;
	}
	
	// NH - we need this for moveSubFolder and moveMapDiagram to work - see the wiki
//	protected void nullId(){
//		this.id=null;
//	}

	public void setRepository(HibRepository repository) {
		this.repository = repository;
	}

	public HibRepository getRepository() {
		return repository;
	}

	public HibFolder getFolder() {
		return this.folder;
	}

	void setFolder(HibFolder hibFolder) {
		this.folder = hibFolder;
	}

	public void changeFolder(HibFolder newFolder) {
		HibFolder oldOwner = this.folder;
		if (oldOwner != null) {
			oldOwner.getMapDiagrams().remove(this);
		}
		this.folder = newFolder;
		if (this.folder != null) {
			this.folder.getMapDiagrams().add(this);
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		this.listenable.notifyProperyChange(PropertyType.NAME, oldName, this.name);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		String oldValue = this.description;
		this.description = description;
		this.listenable.notifyProperyChange(PropertyType.DESCRIPTION, oldValue, this.description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getOwner()
	 */
	public IFolder getOwner() {
		return folder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getINode()
	 */
	public int getINode() {
		return iNode;
	}
	
	
	@SuppressWarnings("unused")
	private void setINode(int iNode){
		this.iNode = iNode;
	}

//	/**
//	 * @param m
//	 */
//	protected void copyINode(HibMap m) {
//		this.iNode=m.iNode;
//		}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getINode();
		result = prime * result
				+ ((this.getRepository() == null) ? 0 : this.getRepository().hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibMap))
			return false;
		HibMap other = (HibMap) obj;
		if (this.getINode() != other.getINode())
			return false;
		if (this.getRepository() == null) {
			if (other.getRepository() != null)
				return false;
		} else if (!this.getRepository().equals(other.getRepository()))
			return false;
		return true;
	}

	void changeRepository(HibRepository repository) {
		if(this.repository != null){
			this.repository.getMaps().remove(this);
		}
		if(repository != null){
			repository.getMaps().add(this);
		}
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IMap#getPath()
	 */
	public String getPath() {
		StringBuilder pathBuilder = new StringBuilder(this.getOwner().getPath());
		pathBuilder.append(name);
		return pathBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#addChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public void addChangeListener(IRepositoryItemChangeListener changeListener) {
		this.listenable.addListener(changeListener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#getChangeListeners()
	 */
	public List<IRepositoryItemChangeListener> getChangeListeners() {
		return this.listenable.exportListenerList();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#removeChangeListener(org.pathwayeditor.businessobjects.repository.IRepositoryItemChangeListener)
	 */
	public void removeChangeListener(
			IRepositoryItemChangeListener changeListener) {
		this.listenable.removeListener(changeListener);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IRepositoryItem o) {
		return Integer.valueOf(this.iNode).compareTo(Integer.valueOf(o.getINode()));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.IRepositoryItem#levelOrderIterator()
	 */
	public Iterator<IRepositoryItem> levelOrderIterator() {
		List<IRepositoryItem> list = new ArrayList<IRepositoryItem>(LIST_SIZE);
		list.add(this);
		return list.iterator();
	}

	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getName());
		builder.append("(");
		builder.append("inode=");
		builder.append(this.getINode());
		builder.append(", name=");
		builder.append(this.name);
		builder.append(')');
		return builder.toString();
	}
}
