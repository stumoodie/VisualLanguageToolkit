package org.pathwayeditor.businessobjects.hibernate.pojos;
// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Link generated by hbm2java
 */
public class Link  implements java.io.Serializable {


     private Long id;
     private Canvas canvas;
     private int link_index;
     private ObjectType objectType;
     private String name;
     private String description;
     private String detailedDescription;
     private String url;
     private Map<String,Property> properties = new HashMap<String,Property>(0);
     private Shape targetShape;
     private Shape sourceShape;
     private int lineRed;
     private int lineGreen;
     private int lineBlue;
     private int lineStyle;
     private int lineWidth;
     private short routerType;
     private List<BendPoint> bendPoints = new ArrayList<BendPoint>(0);
     private List<LinkTerminus> linkTermini = new ArrayList<LinkTerminus>(0);

    public Link() {
    }

	
    public Link(Canvas canvas, int link_index, ObjectType objectType, String name, String description, String detailedDescription, String url, Shape targetShape, Shape sourceShape, int lineRed, int lineGreen, int lineBlue, int lineStyle, int lineWidth, short routerType) {
        this.canvas = canvas;
        this.link_index = link_index;
        this.objectType = objectType;
        this.name = name;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.url = url;
        this.targetShape = targetShape;
        this.sourceShape = sourceShape;
        this.lineRed = lineRed;
        this.lineGreen = lineGreen;
        this.lineBlue = lineBlue;
        this.lineStyle = lineStyle;
        this.lineWidth = lineWidth;
        this.routerType = routerType;
    }
    public Link(Canvas canvas, int link_index, ObjectType objectType, String name, String description, String detailedDescription, String url, Map<String,Property> properties, Shape targetShape, Shape sourceShape, int lineRed, int lineGreen, int lineBlue, int lineStyle, int lineWidth, short routerType, List<BendPoint> bendPoints, List<LinkTerminus> linkTermini) {
       this.canvas = canvas;
       this.link_index = link_index;
       this.objectType = objectType;
       this.name = name;
       this.description = description;
       this.detailedDescription = detailedDescription;
       this.url = url;
       this.properties = properties;
       this.targetShape = targetShape;
       this.sourceShape = sourceShape;
       this.lineRed = lineRed;
       this.lineGreen = lineGreen;
       this.lineBlue = lineBlue;
       this.lineStyle = lineStyle;
       this.lineWidth = lineWidth;
       this.routerType = routerType;
       this.bendPoints = bendPoints;
       this.linkTermini = linkTermini;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Canvas getCanvas() {
        return this.canvas;
    }
    
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    public int getLink_index() {
        return this.link_index;
    }
    
    public void setLink_index(int link_index) {
        this.link_index = link_index;
    }
    public ObjectType getObjectType() {
        return this.objectType;
    }
    
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
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
    public String getDetailedDescription() {
        return this.detailedDescription;
    }
    
    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public Map<String,Property> getProperties() {
        return this.properties;
    }
    
    public void setProperties(Map<String,Property> properties) {
        this.properties = properties;
    }
    public Shape getTargetShape() {
        return this.targetShape;
    }
    
    public void setTargetShape(Shape targetShape) {
        this.targetShape = targetShape;
    }
    public Shape getSourceShape() {
        return this.sourceShape;
    }
    
    public void setSourceShape(Shape sourceShape) {
        this.sourceShape = sourceShape;
    }
    public int getLineRed() {
        return this.lineRed;
    }
    
    public void setLineRed(int lineRed) {
        this.lineRed = lineRed;
    }
    public int getLineGreen() {
        return this.lineGreen;
    }
    
    public void setLineGreen(int lineGreen) {
        this.lineGreen = lineGreen;
    }
    public int getLineBlue() {
        return this.lineBlue;
    }
    
    public void setLineBlue(int lineBlue) {
        this.lineBlue = lineBlue;
    }
    public int getLineStyle() {
        return this.lineStyle;
    }
    
    public void setLineStyle(int lineStyle) {
        this.lineStyle = lineStyle;
    }
    public int getLineWidth() {
        return this.lineWidth;
    }
    
    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }
    public short getRouterType() {
        return this.routerType;
    }
    
    public void setRouterType(short routerType) {
        this.routerType = routerType;
    }
    public List<BendPoint> getBendPoints() {
        return this.bendPoints;
    }
    
    public void setBendPoints(List<BendPoint> bendPoints) {
        this.bendPoints = bendPoints;
    }
    public List<LinkTerminus> getLinkTermini() {
        return this.linkTermini;
    }
    
    public void setLinkTermini(List<LinkTerminus> linkTermini) {
        this.linkTermini = linkTermini;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Link) ) return false;
		 Link castOther = ( Link ) other; 
         
		 return ( (this.getCanvas()==castOther.getCanvas()) || ( this.getCanvas()!=null && castOther.getCanvas()!=null && this.getCanvas().equals(castOther.getCanvas()) ) )
 && (this.getLink_index()==castOther.getLink_index())
 && ( (this.getObjectType()==castOther.getObjectType()) || ( this.getObjectType()!=null && castOther.getObjectType()!=null && this.getObjectType().equals(castOther.getObjectType()) ) )
 && ( (this.getProperties()==castOther.getProperties()) || ( this.getProperties()!=null && castOther.getProperties()!=null && this.getProperties().equals(castOther.getProperties()) ) )
 && ( (this.getTargetShape()==castOther.getTargetShape()) || ( this.getTargetShape()!=null && castOther.getTargetShape()!=null && this.getTargetShape().equals(castOther.getTargetShape()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getCanvas() == null ? 0 : this.getCanvas().hashCode() );
         result = 37 * result + this.getLink_index();
         result = 37 * result + ( getObjectType() == null ? 0 : this.getObjectType().hashCode() );
         
         
         
         
         result = 37 * result + ( getProperties() == null ? 0 : this.getProperties().hashCode() );
         result = 37 * result + ( getTargetShape() == null ? 0 : this.getTargetShape().hashCode() );
         
         
         
         
         
         
         
         
         
         return result;
   }   

  // The following is extra code specified in the hbm.xml files

	private static final long serialVersionUID = 8124494867402957446L;
		
  // end of extra code specified in the hbm.xml files

}


