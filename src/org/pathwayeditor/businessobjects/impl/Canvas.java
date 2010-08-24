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
package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingElementSelection;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkTerminus;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ISelectionFactory;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasPropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenableCanvasPropertyChangeItem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.figure.geometry.Dimension;

import uk.ac.ed.inf.graph.util.IFilterCriteria;
import uk.ac.ed.inf.graph.util.IndexCounter;
import uk.ac.ed.inf.graph.util.impl.FilteredIterator;

public class Canvas implements ICanvas {
	private static final int DEFAULT_CANVAS_WIDTH = 200;
	private static final int DEFAULT_CANVAS_HEIGHT = 300;
	private static final int DEFAULT_BGD_GREEN = 255;
	private static final int DEFAULT_BGD_BLUE = 255;
	private static final int DEFAULT_BGD_RED = 255;
	private static final int DEFAULT_GRID_HEIGHT = 20;
	private static final int DEFAULT_GRID_WIDTH = 20;
	public static final boolean DEFAULT_GRIB_ENABLED_VALUE = false;
	public static final boolean DEFAULT_SNAP_TO_GRID_VALUE = false;
	private static final int MODEL_EMPTY_COUNT = 1; // has just root node when "empty" 
	private static final int MIN_NAME_LEN = 1;
	private static final Pattern NAME_REGEXP = Pattern.compile("(\\w.*\\w)|(\\w)");
	public static final Dimension DEFAULT_GRID_SIZE = new Dimension(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
	public static final RGB DEFAULT_BACKGROUND_COLOUR = new RGB(DEFAULT_BGD_RED, DEFAULT_BGD_GREEN, DEFAULT_BGD_BLUE);
	public static final Dimension DEFAULT_CANVAS_SIZE = new Dimension(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
	
	private INotationSubsystem notation;
	private Dimension gridSize = DEFAULT_GRID_SIZE;
	private boolean gridEnabled = DEFAULT_GRIB_ENABLED_VALUE;
	private boolean snapToGridEnabled = DEFAULT_SNAP_TO_GRID_VALUE;
	private RGB backgroundColour = DEFAULT_BACKGROUND_COLOUR;
	private Dimension canvasSize = DEFAULT_CANVAS_SIZE;
	private String canvasName;
	private final int mapINode;
	private final String repository;
	private IModel model ;
	private final IndexCounter creationSerialCounter = new IndexCounter();
	private final SortedSet<ICanvasAttribute> canvasAttributes = new TreeSet<ICanvasAttribute>();
	private final ListenableCanvasPropertyChangeItem listenablePropertyChangeItem  = new ListenableCanvasPropertyChangeItem(this);
	private final IFilterCriteria<ICanvasAttribute> linkAttribCriteria;
	private final IFilterCriteria<ICanvasAttribute> labelAttribCriteria;
	private final IFilterCriteria<ICanvasAttribute> shapeAttribCriteria;
	private final IFilterCriteria<ICanvasAttribute> linkTermCriteria;
	private final IFilterCriteria<ICanvasAttribute> attribCriteria;

	public Canvas(String repoName, int iNode, INotationSubsystem notationSubsystem, String canvasName) {
		this.linkAttribCriteria = new IFilterCriteria<ICanvasAttribute>(){
			@Override
			public boolean matched(ICanvasAttribute testObj) {
				return testObj instanceof ILinkAttribute;
			}
		};
		this.labelAttribCriteria = new IFilterCriteria<ICanvasAttribute>(){
			@Override
			public boolean matched(ICanvasAttribute testObj) {
				return testObj instanceof ILabelAttribute;
			}
		};
		this.shapeAttribCriteria = new IFilterCriteria<ICanvasAttribute>(){
			@Override
			public boolean matched(ICanvasAttribute testObj) {
				return testObj instanceof IShapeAttribute;
			}
		};
		this.linkTermCriteria = new IFilterCriteria<ICanvasAttribute>(){
			@Override
			public boolean matched(ICanvasAttribute testObj) {
				return testObj instanceof ILinkTerminus;
			}
		};
		this.attribCriteria = new IFilterCriteria<ICanvasAttribute>(){
			@Override
			public boolean matched(ICanvasAttribute testObj) {
				return true;
			}
		};
		if(repoName == null || notationSubsystem == null) throw new IllegalArgumentException("One or more parameters is null");
		
		if(!checkValidName(canvasName)) throw new IllegalArgumentException("Name invalid: " + canvasName);
		
		this.repository = repoName;
		this.mapINode = iNode;
		this.notation = notationSubsystem;
		this.canvasName = canvasName;
	}
	
//	public Canvas(String newRepoName, int newINode, Canvas other) {
//		this();
//		this.repository = newRepoName;
//		this.mapINode = newINode;
//		this.gridSize = other.getGridSize();
//		this.gridEnabled = other.gridEnabled;
//		this.canvasName = other.canvasName;
//		this.snapToGridEnabled = other.snapToGridEnabled;
//		this.backgroundColour = other.getBackgroundColour();
//		this.canvasSize = other.getCanvasSize();
//		this.model = new Model(this, other.getGraph());
//		this.notation = other.notation;
//		this.creationSerialCounter = new IndexCounter();
//	}

	@Override
	public INotationSubsystem getNotationSubsystem() {
		return this.notation;
	}
	
	@Override
	public Dimension getGridSize() {
		return this.gridSize;
	}

	@Override
	public void setGridSize(Dimension newGridSize){
		Dimension oldGridSize = this.getGridSize();
		this.gridSize = newGridSize;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.GRID_SIZE, oldGridSize, newGridSize);
	}
	
	@Override
	public boolean isGridEnabled() {
		return this.gridEnabled;
	}

	@Override
	public void setGridEnabled(boolean gridEnabled) {
		boolean oldGridEnabled = this.gridEnabled;
		this.gridEnabled = gridEnabled;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.GRID_SHOWN, oldGridEnabled, gridEnabled);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getBackgroundColour()
	 */
	@Override
	public RGB getBackgroundColour() {
		return this.backgroundColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#isSnapToGridOn()
	 */
	@Override
	public boolean isSnapToGridOn() {
		return this.snapToGridEnabled;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	@Override
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
	@Override
	public void setSnapToGrid(boolean snapToGridStatus) {
		boolean oldValue = this.snapToGridEnabled;
		this.snapToGridEnabled = snapToGridStatus ;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.SNAP_TO_GRID_ENABLED, oldValue, this.snapToGridEnabled);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMapSize()
	 */
	@Override
	public Dimension getCanvasSize() {
		return this.canvasSize;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setMapSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	@Override
	public void setCanvasSize(Dimension size) {
		if (size == null)
			throw new IllegalArgumentException () ;

		Dimension oldCanvasSize = this.canvasSize;
		this.canvasSize = size;
		this.listenablePropertyChangeItem.notifyPropertyChange(CanvasPropertyChange.CANVAS_SIZE, oldCanvasSize, this.canvasSize);
	}

	@Override
	public String getRepositoryName() {
		return this.repository;
	}

	@Override
	public IndexCounter getCreationSerialCounter(){
		return this.creationSerialCounter;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#createCopy(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	@Override
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
	@Override
	public IModel getModel() {
		return this.model;
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
		if (!(obj instanceof ICanvas))
			return false;
		ICanvas other = (ICanvas) obj;
		if (this.mapINode != other.getINode())
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
	@Override
	public void addChangeListener(ICanvasPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#listenerIterator()
	 */
	@Override
	public Iterator<ICanvasPropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasPropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(repoName=");
		builder.append(this.getRepositoryName());
		builder.append(", iNode=");
		builder.append(this.getINode());
		builder.append(")");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsLabelAttribute(int)
	 */
	@Override
	public boolean containsLabelAttribute(int attributeSerial) {
		return findAttribute(this.labelAttributeIterator(), attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsLinkAttribute(int)
	 */
	@Override
	public boolean containsLinkAttribute(int attributeSerial) {
		return findAttribute(this.linkAttributeIterator(), attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#containsShapeAttribute(int)
	 */
	@Override
	public boolean containsShapeAttribute(int attributeSerial) {
		return findAttribute(this.shapeAttributeIterator(), attributeSerial) != null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#findAttribute(int)
	 */
	@Override
	public ICanvasAttribute findAttribute(int attributeSerial) {
		ICanvasAttribute retVal = findAttribute(this.canvasAttributeIterator(), attributeSerial);
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getLabelAttribute(int)
	 */
	@Override
	public ILabelAttribute getLabelAttribute(int attributeSerial) {
		ILabelAttribute retVal = (ILabelAttribute)findAttribute(this.labelAttributeIterator(), attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getLinkAttribute(int)
	 */
	@Override
	public ILinkAttribute getLinkAttribute(int attributeSerial) {
		ILinkAttribute retVal = (ILinkAttribute)findAttribute(this.linkAttributeIterator(), attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getShapeAttribute(int)
	 */
	@Override
	public IShapeAttribute getShapeAttribute(int attributeSerial) {
		IShapeAttribute retVal = (IShapeAttribute)findAttribute(this.shapeAttributeIterator(), attributeSerial);
		if(retVal == null) {
			throw new IllegalArgumentException("attributeSerial must refer to an attribute contained by this canvas");
		}
		return retVal;
	}

	private static <T extends ICanvasAttribute> T findAttribute(Iterator<T> searchIter, int creationSerial) {
		T retVal = null;
		while(searchIter.hasNext() && retVal == null){
			T attribute = searchIter.next();
			if(attribute.getCreationSerial() == creationSerial) {
				retVal = attribute;
			}
		}
		return retVal;  
	}

	@Override
	public boolean isEmpty(){
		return this.numCanvasAttributes() == MODEL_EMPTY_COUNT;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#canCopyHere(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	@Override
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
	@Override
	public int getINode() {
		return this.mapINode;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getName()
	 */
	@Override
	public String getName() {
		return this.canvasName;
	}
	
	@Override
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
	
	@Override
	public void setName(String name){
		if(!isValidName(name)) throw new IllegalArgumentException("Invalid name: " + name);
		
		this.canvasName = name;
	}

	@Override
	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	@Override
	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#numCanvasAttributes()
	 */
	@Override
	public int numCanvasAttributes() {
		int retVal = 0;
		Iterator<ICanvasAttribute> iter = this.canvasAttributeIterator(); 
		while(iter.hasNext()){
			iter.next();
			retVal++;
		}
		return retVal;
	}

	private <T extends ICanvasAttribute> Iterator<T> createAttribIter(IFilterCriteria<ICanvasAttribute> criteria){
		FilteredIterator<ICanvasAttribute> filteredIter = new FilteredIterator<ICanvasAttribute>(this.canvasAttributes.iterator(), criteria);
		return new IterationCaster<T, ICanvasAttribute>(filteredIter);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#canvasIterator()
	 */
	@Override
	public Iterator<ICanvasAttribute> canvasAttributeIterator() {
		return createAttribIter(this.attribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#linkAttributeIterator()
	 */
	@Override
	public Iterator<ILinkAttribute> linkAttributeIterator() {
		return createAttribIter(linkAttribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#linkTerminusIterator()
	 */
	@Override
	public Iterator<ILinkTerminus> linkTerminusIterator() {
		return createAttribIter(this.linkTermCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#shapeAttributeIterator()
	 */
	@Override
	public Iterator<IShapeAttribute> shapeAttributeIterator() {
		return createAttribIter(this.shapeAttribCriteria);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#labelAttributeIterator()
	 */
	@Override
	public Iterator<ILabelAttribute> labelAttributeIterator() {
		return this.createAttribIter(labelAttribCriteria);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ICanvas o) {
		int retVal = this.getRepositoryName().compareTo(o.getRepositoryName());
		if(retVal == 0){
			retVal = Integer.valueOf(this.getINode()).compareTo(Integer.valueOf(o.getINode()));
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getMapper()
	 */
	@Override
	public IBusinessObjectGraphElementMapper getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#addCanvasAttribute(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute)
	 */
	@Override
	public void addCanvasAttribute(ICanvasAttribute attribute) {
		this.canvasAttributes.add(attribute);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListenee#numChangeListeners()
	 */
	@Override
	public int numChangeListeners() {
		return this.listenablePropertyChangeItem.numChangeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#getRootAttribute()
	 */
	@Override
	public IRootAttribute getRootAttribute() {
		return (IRootAttribute)this.canvasAttributes.first();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvas#setModel(org.pathwayeditor.businessobjects.drawingprimitives.IModel)
	 */
	@Override
	public IModel setModel(IModel model) {
		return this.setModel(model);
	}
}
