package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;

import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;



/**
 * BendPoint generated by hbm2java
 */
public class HibBendPoint  implements IBendPoint,  Serializable {

     private Long id;
     private HibLinkAttribute owningLink;
     private int indexPos;
     private int XPosition;
     private int YPosition;

    public HibBendPoint() {
    }

    public HibBendPoint(HibLinkAttribute owningLink, int indexPos, int XPosition, int YPosition) {
       this.owningLink = owningLink;
       this.indexPos = indexPos;
       this.XPosition = XPosition;
       this.YPosition = YPosition;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public ILink getOwningLink() {
        return this.owningLink;
    }
    
    public void setOwningLink(HibLinkAttribute owningLink) {
        this.owningLink = owningLink;
    }
    public int getIndexPos() {
        return this.indexPos;
    }
    
    public void setIndexPos(int indexPos) {
        this.indexPos = indexPos;
    }
    public int getXPosition() {
        return this.XPosition;
    }
    
    public void setXPosition(int XPosition) {
        this.XPosition = XPosition;
    }
    public int getYPosition() {
        return this.YPosition;
    }
    
    public void setYPosition(int YPosition) {
        this.YPosition = YPosition;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HibBendPoint) ) return false;
		 HibBendPoint castOther = ( HibBendPoint ) other; 
         
		 return ( (this.getOwningLink()==castOther.getOwningLink()) || 
				 ( this.getOwningLink()!=null && castOther.getOwningLink()!=null && 
						 this.getOwningLink().equals(castOther.getOwningLink()) ) )
 && (this.getIndexPos()==castOther.getIndexPos());
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getOwningLink() == null ? 0 : this.getOwningLink().hashCode() );
         result = 37 * result + this.getIndexPos();
         
         
         return result;
   }   

  // The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = 6225340614454215601L;

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint#getLocation()
	 */
	public Location getLocation() {
		return new Location ( XPosition , YPosition );
	}
    	

}


