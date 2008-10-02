package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

/**
 * NonRootFolder generated by hbm2java
 */
public class HibSubFolder extends HibFolder implements ISubFolder, Serializable {
	private static final long serialVersionUID = -7826318326217020101L;
	public static final String FOLDER_RENAME_EVENT = "FOLDER_RENAME_EVENT";
	private HibFolder parentFolder;
	private String name="";
	private String description="";

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibSubFolder() {
		super();
	}

	public HibSubFolder(HibFolder parentFolder, String name) {
		super(parentFolder.getRepository());
		this.parentFolder = parentFolder;
		this.name = name;
	}

//	public HibSubFolder(HibFolder newParent, HibSubFolder other) {
//		this(newParent,other,false);
//	}

	public HibSubFolder(HibFolder newParent, HibSubFolder other) {
		super(newParent.getRepository(), other);
		setRepository(newParent.getRepository());
		setMapDiagramRepositories();
		setSubFolderRepositoriesAndMapDiagramRepositories();
		this.parentFolder = newParent;
		this.name = other.name;
	}

//	public HibSubFolder(HibFolder newParent, HibSubFolder other,boolean isCompleteCopy) {
//		super(newParent.getRepository(), other,isCompleteCopy);
//		setRepository(newParent.getRepository());
//		setMapDiagramRepositories();
//		setSubFolderRepositoriesAndMapDiagramRepositories();
//		this.parentFolder = newParent;
//		this.name = other.name;
//	}
	

	/**
	 * 
	 */
	private void setMapDiagramRepositories() {
		for(HibMap mapDiag: getMapDiagrams()){
			mapDiag.setRepository(getRepository());
		}
	}

	/**
	 * 
	 */
	private void setSubFolderRepositoriesAndMapDiagramRepositories() {
		for (HibSubFolder sub: getSubFolders()){
			sub.setRepository(getRepository());
			for (HibMap d: sub.getMapDiagrams())
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
		String oldname=this.name;
		this.name = name;
		firePropertyChange(FOLDER_RENAME_EVENT, oldname, name);
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
	public String getPath() {
		StringBuilder pathBuilder = new StringBuilder(parentFolder.getPath());
		pathBuilder.append(name);
		pathBuilder.append("/");
//		path=path.replaceAll("//","/");
		return pathBuilder.toString();
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDescendent(ISubFolder childFolder){
		IFolder parent = childFolder.getParent();
		boolean retVal = false;
		// now we traverse up the tree to it's root and see this
		// folder is present. If it is then this class is an
		// ancestor of childFolder.
		while(parent instanceof ISubFolder && !retVal) {
			retVal = this.equals(parent);
			parent = ((ISubFolder)parent).getParent();
		}
		return retVal;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.repository.IFolder#canMoveSubfolder(org.pathwayeditor.businessobjects.repository.ISubFolder)
	 */
	public boolean canMoveSubfolder(ISubFolder subFolder) {
		boolean folderCanBeMoved = false;
		if (subFolder == null)
			return folderCanBeMoved;
		if (!subFolder.isDescendent(this) && canUseSubfolderName(subFolder.getName()) && this.getRepository().equals(subFolder.getRepository()))
			folderCanBeMoved = true;
		return folderCanBeMoved;
	}

	public boolean canCopySubfolder(ISubFolder origSubFolder){
		return origSubFolder != null && !origSubFolder.getParent().equals(this) && this.canUseSubfolderName(origSubFolder.getName())
		&& !origSubFolder.isDescendent(this);
	}
}