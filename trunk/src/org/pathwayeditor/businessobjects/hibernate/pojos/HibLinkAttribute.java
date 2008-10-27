package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.PropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

/**
 * Link generated by hbm2java
 */
public class HibLinkAttribute implements ILinkAttribute , Serializable {
	private static final long serialVersionUID = 8124494867402957446L;

	private RGB DEFAULT_LINE_COLOUR = new RGB(255, 255, 255);
	private Long id;
	private HibCanvas hibCanvas;
	private int creationSerial;
	private HibObjectType hibObjectType;
	private ILinkObjectType objectType;
	private String name;
	private String description;
	private String detailedDescription;
	private String url;
	private RGB lineColour = DEFAULT_LINE_COLOUR;
	private LineStyle lineStyle;
	private int lineWidth;
	private ConnectionRouter routerType;
	private List<HibBendPoint> hibBendPoints = new ArrayList<HibBendPoint>(0);
	private HibLinkTerminus sourceTerminus;
	private HibLinkTerminus targetTerminus;
	private HibLinkEdge edge ;
	private Map<String, HibProperty> hibLinkProperties = new HashMap<String, HibProperty>(0);
	private IPropertyBuilder propertyBuilder;

	/**
	 * Default constructor to be used only by hibernate.
	 * @deprecated use any of the other constructors to construct this class in application code.
	 */
	HibLinkAttribute() {
	}

	/**
	 * Constructs new instance of this class.
	 * @param hibCanvas
	 * @param linkIndex
	 * @param objectType
	 * @param hibObjectType
	 */
	public HibLinkAttribute(HibCanvas hibCanvas, int linkIndex, ILinkObjectType objectType, HibObjectType hibObjectType) {
		this.hibCanvas = hibCanvas;
		this.propertyBuilder = new PropertyBuilder(hibCanvas);
		this.creationSerial = linkIndex;
		this.objectType = objectType;
		this.hibObjectType = hibObjectType;
		this.sourceTerminus = new HibLinkTerminus(this, LinkTermType.SOURCE, objectType.getSourceTerminusDefinition());
		this.targetTerminus = new HibLinkTerminus(this, LinkTermType.TARGET, objectType.getTargetTerminusDefinition());
		addDefaults(objectType.getDefaultLinkAttributes());
	}

	/**
	 * Constructs new instance that is a copy of another one.
	 * @param hibCanvas
	 * @param linkIndex
	 * @param otherAttribute
s	 */
	public HibLinkAttribute(HibCanvas hibCanvas, int linkIndex, HibLinkAttribute otherAttribute) {
		this.hibCanvas = hibCanvas;
		this.propertyBuilder = new PropertyBuilder(hibCanvas);
		this.creationSerial = linkIndex;
		this.objectType = otherAttribute.objectType;
		this.hibObjectType = otherAttribute.hibObjectType;
		this.sourceTerminus = new HibLinkTerminus(this, otherAttribute.getSourceTerminus());
		this.targetTerminus = new HibLinkTerminus(this, otherAttribute.getTargetTerminus());
		this.lineColour = otherAttribute.getLineColor();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		this.name = otherAttribute.getName();
		this.description = otherAttribute.getDescription();
		this.detailedDescription = otherAttribute.getDetailedDescription();
		this.url = otherAttribute.getUrl();
		this.routerType = otherAttribute.getRouter();
		for(HibProperty props : otherAttribute.hibLinkProperties.values()){
			HibProperty copiedProp = (HibProperty)props.getDefinition().copyProperty(this.propertyBuilder);
			this.hibLinkProperties.put(copiedProp.getDefinition().getName(), copiedProp);
		}
	}

	/**
	 * @param linkAttributeDefaults
	 */
	private void addDefaults(ILinkAttributeDefaults linkAttributeDefaults) {
		this.lineColour = linkAttributeDefaults.getLineColour();
		this.lineStyle = linkAttributeDefaults.getLineStyle();
		this.lineWidth = linkAttributeDefaults.getLineWidth();
		this.name = linkAttributeDefaults.getName();
		this.description = linkAttributeDefaults.getDescription();
		this.detailedDescription = linkAttributeDefaults.getDetailedDescription();
		this.url = linkAttributeDefaults.getUrl();
		this.routerType = linkAttributeDefaults.getRouter();
		Iterator<IPropertyDefinition> propIter = linkAttributeDefaults.propertyIterator();
		while(propIter.hasNext()){
			IPropertyDefinition propDefn = propIter.next();
			this.hibLinkProperties.put(propDefn.getName(), (HibProperty)propDefn.createProperty(propertyBuilder));
		}
	}

	public Long getId() {
		return this.id;
	}
	
	void setLinkEdge(HibLinkEdge edge) {
		this.edge = edge;
	}
	
	public void changeLinkEdge(HibLinkEdge newEdge){
		if(this.edge != null){
			this.edge.setAttribute(null);
		}
		if(newEdge != null){
			newEdge.setAttribute(this);
		}
		this.edge = newEdge;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public HibCanvas getCanvas() {
		return this.hibCanvas;
	}

	public void setCanvas(HibCanvas hibCanvas) {
		if(hibCanvas != null){
			this.propertyBuilder = new PropertyBuilder(hibCanvas);
		}
		else{
			this.propertyBuilder = null;
		}
		this.hibCanvas = hibCanvas;
	}

	public void changeHibCanvas(HibCanvas canvas){
		if(this.hibCanvas != null){
			this.hibCanvas.getLinkAttributes().remove(this);
		}
		if(canvas != null){
			canvas.getLinkAttributes().add(this);
		}
		this.setCanvas(canvas);
	}
	
	public int getCreationSerial() {
		return this.creationSerial;
	}

	public void setCreationSerial(int link_index) {
		this.creationSerial = link_index;
	}

	public ILinkObjectType getObjectType() {
		return this.objectType;
	}
	
	public HibObjectType getHibObjectType () {
		return this.hibObjectType ;
	}
	
	void setHibObjectType (HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType ;
	}

	public void setObjectType(ILinkObjectType objectType) {
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

	public Map<String, HibProperty> getLinkProperties() {
		return this.hibLinkProperties;
	}

	public void setHibLinkProperties(Map<String, HibProperty> hibProperties) {
		this.hibLinkProperties = hibProperties;
	}

	public int getLineRed() {
		return this.lineColour.getRed();
	}

	public void setLineRed(int lineRed) {
		this.lineColour = this.lineColour.newRed(lineRed);
	}

	public Map<String, ? extends IAnnotationProperty> getHibLinkProperties() {
		return this.hibLinkProperties;
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

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		if ( lineStyle == null )
			throw new IllegalArgumentException ( "Line style cannot be null." ) ;
		this.lineStyle = lineStyle;
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public ConnectionRouter getRouterType() {
		return this.routerType;
	}

	public void setRouterType(ConnectionRouter routerType) {
		this.routerType = routerType;
	}

	public List<HibBendPoint> getBendPoints() {
		return this.hibBendPoints;
	}

	void setBendPoints(List<HibBendPoint> hibBendPoints) {
		this.hibBendPoints = hibBendPoints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.hibCanvas == null) ? 0 : this.hibCanvas.hashCode());
		result = prime * result + this.creationSerial;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final HibLinkAttribute other = (HibLinkAttribute) obj;
		if (this.hibCanvas == null) {
			if (other.hibCanvas != null)
				return false;
		} else if (!this.hibCanvas.equals(other.hibCanvas))
			return false;
		if (this.creationSerial != other.creationSerial)
			return false;
		return true;
	}

//	void addLinkProperty ( String name , HibProperty toAdd ) 
//	{
//		if (toAdd == null)
//			throw new IllegalArgumentException("property cannot be null");
//		this.hibLinkProperties.put(name ,toAdd);
//	}
//	
//	void removeLinkProperty(String toRemove) {
//		if (toRemove == null)
//			throw new IllegalArgumentException("id cannot be null");
//		HibProperty propertyToRemove = hibLinkProperties.get(toRemove) ;
//		if  (propertyToRemove == null)
//			throw new IllegalStateException("property cannot be null");
//		
//		this.hibLinkProperties.remove(toRemove) ;
//	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#addBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public void addBendPoint(IBendPoint newBendPoint) {
		if (newBendPoint == null)
			throw new IllegalArgumentException("bendPoint cannot be null");
		HibLinkAttribute oldLink = (HibLinkAttribute) newBendPoint.getOwningLink() ;
		if (oldLink != null) {
			oldLink.getBendPoints().remove(newBendPoint);
		}
		this.hibBendPoints.add((HibBendPoint) newBendPoint);
		((HibBendPoint)newBendPoint).setOwningLink(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#bendPointIterator()
	 */
	public Iterator<IBendPoint> bendPointIterator() {
		List<IBendPoint> bendPoints = new ArrayList<IBendPoint> ( this.hibBendPoints) ;
		return bendPoints.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#containsBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public boolean containsBendPoint(IBendPoint bendPoint) {
		if ( bendPoint == null )
			throw new IllegalArgumentException () ;
		if ( bendPoint.getOwningLink() != this )
			throw new IllegalArgumentException () ;
		return this.hibBendPoints.contains((HibBendPoint) bendPoint ) ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getRouter()
	 */
	public ConnectionRouter getRouter() {
		return this.routerType ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#numBendPoints()
	 */
	public int numBendPoints() {
		return hibBendPoints.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#removeBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public void removeBendPoint(IBendPoint bendPoint) {
		if ( bendPoint == null )
			throw new IllegalArgumentException () ;
		if ( bendPoint.getOwningLink() != this )
			throw new IllegalArgumentException () ;
		if ( !containsBendPoint(bendPoint))
			throw new IllegalArgumentException () ;
		
		this.hibBendPoints.remove(bendPoint);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#setRouter(org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter)
	 */
	public void setRouter(ConnectionRouter router) {
		if ( router == null)
			throw new IllegalArgumentException ( "Router cannot be null.") ;
		this.routerType = router ;
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition)
	 */
	public IAnnotationProperty getProperty(IPropertyDefinition propDefn) {
		return this.hibLinkProperties.get(propDefn.getName());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(java.lang.String)
	 */
	public IAnnotationProperty getProperty(String propName) {
		return this.hibLinkProperties.get(propName);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#propertyIterator()
	 */
	public Iterator<IAnnotationProperty> propertyIterator() {
		return new IterationCaster<IAnnotationProperty, HibProperty>(this.hibLinkProperties.values().iterator());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLineColor()
	 */
	public RGB getLineColor() {
		return this.lineColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#setLineColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColor(RGB newColor) {
		if ( newColor == null)
			throw new IllegalArgumentException () ;
			
		this.lineColour = newColor;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLinkEdge()
	 */
	public ILinkEdge getLinkEdge() {
		return this.edge;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean hasProperty(IPropertyDefinition property) {
		return this.hibLinkProperties.containsKey(property.getName());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLinkSourceDecoration()
	 */
	public HibLinkTerminus getSourceTerminus() {
		return this.sourceTerminus;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLinkTargetDecoration()
	 */
	public HibLinkTerminus getTargetTerminus() {
		return this.targetTerminus;
	}

	public void setSourceTerminus(HibLinkTerminus sourceTerminus) {
		this.sourceTerminus = sourceTerminus;
	}

	public void setTargetTerminus(HibLinkTerminus targetTerminus) {
		this.targetTerminus = targetTerminus;
	}
}
