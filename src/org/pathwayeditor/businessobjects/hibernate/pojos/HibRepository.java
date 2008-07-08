package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.pojos.IBusinessObjectData;
import org.pathwayeditor.businessobjects.pojos.Repository;

/**
 * DataStore generated by hbm2java
 */
public class HibRepository implements IBusinessObjectData<Repository>, Serializable {
	private static final long serialVersionUID = -841109914395755034L;
	private Long id = null;
	private String name = null;
	private String description = null;
//	private HibRootFolder hibRootFolder = null;
	private HibRootFolder rootFolder = null;
	private Repository businessObject = null;
	private int buildNum;

	public HibRepository() {
	}

	public HibRepository(String name, String description, int buildNum ) {
		this.name = name;
		this.description = description;
		this.buildNum = buildNum;
	}

   public void changeRootFolder(HibRootFolder newRootFolder){
	   HibRootFolder oldRootFolder = this.rootFolder;
	   this.rootFolder = newRootFolder;
	   if(oldRootFolder != null){
		   oldRootFolder.setRepository(null);
	   }
	   if(this.rootFolder != null){
		   this.rootFolder.setRepository(this);
	   }
   }
	
	public Long getId() {
		return this.id;
	}

	void setId(Long id) {
		this.id = id;
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

	public HibRootFolder getRootFolder() {
		return this.rootFolder;
	}

	void setRootFolder(HibRootFolder hibRootFolder) {
		this.rootFolder = hibRootFolder;
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
		buffer.append("name").append("='").append(getName()).append("' ");
		buffer.append("description").append("='").append(getDescription())
				.append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibRepository))
			return false;
		HibRepository castOther = (HibRepository) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());

		return result;
	}


     public Repository getBusinessObject(){
		if(this.businessObject == null){
			this.businessObject = new Repository(this);
		}
		return this.businessObject;
	}
	
	public void setBuildNum(int buildNum){
		this.buildNum = buildNum;
	}
	
	public int getBuildNum(){
		return this.buildNum;
	}
}
