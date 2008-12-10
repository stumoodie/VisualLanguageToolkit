package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;

public class HibNotation implements INotation, Serializable {
	private static final long serialVersionUID = 6061237727249348954L;

	private Long id;
	private String qualifiedName;
	private String displayName;
	private String description;
	private Version version; 
	private Set<HibObjectType> hibObjectTypes = new HashSet<HibObjectType>(0);

	/**
	 * This constructor should only be used by hibernate.
	 * @deprecated Use another constructor. This constructor should not be used by application code.
	 */
	HibNotation() {
		version = new Version (0, 0, 0) ;
	}

	/**
	 * Construct a new hibernate notation description.
	 * @param displayName
	 * @param description
	 */
	public HibNotation(String qualifiedName, String displayName, String description, Version version) {
		this.qualifiedName = qualifiedName;
		this.displayName = displayName;
		this.description = description;
		this.version = version;
	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public String getQualifiedName() {
		return this.qualifiedName;
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public void setDisplayName(String name) {
		this.displayName = name;
	}

	public int getMajorVersion() {
		return this.version.getMajorVersion();
	}

	public void setMajorVersion(int majorVersion) {
		this.version = this.version.newMajorVersion(majorVersion);
	}

	public int getMinorVersion() {
		return this.version.getMinorVersion();
	}

	public void setMinorVersion(int minorVersion) {
		this.version = version.newMinorVersion(minorVersion);
	}

	public int getPatchVersion() {
		return this.version.getPatchVersion();
	}

	public void setPatchVersion(int patchVersion) {
		this.version = this.version.newPatchVersion(patchVersion);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<HibObjectType> getObjectTypes() {
		return this.hibObjectTypes;
	}

	void setObjectTypes(Set<HibObjectType> hibObjectTypes) {
		this.hibObjectTypes = hibObjectTypes;
	}

	public void addObjectType(HibObjectType newObjectType) {
		if (newObjectType == null)
			throw new IllegalArgumentException("newObjectType cannot be null");

		HibNotation oldContext = newObjectType.getNotation();
		if (oldContext != null) {
			oldContext.hibObjectTypes.remove(newObjectType);
		}
		this.hibObjectTypes.add(newObjectType);
		newObjectType.setNotation(this);
	}

	public void removeObjectType(HibObjectType hibObjectType) {
		if (hibObjectType == null)
			throw new IllegalArgumentException("objectType cannot be null");
		if (hibObjectType.getNotation() != this)
			throw new IllegalArgumentException(
					"objectType must be a child of this folder");

		this.hibObjectTypes.remove(hibObjectType);
		hibObjectType.setNotation(null);
	}

	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(globalId=");
		builder.append(this.qualifiedName);
		builder.append(", name=");
		builder.append(this.displayName);
		builder.append(", version=");
		builder.append(version);
		builder.append(")");
		return builder.toString();
	}
	
	public int compareTo(INotation notation){
		int retVal = this.qualifiedName.compareTo(notation.getQualifiedName());
		retVal = retVal == 0 ? this.version.compareTo(notation.getVersion()) : retVal;
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getDisplayName()
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getVersion()
	 */
	public Version getVersion() {
		return this.version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getQualifiedName() == null) ? 0 : this.getQualifiedName().hashCode());
		result = prime * result + ((this.getVersion() == null) ? 0 : this.getVersion().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof INotation))
			return false;
		INotation other = (INotation) obj;
		if (this.getQualifiedName() == null) {
			if (other.getQualifiedName() != null)
				return false;
		} else if (!this.getQualifiedName().equals(other.getQualifiedName()))
			return false;
		if (this.getVersion() == null) {
			if (other.getVersion() != null)
				return false;
		} else if (!this.version.equals(other.getVersion()))
			return false;
		return true;
	}
}
