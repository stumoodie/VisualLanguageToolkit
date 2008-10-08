package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

import uk.ed.inf.graph.util.IndexCounter;

/**
 * Canvas generated by hbm2java
 */
public class HibCanvas implements ICanvas , Serializable {
	private static final long serialVersionUID = 807306412269098190L;

	private static final int DEFAULT_CANVAS_WIDTH = 200;
	private static final int DEFAULT_CANVAS_HEIGHT = 300;
	private static final int DEFAULT_BGD_GREEN = 255;
	private static final int DEFAULT_BGD_BLUE = 255;
	private static final int DEFAULT_BGD_RED = 255;
	private static final int DEFAULT_GRID_HEIGHT = 20;
	private static final int DEFAULT_GRID_WIDTH = 20;

	private Long id;
	private IMap map;
	private HibNotation hibNotation;
	private INotationSubsystem notation;
	private Size gridSize = new Size(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
	private boolean gridEnabled;
	private boolean snapToGridEnabled;
	private RGB backgroundColour = new RGB(DEFAULT_BGD_RED, DEFAULT_BGD_GREEN, DEFAULT_BGD_BLUE);
	private Size canvasSize = new Size(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
	private Date created = new Date();
	private Date modified = new Date();
	private int mapINode;
	private IRepository repository ;
	private HibModel model ;
	private IndexCounter creationSerialCounter;
	private Set<HibShapeAttribute> shapeAttributes = new HashSet<HibShapeAttribute>(0);
	private Set<HibLinkAttribute> linkAttributes = new HashSet<HibLinkAttribute>(0);
	private Set<HibLabelAttribute> labelAttributes = new HashSet<HibLabelAttribute>(0);

	/**
	 * Default constructor for use ONLY by hibernate.
	 * @deprecated Use one of the other constructors to construct this class in application code.
	 */
	HibCanvas() {
	}

	public HibCanvas(IMap map, INotationSubsystem notationSubsystem, HibNotation hibNotation) {
		this.map = map;
		this.notation = notationSubsystem;
		this.hibNotation = hibNotation;
	}
	
	public HibCanvas(IMap newMap, HibCanvas other) {
		this.repository = newMap.getRepository();
		this.mapINode = newMap.getINode();
		this.map = newMap;
		this.hibNotation = other.hibNotation;
		this.gridSize = other.getGridSize();
		this.gridEnabled = other.gridEnabled;
		this.snapToGridEnabled = other.snapToGridEnabled;
		this.backgroundColour = other.getBackgroundColour();
		this.canvasSize = other.getCanvasSize();
		this.model = new HibModel(this, other.getGraph());
		this.notation = other.notation;
		this.creationSerialCounter = new IndexCounter();
	}

	public Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public IMap getMapDiagram() {
		return this.map;
	}

	public void setMapDiagram(IMap hibMapDiagram) {
		this.map = hibMapDiagram;
	}

	public INotationSubsystem getNotationSubsystem() {
		return this.notation;
	}
	
	public void setNotationSubsystem(INotationSubsystem notationSubsystem){
		this.notation = notationSubsystem;
	}

	public void setHibNotation(HibNotation hibNotation) {
		this.hibNotation = hibNotation;
	}
	
	public HibNotation getHibNotation(){
		return this.hibNotation;
	}
	
	public Date getCreated() {
		return new Date(this.created.getTime());
	}

	public void setCreated(Date created) {
		this.created = new Date(created.getTime());
	}

	public Date getModified() {
		return new Date(this.modified.getTime());
	}

	public void setModified(Date modified) {
		this.modified = new Date(modified.getTime());
	}

	int getGridX() {
		return this.gridSize.getWidth();
	}

	void setGridX(int gridX) {
		this.gridSize = this.gridSize.newWidth(gridX);
	}

	int getGridY() {
		return this.gridSize.getHeight();
	}

	void setGridY(int gridY) {
		this.gridSize = this.gridSize.newHeight(gridY);
	}

	public Size getGridSize() {
		return this.gridSize;
	}

	public void setGridSize(Size newGridSize){
		this.gridSize = newGridSize;
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
		return this.backgroundColour.getRed();
	}

	public void setBackgroundRed(int backgroundRed) {
		this.backgroundColour = this.backgroundColour.newRed(backgroundRed);
	}

	public int getBackgroundGreen() {
		return this.backgroundColour.getGreen();
	}

	public void setBackgroundGreen(int backgroundGreen) {
		this.backgroundColour = this.backgroundColour.newGreen(backgroundGreen);
	}

	public int getBackgroundBlue() {
		return this.backgroundColour.getBlue();
	}

	public void setBackgroundBlue(int backgroundBlue) {
		this.backgroundColour = this.backgroundColour.newBlue(backgroundBlue);
	}

	public int getCanvasWidth() {
		return this.canvasSize.getWidth();
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasSize = this.canvasSize.newWidth(canvasWidth);
	}

	public int getCanvasHeight() {
		return this.canvasSize.getHeight();
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasSize = this.canvasSize.newHeight(canvasHeight);
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getBackgroundColour()
	 */
	public RGB getBackgroundColour() {
		return this.backgroundColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMap()
	 */
	public IMap getOwningMap() {
		return this.getMapDiagram() ;
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

		this.backgroundColour = backgroundColour;
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
	public Size getCanvasSize() {
		return this.canvasSize;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setMapSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setCanvasSize(Size size) {
		if (size == null)
			throw new IllegalArgumentException () ;
		
		this.canvasSize = size;
	}

	public int getMapINode() {
		return this.mapINode;
	}

	public void setMapINode(int mapINode) {
		this.mapINode = mapINode;
	}

	public IRepository getRepository() {
		return this.repository;
	}

	public void setRepository(IRepository repository) {
		this.repository = repository;
	}

	public HibModel getGraph() {
		return this.model;
	}

	public void setGraph(HibModel graph) {
		this.model = graph;
	}

	
	void setLastIndexValue(int lastIndexValue){
		this.creationSerialCounter = new IndexCounter(lastIndexValue);
	}
	
	int getLastIndexValue(){
		return this.creationSerialCounter.getLastIndex();
	}
	
	public IndexCounter getAttributeSerialCounter(){
		return this.creationSerialCounter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#createCopy(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public ICanvas createCopy(IMap map) {
		return new HibCanvas((HibMap)map, this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getModel()
	 */
	public HibModel getModel() {
		return this.model;
	}
	
	void setModel(HibModel model){
		this.model = model;
	}

	public Set<HibShapeAttribute> getShapeAttributes() {
		return this.shapeAttributes;
	}

	public void setShapeAttributes(Set<HibShapeAttribute> shapeAttributes) {
		this.shapeAttributes = shapeAttributes;
	}

	public Set<HibLinkAttribute> getLinkAttributes() {
		return this.linkAttributes;
	}

	public void setLinkAttributes(Set<HibLinkAttribute> linkAttributes) {
		this.linkAttributes = linkAttributes;
	}

	public Set<HibLabelAttribute> getLabelAttributes() {
		return this.labelAttributes;
	}

	public void setLabelAttributes(Set<HibLabelAttribute> labelAttributes) {
		this.labelAttributes = labelAttributes;
	}
}
