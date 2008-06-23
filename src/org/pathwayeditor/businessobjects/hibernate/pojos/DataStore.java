package org.pathwayeditor.businessobjects.hibernate.pojos;
// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA



/**
 * DataStore generated by hbm2java
 */
public class DataStore  implements java.io.Serializable {


     private Long id;
     private String name;
     private String description;
     private RootFolder rootFolder;

    public DataStore() {
    }

	
    public DataStore(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public DataStore(String name, String description, RootFolder rootFolder) {
       this.name = name;
       this.description = description;
       this.rootFolder = rootFolder;
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
    public RootFolder getRootFolder() {
        return this.rootFolder;
    }
    
     void setRootFolder(RootFolder rootFolder) {
        this.rootFolder = rootFolder;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("id").append("='").append(getId()).append("' ");			
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("description").append("='").append(getDescription()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DataStore) ) return false;
		 DataStore castOther = ( DataStore ) other; 
         
		 return ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         
         
         return result;
   }   

  // The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = -841109914395755034L;
    	
    public DataStore(DataStore other) {
    	this.name = other.name;
    	this.description = other.description;
    	this.rootFolder = new RootFolder(this, other.rootFolder);
    }

    public void changeRootFolder(RootFolder newRootFolder){
	   RootFolder oldFolder = this.rootFolder;
	   this.rootFolder = newRootFolder;
	   if(oldFolder != null){
		   oldFolder.setDataStore(null);
	   }
	   if(this.rootFolder != null){
		   this.rootFolder.setDataStore(this);
	   }
   }
    	
  // end of extra code specified in the hbm.xml files

}


