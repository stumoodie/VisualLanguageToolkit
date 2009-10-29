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
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

public class HibLabelAttribute extends HibCanvasAttribute implements Serializable, ILabelAttribute {
	private final Logger logger = Logger.getLogger(this.getClass()); 
	private static final long serialVersionUID = -2354270083525870259L;

	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final int DEFAULT_HEIGHT = 1;
	private static final int DEFAULT_WIDTH = 1;

	private Point position = new Point(DEFAULT_X, DEFAULT_Y);
	private Dimension size = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private transient HibProperty visualisableProperty;
	private RGB background;
	private RGB foreground;
	private boolean noBorder;
	private boolean noFill;
	private double lineWidth;
	private LineStyle lineStyle = LineStyle.SOLID;
	private HibLabelNode labelNode;
//	private transient INodeObjectType objectType;
//	private transient IConvexHull convexHull = null;
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem(this);

	/**
	 * Default constructor that should only be used by hibernate.
	 * @deprecated should not be used by hibernate code, use one of the other constructors. 
	 */
	HibLabelAttribute() {
		size = new Dimension (0 , 0) ;
		position = Point.ORIGIN;
		background = new RGB ( 0 , 0 ,0 ) ;
		foreground = RGB.BLACK;
	}

	public HibLabelAttribute(HibCanvas hibCanvas, int creationSerial, HibProperty property,	ILabelAttributeDefaults labelDefaults) {
		super(hibCanvas, creationSerial);
		this.visualisableProperty = property;
		property.setDisplayedLabel(this);
//		this.objectType = new LabelObjectType(hibCanvas.getNotationSubsystem().getSyntaxService());
		populateDefaults(labelDefaults);
	}

	public HibLabelAttribute(HibCanvas hibCanvas, int creationSerial, ILabelAttribute otherAttribute, HibProperty copiedProperty) {
		super(hibCanvas, creationSerial);
		this.visualisableProperty = copiedProperty;
		copiedProperty.setDisplayedLabel(this);
		this.position = otherAttribute.getLocation();
		this.size = otherAttribute.getSize();
		this.background = otherAttribute.getBackgroundColor();
		this.foreground = otherAttribute.getForegroundColor();
//		this.objectType = otherAttribute.getObjectType();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		this.noBorder = otherAttribute.hasNoBorder();
		this.noFill = otherAttribute.hasNoFill();
	}

	private void populateDefaults(ILabelAttributeDefaults labelDefaults) {
		this.size = new Dimension(0, 0);
		this.background = labelDefaults.getFillColour();
		this.foreground = labelDefaults.getLineColour();
		this.lineStyle = labelDefaults.getLineStyle();
		this.lineWidth = labelDefaults.getLineWidth();
		this.noBorder = labelDefaults.hasNoBorder();
		this.noFill = labelDefaults.hasNoFill();
	}
	

	public Point getPosition() {
		return this.position;
	}

	void setCurrentDrawingElement(HibLabelNode node) {
		this.labelNode = node;
	}

	public void setXPosition(double XPosition) {
		this.position = this.position.newX(XPosition);
	}

	public double getXPosition() {
		return this.position.getX();
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

	public HibProperty getProperty() {
		return this.visualisableProperty;
	}

	void setProperty(HibProperty visualisableProperty) {
		this.visualisableProperty = visualisableProperty;
	}

	public int getBackgroundRed() {
		return this.background.getRed();
	}

	public void setBackgroundRed(int backgroundRed) {
		this.background = this.background.newRed(backgroundRed);
	}

	public int getBackgroundGreen() {
		return this.background.getGreen();
	}

	public void setBackgroundGreen(int backgroundGreen) {
		this.background = this.background.newGreen(backgroundGreen);
	}

	public int getBackgroundBlue() {
		return this.background.getBlue();
	}

	public void setBackgroundBlue(int backgroundBlue) {
		this.background = this.background.newBlue(backgroundBlue);
	}

	public int getForegroundRed() {
		return this.foreground.getRed();
	}

	public void setForegroundRed(int red) {
		this.foreground = this.foreground.newRed(red);
	}

	public int getForegroundGreen() {
		return this.foreground.getGreen();
	}

	public void setForegroundGreen(int green) {
		this.foreground = this.foreground.newGreen(green);
	}

	public int getForegroundBlue() {
		return this.foreground.getBlue();
	}

	public void setForegroundBlue(int blue) {
		this.foreground = this.foreground.newBlue(blue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getLocation()
	 */
	public Point getLocation() {
		return this.position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getSize
	 * ()
	 */
	public Dimension getSize() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getBackgroundColor()
	 */
	public RGB getBackgroundColor() {
		return this.background;
	}

	public void setBackgroundColor(RGB color) {
		if (color == null)
			throw new IllegalArgumentException("Color cannot be null.");

		if(this.background != color){
			RGB oldColour = this.background;
			this.background = color;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.FILL_COLOUR, oldColour, this.background);
		}
	}
	
//	public void setObjectType ( INodeObjectType nodeObjectType)
//	{
//		this.objectType = nodeObjectType ;
//	}
//
	public void setLocation(Point location) {
		if (location == null)
			throw new IllegalArgumentException("location cannot be null.");

		if(!this.position.equals(location)){
			Point oldValue = this.position;
			this.position = location;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LOCATION, oldValue, this.position);
		}
	}

	public void setSize(Dimension size) {
		if (size == null)
			throw new IllegalArgumentException("size cannot be null.");

		if(!this.size.equals(size)){
			Dimension oldValue = this.size;
			this.size = size;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.SIZE, oldValue, this.size);
		}
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#
//	 * getObjectType()
//	 */
//	public INodeObjectType getObjectType() {
//		return this.objectType;
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#hasProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean hasProperty(IPropertyDefinition property) {
		return this.visualisableProperty.getDefinition().equals(property);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void addChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	public Iterator<ICanvasAttributePropertyChangeListener> listenerIterator() {
		return this.listenablePropertyChangeItem.listenerIterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	public void removeChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getCurrentDrawingElement()
	 */
	public ILabelNode getCurrentDrawingElement() {
		return this.labelNode;
	}
	
	void setCurrentLabelNode(HibLabelNode labelNode){
		this.labelNode = labelNode;
	}
	
	HibLabelNode getCurrentLabelNode(){
		return this.labelNode;
	}

	public boolean isValid() {
//		boolean objectTypeTest = this.getObjectType() != null;
		boolean labelNodeTest = this.getCurrentDrawingElement() != null
			&& this.getCurrentDrawingElement().getAttribute().equals(this);
		// valid to have no visualisable label set if the label node has been removed
		boolean propertySetTest = this.getProperty() != null;
		if(!labelNodeTest || this.getProperty() == null) {
			logger.error("attribute=" + this + ", labelnode set and points to this attribute=" + labelNodeTest
					+ ", propertySet=" + propertySetTest);
		}
//		if(!objectTypeTest || !labelNodeTest || this.getVisualisableProperty() == null) {
//			logger.error("attribute=" + this + " objectType set=" + objectTypeTest
//					+ ", labelnode set and points to this attribute=" + labelNodeTest
//					+ ", propertySet=" + propertySetTest);
//		}
		return propertySetTest && labelNodeTest && propertySetTest;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#getHibObjectType()
	 */
	// no persisted object type so return null
	@Override
	public HibObjectType getHibObjectType() {
		return null;
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
//		this.convexHull = this.getConvexHull().changeEnvelope(newBounds);
		this.setLocation(newBounds.getOrigin());
		this.setSize(newBounds.getDimension());
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getConvexHull()
//	 */
//	public IConvexHull getConvexHull() {
//		if(convexHull == null){
//			this.convexHull = new RectangleHull(getBounds());
//		}
//		return this.convexHull;
//	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setConvexHull(org.pathwayeditor.figure.geometry.IConvexHull)
//	 */
//	public void setConvexHull(IConvexHull newHull) {
//		this.convexHull = newHull;
//	}

	public boolean areListenersEnabled() {
		return this.listenablePropertyChangeItem.areListenersEnabled();
	}

	public void setListenersEnabled(boolean enabled) {
		this.listenablePropertyChangeItem.setListenersEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getForegroundColor()
	 */
	public RGB getForegroundColor() {
		return this.foreground;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setForegroundColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	public void setForegroundColor(RGB color) {
		if(this.foreground != color){
			RGB oldValue = this.foreground;
			this.foreground = color;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldValue, this.foreground);
		}
	}

	public void setNoBorder(boolean noBorder){
		if(this.noBorder != noBorder){
			boolean oldValue = this.noBorder;
			this.noBorder = noBorder;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.NO_BORDER, oldValue, this.noBorder);
		}
	}
	
	public boolean hasNoBorder(){
		return this.noBorder;
	}
	
	boolean getNoBorder(){
		return this.noBorder;
	}

	public void setNoFill(boolean noFill){
		if(this.noFill != noFill){
			boolean oldValue = this.noFill;
			this.noFill = noFill;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.NO_FILL, oldValue, this.noFill);
		}
	}
	
	public boolean hasNoFill(){
		return this.noFill;
	}
	
	boolean getNoFill(){
		return this.noFill;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLineWidth()
	 */
	public double getLineWidth() {
		return this.lineWidth;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	public void setLineStyle(LineStyle lineStyle) {
		if(!this.lineStyle.equals(lineStyle)){
			LineStyle oldValue = this.lineStyle;
			this.lineStyle = lineStyle;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_STYLE, oldValue, this.lineStyle);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setLineWidth(int)
	 */
	public void setLineWidth(double lineWidth) {
		if(this.lineWidth != lineWidth){
			double oldValue = this.lineWidth;
			this.lineWidth = lineWidth;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_WIDTH, oldValue, this.lineWidth);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getMinimumSize()
	 */
	public Dimension getMinimumSize() {
		return this.visualisableProperty.getDefinition().getLabelDefaults().getMinimumSize();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvasAttribute#injectObjectType(org.pathwayeditor.businessobjects.hibernate.pojos.IObjectTypeInjector)
	 */
	@Override
	public void injectObjectType(IObjectTypeInjector injector) throws InconsistentNotationDefinitionException {
		// do nothing
	}
}
