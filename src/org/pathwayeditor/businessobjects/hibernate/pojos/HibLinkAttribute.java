package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.hibernate.helpers.PropertyBuilder;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

import uk.ed.inf.graph.util.IndexCounter;

public class HibLinkAttribute implements ILinkAttribute , Serializable {
	private static final long serialVersionUID = 8124494867402957446L;
	private static final int VALID_NUM_LINK_TERMINI = 2;
	private static final RGB DEFAULT_LINE_COLOUR = new RGB(255, 255, 255);
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final int DEFAULT_LINE_WIDTH = 1;
	private static final ConnectionRouter DEFAULT_ROUTER_TYPE = ConnectionRouter.FAN;
	private static final int MIN_LINE_WIDTH = 1;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private Long id;
	private HibCanvas hibCanvas;
	private int creationSerial;
	private HibObjectType hibObjectType;
	private ILinkObjectType objectType;
	private String name = "";
	private String description = "";
	private String detailedDescription = "";
	private String url = "";
	private RGB lineColour = DEFAULT_LINE_COLOUR;
	private LineStyle lineStyle = DEFAULT_LINE_STYLE;
	private int lineWidth = DEFAULT_LINE_WIDTH;
	private ConnectionRouter routerType = DEFAULT_ROUTER_TYPE;
	private List<HibBendPoint> hibBendPoints = new ArrayList<HibBendPoint>();
	private HibLinkEdge edge ;
	private Map<String, HibProperty> hibLinkProperties = new HashMap<String, HibProperty>();
	private IPropertyBuilder propertyBuilder;
	private List<HibLinkTerminus> linkTermini = new ArrayList<HibLinkTerminus>();
	private IndexCounter bendPointCounter = new IndexCounter();
	private final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem();

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
		// the ordering here is important as the code expects the SOURCe to be first. Not satisfactory
		// FIXME: the following is very fragile. The LinkTermType is used later on to lookup the correct terminus.
		// This is a bug waiting to happen and needs fixing.
		this.linkTermini.add(new HibLinkTerminus(this, LinkTermType.SOURCE, objectType.getSourceTerminusDefinition()));
		this.linkTermini.add(new HibLinkTerminus(this, LinkTermType.TARGET, objectType.getTargetTerminusDefinition()));
		addDefaults(objectType.getDefaultLinkAttributes());
		this.getCanvas().getLinkAttributes().add(this) ;
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
		this.lineColour = otherAttribute.getLineColor();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		this.name = otherAttribute.getName();
		this.description = otherAttribute.getDescription();
		this.detailedDescription = otherAttribute.getDetailedDescription();
		this.url = otherAttribute.getUrl();
		this.routerType = otherAttribute.getRouterType();
		for(HibProperty prop : otherAttribute.hibLinkProperties.values()){
			HibProperty copiedProp = (HibProperty)prop.getDefinition().copyProperty(this.propertyBuilder, prop);
			this.hibLinkProperties.put(copiedProp.getDefinition().getName(), copiedProp);
		}
		for(HibLinkTerminus linkTerm : otherAttribute.getLinkTermini()){
			HibLinkTerminus copiedTerminus = new HibLinkTerminus(this, linkTerm);
			this.linkTermini.add(copiedTerminus);
		}
		this.getCanvas().getLinkAttributes().add(this) ;
		this.bendPointCounter = new IndexCounter(otherAttribute.bendPointCounter.getLastIndex());
		for(HibBendPoint bendPoint : otherAttribute.hibBendPoints) {
			this.hibBendPoints.add(new HibBendPoint(this, bendPoint));
		}
	}

	/**
	 * @param linkAttributeDefaults
	 */
	private void addDefaults(ILinkAttributeDefaults linkAttributeDefaults) {
		this.setLineColor(linkAttributeDefaults.getLineColour());
		this.setLineStyle(linkAttributeDefaults.getLineStyle());
		this.setLineWidth(linkAttributeDefaults.getLineWidth());
		this.setName(linkAttributeDefaults.getName());
		this.setDescription(linkAttributeDefaults.getDescription());
		this.setDetailedDescription(linkAttributeDefaults.getDetailedDescription());
		this.setUrl(linkAttributeDefaults.getUrl());
		this.setRouterType(linkAttributeDefaults.getRouter());
		Iterator<IPropertyDefinition> propIter = linkAttributeDefaults.propertyDefinitionIterator();
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

	public void injectLinkObjectType(ILinkObjectType objectType) throws InconsistentNotationDefinitionException {
		this.objectType = objectType;
		injectPropertyDefinitions();
		this.getSourceTerminus().injectLinkTerminusDefaults(objectType.getSourceTerminusDefinition());
		this.getTargetTerminus().injectLinkTerminusDefaults(objectType.getTargetTerminusDefinition());
	}

	private void injectPropertyDefinitions() throws InconsistentNotationDefinitionException {
		Iterator<IPropertyDefinition> it = this.objectType.getDefaultLinkAttributes().propertyDefinitionIterator();
		int propCntr = 0;
		while (it.hasNext()) {
			IPropertyDefinition definition = it.next();
			HibProperty property = this.hibLinkProperties.get(definition.getName());
			if(property==null) {
					throw new InconsistentNotationDefinitionException("The object type has property definitions which have no matching property in this Shape Attribute");
			}
			property.setPropertyDefinition(definition);
			propCntr++;
		}
		if(propCntr != this.hibLinkProperties.size()) {
			throw new InconsistentNotationDefinitionException("Object inconsistent with object type. Cannot find definitions for some properties");
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if ( name == null)
			throw new IllegalArgumentException ( "Name cannot be null." ) ;
		
		String oldValue = this.name;
		this.name = name;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.NAME, oldValue, this.name);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		if ( description == null )
			throw new IllegalArgumentException ( "Description cannot be null." ) ;
		
		String oldValue = this.name;
		this.description = description;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.DESCRIPTION, oldValue, this.description);
	}

	public String getDetailedDescription() {
		return this.detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		if ( detailedDescription == null )
			throw new IllegalArgumentException ( "DetailedDescription cannot be null." ) ;
		
		String oldValue = this.detailedDescription;
		this.detailedDescription = detailedDescription;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.DETAILED_DESCRIPTION, oldValue, this.detailedDescription);
}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		if ( url == null )
			throw new IllegalArgumentException ( "URL cannot be null." ) ;
		
		String oldValue = this.url;
		this.url = url;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.URL, oldValue, this.url);
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
		
		LineStyle oldValue = this.lineStyle;
		this.lineStyle = lineStyle;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.LINE_STYLE, oldValue, this.lineStyle);
	}

	public int getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		if ( lineWidth < MIN_LINE_WIDTH)
			throw new IllegalArgumentException ( "Line width cannot be less than " + MIN_LINE_WIDTH) ;
		
		int oldValue = this.lineWidth;
		this.lineWidth = lineWidth;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.LINE_WIDTH, oldValue, this.lineWidth);
	}

	public ConnectionRouter getRouterType() {
		return this.routerType;
	}

	public void setRouterType(ConnectionRouter routerType) {
		if ( routerType == null )
			throw new IllegalArgumentException ( "Router type cannot be null." ) ;
		
		ConnectionRouter oldValue = this.routerType;
		this.routerType = routerType;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.ROUTER_TYPE, oldValue, this.routerType);
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
		boolean retVal = false;
		if ( bendPoint != null && bendPoint.getOwningLink() == this){
			retVal = this.hibBendPoints.contains((HibBendPoint) bendPoint );
		}
		return retVal;
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
		if ( !containsBendPoint(bendPoint) ) throw new IllegalArgumentException ("no bendpoint") ;

		HibBendPoint hibBendPoint = (HibBendPoint)bendPoint; 
		this.hibBendPoints.remove(hibBendPoint);
		// break link to this attribute so it will be deleted.
		hibBendPoint.setOwningLink(null);
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.BEND_POINT_REMOVED, null, null);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(org.pathwayeditor.businessobjects.typedefn.IPropertyDefinition)
	 */
	public HibProperty getProperty(IPropertyDefinition propDefn) {
		return this.hibLinkProperties.get(propDefn.getName());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getProperty(java.lang.String)
	 */
	public HibProperty getProperty(String propName) {
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
			throw new IllegalArgumentException ("Line colour cannot be null") ;

		RGB oldValue = this.lineColour;
		this.lineColour = newColor;
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.LINE_COLOUR, oldValue, this.lineStyle);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getLinkEdge()
	 */
	public ILinkEdge getCurrentDrawingElement() {
		return this.edge;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean hasProperty(IPropertyDefinition property) {
		boolean retVal = false;
		if(property != null){
			retVal = this.hibLinkProperties.containsKey(property.getName());
		}
		return retVal;
	}

	public List<HibLinkTerminus> getLinkTermini() {
		return this.linkTermini;
	}

	public void setLinkTermini(List<HibLinkTerminus> linkTermini) {
		this.linkTermini = linkTermini;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getSourceTerminus()
	 */
	public HibLinkTerminus getSourceTerminus() {
		return this.linkTermini.get(LinkTermType.SOURCE.toInt());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getTargetTerminus()
	 */
	public HibLinkTerminus getTargetTerminus() {
		return this.linkTermini.get(LinkTermType.TARGET.toInt());
	}

	
	void setLastBendPointSerial(int lastSerial) {
		this.bendPointCounter = new IndexCounter(lastSerial);
	}
	

	int getLastBendPointSerial() {
		return this.bendPointCounter.getLastIndex();
	}
	

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(canvas=");
		builder.append(this.getCanvas());
		builder.append(", serial=");
		builder.append(this.getCreationSerial());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	public Iterator<IPropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(IPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#addbendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint, int)
	 */
	public void changeBendPointPosition(IBendPoint bendPoint, int indexPos) {
		if(!this.containsBendPoint(bendPoint)) throw new IllegalArgumentException("bendPoint must be contained by this attribute");

		if(indexPos >= this.hibBendPoints.size() || indexPos < 0){
			throw new IndexOutOfBoundsException("list size= " + this.hibBendPoints.size() + ", no bendpoint at position: " + indexPos);
		}
		else{
			int oldIdx = this.hibBendPoints.indexOf(bendPoint);
			if(indexPos < this.hibBendPoints.size()-1) {
				this.hibBendPoints.remove(oldIdx);
				// insert at new position
				this.hibBendPoints.add(indexPos, (HibBendPoint)bendPoint);
			}
			else {
				this.hibBendPoints.remove(oldIdx);
				// we want to add it to the end of the list so we append it bp to the list.
				this.hibBendPoints.add((HibBendPoint)bendPoint);
			}
			this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.BEND_POINT_REINDEXED, oldIdx, indexPos);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#containsBendPoint(int)
	 */
	public boolean containsBendPoint(int index) {
		boolean retVal = index >= 0 && index < this.hibBendPoints.size();
		if(retVal){
			retVal = this.hibBendPoints.get(index) != null; 
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#getBendPoint(int)
	 */
	public IBendPoint getBendPoint(int index) {
		if(!containsBendPoint(index)) throw new IllegalArgumentException("not bendpoint at this index position");
		
		return this.hibBendPoints.get(index);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#removeBendPoint(int)
	 */
	public void removeBendPoint(int index) {
		if(!containsBendPoint(index)) throw new IllegalArgumentException("not bendpoint at this index position");

		HibBendPoint hibBendPoint = this.hibBendPoints.get(index);
		this.hibBendPoints.remove(index);
		hibBendPoint.setOwningLink(null);
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.BEND_POINT_REMOVED, null, null);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute#createNewBendPoint(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location, org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public IBendPoint createNewBendPoint(Location location, Location firstRelativeDim, Location secondRelativeDim) {
		HibBendPoint retVal = new HibBendPoint(this, this.bendPointCounter.nextIndex(), location, firstRelativeDim, secondRelativeDim);
		this.hibBendPoints.add(retVal);
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.BEND_POINT_ADDED, null, retVal);
		return retVal;
	}

	public IBendPoint createNewBendPoint(int position, Location location, Location firstRelativeDim, Location secondRelativeDim) {
		HibBendPoint retVal = new HibBendPoint(this, this.bendPointCounter.nextIndex(), location, firstRelativeDim, secondRelativeDim);
		if(position < 0 || position > this.hibBendPoints.size()) {
			throw new IndexOutOfBoundsException("list size= " + this.hibBendPoints.size() + ", no bendpoint can be added at position: " + position);
		}
		else if(position < this.hibBendPoints.size()) {
			this.hibBendPoints.add(position, retVal);
		}
		else {
			this.hibBendPoints.add(retVal);
		}
		this.listenablePropertyChangeItem.notifyProperyChange(PropertyChange.BEND_POINT_ADDED, null, retVal);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean containsProperty(IPropertyDefinition propDefn) {
		boolean retVal = false;
		if(propDefn != null) {
			retVal = this.hibLinkProperties.containsKey(propDefn.getName());
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(java.lang.String)
	 */
	public boolean containsProperty(String propName) {
		boolean retVal = false;
		if(propName != null) {
			retVal = this.hibLinkProperties.containsKey(propName);
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#numProperties()
	 */
	public int numProperties() {
		return this.hibLinkProperties.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#containsProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public boolean containsProperty(IAnnotationProperty property) {
		boolean retVal = false;
		if(property != null) {
			IAnnotationProperty foundProp = this.hibLinkProperties.get(property.getDefinition().getName());
			if(foundProp != null) {
				retVal = foundProp.equals(property);
			}
		}
		return retVal;
	}

	public boolean isValid() {
		boolean retVal = true;
		if(this.objectType != null && this.linkTermini.size() == VALID_NUM_LINK_TERMINI
				&& this.getSourceTerminus() != null && this.getTargetTerminus() != null
				// check property
				&& this.objectType.getLinkConnectionRules().isValidTarget(this.getCurrentDrawingElement().getSourceShape().getObjectType(),
								this.getCurrentDrawingElement().getTargetShape().getObjectType())){
			// now check properties
			Iterator<IPropertyDefinition> it = this.objectType.getDefaultLinkAttributes().propertyDefinitionIterator();
			int propCntr = 0;
			while (it.hasNext()) {
				IPropertyDefinition definition = it.next();
				HibProperty property = this.hibLinkProperties.get(definition.getName());
				if(property==null) {
					logger.error("Attribute invalid: attribute = " + this + ". The object type has property definitions which have no matching property in this Shape Attribute. Objecttype=" + this.objectType);
					retVal = false;
					break;
				}
				property.setPropertyDefinition(definition);
				propCntr++;
			}
			if(retVal && propCntr != this.hibLinkProperties.size()) {
				logger.error("Attribute invalid: attribute= " + this + ". Cannot find definitions for some properties stroed in attribute");
				retVal = false;
			}
			if(retVal) {
				// validate terminit
				retVal = this.getSourceTerminus().isValid() && this.getTargetTerminus().isValid();
			}
		}
		else {
			retVal = false;
		}
		return retVal;
	}
}
