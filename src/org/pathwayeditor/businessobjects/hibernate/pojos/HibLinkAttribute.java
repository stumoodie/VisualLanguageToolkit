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
import org.pathwayeditor.businessobjects.drawingprimitives.ILink;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEndDecoration;
import org.pathwayeditor.businessobjects.drawingprimitives.IShape;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition;

/**
 * Link generated by hbm2java
 */
public class HibLinkAttribute implements ILink , Serializable {
	private static final long serialVersionUID = 8124494867402957446L;

	private Long id;
	private HibCanvas hibCanvas;
	private int creationSerial;
	private HibObjectType hibObjectType;
	private String name;
	private String description;
	private String detailedDescription;
	private String url;
	private Map<String, HibProperty> hibProperties = new HashMap<String, HibProperty>(0);
	private HibShapeAttribute targetShape;
	private HibShapeAttribute sourceShape;
	private int lineRed;
	private int lineGreen;
	private int lineBlue;
	private int lineStyle;
	private int lineWidth;
	private short routerType;
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
		return null;
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
		this.url = url;
	}

	public Map<String, HibProperty> getProperties() {
		return this.hibProperties;
	}

	public void setProperties(Map<String, HibProperty> hibProperties) {
		this.hibProperties = hibProperties;
	}

	public IShape getTargetShape() {
		return this.targetShape;
	}

	public void setTargetShape(HibShapeAttribute targetShape) {
		this.targetShape = targetShape;
	}

	public IShape getSourceShape() {
		return this.sourceShape;
	}

	public void setSourceShape(HibShapeAttribute sourceShape) {
		this.sourceShape = sourceShape;
	}

	public int getLineRed() {
		return this.lineRed;
	}

	public void setLineRed(int lineRed) {
		this.lineRed = lineRed;
	}

	public Map<String, HibProperty> getHibLinkProperties() {
		return this.hibProperties;
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
			oldLink.getProperties().remove(toAdd);
		}
		this.hibLinkProperties.put(name ,toAdd);
		toAdd.setHibLink(this);
	}
	
	void removeLinkProperty(String toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("id cannot be null");
		HibProperty propertyToRemove = hibProperties.get(toRemove) ;
		if  (propertyToRemove == null)
			throw new IllegalStateException("property cannot be null");
		
		this.hibProperties.remove(toRemove) ;
		propertyToRemove.setHibLink(null);
	}
	
	void addBendPoint ( HibBendPoint toAdd)
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibLinkAttribute oldLink = (HibLinkAttribute) toAdd.getOwningLink() ;
		if (oldLink != null) {
			oldLink.getProperties().remove(toAdd);
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
		
		this.hibProperties.remove(toRemove) ;
		bendpointToRemove.setOwningLink(null);		
	}
	
	void addLinkTermini ( HibLinkTerminus toAdd)
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibLinkAttribute oldLink = toAdd.getLink() ;
		if (oldLink != null) {
			oldLink.getProperties().remove(toAdd);
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#canChangeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canChangeSource(IShape newShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#canChangeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public boolean canChangeTarget(IShape newShape) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeSource(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeSource(IShape newShape) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#changeTarget(org.pathwayeditor.businessobjects.drawingprimitives.IShape)
	 */
	public void changeTarget(IShape newShape) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#containsBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public boolean containsBendPoint(IBendPoint bendPoint) {
		return this.hibBendPoints.contains((HibBendPoint) bendPoint ) ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getLinkSourceDecoration()
	 */
	public ILinkEndDecoration getLinkSourceDecoration() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getLinkTargetDecoration()
	 */
	public ILinkEndDecoration getLinkTargetDecoration() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#getRouter()
	 */
	public ConnectionRouter getRouter() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#numBendPoints()
	 */
	public int numBendPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#removeBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint)
	 */
	public void removeBendPoint(IBendPoint bendPoint) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILink#setRouter(org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter)
	 */
	public void setRouter(ConnectionRouter router) {
		// TODO Auto-generated method stub
		
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(java.lang.String)
	 */
	public IAnnotationProperty getProperty(String propName) {
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
