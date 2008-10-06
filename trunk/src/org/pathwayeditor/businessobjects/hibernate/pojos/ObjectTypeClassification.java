/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

/**
 * @author smoodie
 *
 */
public enum ObjectTypeClassification {
	ROOTOBJECT(0), SHAPE(1), LINK(2);
	
	private final int id; 
	
	private ObjectTypeClassification(int id){
		this.id = id;
	}
	
	
	public int toInt(){
		return this.id;
	}
	
	public ObjectTypeClassification fromInt(int id){
		ObjectTypeClassification retVal = null;
		switch(id){
		case 0:
			retVal = ROOTOBJECT;
			break;
		case 1:
			retVal = SHAPE;
			break;
		case 2:
			retVal = LINK;
			break;
		default:
			throw new IllegalArgumentException("Type id of, " + id + ", is unknown");
		}
		return retVal;
	}
}
