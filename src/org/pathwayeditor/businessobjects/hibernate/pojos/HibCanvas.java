/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasPropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableCanvasPropertyChangeItem;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.graph.IterationCaster;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.geometry.Dimension;

import uk.ed.inf.graph.util.IFilterCriteria;
import uk.ed.inf.graph.util.IndexCounter;
import uk.ed.inf.graph.util.impl.FilteredIterator;

public class HibCanvas implements ICanvas, Serializable {
	private static final long serialVersionUID = 807306412269098190L;

	private static final int DEFAULT_CANVAS_WIDTH = 200;
	private static final int DEFAULT_CANVAS_HEIGHT = 300;
	private static final int DEFAULT_BGD_GREEN = 255;
	private static final int DEFAULT_BGD_BLUE = 255;
	private static final int DEFAULT_BGD_RED = 255;
	private static final int DEFAULT_GRID_HEIGHT = 20;
	private static final int DEFAULT_GRID_WIDTH = 20;
	private static final boolean DEFAULT_GRIB_ENABLED_VALUE = false;
	private static final boolean DEFAULT_SNAP_TO_GRID_VALUE = false;
	private static final int MODEL_EMPTY_COUNT = 1; // has just root node when "empty" 
	private static final int MIN_NAME_LEN = 1;
	private static final Pattern NAME_REGEXP = Pattern.compile("(\\w.*\\w)|(\\w)");
	
	private Long id;
	private HibNotation hibNotation;
	private INotationSubsystem notation;
	private Dimension gridSize = new Dimension(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
	private boolean gridEnabled = DEFAULT_GRIB_ENABLED_VALUE;
	private boolean snapToGridEnabled = DEFAULT_SNAP_TO_GRID_VALUE;
	private RGB backgroundColour = new RGB(DEFAULT_BGD_RED, DEFAULT_BGD_GREEN, DEFAULT_BGD_BLUE);
	private Dimension canvasSize = new Dimension(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
	private String canvasName;
	private int mapINode;
	private String repository;
	private HibModel model ;
	private IndexCounter creationSerialCounter = new IndexCounter();
	private Set<HibCanvasAttribute> canvasAttributes = new HashSet<HibCanvasAttribute>(0);
	private transient final ListenableCanvasPropertyChangeItem listenablePropertyChangeItem  = new ListenableCanvasPropertyChangeItem(this);
	private final IFilterCriteria<HibCanvasAttribute> linkAttribCriteria;
	private final IFilterCriteria<HibCanvasAttribute> labelAttribCriteria;
	private final IFilterCriteria<HibCanvasAttribute> shapeAttribCriteria;
	private final IFilterCriteria<HibCanvasAttribute> linkTermCriteria;
	private final IFilterCriteria<HibCanvasAttribute> attribCriteria;

	/**
	 * Default constructor for use ONLY by hibernate.
	 * @deprecated Use one of the other constructors to construct this class in application code.
	 */
	HibCanvas() {
		this.linkAttribCriteria = new IFilterCriteria<HibCanvasAttribute>(){
			public boolean matched(HibCanvasAttribute testObj) {
				return testObj instanceof ILinkAttribute && !isDrawingElementRemoved(testObj);
			}
		};
		this.labelAttribCriteria = new IFilterCriteria<HibCanvasAttribute>(){
			public boolean matched(HibCanvasAttribute testObj) {
				return testObj instanceof ILabelAttribute && !isDrawingElementRemoved(testObj);
			}
		};
		this.shapeAttribCriteria = new IFilterCriteria<HibCanvasAttribute>(){
			public boolean matched(HibCanvasAttribute testObj) {
				return testObj instanceof IShapeAttribute && !isDrawingElementRemoved(testObj);
			}
		};
		this.linkTermCriteria = new IFilterCriteria<HibCanvasAttribute>(){
			public boolean matched(HibCanvasAttribute testObj) {
				return testObj instanceof ILinkTerminus && !isDrawingElementRemoved(testObj);
			}
		};
		this.attribCriteria = new IFilterCriteria<HibCanvasAttribute>(){
			public boolean matched(HibCanvasAttribute testObj) {
				return !isDrawingElementRemoved(testObj);
			}
		};
	}
	
	private static boolean isDrawingElementRemoved(HibCanvasAttribute attrib){
		boolean retVal = false;
		if(attrib instanceof IDrawingNodeAttribute){
			IDrawingNodeAttribute node = (IDrawingNodeAttribute)attrib;
			retVal = node.getCurrentDrawingElement().isRemoved();
		}
		else if(attrib instanceof ILinkAttribute){
			ILinkAttribute link = (ILinkAttribute)attrib;
			retVal = link.getCurrentDrawingElement().isRemoved();
		}
		return retVal;
	}

	public HibCanvas(String repoName, int iNode, IHibNotationFactory hibNotationFactory, INotationSubsystem notationSubsystem,
			String canvasName) {
		this();
		if(repoName == null || hibNotationFactory == null || notationSubsystem == null) throw new IllegalArgumentException("One or more parameters is null");
		
		if(!checkValidName(canvasName)) throw new IllegalArgumentException("Name invalid: " + canvasName);
		
		this.repository = repoName;
		this.mapINode = iNode;
		this.notation = notationSubsystem;
		this.hibNotation = hibNotationFactory.getNotation();
		IRootObjectType rootObjectType = notationSubsystem.getSyntaxService().getRootObjectType();
		this.model = new HibModel(this, rootObjectType, hibNotationFactory);
		this.canvasName = canvasName;
	}
	
	public HibCanvas(String newRepoName, int newINode, HibCanvas other) {
		this();
		this.repository = newRepoName;
		this.mapINode = newINode;
		this.hibNotation = other.hibNotation;
		this.gridSize = other.getGridSize();
		this.gridEnabled = other.gridEnabled;
		this.canvasName = other.canvasName;
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
	
	double getGridX() {
		return this.gridSize.getWidth();
	}

	void setGridX(double gridX) {
		this.gridSize = this.gridSize.newWidth(gridX);
	}

	double getGridY() {
		return this.gridSize.getHeight();
	}

	void setGridY(double gridY) {
		this.gridSize = this.gridSize.newHeight(gridY);
	}

	public Dimension getGridSize() {
		return this.gridSize;
	}

	public void setGridSize(Dimension newGridSize){
		Dimension oldGridSize = this.getGridSize();
		this.gridSize = newGridSize;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.GRID_SIZE, oldGridSize, newGridSize);
	}
	
	public boolean isGridEnabled() {
		return this.gridEnabled;
	}

	public void setGridEnabled(boolean gridEnabled) {
		boolean oldGridEnabled = this.gridEnabled;
		this.gridEnabled = gridEnabled;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.GRID_SHOWN, oldGridEnabled, gridEnabled);
	}

//	public boolean isSnapToGridEnabled() {
//		return this.snapToGridEnabled;
//	}
//
//	public void setSnapToGridEnabled(boolean snapToGridEnabled) {
//		boolean oldSnapToGridEnabled = this.snapToGridEnabled;
//		this.snapToGridEnabled = snapToGridEnabled;
//		this.listenerablePropertyChangeItem.notifyProperyChange(PropertyChange.SNAP_TO_GRID_ENABLED, oldSnapToGridEnabled, this.snapToGridEnabled);
//	}

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

	public double getCanvasWidth() {
		return this.canvasSize.getWidth();
	}

	public void setCanvasWidth(double canvasWidth) {
		this.canvasSize = this.canvasSize.newWidth(canvasWidth);
	}

	public double getCanvasHeight() {
		return this.canvasSize.getHeight();
	}

	public void setCanvasHeight(double canvasHeight) {
		this.canvasSize = this.canvasSize.newHeight(canvasHeight);
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getBackgroundColour()
	 */
	public RGB getBackgroundColour() {
		return this.backgroundColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#isSnapToGridOn()
	 */
	public boolean isSnapToGridOn() {
		return this.snapToGridEnabled;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setBackgroundColour(RGB backgroundColour) {
		if ( backgroundColour == null)
			throw new IllegalArgumentException ( "BackgroundColor cannot be null") ;

		RGB oldBackgroundColour = this.backgroundColour;
		this.backgroundColour = backgroundColour;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.BACKGROUND_COLOUR, oldBackgroundColour, this.backgroundColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setSnapToGrid(boolean)
	 */
	public void setSnapToGrid(boolean snapToGridStatus) {
		boolean oldValue = this.snapToGridEnabled;
		this.snapToGridEnabled = snapToGridStatus ;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.SNAP_TO_GRID_ENABLED, oldValue, this.snapToGridEnabled);
	}
	
	boolean getSnapToGrid(){
		return this.snapToGridEnabled;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMapSize()
	 */
	public Dimension getCanvasSize() {
		return this.canvasSize;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setMapSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setCanvasSize(Dimension size) {
		if (size == null)
			throw new IllegalArgumentException () ;

		Dimension oldCanvasSize = this.canvasSize;
		this.canvasSize = size;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.CANVAS_SIZE, oldCanvasSize, this.canvasSize);
	}

	public int getMapINode() {
		return this.mapINode;
	}

	public void setMapINode(int mapINode) {
		this.mapINode = mapINode;
	}

	public String getRepositoryName() {
		return this.repository;
	}

	public void setRepositoryName(String repository) {
		this.repository = repository;
	}

	public HibModel getGraph() {
		return this.model;
	}

	public void setGraph(HibModel graph) {
		this.model = graph;
	}
	
	void setLastCreationSerial(int lastIndexValue){
		this.creationSerialCounter = new IndexCounter(lastIndexValue);
	}
	
	int getLastCreationSerial(){
		return this.creationSerialCounter.getLastIndex();
	}
	
	public void setCreationSerialCounter(IndexCounter newCounter){
		this.creationSerialCounter = newCounter;
	}

	public IndexCounter getCreationSerialCounter(){
		return this.creationSerialCounter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#createCopy(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public void copyHere(ICanvas canvas) {
		if(this.canCopyHere(canvas) == false) throw new IllegalArgumentException("the parameter canvas cannot be copied here");
		
		IModel otherModel = canvas.getModel();
		Iterator<IShapeNode> topShapeNodeIter = otherModel.getRootNode().getSubModel().shapeNodeIterator();
		ISelectionFactory selectionFact = otherModel.newSelectionFactory();
		while(topShapeNodeIter.hasNext()){
			selectionFact.addDrawingNode(topShapeNodeIter.next());
		}
		IDrawingElementSelection selection = selectionFact.createGeneralSelection();
		this.getModel().getRootNode().getSubModel().copyHere(selection);
		// now copy additional info
		ICanvas thisCanvas = this; // doing this so we can be use to use interface methods which will have 
									// listeners associated with them.
		thisCanvas.setBackgroundColour(canvas.getBackgroundColour());
		thisCanvas.setCanvasSize(canvas.getCanvasSize());
		thisCanvas.setGridSize(canvas.getGridSize());
		thisCanvas.setGridEnabled(canvas.isGridEnabled());
		thisCanvas.setSnapToGrid(canvas.isSnapToGridOn());
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

	public Set<HibCanvasAttribute> getCanvasAttributes() {
		return this.canvasAttributes;
	}

	public void setCanvasAttributes(Set<HibCanvasAttribute> shapeAttributes) {
		this.canvasAttributes = shapeAttributes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.mapINode;
		result = prime * result
				+ ((this.repository == null) ? 0 : this.repository.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HibCanvas))
			return false;
		HibCanvas other = (HibCanvas) obj;
		if (this.mapINode != other.getMapINode())
			return false;
		if (this.repository == null) {
			if (other.getRepositoryName() != null)
				return false;
		} else if (!this.repository.equals(other.getRepositoryName()))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(ICanvasPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#listenerIterator()
	 */
	public Iterator<ICanvasPropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(ICanvasPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(repoName=");
		builder.append(this.getRepositoryName());
		builder.append(", iNode=");
		builder.append(this.getMapINode());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsLabelAttribute(int)
	 */
	public boolean containsLabelAttribute(int attributeSerial) {
		return findAttribute(this.canvasAttributes, attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsLinkAttribute(int)
	 */
	public boolean containsLinkAttribute(int attributeSerial) {
		return findAttribute(this.canvasAttributes, attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsShapeAttribute(int)
	 */
	public boolean containsShapeAttribute(int attributeSerial) {
		return findAttribute(this.canvasAttributes, attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#findAttribute(int)
	 */
	public ICanvasAttribute findAttribute(int attributeSerial) {
		ICanvasAttribute retVal = findAttribute(this.canvasAttributes, attributeSerial);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getLabelAttribute(int)
	 */
	public ILabelAttribute getLabelAttribute(int attributeSerial) {
		ILabelAttribute retVal = (ILabelAttribute)findAttribute(this.canvasAttributes, attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getLinkAttribute(int)
	 */
	public ILinkAttribute getLinkAttribute(int attributeSerial) {
		ILinkAttribute retVal = (ILinkAttribute)findAttribute(this.canvasAttributes, attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getShapeAttribute(int)
	 */
	public IShapeAttribute getShapeAttribute(int attributeSerial) {
		IShapeAttribute retVal = (IShapeAttribute)findAttribute(this.canvasAttributes, attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	private static <T extends ICanvasAttribute> T findAttribute(Set<T> searchSet, int creationSerial) {
		T retVal = null;
		for(T attribute : searchSet) {
			if(attribute.getCreationSerial() == creationSerial) {
				retVal = attribute;
			}
		}
		return retVal;  
	}

	public boolean isEmpty(){
		return this.model.numDrawingElements() == MODEL_EMPTY_COUNT;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public boolean canCopyHere(ICanvas canvas) {
		boolean retVal = false;
		if(canvas != null){
			retVal = !this.equals(canvas) && this.getNotationSubsystem().equals(canvas.getNotationSubsystem())
				&& this.isEmpty();
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getINode()
	 */
	public int getINode() {
		return this.mapINode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getName()
	 */
	public String getName() {
		return this.canvasName;
	}
	
	public boolean isValidName(String name){
		return checkValidName(name);
	}
	
	public static boolean checkValidName(String name){
		boolean retVal = false;
//		if(name!=null&&name.length()==1){
//			final Matcher matcher = ONE_CHAR_NAME_REGEXP.matcher(name);
//			retVal = matcher.matches();
//		}
//		else
		if(name != null && name.length() >= MIN_NAME_LEN){
			// string not null and not empty
			final Matcher matcher = NAME_REGEXP.matcher(name);
			retVal = matcher.matches();
		}
		return retVal;
	}
	
	public void setName(String name){
		if(!isValidName(name)) throw new IllegalArgumentException("Invalid name: " + name);
		
		this.canvasName = name;
	}

	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#numCanvasAttributes()
	 */
	public int numCanvasAttributes() {
		return this.canvasAttributes.size();
	}

	private <T extends ICanvasAttribute> Iterator<T> createAttribIter(IFilterCriteria<HibCanvasAttribute> criteria){
		FilteredIterator<HibCanvasAttribute> filteredIter = new FilteredIterator<HibCanvasAttribute>(this.canvasAttributes.iterator(), criteria);
		return new IterationCaster<T, HibCanvasAttribute>(filteredIter);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#canvasIterator()
	 */
	public Iterator<ICanvasAttribute> canvasAttributeIterator() {
		return createAttribIter(this.attribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#linkAttributeIterator()
	 */
	public Iterator<ILinkAttribute> linkAttributeIterator() {
		return createAttribIter(linkAttribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#linkTerminusIterator()
	 */
	public Iterator<ILinkTerminus> linkTerminusIterator() {
		return createAttribIter(this.linkTermCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#shapeAttributeIterator()
	 */
	public Iterator<IShapeAttribute> shapeAttributeIterator() {
		return createAttribIter(this.shapeAttribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#labelAttributeIterator()
	 */
	public Iterator<ILabelAttribute> labelAttributeIterator() {
		return this.createAttribIter(labelAttribCriteria);
	}
}
