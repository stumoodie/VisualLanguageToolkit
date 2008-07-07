package org.pathwayeditor.businessobjects.hibernate.pojos;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.repository.IFolder;

/**
 * Folder generated by hbm2java
 */
public abstract class HibFolder implements Serializable {
	private static final long serialVersionUID = 8668639813872187460L;
	private Long id;
	private Set<HibMapDiagram> hibMapDiagrams = new HashSet<HibMapDiagram>(0);
	private Set<HibSubFolder> subFolders = new HashSet<HibSubFolder>(0);

	public HibFolder() {
	}

	public HibFolder(HibFolder other) {
		for (HibMapDiagram diagram : other.getMapDiagrams()) {
			this.hibMapDiagrams.add(new HibMapDiagram(this, diagram));
		}
		for (HibSubFolder subFolder : other.getSubFolders()) {
			this.subFolders.add(new HibSubFolder(this, subFolder));
		}
	}

	public Long getId() {
		return this.id;
	}

	void setId(Long id) {
		this.id = id;
	}

	public Set<HibMapDiagram> getMapDiagrams() {
		return this.hibMapDiagrams;
	}

	void setMapDiagrams(Set<HibMapDiagram> hibMapDiagrams) {
		this.hibMapDiagrams = hibMapDiagrams;
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
		}
		this.subFolders.add(newSubFolder);
		newSubFolder.setParentFolder(this);
	}

	void removeSubFolder(HibSubFolder subFolder) {
		if (subFolder == null)
			throw new IllegalArgumentException("subFolder cannot be null");
		if (subFolder.getParentFolder() != this)
			throw new IllegalArgumentException(
					"subFolder must be a child of this folder");

		this.subFolders.remove(subFolder);
		subFolder.setParentFolder(null);
	}
	
	void addMapDiagram(HibMapDiagram newMapDiagram) {
		if (newMapDiagram == null)
			throw new IllegalArgumentException("newMapDiagram cannot be null");

		HibFolder oldParentFolder = newMapDiagram.getFolder();
		if (oldParentFolder != null) {
			oldParentFolder.getMapDiagrams().remove(newMapDiagram);
		}
		this.hibMapDiagrams.add(newMapDiagram);
		newMapDiagram.setFolder(this);
	}

	void removeMapDiagram(HibMapDiagram mapDiagram) {
		if (mapDiagram == null)
			throw new IllegalArgumentException("mapDiagram cannot be null");
		if (mapDiagram.getFolder() != this)
			throw new IllegalArgumentException(
					"mapDiagram must belong to this folder");

		this.hibMapDiagrams.remove(mapDiagram);
		mapDiagram.setFolder(null);
	}
	
	public abstract IFolder getBusinessObject();

}
