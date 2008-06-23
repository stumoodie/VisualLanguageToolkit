package org.pathwayeditor.businessobjects.hibernate.pojos;
// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA


import java.util.Set;

/**
 * NonRootFolder generated by hbm2java
 */
public class NonRootFolder extends org.pathwayeditor.businessobjects.hibernate.pojos.Folder implements java.io.Serializable {


     private Folder parentFolder;
     private String name;

    public NonRootFolder() {
    }

	
    public NonRootFolder(Folder parentFolder, String name) {
        this.parentFolder = parentFolder;
        this.name = name;
    }
    public NonRootFolder(Set<MapDiagram> mapDiagrams, Set<NonRootFolder> subFolders, Folder parentFolder, String name) {
        super(mapDiagrams, subFolders);        
       this.parentFolder = parentFolder;
       this.name = name;
    }
   
    public Folder getParentFolder() {
        return this.parentFolder;
    }
    
     void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }



  // The following is extra code specified in the hbm.xml files

    private static final long serialVersionUID = -7826318326217020101L;
	    	
    public NonRootFolder(Folder newParent, NonRootFolder other){
    	this.parentFolder = newParent;
    	this.name = other.name;
    }
	
    public void changeParentFolder(Folder newParentFolder){
	   Folder oldParentFolder = this.parentFolder;
	   this.parentFolder = newParentFolder;
	   if(oldParentFolder != null){
		   oldParentFolder.getSubFolders().remove(this);
	   }
	   if(this.parentFolder != null){
		   this.parentFolder.getSubFolders().add(this);
	   }
   }
	    	
  // end of extra code specified in the hbm.xml files

}


