package org.pathwayeditor.businessobjects.hibernate.pojos;
// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA


import java.util.HashMap;
import java.util.Map;

/**
 * LinkTerminus generated by hbm2java
 */
public class HibLinkTerminus  implements java.io.Serializable {


     private Long id;
     private HibLink hibLink;
     private int linkEndType;
     private short offset;
     private HibLinkTerminusDecorator decorator;
     private HibLinkEndDecorator linkenddecorators;
     private Map<String,HibProperty> hibProperties = new HashMap<String,HibProperty>(0);

    public HibLinkTerminus() {
    }

	
    public HibLinkTerminus(HibLink hibLink, int linkEndType, short offset) {
        this.hibLink = hibLink;
        this.linkEndType = linkEndType;
        this.offset = offset;
    }
    public HibLinkTerminus(HibLink hibLink, int linkEndType, short offset, HibLinkTerminusDecorator decorator, HibLinkEndDecorator linkenddecorators, Map<String,HibProperty> hibProperties) {
       this.hibLink = hibLink;
       this.linkEndType = linkEndType;
       this.offset = offset;
       this.decorator = decorator;
       this.linkenddecorators = linkenddecorators;
       this.hibProperties = hibProperties;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public HibLink getLink() {
        return this.hibLink;
    }
    
    public void setLink(HibLink hibLink) {
        this.hibLink = hibLink;
    }
    public int getLinkEndType() {
        return this.linkEndType;
    }
    
    public void setLinkEndType(int linkEndType) {
        this.linkEndType = linkEndType;
    }
    public short getOffset() {
        return this.offset;
    }
    
    public void setOffset(short offset) {
        this.offset = offset;
    }
    public HibLinkTerminusDecorator getDecorator() {
        return this.decorator;
    }
    
    public void setDecorator(HibLinkTerminusDecorator decorator) {
        this.decorator = decorator;
    }
    public HibLinkEndDecorator getLinkenddecorators() {
        return this.linkenddecorators;
    }
    
    public void setLinkenddecorators(HibLinkEndDecorator linkenddecorators) {
        this.linkenddecorators = linkenddecorators;
    }
    public Map<String,HibProperty> getProperties() {
        return this.hibProperties;
    }
    
    public void setProperties(Map<String,HibProperty> hibProperties) {
        this.hibProperties = hibProperties;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HibLinkTerminus) ) return false;
		 HibLinkTerminus castOther = ( HibLinkTerminus ) other; 
         
		 return ( (this.getLink()==castOther.getLink()) || ( this.getLink()!=null && castOther.getLink()!=null && this.getLink().equals(castOther.getLink()) ) )
 && (this.getLinkEndType()==castOther.getLinkEndType());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getLink() == null ? 0 : this.getLink().hashCode() );
         result = 37 * result + this.getLinkEndType();
         
         
         
         
         return result;
   }   

  // The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = -4462637156010353035L;
    	
  // end of extra code specified in the hbm.xml files
	
	public void addLinkTerminusProperty ( String name , HibProperty toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibLinkTerminus oldLinkTerminus = toAdd.getLinkTerminus() ;
		if (oldLinkTerminus != null) {
			oldLinkTerminus.getProperties().remove(toAdd);
		}
		this.hibProperties.put(name ,toAdd);
		toAdd.setLinkTerminus(this);
	}
	
	void removeLinTerminusProperty(String toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibProperty propertyToRemove = hibProperties.get(toRemove) ;
		if  (propertyToRemove == null)
			throw new IllegalStateException("property cannot be null");
		if (propertyToRemove.getLinkTerminus() != this)
			throw new IllegalArgumentException(
					"property must belong to this canvas");	
		
		this.hibProperties.remove(toRemove) ;
		propertyToRemove.setLinkTerminus(null);
	}
	
//	public void changeLinkEndDecorator ( HibLinkEndDecorator newLinkEndDecorator)
//	{
//		   HibLinkEndDecorator oldDecorator = this.linkenddecorators ;
//		   this.linkenddecorators = newLinkEndDecorator;
//		   if(oldDecorator != null){
//			   oldDecorator.setLinkTerminus(null);
//		   }
//		   if(this.linkenddecorators != null){
//			   this.linkenddecorators.setLinkTerminus(this) ;
//		   }
//	}
//	
//	public void changeLinkTerminusDecorator ( HibLinkTerminusDecorator newLinkTerminusDecorator)
//	{
//		   HibLinkTerminusDecorator oldDecorator = this.decorator ;
//		   this.decorator = newLinkTerminusDecorator;
//		   if(oldDecorator != null){
//			   oldDecorator.setLinkTerminus(null);
//		   }
//		   if(this.linkenddecorators != null){
//			   this.linkenddecorators.setLinkTerminus(this) ;
//		   }
//	}
	
	public void addProperty ( String name , HibProperty toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibLinkTerminus oldLinkTerminus = toAdd.getLinkTerminus() ;
		if (oldLinkTerminus != null) {
			oldLinkTerminus.getProperties().remove(toAdd);
		}
		this.hibProperties.put(name ,toAdd);
		toAdd.setLinkTerminus(this);
	}
	
	void removeProperty(String toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibProperty propertyToRemove = hibProperties.get(toRemove) ;
		if  (propertyToRemove == null)
			throw new IllegalStateException("property cannot be null");
		
		this.hibProperties.remove(toRemove) ;
		propertyToRemove.setLinkTerminus(null);
	}

}


