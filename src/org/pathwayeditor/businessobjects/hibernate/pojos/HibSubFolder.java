package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * NonRootFolder generated by hbm2java
 */
public class HibSubFolder extends HibFolder implements ISubFolder, Serializable {
	private static final long serialVersionUID = -7826318326217020101L;
	private HibFolder parentFolder;
	private String name="";

	public HibSubFolder() {
		super();
	}

	public HibSubFolder(HibFolder parentFolder, String name) {
		super();
		setRepository(parentFolder.getRepository());
		this.parentFolder = parentFolder;
		this.name = name;
	}

	public HibSubFolder(HibFolder newParent, HibSubFolder other) {
		this(newParent,other,false);
	}
	public HibSubFolder(HibFolder newParent, HibSubFolder other,boolean isCompleteCopy) {
		super(other,isCompleteCopy);
		setRepository(newParent.getRepository());
		setMapDiagramRepositories();
		setSubFolderRepositoriesAndMapDiagramRepositories();
		this.parentFolder = newParent;
		this.name = other.name;
	}
	

	/**
	 * 
	 */
	private void setMapDiagramRepositories() {
		for(HibMapDiagram mapDiag: getMapDiagrams()){
			mapDiag.setRepository(getRepository());
		}
	}

	/**
	 * 
	 */
	private void setSubFolderRepositoriesAndMapDiagramRepositories() {
		for (HibSubFolder sub: getSubFolders()){
			sub.setRepository(getRepository());
			for (HibMapDiagram d: sub.getMapDiagrams())
				d.setRepository(getRepository());
			sub.setSubFolderRepositoriesAndMapDiagramRepositories();
		}
	}

	public HibFolder getParentFolder() {
		return this.parentFolder;
	}

	void setParentFolder(HibFolder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public void changeParentFolder(HibFolder newParentFolder) {
		HibFolder oldParentFolder = this.parentFolder;
		this.parentFolder = newParentFolder;
		if (oldParentFolder != null) {
			oldParentFolder.getSubFolders().remove(this);
		}
		if (this.parentFolder != null) {
			this.parentFolder.getSubFolders().add(this);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.repository.ISubFolder#getParent()
	 */
	public IFolder getParent() {
		return getParentFolder();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibFolder#getPath()
	 */
	@Override
	public String getPath() {
		String path=parentFolder.getPath()+"/"+name;
		path=path.replaceAll("//","/");
		return path;
	}
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof HibSubFolder))
//			return false;
//		final HibSubFolder other = (HibSubFolder) obj;
//		if (this.parentFolder != null) {
//			if (other.getParentFolder() == null)
//				return false;
//			else if (!this.parentFolder.equals(other.getParentFolder()))
//				return false;
//		}
//		if(this.name!=null){
//			if(other.getName()==null)
//				return false;
//			else if(!this.name.equals(other.getName()))
//				return false;
//		}
//		return true;
//	}
//	/* (non-Javadoc)
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		return (name==null?173:name.hashCode())+(parentFolder==null?0:parentFolder.hashCode());
//	}
}
