package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.pojos.IBusinessObjectData;
import org.pathwayeditor.businessobjects.pojos.SubFolder;
import org.pathwayeditor.businessobjects.repository.ISubFolder;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

/**
 * NonRootFolder generated by hbm2java
 */
public class HibNonRootFolder extends HibFolder implements IBusinessObjectData<ISubFolder>, Serializable {
	private static final long serialVersionUID = -7826318326217020101L;
	private HibFolder parentFolder;
	private String name;
	private ISubFolder businessObject = null;

	public HibNonRootFolder() {
		super();
	}

	public HibNonRootFolder(HibFolder parentFolder, String name) {
		super();
		this.parentFolder = parentFolder;
		this.name = name;
	}

	public HibNonRootFolder(HibFolder newParent, HibNonRootFolder other) {
		super(other);
		this.parentFolder = newParent;
		this.name = other.name;
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
	 * @see org.pathwayeditor.businessobjects.pojos.IBusinessObjectData#getBusinessObject()
	 */
	public ISubFolder getBusinessObject() {
		if(this.businessObject  == null){
			this.businessObject = new SubFolder(this);
		}
		return this.businessObject;
	}
}
