package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.pojos.IBusinessObjectData;
import org.pathwayeditor.businessobjects.pojos.SubFolder;


/**
 * NonRootFolder generated by hbm2java
 */
public class HibSubFolder extends HibFolder implements IBusinessObjectData<SubFolder>, Serializable {
	private static final long serialVersionUID = -7826318326217020101L;
	private HibFolder parentFolder;
	private String name;
	private SubFolder businessObject = null;

	public HibSubFolder() {
		super();
	}

	public HibSubFolder(HibFolder parentFolder, String name) {
		super();
		this.parentFolder = parentFolder;
		this.name = name;
	}

	public HibSubFolder(HibFolder newParent, HibSubFolder other) {
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
	public SubFolder getBusinessObject() {
		if(this.businessObject  == null){
			this.businessObject = new SubFolder(this);
		}
		return this.businessObject;
	}
}
