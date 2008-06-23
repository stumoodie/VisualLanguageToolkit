package org.pathwayeditor.businessobjects.hibernate.pojos;
// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Context generated by hbm2java
 */
public class Context  implements java.io.Serializable {


     private Long id;
     private String globalId;
     private String name;
     private int majorVersion;
     private int minorVersion;
     private int patchVersion;
     private String description;
     private Set<ObjectType> objectTypes = new HashSet<ObjectType>(0);

    public Context() {
    }

	
    public Context(String globalId, String name, int majorVersion, int minorVersion, int patchVersion, String description) {
        this.globalId = globalId;
        this.name = name;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
        this.description = description;
    }
    public Context(String globalId, String name, int majorVersion, int minorVersion, int patchVersion, String description, Set<ObjectType> objectTypes) {
       this.globalId = globalId;
       this.name = name;
       this.majorVersion = majorVersion;
       this.minorVersion = minorVersion;
       this.patchVersion = patchVersion;
       this.description = description;
       this.objectTypes = objectTypes;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getGlobalId() {
        return this.globalId;
    }
    
    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getMajorVersion() {
        return this.majorVersion;
    }
    
    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }
    public int getMinorVersion() {
        return this.minorVersion;
    }
    
    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }
    public int getPatchVersion() {
        return this.patchVersion;
    }
    
    public void setPatchVersion(int patchVersion) {
        this.patchVersion = patchVersion;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<ObjectType> getObjectTypes() {
        return this.objectTypes;
    }
    
    public void setObjectTypes(Set<ObjectType> objectTypes) {
        this.objectTypes = objectTypes;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Context) ) return false;
		 Context castOther = ( Context ) other; 
         
		 return ( (this.getGlobalId()==castOther.getGlobalId()) || ( this.getGlobalId()!=null && castOther.getGlobalId()!=null && this.getGlobalId().equals(castOther.getGlobalId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getGlobalId() == null ? 0 : this.getGlobalId().hashCode() );
         
         
         
         
         
         
         return result;
   }   

  // The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = 6061237727249348954L;
     void addObjectType(ObjectType newObjectType){
    	 if(newObjectType == null) throw new IllegalArgumentException("newObjectType cannot be null");
    	 
    	 Context oldContext = newObjectType.getContext();
    	 if(oldContext != null){
    		 oldContext.objectTypes.remove(newObjectType);
    	 }
    	 this.objectTypes.add(newObjectType);
    	 newObjectType.setContext(this);
     }
    	
     void removeobjectType(ObjectType objectType){
    	 if(objectType == null) throw new IllegalArgumentException("objectType cannot be null");
    	 if(objectType.getContext() != this) throw new IllegalArgumentException("objectType must be a child of this folder");
    	 
    	 this.objectTypes.remove(objectType);
    	 objectType.setContext(null);
     }
  // end of extra code specified in the hbm.xml files

}


