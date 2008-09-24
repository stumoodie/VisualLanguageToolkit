package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IMap;

public class HibMap implements IMap, Serializable, IPropertyChangeSupport {
	private static final long serialVersionUID = -7566323206185334088L;

	private Long id;
	private HibFolder folder;
	private String name = "";
	private String description = "";
	private HibRepository repository;
	private int iNode;
	private PropertyChangeSupport listenerManager; // stores all registered listeners for this class

	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibMap() {
		listenerManager = new PropertyChangeSupport(this);
	}

	public HibMap(HibFolder hibFolder, String name) {
		this();
		this.folder = hibFolder;
		this.name = name;
		this.repository = hibFolder.getRepository();
		this.iNode = this.repository.getINodeCounter().nextIndex();
		listenerManager = new PropertyChangeSupport(this);
	}

	public HibMap(HibFolder newParent, HibMap other) {
		this(newParent,other,false);
		this.iNode = this.repository.getINodeCounter().nextIndex();
	}

	public HibMap(HibFolder newParent, HibMap other, boolean isCompleteCopy) {
		this.folder = newParent;
		this.name = other.name;
		this.description = other.description;
		this.repository = newParent.getRepository();
		if(isCompleteCopy)
			this.iNode=other.iNode;
		listenerManager = new PropertyChangeSupport(this);
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
	
	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) { 
		this.id = id;
	}
	
	// NH - we need this for moveSubFolder and moveMapDiagram to work - see the wiki
	protected void nullId(){
		this.id=null;
	}

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
			oldOwner.removeMapDiagram(this);
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
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * @param m
	 */
	protected void copyINode(HibMap m) {
		this.iNode=m.iNode;
		}

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
}
