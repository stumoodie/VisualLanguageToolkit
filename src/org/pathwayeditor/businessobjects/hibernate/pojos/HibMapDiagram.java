package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.pojos.IBusinessObjectData;
import org.pathwayeditor.businessobjects.pojos.Map;


public class HibMapDiagram  implements IBusinessObjectData<Map>, java.io.Serializable {
	private static final long serialVersionUID = -7566323206185334088L;
	
     private Long id;
     private HibFolder owner;
     private String name = "";
     private String description = "";
     private Map businessObject = null;

    public HibMapDiagram() {
    }

    public HibMapDiagram(HibFolder hibFolder, String name) {
       this.owner = hibFolder;
       this.name = name;
    }
   
    public HibMapDiagram(HibFolder newParent, HibMapDiagram other){
    	this.owner = newParent;
    	this.name = other.name;
    	this.description = other.description;
    }
   
    public Long getId() {
        return this.id;
    }
    
    @SuppressWarnings("unused")
	private void setId(Long id) {
        this.id = id;
    }
    
    public HibFolder getFolder() {
        return this.owner;
    }
    
    void setOwner(HibFolder hibFolder) {
        this.owner = hibFolder;
    }
    
	public void changeOwner(HibFolder newOwner){
		HibFolder oldOwner = this.owner;
		this.owner = newOwner;
		if(oldOwner != null){
			oldOwner.getMapDiagrams().remove(this);
		}
		if(this.owner != null){
			this.owner.getMapDiagrams().add(this);
		}
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.pojos.IBusinessObjectData#getBusinessObject()
	 */
	public Map getBusinessObject() {
		if(this.businessObject == null){
			this.businessObject = new Map(this);
		}
		return this.businessObject;
	}
}


