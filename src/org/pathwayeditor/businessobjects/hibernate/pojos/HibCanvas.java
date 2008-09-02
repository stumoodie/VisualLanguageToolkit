package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.contextadapter.IContext;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.repository.IMap;

/**
 * Canvas generated by hbm2java
 */
public class HibCanvas implements ICanvas , Serializable {
	private static final long serialVersionUID = 807306412269098190L;

	private Long id;
	private HibMapDiagram mapDiagram;
	private HibContext context;
	private int gridX;
	private int gridY;
	private boolean gridEnabled;
	private boolean snapToGridEnabled;
	private int backgroundRed;
	private int backgroundGreen;
	private int backgroundBlue;
	private int canvasWidth;
	private int canvasHeight;
	private Set<HibShapeAttribute> hibShapeAttributes = new HashSet<HibShapeAttribute>(0);
	private Set<HibLinkAttribute> hibLinkAttributes = new HashSet<HibLinkAttribute>(0);
	private Set<HibLabelAttribute> hibLabelAttributes = new HashSet<HibLabelAttribute>(0);
	private Set<HibProperty> properties = new HashSet<HibProperty>(0);
	private Date created = null ;
	private Date modified = null ;
	private int mapINode;
	private HibRepository repository ;
	private HibCompoundGraph graph ;

	/**
	 * Default constructor used by hibernate.
	 */
	public HibCanvas() {
	}

	/**
	 * Constructor to be used by BO facade. This creates a canvas that is owned by a map.
	 * @param mapDiagram
	 * @param context
	 */
	public HibCanvas(HibMapDiagram mapDiagram, HibContext context) {
		this.mapDiagram = mapDiagram;
		this.context = context;
	}
	
	public HibCanvas ( HibMapDiagram mapDiagram , HibContext context, int gridX , int gridY , boolean gridEnabled , 
						boolean snapToGridEnabled , int backgroundRed , int backgroundGreen , int backgroundBlue ,
						int canvasWidth , int canvasHeight) 
	{
		this.mapDiagram = mapDiagram;
		this.context = context;
		this.gridX = gridX;
		this.gridY = gridY;
		this.gridEnabled = gridEnabled;
		this.snapToGridEnabled = snapToGridEnabled;
		this.backgroundRed = backgroundRed;
		this.backgroundGreen = backgroundGreen;
		this.backgroundBlue = backgroundBlue;
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.created = new Date();
		this.modified= new Date();
	}

	public HibCanvas(HibMapDiagram newMap, HibCanvas other) {
		this.mapDiagram = newMap;
		this.context = other.context;
		this.gridX = other.gridX;
		this.gridY = other.gridY;
		this.gridEnabled = other.gridEnabled;
		this.snapToGridEnabled = other.snapToGridEnabled;
		this.backgroundRed = other.backgroundRed;
		this.backgroundGreen = other.backgroundGreen;
		this.backgroundBlue = other.backgroundBlue;
		this.canvasWidth = other.canvasWidth;
		this.canvasHeight = other.canvasHeight;
		this.repository = other.repository ;
		
		for(HibShapeAttribute hibShapeAttribute : other.hibShapeAttributes){
			this.hibShapeAttributes.add(hibShapeAttribute);
		}
		for(HibLinkAttribute hibLinkAttribute : other.hibLinkAttributes){
			this.hibLinkAttributes.add(hibLinkAttribute);
		}
		for(HibLabelAttribute hibLabelAttribute : other.hibLabelAttributes){
			this.hibLabelAttributes.add(hibLabelAttribute);
		}
		this.created = new Date();
		this.modified= new Date();
		
		
	}

	public HibCanvas(HibCanvas other) {
		this(null, other);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HibMapDiagram getMapDiagram() {
		return this.mapDiagram;
	}

	public void setMapDiagram(HibMapDiagram hibMapDiagram) {
		this.mapDiagram = hibMapDiagram;
	}

	public IContext getContext() {
		return this.context;
	}

	public void setContext(HibContext hibContext) {
		this.context = hibContext;
	}
	
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getGridX() {
		return this.gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return this.gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

	public boolean isGridEnabled() {
		return this.gridEnabled;
	}

	public void setGridEnabled(boolean gridEnabled) {
		this.gridEnabled = gridEnabled;
	}

	public boolean isSnapToGridEnabled() {
		return this.snapToGridEnabled;
	}

	public void setSnapToGridEnabled(boolean snapToGridEnabled) {
		this.snapToGridEnabled = snapToGridEnabled;
	}

	public int getBackgroundRed() {
		return this.backgroundRed;
	}

	public void setBackgroundRed(int backgroundRed) {
		this.backgroundRed = backgroundRed;
	}

	public int getBackgroundGreen() {
		return this.backgroundGreen;
	}

	public void setBackgroundGreen(int backgroundGreen) {
		this.backgroundGreen = backgroundGreen;
	}

	public int getBackgroundBlue() {
		return this.backgroundBlue;
	}

	public void setBackgroundBlue(int backgroundBlue) {
		this.backgroundBlue = backgroundBlue;
	}

	public int getCanvasWidth() {
		return this.canvasWidth;
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public int getCanvasHeight() {
		return this.canvasHeight;
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	public Set<HibShapeAttribute> getHibShapeAttributes() {
		return this.hibShapeAttributes;
	}

	void setHibShapeAttributes(Set<HibShapeAttribute> shapes) {
		this.hibShapeAttributes = shapes;
	}

	public Set<HibLinkAttribute> getHibLinkAttributes() {
		return this.hibLinkAttributes;
	}

	void setHibLinkAttributes(Set<HibLinkAttribute> hibLinkAttributes) {
		this.hibLinkAttributes = hibLinkAttributes;
	}

	public Set<HibLabelAttribute> getHibLabelAttributes() {
		return this.hibLabelAttributes;
	}

	void setHibLabelAttributes(Set<HibLabelAttribute> hibLabelAttributes) {
		this.hibLabelAttributes = hibLabelAttributes;
	}
	
	public Set<HibProperty> getProperties() {
		return this.properties;
	}

	public void setProperties(Set<HibProperty> hibProperties) {
		this.properties = hibProperties;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HibCanvas))
			return false;
		HibCanvas castOther = (HibCanvas) other;

		return ((this.getMapDiagram() == castOther.getMapDiagram()) || (this
				.getMapDiagram() != null
				&& castOther.getMapDiagram() != null && this.getMapDiagram()
				.equals(castOther.getMapDiagram())));
	}

	public int hashCode() {
		int result = 17;
		result = 37
				* result
				+ (getMapDiagram() == null ? 0 : this.getMapDiagram()
						.hashCode());

		return result;
	}


	public void addShape ( HibShapeAttribute toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("shape cannot be null");
		HibCanvas oldCanvas = toAdd.getCanvas() ;
		if (oldCanvas != null) {
			oldCanvas.getHibShapeAttributes().remove(toAdd);
		}
		this.hibShapeAttributes.add(toAdd);
		toAdd.setCanvas(this);
	}
	
	void removeShape(HibShapeAttribute toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("shape cannot be null");
		if (toRemove.getCanvas() != this)
			throw new IllegalArgumentException(
					"shape must belong to this canvas");

		this.hibShapeAttributes.remove(toRemove);
		toRemove.setCanvas(null);
	}
	
	public void addLabel ( HibLabelAttribute toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("label cannot be null");
		HibCanvas oldCanvas = toAdd.getCanvas() ;
		if (oldCanvas != null) {
			oldCanvas.getHibShapeAttributes().remove(toAdd);
		}
		this.hibLabelAttributes.add(toAdd);
		toAdd.setCanvas(this);
	}
	
	void removeLabel(HibLabelAttribute toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("label cannot be null");
		if (toRemove.getCanvas() != this)
			throw new IllegalArgumentException(
					"label must belong to this canvas");

		this.hibLabelAttributes.remove(toRemove);
		toRemove.setCanvas(null);
	}
	
	public void addLink ( HibLinkAttribute toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("Link cannot be null");
		HibCanvas oldCanvas = (HibCanvas) toAdd.getCanvas() ;
		if (oldCanvas != null) {
			oldCanvas.getHibShapeAttributes().remove(toAdd);
		}
		this.hibLinkAttributes.add(toAdd);
		toAdd.setCanvas(this);
	}
	
	void removeLink(HibLinkAttribute toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("Link cannot be null");
		if (toRemove.getCanvas() != this)
			throw new IllegalArgumentException(
					"Link must belong to this canvas");

		this.hibLinkAttributes.remove(toRemove);
		toRemove.setCanvas(null);
	}
	
	public void addProperty ( HibProperty toAdd ) 
	{
		if (toAdd == null)
			throw new IllegalArgumentException("property cannot be null");
		HibCanvas oldCanvas = toAdd.getCanvas() ;
		if (oldCanvas != null) {
			oldCanvas.getHibShapeAttributes().remove(toAdd);
		}
		this.properties.add(toAdd);
		toAdd.setCanvas(this);
	}
	
	void removeProperty(HibProperty toRemove) {
		if (toRemove == null)
			throw new IllegalArgumentException("property cannot be null");
		if (toRemove.getCanvas() != this)
			throw new IllegalArgumentException(
					"property must belong to this canvas");

		this.properties.remove(toRemove);
		toRemove.setCanvas(null);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getBackgroundColour()
	 */
	public RGB getBackgroundColour() {
		return new RGB ( this.backgroundRed , this.backgroundGreen , this.backgroundBlue);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMap()
	 */
	public IMap getMap() {
		return this.mapDiagram ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#gridHeight()
	 */
	public int gridHeight() {
		return this.gridY;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#gridWidth()
	 */
	public int gridWidth() {
		return this.gridX;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#isSnapToGridOn()
	 */
	public boolean isSnapToGridOn() {
		return this.gridEnabled;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setBackgroundColour(RGB backgroundColour) {
		if ( backgroundColour == null)
			throw new IllegalArgumentException ( "BackgroundColor cannot be null") ;
			this.backgroundBlue = backgroundColour.getBlue() ;
			this.backgroundRed = backgroundColour.getRed() ;
			this.backgroundGreen = backgroundColour.getGreen() ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setGrid(int, int)
	 */
	public void setGrid(int width, int height) {
		if ( width < 0 || height < 0)
			throw new IllegalArgumentException ( "Width or height cannot be less than 0." ) ;
			
		this.gridX = width ;
		this.gridY = height ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setSnapToGrid(boolean)
	 */
	public void setSnapToGrid(boolean snapToGridStatus) {
		this.gridEnabled = snapToGridStatus ;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMapSize()
	 */
	public Size getMapSize() {
		return new Size ( this.canvasHeight , this.canvasWidth );
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setMapSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setMapSize(Size size) {
		if (size == null)
			throw new IllegalArgumentException () ;
		
		this.canvasHeight = size.getHeight() ;
		this.canvasWidth = size.getWidth() ; 
		
	}

	public int getMapINode() {
		return this.mapINode;
	}

	public void setMapINode(int mapINode) {
		this.mapINode = mapINode;
	}

	public HibRepository getRepository() {
		return this.repository;
	}

	public void setRepository(HibRepository repository) {
		this.repository = repository;
	}

	public HibCompoundGraph getGraph() {
		return this.graph;
	}

	public void setGraph(HibCompoundGraph graph) {
		this.graph = graph;
	}
	
	
	
	

}
