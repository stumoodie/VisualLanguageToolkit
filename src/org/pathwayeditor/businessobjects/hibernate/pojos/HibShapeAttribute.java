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

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelSubModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.PropertyChange;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.figuredefn.GraphicsInstructionList;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

public class HibShapeAttribute extends HibAnnotatedCanvasAttribute implements IShapeAttribute,  Serializable {
	private static final long serialVersionUID = -8557015458835029042L;
	private transient final Logger logger = Logger.getLogger(this.getClass());

	private static final Point DEFAULT_POSITION = Point.ORIGIN;
	private static final Dimension DEFAULT_SIZE = new Dimension(10,10);
	private static final RGB DEFAULT_FILL = RGB.WHITE;
	private static final RGB DEFAULT_LINE = RGB.BLACK;
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final int DEFAULT_LINE_WIDTH = 1;
	private static final String DEFAULT_FIGURE_DEFN = "curbounds rect";

	private transient Point position = DEFAULT_POSITION;
	private transient Dimension size = DEFAULT_SIZE;
	private HibObjectType hibObjectType;
	private transient IShapeObjectType shapeObjectType;
	private transient RGB fillColour = DEFAULT_FILL;
	private transient RGB lineColour = DEFAULT_LINE;
	private LineStyle lineStyle = DEFAULT_LINE_STYLE;
	private double lineWidth = DEFAULT_LINE_WIDTH;
	private String figureDefn = DEFAULT_FIGURE_DEFN;
	private HibShapeNode shapeNode;
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem;
	private transient IConvexHull hull = null;
	private GraphicsInstructionList graphicsInstructions;
	

	/**
	 * Default constructor to be used only by hibernate.
	 * @deprecated use any of the other constructors to construct this class in application code.
	 */
	HibShapeAttribute() {
		super();
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
	}

	public HibShapeAttribute(HibCanvas hibCanvas, int creationSerial, IShapeObjectType shapeObjectType, HibObjectType hibObjectType){
		super(hibCanvas, creationSerial, shapeObjectType.getDefaultAttributes());
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
		this.hibObjectType = hibObjectType;
		this.shapeObjectType = shapeObjectType;
		this.populateDefaults(shapeObjectType.getDefaultAttributes());
	}
	
	public HibShapeAttribute(HibCanvas newCanvas, int newCreationSerial, HibShapeAttribute other) {
		super(newCanvas, newCreationSerial, other);
		this.listenablePropertyChangeItem = new ListenablePropertyChangeItem();
		this.position = other.position;
		this.size = other.size;
		this.hibObjectType = other.hibObjectType;
		this.fillColour = other.fillColour;
		this.lineColour = other.lineColour;
		this.lineStyle = other.lineStyle;
		this.lineWidth = other.lineWidth;
		this.shapeObjectType = other.shapeObjectType;
		this.figureDefn=other.figureDefn;
	}
	
	
	private void populateDefaults(IShapeAttributeDefaults shapeDefaults){
		this.setFillColour(shapeDefaults.getFillColour());
		this.setSize(shapeDefaults.getSize());
		this.setLineColour(shapeDefaults.getLineColour());
		this.setLineStyle(shapeDefaults.getLineStyle());
		this.setLineWidth(shapeDefaults.getLineWidth());
		this.setShapeDefinition(shapeDefaults.getShapeDefinition());
	}

	public double getXPosition() {
		return this.position.getX();
	}

	public void setXPosition(double XPosition) {
		this.position = this.position.newX(XPosition);
	}

	public double getYPosition() {
		return this.position.getY();
	}
	
	public void setYPosition(double YPosition) {
		this.position = this.position.newY(YPosition);
	}

	public double getWidth() {
		return this.size.getWidth();
	}
	
	public void setWidth(double width) {
		this.size = this.size.newWidth(width);
	}

	public double getHeight() {
		return this.size.getHeight();
	}

	public void setHeight(double height) {
		this.size = this.size.newHeight(height);
	}

	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}
	
	@Override
	public void injectObjectType(IObjectType objectType) throws InconsistentNotationDefinitionException {
		this.shapeObjectType = (IShapeObjectType)objectType;
		super.injectPropertyDefinitions(shapeObjectType.getDefaultAttributes());
	}

	public HibObjectType getHibObjectType () {
		return this.hibObjectType ;
	}

	void setHibObjectType(HibObjectType hibObjectType) {
		this.hibObjectType = hibObjectType;
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
	

	public double getLineWidth() {
		return this.lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		if ( lineWidth < MIN_LINE_WIDTH )
			throw new IllegalArgumentException ("Line width cannot be less than " + MIN_LINE_WIDTH) ;

		double oldLineWidth = this.lineWidth;
		this.lineWidth = lineWidth;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LINE_WIDTH, oldLineWidth, this.lineWidth);
	}

	void setCurrentShapeNode(HibShapeNode newNode){
		this.shapeNode = newNode;
	}

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
	public Point getLocation() {
		return this.position;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getSize()
	 */
	public Dimension getSize() {
		return this.size;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setFillColour(RGB fillColour) {
		if ( fillColour == null )
			throw new IllegalArgumentException ("Fill colour cannot be null") ;

		RGB oldFillColour = this.fillColour;
		this.fillColour = fillColour;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.FILL_COLOUR, oldFillColour, this.fillColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setLineColour(RGB lineColour) {
		if ( lineColour == null )
			throw new IllegalArgumentException ("Line colour cannot be null") ;

		RGB oldLineColour = this.lineColour;
		this.lineColour = lineColour;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LINE_COLOUR, oldLineColour, this.lineColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	public void setLineStyle(LineStyle lineStyle) {
		if ( lineStyle == null )
			throw new IllegalArgumentException ( "Line style cannot be null") ;

		LineStyle oldLineStyle = this.lineStyle;
		this.lineStyle = lineStyle ;
		this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LINE_STYLE, oldLineStyle, this.lineStyle);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLocation(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location)
	 */
	public void setLocation(Point newLocation) {
		if ( newLocation == null )
			throw new IllegalArgumentException ("the new location cannot be null") ;

		if(!this.position.equals(newLocation)){
			Point oldLocation = this.position;
			this.position = newLocation;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.LOCATION, oldLocation, this.position);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setSize(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size)
	 */
	public void setSize(Dimension size) {
		if ( size == null )
			throw new IllegalArgumentException ("Size cannot be null") ;

		if(!this.size.equals(size)){
			Dimension oldSize = this.size;
			this.size = size;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.SIZE, oldSize, this.size);
		}
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getCurrentDrawingElement()
	 */
	public HibShapeNode getCurrentDrawingElement() {
		return this.shapeNode;
	}

	HibShapeNode getCurrentShapeNode(){
		return this.shapeNode;
	}
	
	public boolean isValid() {
		boolean objectTypeSet = this.getObjectType() != null;
		boolean reciprocalAttributeSet = this.getCurrentDrawingElement() != null && this.getCurrentDrawingElement().getAttribute() != null
				&& this.getCurrentDrawingElement().getAttribute().equals(this);
		boolean syntaxRulesCorrect = this.getCurrentDrawingElement() != null && this.getCurrentDrawingElement().getParent().canParent(this.getObjectType());
		boolean propertiesValid = super.arePropertiesValid(this.getObjectType().getDefaultAttributes());
		boolean retVal = false;
		if(objectTypeSet && reciprocalAttributeSet && syntaxRulesCorrect && propertiesValid){
			retVal = true;
		}
		else{
//		if (!objectTypeSet || !reciprocalAttributeSet || !syntaxRulesCorrect || !propertiesValid) {
			logger.error("Attribute invalid, may be objecttypes or incompletely formed relationship with node");
		}
//		return objectTypeSet && reciprocalAttributeSet && syntaxRulesCorrect && propertiesValid;
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject#getLabelSubModel()
	 */
	public ILabelSubModel getLabelSubModel() {
		return this.getCurrentDrawingElement().getSubModel();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	public Envelope getBounds() {
		return new Envelope(this.position, this.size);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	public void setBounds(Envelope newBounds) {
		this.setLocation(newBounds.getOrigin());
		this.setSize(newBounds.getDimension());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getConvexHull()
	 */
	public IConvexHull getConvexHull() {
		return this.hull;
	}
	
	public void setConvexHull(IConvexHull hull){
		this.hull = hull;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getFigureDefinition()
	 */
	public GraphicsInstructionList getGraphicalDefinition() {
		return this.graphicsInstructions;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#setFigureDefinition(org.pathwayeditor.figure.customfigure.GraphicsInstructionList)
	 */
	public void setGraphicalDefinition(GraphicsInstructionList instList) {
		this.graphicsInstructions = instList;
	}

	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getShapeDefinition()
	 */
	public String getShapeDefinition() {
		return this.figureDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#setShapeDefinition(java.lang.String)
	 */
	public void setShapeDefinition(String shapeDefn) {
		if(shapeDefn == null) throw new IllegalArgumentException("primitive shape cannot be null");

		if(!this.figureDefn.equals(shapeDefn)){
			String oldPrimitiveShape = this.figureDefn;
			this.figureDefn = shapeDefn;
			this.listenablePropertyChangeItem.notifyPropertyChange(PropertyChange.PRIMITIVE_SHAPE_TYPE, oldPrimitiveShape, this.figureDefn);
		}
	}

}
