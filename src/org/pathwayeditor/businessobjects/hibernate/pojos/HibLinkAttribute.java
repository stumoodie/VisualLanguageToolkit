package org.pathwayeditor.businessobjects.hibernate.pojos;

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEndDecorator;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

/**
 * Link generated by hbm2java
 */
public class HibLinkAttribute implements ILinkAttribute , Serializable {
	private static final long serialVersionUID = 8124494867402957446L;

	private Long id;
	private HibCanvas hibCanvas;
	private int creationSerial;
	private HibObjectType hibObjectType;
	private String name;
	private String description;
	private String detailedDescription;
	private String url;
	private int lineRed;
	private int lineGreen;
	private int lineBlue;
	private LineStyle lineStyle;
	private int lineWidth;
	private ConnectionRouter routerType;
	private List<HibBendPoint> hibBendPoints = new ArrayList<HibBendPoint>(0);
	private Set<HibLinkTerminus> linkTermini = new HashSet<HibLinkTerminus>(0);
	private HibLinkEdge edge ;
	private Map<String, HibProperty> hibLinkProperties = new HashMap<String, HibProperty>(0);

	public HibLinkAttribute() {
	}

	public HibLinkAttribute(HibCanvas hibCanvas, int link_index,	HibObjectType hibObjectType) {
		this.hibCanvas = hibCanvas;
		this.creationSerial = link_index;
		this.hibObjectType = hibObjectType;
	}

	public Long getId() {
		return this.id;
	}
	
	public HibLinkEdge getEdge() {
		return this.edge;
	}

	public void setEdge(HibLinkEdge edge) {
		this.edge = edge;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ICanvas getCanvas() {
		return this.hibCanvas;
	}

	public void setCanvas(HibCanvas hibCanvas) {
		this.hibCanvas = hibCanvas;
	}

	public int getCreationSerial() {
		return this.creationSerial;
	}

	public void setCreationSerial(int link_index) {
		this.creationSerial = link_index;
	}

	public ILinkObjectType getObjectType() {
		// TODO
		throw new UnsupportedOperationException () ;
	}
	
	public HibObjectType getHibObjectType () {
		return this.hibObjectType ;
	}
	
	void setHibObjectType (HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType ;
	}

	public void setObjectType(HibObjectType hibObjectType) {
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
		if ( url == null )
			throw new IllegalArgumentException ( "Url cannot be null.") ;
		this.url = url;
	}

	public Map<String, HibProperty> getLinkProperties() {
		return this.hibLinkProperties;
	}

	public void setLinkProperties(Map<String, HibProperty> hibProperties) {
		this.hibLinkProperties = hibProperties;
	}

	public int getLineRed() {
		return this.lineRed;
	}

	public void setLineRed(int lineRed) {
		this.lineRed = lineRed;
	}

	public Map<String, HibProperty> getHibLinkProperties() {
		return this.hibLinkProperties;
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

	public void setBendPoints(List<HibBendPoint> hibBendPoints) {
		this.hibBendPoints = hibBendPoints;
	}

	public Set<HibLinkTerminus> getLinkTermini() {
		return this.linkTermini;
	}

	public void setLinkTermini(Set<HibLinkTerminus> linkTermini) {
		this.linkTermini = linkTermini;
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

	public void addLinkProperty ( String name , HibProperty toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibLinkAttribute oldLink = toAdd.getHibLink() ;
		if (oldLink != null) {
			oldLink.getLinkProperties().remove(toAdd);
		}
		this.hibLinkProperties.put(name ,toAdd);
		toAdd.setHibLink(this);
	}
	
	void removeLinkProperty(String toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibProperty propertyToRemove = hibLinkProperties.get(toRemove) ;
		if  (propertyToRemove == null)
			throw new IllegalStateException("property cannot be null");
		
		this.hibLinkProperties.remove(toRemove) ;
		propertyToRemove.setHibLink(null);
	}
	
	void addBendPoint ( HibBendPoint toAdd)
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibLinkAttribute oldLink = (HibLinkAttribute) toAdd.getOwningLink() ;
		if (oldLink != null) {
			oldLink.getLinkProperties().remove(toAdd);
		}
		this.hibBendPoints.add(toAdd);
		toAdd.setOwningLink(this);		
	}
	
	void removeBendPoints ( HibBendPoint toRemove)
	{
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibBendPoint bendpointToRemove = hibBendPoints.get(hibBendPoints.indexOf(toRemove)) ;
		if  (bendpointToRemove == null)
			throw new IllegalStateException("property cannot be null");
		if (bendpointToRemove.getOwningLink() != this)
			throw new IllegalArgumentException(
					"property must belong to this canvas");	
		
		this.hibLinkProperties.remove(toRemove) ;
		bendpointToRemove.setOwningLink(null);		
	}
	
	void addLinkTermini ( HibLinkTerminus toAdd)
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibLinkAttribute oldLink = toAdd.getLink() ;
		if (oldLink != null) {
			oldLink.getLinkProperties().remove(toAdd);
		}
		this.linkTermini.add(toAdd);
		toAdd.setLink(this);		
	}
	
	void removeLinkTermini ( HibLinkTerminus toRemove)
	{
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		if (toRemove.getLink() != this)
			throw new IllegalArgumentException(
					"property must belong to this canvas");	
		
		this.linkTermini.remove(toRemove) ;
		toRemove.setLink(null);		
	}
	
	
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getLinkSourceDecoration()
	 */
	public ILinkEndDecorator getLinkSourceDecoration() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException () ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getLinkTargetDecoration()
	 */
	public ILinkEndDecorator getLinkTargetDecoration() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException () ;
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getFirstObject()
	 */
	public IZOrderedObject getFirstObject() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException () ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getLastObject()
	 */
	public IZOrderedObject getLastObject() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException () ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getNextObject()
	 */
	public IZOrderedObject getNextObject() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException () ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject#getPreviousObject()
	 */
	public IZOrderedObject getPreviousObject() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException () ;
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
	public Set<IAnnotationProperty> propertyIterator() {
		return new HashSet (this.hibLinkProperties.values() ) ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLineColor()
	 */
	public RGB getLineColor() {
		return new RGB (this.lineRed , this.lineGreen , this.lineBlue );
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#setLineColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColor(RGB newColor) {
		if ( newColor == null)
			throw new IllegalArgumentException () ;
			
		this.lineBlue = newColor.getBlue() ;
		this.lineRed = newColor.getRed() ;
		this.lineGreen = newColor.getGreen() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#addLinkProperty(java.lang.String, org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public void addLinkProperty(String name, IAnnotationProperty toAdd) {
		this.addLinkProperty(name, (HibProperty)toAdd) ;
	}
}
