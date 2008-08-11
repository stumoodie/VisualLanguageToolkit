package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.IChildModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * Shape generated by hbm2java
 */
public class HibShapeAttribute implements IShapeAttribute,  Serializable {
	private static final long serialVersionUID = -8557015458835029042L;

	private HibCanvas canvas;
	private Long id;
	private int creationSerial;
	private int XPosition;
	private int YPosition;
	private int width;
	private int height;
	private HibObjectType hibObjectType;
	private String name;
	private String description;
	private String detailedDescription;
	private String url;
	private int fillRed;
	private int fillGreen;
	private int fillBlue;
	private int lineRed;
	private int lineGreen;
	private int lineBlue;
	private LineStyle hibLineStyle;
	private int lineWidth;
	private int padding;
	private short shapeType;
	private HibShapeNode shapeNode;
	private Map<String, HibProperty> hibProperties = new HashMap<String, HibProperty>(0);

	public HibShapeAttribute() {
	}

	public HibShapeAttribute(HibCanvas hibCanvas, int creationSerial){
		this.canvas = hibCanvas;
		this.creationSerial = creationSerial;
	}
	
	public HibShapeAttribute(HibShapeAttribute other) {
		this.XPosition = other.XPosition;
		this.YPosition = other.YPosition;
		this.width = other.width;
		this.height = other.height;
		this.hibObjectType = other.hibObjectType;
		this.name = other.name;
		this.description = other.description;
		this.detailedDescription = other.detailedDescription;
		this.url = other.url;
		this.fillRed = other.fillRed;
		this.fillGreen = other.fillGreen;
		this.fillBlue = other.fillBlue;
		this.lineRed = other.lineRed;
		this.lineGreen = other.lineGreen;
		this.lineBlue = other.lineBlue;
		this.hibLineStyle = other.hibLineStyle;
		this.lineWidth = other.lineWidth;
		this.padding = other.padding;

		for (String propertyName : other.hibProperties.keySet()) {
			HibProperty otherProp = other.hibProperties.get(propertyName);
			this.hibProperties.put(propertyName, otherProp.copy(getCanvas()));
		}
	}

	public Long getId() {
		return this.id;
	}

	void setId(Long id) {
		this.id = id;
	}

	void setCreationSerial(int creationSerial){
		this.creationSerial = creationSerial;
	}
	
	public int getCreationSerial(){
		return this.creationSerial;
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

	public int getWidth() {
		return this.width;
	}
	
	public void setCanvas(HibCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public IShapeObjectType getObjectType() {
		// TODO
		return null;
	}
	
	public HibObjectType getHibObjectType () {
		return this.hibObjectType ;
	}

	void setHibObjectType(HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType;
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

	public int getFillRed() {
		return this.fillRed;
	}

	public void setFillRed(int fillRed) {
		this.fillRed = fillRed;
	}

	public int getFillGreen() {
		return this.fillGreen;
	}

	public void setFillGreen(int fillGreen) {
		this.fillGreen = fillGreen;
	}

	public int getFillBlue() {
		return this.fillBlue;
	}

	public void setFillBlue(int fillBlue) {
		this.fillBlue = fillBlue;
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

	public LineStyle getLineStyle() {
		// FIXME
		return LineStyle.DASH_DOT;
	}
	
	public LineStyle getHibLineStyle () {
		return this.hibLineStyle ;
	}
	
	public void setHibLineStyle(LineStyle lineStyle) {
		this.hibLineStyle = lineStyle;
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		if ( lineWidth < 0 )
			throw new IllegalArgumentException () ;
		
		this.lineWidth = lineWidth;
	}

	public int getPadding() {
		return this.padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public short getShapeType() {
		return this.shapeType;
	}

	public void setShapeType(short shapeType) {
		this.shapeType = shapeType;
	}

	public Map<String, HibProperty> getProperties() {
		return this.hibProperties;
	}

	public void setProperties(Map<String, HibProperty> hibProperties) {
		this.hibProperties = hibProperties;
	}

	public HibCanvas getCanvas() {
		return this.canvas;
	}
	
	

	public void changeCanvas(HibCanvas newCanvas) {
		HibCanvas oldCanvas = this.canvas ;
		this.canvas = newCanvas;
		if (oldCanvas != null) {
			oldCanvas.getShapes().remove(this);
		}
		if (this.canvas != null) {
			this.canvas.getShapes().add(this);
		}
		
	}

	public void addProperty ( String name , HibProperty toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibShapeAttribute oldShape = toAdd.getShape() ;
		if (oldShape != null) {
			oldShape.getProperties().remove(toAdd);
		}
		this.hibProperties.put(name ,toAdd);
		toAdd.setShape(this);
	}
	
	void removeProperty(String toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibProperty propertyToRemove = hibProperties.get(toRemove) ;
		if  (propertyToRemove == null)
			throw new IllegalStateException("property cannot be null");
		this.hibProperties.remove(toRemove) ;
		propertyToRemove.setShape(null);
	}
	
	public boolean equals (Object other) 
	{
        if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HibShapeAttribute) ) return false;
		 HibShapeAttribute castOther = ( HibShapeAttribute ) other; 
		 
		
		 if ( this.canvas ==null || castOther.getCanvas() == null )
				return false ;
		 if ( this.canvas != castOther.getCanvas() )
				return false ;
		 if ( this.creationSerial != castOther.getCreationSerial() )
			 return false ;
		 if ( this.XPosition != castOther.getXPosition())
			 return false ;
		 if ( this.YPosition != castOther.getYPosition())
			 return false ;
		 if ( this.width != castOther.getWidth())
			 return false ;
		 if ( this.height != castOther.getHeight())
			 return false ;
		 if ( this.hibObjectType == null || castOther.getHibObjectType() == null )
			 return false ;
		 if ( !this.hibObjectType.equals(castOther.hibObjectType))
			 return false ;
		 if ( !this.name.equals(castOther.getName()))
			 return false ;
		 if ( !this.description.equals(castOther.getDescription()))
			 return false ;
		 if ( !this.detailedDescription.equals(castOther.getDetailedDescription()))
			 return false ;
		 if ( !this.url.equals(castOther.getUrl()))
			 return false ;
		 if ( this.fillRed != castOther.getFillRed())
			 return false ;
		 if ( this.fillBlue != castOther.getFillBlue())
			 return false ;
		 if ( this.fillGreen != castOther.getFillGreen())
			 return false ;
		 if ( this.lineBlue != castOther.getLineBlue())
			 return false ;
		 if ( this.lineRed != castOther.getLineRed())
			 return false ;
		 if ( this.lineGreen != castOther.getLineGreen())
			 return false ;
//		 if ( this.lineStyle != castOther.getLineStyle())
//			 return false ;
		 if ( this.lineWidth != castOther.getLineWidth())
			 return true ;
		 if ( this.padding != castOther.padding)
			 return true ;
		 if (this.shapeType != castOther.getShapeType())
			 return false ;
		 
		 return true ;
	}
	
	public int hashCode() {
        int result = 17;
    	
        result = 37 * result + ( getCanvas() == null ? 0 : this.getCanvas().hashCode() );
        result = 37 * result + this.getCreationSerial();
        result = 37 * result + this.XPosition ;
        result = 37 * result + this.YPosition ;
        result = 37 * result + this.width ;
        result = 37 * result + this.height ;
        result = 37 * result + ( getObjectType() == null ? 0 : this.hibObjectType.hashCode() );
        result = 37 * result + ( getName() == null ? 0 : this.name.hashCode() );
        result = 37 * result + ( getDescription() == null ? 0 : this.description.hashCode() );
        result = 37 * result + ( getDetailedDescription() == null ? 0 : this.detailedDescription.hashCode() );
        result = 37 * result + ( getUrl() == null ? 0 : this.url.hashCode() );
        result = 37 * result + this.fillBlue ;
        result = 37 * result + this.fillRed ;
        result = 37 * result + this.fillGreen ;
        result = 37 * result + this.lineGreen ;
        result = 37 * result + this.lineBlue ;
        result = 37 * result + this.lineRed ;
        result = 37 * result + this.hibLineStyle.getCode() ;
        result = 37 * result + this.lineWidth ;
        result = 37 * result + this.padding ;
        result = 37 * result + this.shapeType ;
        
        return result;
  }   
	
//	TODO tests from here on
	
	public HibShapeNode getShapeNode() {
		return this.shapeNode;
	}
	
	void setShapeNode(HibShapeNode newNode){
		this.shapeNode = newNode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getFillColour()
	 */
	public RGB getFillColour() {
		return new RGB ( this.fillRed , this.fillGreen , this.fillBlue );
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineColour()
	 */
	public RGB getLineColour() {
		return new RGB ( this.lineRed , this.lineGreen , this.lineBlue );
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLocation()
	 */
	public Location getLocation() {
		return new Location( this.XPosition , this.YPosition);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getPrimitiveShape()
	 */
	public IPrimitiveShape getPrimitiveShape() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getProperty(java.lang.String)
	 */
	public IAnnotationProperty getProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getShapeModel()
	 */
	public IChildModel getShapeModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getSize()
	 */
	public Size getSize() {
		return new Size ( this.width , this.height);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#hasProperty(java.lang.String)
	 */
	public boolean hasProperty(String propertyName) {
		return this.hibProperties.containsKey(propertyName);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setFillColour(RGB fillColour) {
		if ( fillColour == null )
			throw new IllegalArgumentException () ;
		
		this.fillBlue = fillColour.getBlue() ;
		this.fillRed = fillColour.getRed() ;
		this.fillGreen = fillColour.getGreen() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColour(RGB lineColour) {
		if ( lineColour == null )
			throw new IllegalArgumentException () ;
		
		this.lineBlue = lineColour.getBlue() ;
		this.lineRed = lineColour.getRed() ;
		this.lineGreen = lineColour.getGreen() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	public void setLineStyle(LineStyle lineStyle) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Location newLocation) {
		if ( newLocation == null )
			throw new IllegalArgumentException () ;
		
		this.XPosition = newLocation.getX() ;
		this.YPosition = newLocation.getY() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setPrimitiveShape(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape)
	 */
	public void setPrimitiveShape(IPrimitiveShape primitiveShape) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Size size) {
		if ( size == null )
			throw new IllegalArgumentException () ;
		
		this.width = size.getWidth() ;
		this.height = size.getHeight() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getFirstObject()
	 */
	public IZOrderedObject getFirstObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getLastObject()
	 */
	public IZOrderedObject getLastObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getNextObject()
	 */
	public IZOrderedObject getNextObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getPreviousObject()
	 */
	public IZOrderedObject getPreviousObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition)
	 */
	public IAnnotationProperty getProperty(IPropertyDefinition propDefn) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#propertyIterator()
	 */
	public Set<IAnnotationProperty> propertyIterator() {
		// TODO Auto-generated method stub
		return null;
	}
}