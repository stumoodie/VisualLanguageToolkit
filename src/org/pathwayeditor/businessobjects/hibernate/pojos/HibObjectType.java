package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

/**
 * ObjectType generated by hbm2java
 */
public class HibObjectType implements Serializable {
	private static final int DEFAULT_UNIQUE_ID = -999991;
	
	private static final long serialVersionUID = -1860135213188225684L;

	private Long id;
	private HibNotation hibNotation;
	private String displayName;
	private String description;
	private ObjectTypeClassification classificationCode; 
	private int uniqueId = DEFAULT_UNIQUE_ID;
	
	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibObjectType() {
	}

	public HibObjectType(int uniqueId, String name, String description, ObjectTypeClassification classnCode) {
		this.hibNotation = null;
		this.uniqueId = uniqueId;
		this.displayName = name;
		this.description = description;
		this.classificationCode = classnCode;
	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the uniqueId
	 */
	public int getUniqueId() {
		return this.uniqueId;
	}

	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public HibNotation getNotation() {
		return this.hibNotation;
	}

	void setNotation(HibNotation hibNotation) {
		this.hibNotation = hibNotation;
	}
	
	public void changeNotation(HibNotation hibNotation){
		if(this.hibNotation != null){
			this.hibNotation.getObjectTypes().remove(this);
		}
		if(hibNotation != null){
			hibNotation.getObjectTypes().add(this);
		}
		this.hibNotation = hibNotation;
	}

	public String getName() {
		return this.displayName;
	}

	public void setName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ObjectTypeClassification getClassificationCode() {
		return this.classificationCode;
	}

	public void setClassificationCode(ObjectTypeClassification classificationCode) {
		this.classificationCode = classificationCode;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibObjectType))
			return false;
		HibObjectType castOther = (HibObjectType) other;

		return ((this.getNotation() == castOther.getNotation()) || (this
				.getNotation() != null
				&& castOther.getNotation() != null && this.getNotation().equals(
				castOther.getNotation())))
				&& ((this.getName().equals(castOther.getName())) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getNotation() == null ? 0 : this.getNotation().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());

		return result;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(notation=");
		builder.append(this.hibNotation.getQualifiedName());
		builder.append(", uid=");
		builder.append(this.uniqueId);
		builder.append(", classnCode=");
		builder.append(this.classificationCode);
		builder.append(",name=");
		builder.append(this.getName());
		builder.append(")");
		return builder.toString();
	}
}
