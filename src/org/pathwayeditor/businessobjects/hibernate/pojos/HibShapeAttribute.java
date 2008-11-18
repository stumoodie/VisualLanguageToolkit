package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.PropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

public class HibShapeAttribute implements IShapeAttribute,  Serializable {
	private static final long serialVersionUID = -8557015458835029042L;

	private static final Location DEFAULT_POSITION = new Location(0,0);
	private static final Size DEFAULT_SIZE = new Size(10,10);
	private static final String DEFAULT_NAME = "";
	private static final String DEFAULT_DESCN = "";
	private static final String DEFAULT_DETAILS = "";
	private static final String DEFAULT_URL = "";
	private static final RGB DEFAULT_FILL = RGB.WHITE;
	private static final RGB DEFAULT_LINE = RGB.BLACK;
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final int DEFAULT_LINE_WIDTH = 1;
	private static final int DEFAULT_PADDING = 0;
	private static final PrimitiveShapeType DEFAULT_SHAPE_TYPE = PrimitiveShapeType.RECTANGLE;

	private HibCanvas canvas;
	private Long id;
	private int creationSerial;
	private Location position = DEFAULT_POSITION;
	private Size size = DEFAULT_SIZE;
	private HibObjectType hibObjectType;
	private IShapeObjectType shapeObjectType;
	private String name = DEFAULT_NAME;
	private String description = DEFAULT_DESCN;
	private String detailedDescription = DEFAULT_DETAILS;
	private String url = DEFAULT_URL;
	private RGB fillColour = DEFAULT_FILL;
	private RGB lineColour = DEFAULT_LINE;
	private LineStyle lineStyle = DEFAULT_LINE_STYLE;
	private int lineWidth = DEFAULT_LINE_WIDTH;
	private int padding = DEFAULT_PADDING;
	private PrimitiveShapeType shapeType = DEFAULT_SHAPE_TYPE;
//	private HibShapeNode shapeNode;
	private Map<String, HibProperty> hibProperties = new HashMap<String, HibProperty>(0);
	private IPropertyBuilder propertyBuilder;
	private final ListenablePropertyChangeItem listenablePropertyChangeItem;
	

	/**
	 * Default constructor to be used only by hibernate.
	 * @deprecated use any of the other constructors to construct this class in application code.
	 */
	HibShapeAttribute() {
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
	}

	public HibShapeAttribute(HibCanvas hibCanvas, int creationSerial, IShapeObjectType shapeObjectType, HibObjectType hibObjectType){
		this();
		this.canvas = hibCanvas;
		this.creationSerial = creationSerial;
		this.canvas.getShapeAttributes().add(this);
		this.hibObjectType = hibObjectType;
		this.shapeObjectType = shapeObjectType;
		this.propertyBuilder = new PropertyBuilder(hibCanvas);
		this.getCanvas().getShapeAttributes().add(this) ;
		this.populateDefaults(shapeObjectType.getDefaultAttributes());
		
	}
	
	public HibShapeAttribute(HibCanvas newCanvas, int newCreationSerial, HibShapeAttribute other) {
		this();
		this.canvas = newCanvas;
		this.creationSerial = newCreationSerial;
		this.canvas.getShapeAttributes().add(this);
		this.position = other.position;
		this.size = other.size;
		this.hibObjectType = other.hibObjectType;
		this.name = other.name;
		this.description = other.description;
		this.detailedDescription = other.detailedDescription;
		this.url = other.url;
		this.fillColour = other.fillColour;
		this.lineColour = other.lineColour;
		this.lineStyle = other.lineStyle;
		this.lineWidth = other.lineWidth;
		this.padding = other.padding;
		this.shapeObjectType = other.shapeObjectType;
		this.shapeType=other.shapeType;
		for (HibProperty property : other.hibProperties.values()) {
			IPropertyDefinition defn = property.getDefinition(); 
			this.hibProperties.put(defn.getName(), (HibProperty)defn.copyProperty(this.propertyBuilder, property));
		}
	}
	
	
	private void populateDefaults(IShapeAttributeDefaults shapeDefaults){
		this.description = shapeDefaults.getDescription();
		this.detailedDescription = shapeDefaults.getDetailedDescription();
		this.setFillColour(shapeDefaults.getFillColour());
		this.setSize(shapeDefaults.getSize());
		this.setLineColour(shapeDefaults.getLineColour());
		this.lineStyle = shapeDefaults.getLineStyle();
		this.lineWidth = shapeDefaults.getLineWidth();
		this.name = shapeDefaults.getName();
		this.url = shapeDefaults.getURL();
		this.shapeType = shapeDefaults.getShapeType();
		Iterator<IPropertyDefinition> propIter = shapeDefaults.propertyDefinitionIterator();
		while(propIter.hasNext()){
			IPropertyDefinition propDefn = propIter.next();
			this.hibProperties.put(propDefn.getName(), (HibProperty)propDefn.createProperty(propertyBuilder));
		}
		this.getCanvas().getShapeAttributes().add(this) ;
	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	void setCreationSerial(int creationSerial){
		this.creationSerial = creationSerial;
	}
	
	public int getCreationSerial(){
		return this.creationSerial;
	}
	
	public int getXPosition() {
		return this.position.getX();
	}

	public void setXPosition(int XPosition) {
		this.position = this.position.newX(XPosition);
	}

	public int getYPosition() {
		return this.position.getY();
	}
	
	public void setYPosition(int YPosition) {
		this.position = this.position.newY(YPosition);
	}

	public int getWidth() {
		return this.size.getWidth();
	}
	
	void setCanvas(HibCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void changeHibCanvas(HibCanvas canvas){
		if(this.canvas != null){
			this.canvas.getShapeAttributes().remove(this);
		}
		if(canvas != null){
			canvas.getShapeAttributes().add(this);
		}
		this.setCanvas(canvas);
	}
	
	public void setWidth(int width) {
		this.size = this.size.newWidth(width);
	}

	public int getHeight() {
		return this.size.getHeight();
	}

	public void setHeight(int height) {
		this.size = this.size.newHeight(height);
	}

	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}
	
	public void setShapeObjectType(IShapeObjectType shapeObjectType) {
		this.shapeObjectType = shapeObjectType;
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
		String oldName = this.name;
		this.name = name;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.NAME, oldName, this.name);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		String oldDescription = this.description;
		this.description = description;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.DESCRIPTION, oldDescription, this.description);
	}

	public String getDetailedDescription() {
		return this.detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		String oldDetailedDescription = this.detailedDescription;
		this.detailedDescription = detailedDescription;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.DETAILED_DESCRIPTION, oldDetailedDescription, this.detailedDescription);
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		String oldUrl = this.url;
		this.url = url;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.URL, oldUrl, this.url);
	}

	public int getFillRed() {
		return this.fillColour.getRed();
	}

	public void setFillRed(int fillRed) {
		this.fillColour = this.fillColour.newRed(fillRed);
	}

	public int getFillGreen() {
		return this.fillColour.getGreen();
	}

	public void setFillGreen(int fillGreen) {
		this.fillColour = this.fillColour.newGreen(fillGreen);
	}

	public int getFillBlue() {
		return this.fillColour.getBlue();
	}

	public void setFillBlue(int fillBlue) {
		this.fillColour = fillColour.newBlue(fillBlue);
	}

	public int getLineRed() {
		return this.lineColour.getRed();
	}

	public void setLineRed(int lineRed) {
		this.lineColour = this.lineColour.newRed(lineRed);
	}

	public int getLineGreen() {
		return this.lineColour.getGreen();
	}

	public void setLineGreen(int lineGreen) {
		this.lineColour = this.lineColour.newGreen(lineGreen);
	}

	public int getLineBlue() {
		return this.lineColour.getBlue();
	}

	public void setLineBlue(int lineBlue) {
		this.lineColour = this.lineColour.newBlue(lineBlue);
	}
	
	public LineStyle getLineStyle () {
		return this.lineStyle ;
	}
	
	public void setHibLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		if ( lineWidth < 0 )
			throw new IllegalArgumentException () ;

		int oldLineWidth = this.lineWidth;
		this.lineWidth = lineWidth;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.LINE_WIDTH, oldLineWidth, this.lineWidth);
	}

	public int getPadding() {
		return this.padding;
	}

	public void setPadding(int padding) {
		int oldPadding = padding;
		this.padding = padding;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.PADDING, oldPadding, this.padding);
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
	
	

//	public void changeCanvas(HibCanvas newCanvas) {
//		HibCanvas oldCanvas = this.canvas ;
//		this.canvas = newCanvas;
//		if (oldCanvas != null) {
//			oldCanvas.getHibShapeAttributes().remove(this);
//		}
//		if (this.canvas != null) {
//			this.canvas.getHibShapeAttributes().add(this);
//		}
//		
//	}

	public void addProperty ( String name , HibProperty toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		this.hibProperties.put(name ,toAdd);
	}
	
	void removeProperty(String toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibProperty propertyToRemove = hibProperties.get(toRemove) ;
		if  (propertyToRemove == null)
			throw new IllegalStateException("property cannot be null");
		this.hibProperties.remove(toRemove) ;
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
		 
		 return true ;
	}
	
	public int hashCode() {
        int result = 17;
    	
        result = 37 * result + ( getCanvas() == null ? 0 : this.getCanvas().hashCode() );
        result = 37 * result + this.getCreationSerial();
        
        return result;
  }   
	
	
//	public HibShapeNode getShapeNode() {
//		return this.shapeNode;
//	}
//	
//	void setShapeNode(HibShapeNode newNode){
//		this.shapeNode = newNode;
//	}
//	
//	public void changeShapeNode(HibShapeNode newNode){
//		if(this.shapeNode != null){
//			this.shapeNode.setAttribute(null);
//		}
//		if(newNode != null){
//			newNode.setAttribute(this);
//		}
//		this.shapeNode = newNode;
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getFillColour()
	 */
	public RGB getFillColour() {
		return this.fillColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineColour()
	 */
	public RGB getLineColour() {
		return this.lineColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLocation()
	 */
	public Location getLocation() {
		return this.position;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getPrimitiveShape()
	 */
	public PrimitiveShapeType getPrimitiveShape() {
		return this.shapeType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getProperty(java.lang.String)
	 */
	public IAnnotationProperty getProperty(String propertyName) {
		return this.hibProperties.get(propertyName) ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getSize()
	 */
	public Size getSize() {
		return this.size;
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

		RGB oldFillColour = this.fillColour;
		this.fillColour = fillColour;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.FILL_COLOUR, oldFillColour, this.fillColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColour(RGB lineColour) {
		if ( lineColour == null )
			throw new IllegalArgumentException () ;

		RGB oldLineColour = this.lineColour;
		this.lineColour = lineColour;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.LINE_COLOUR, oldLineColour, this.lineColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	public void setLineStyle(LineStyle lineStyle) {
		if ( lineStyle == null )
			throw new IllegalArgumentException ( "Line style cannot be null") ;

		LineStyle oldLineStyle = this.lineStyle;
		this.lineStyle = lineStyle ;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.LINE_STYLE, oldLineStyle, this.lineStyle);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Location newLocation) {
		if ( newLocation == null )
			throw new IllegalArgumentException () ;

		Location oldLocation = this.position;
		this.position = newLocation;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.LOCATION, oldLocation, this.position);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setPrimitiveShape(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IPrimitiveShape)
	 */
	public void setPrimitiveShape(PrimitiveShapeType primitiveShape) {
		if(primitiveShape == null) throw new IllegalArgumentException("primitive shape cannot be null");

		PrimitiveShapeType oldPrimitiveShape = this.shapeType;
		this.shapeType = primitiveShape;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.PRIMITIVE_SHAPE_TYPE, oldPrimitiveShape, this.shapeType);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Size size) {
		if ( size == null )
			throw new IllegalArgumentException () ;

		Size oldSize = this.size;
		this.size = size;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.SIZE, oldSize, this.size);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition)
	 */
	public IAnnotationProperty getProperty(IPropertyDefinition propDefn) {
		return this.hibProperties.get(propDefn.getName());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#propertyIterator()
	 */
	public Iterator<IAnnotationProperty> propertyIterator() {
		return new IterationCaster<IAnnotationProperty, HibProperty>(this.hibProperties.values().iterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean hasProperty(IPropertyDefinition property) {
		return this.hibProperties.containsKey(property.getName());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#listenerIterator()
	 */
	public Iterator<IPropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("[canvas=");
		builder.append(this.getCanvas());
		builder.append(", serial=");
		builder.append(this.getCreationSerial());
		builder.append("]");
		return builder.toString();
	}
}
