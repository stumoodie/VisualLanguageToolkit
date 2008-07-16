package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.pojos.IBusinessObjectData;
import org.pathwayeditor.businessobjects.pojos.RootFolder;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA


/**
 * RootFolder generated by hbm2java
 */
public class HibRootFolder extends HibFolder implements IBusinessObjectData<RootFolder>, Serializable {
	private static final long serialVersionUID = 3780104587152506255L;
	private HibRepository repository = null;
	private RootFolder businessObject = null;

	/**
	 * Default contructor typically used by hibernate.
	 */
	public HibRootFolder() {
		super();
	}

	/**
	 * Contructor used for new object created by a business object.
	 * @param businessObject
	 * @param repository
	 * @param other
	 */
	public HibRootFolder(RootFolder businessObject, HibRepository repository) {
		super();
		this.repository = repository;
		this.businessObject = businessObject;
	}

	/**
	 * Copy constructor for hibernate object. Copies to a new repository that is provided.
	 * @param repository
	 */
	public HibRootFolder(HibRepository newRepository, HibRootFolder other) {
		super(other);
		this.repository = newRepository;
		this.businessObject = null;
	}

	public HibRepository getRepository() {
		return this.repository;
	}

	void setRepository(HibRepository hibRepository) {
		this.repository = hibRepository;
	}

   public void changeRepository(HibRepository newRepository){
	   HibRepository oldRepository = this.repository;
	   this.repository = newRepository;
	   if(oldRepository != null){
		   oldRepository.setRootFolder(null);
	   }
	   if(this.repository != null){
		   this.repository.setRootFolder(this);
	   }
   }

	/**
	 * @return
	 */
	public RootFolder getBusinessObject() {
		if(this.businessObject == null){
			this.businessObject = new RootFolder(this);
		}
		return this.businessObject;
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
		if (!(obj instanceof HibRootFolder))
			return false;
		final HibRootFolder other = (HibRootFolder) obj;
		if (this.repository != null) {
			if (other.getRepository() == null)
				return false;
			else if (!this.repository.equals(other.getRepository()))
				return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 31+ (repository==null?0:repository.hashCode());
	}

}
