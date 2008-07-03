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
    
    public HibFolder getOwner() {
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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HibMapDiagram) ) return false;
		 HibMapDiagram castOther = ( HibMapDiagram ) other; 
         
		 return ( (this.getOwner()==castOther.getOwner()) || ( this.getOwner()!=null && castOther.getOwner()!=null && this.getOwner().equals(castOther.getOwner()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOwner() == null ? 0 : this.getOwner().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         
         return result;
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


