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

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.DrawingNodeAttributeListenerHandler;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributePropertyChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ListenablePropertyChangeItem;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

public class LabelAttribute extends CanvasAttribute implements ILabelAttribute {
	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final int DEFAULT_HEIGHT = 1;
	private static final int DEFAULT_WIDTH = 1;

	private Point position = new Point(DEFAULT_X, DEFAULT_Y);
	private Dimension size = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private IAnnotationProperty visualisableProperty;
	private RGB background;
	private RGB foreground;
	private boolean noBorder;
	private boolean noFill;
	private double lineWidth;
	private LineStyle lineStyle = LineStyle.SOLID;
	private transient final ListenablePropertyChangeItem listenablePropertyChangeItem = new ListenablePropertyChangeItem(this);
	private transient final DrawingNodeAttributeListenerHandler nodeListenerHandler = new DrawingNodeAttributeListenerHandler(this);
	private IRootAttribute rootAttribute; 

	public LabelAttribute(IRootAttribute hibCanvas, int creationSerial, IAnnotationProperty property,	ILabelAttributeDefaults labelDefaults) {
		super(creationSerial);
		this.rootAttribute = hibCanvas;
		this.visualisableProperty = property;
		this.visualisableProperty.setLabel(this);
		populateDefaults(labelDefaults);
	}

	public LabelAttribute(IRootAttribute hibCanvas, int creationSerial, ILabelAttribute otherAttribute, IAnnotationProperty copiedProperty) {
		super(creationSerial);
		this.rootAttribute = hibCanvas;
		this.visualisableProperty = copiedProperty;
		this.position = otherAttribute.getBounds().getOrigin();
		this.size = otherAttribute.getBounds().getDimension();
		this.background = otherAttribute.getBackgroundColor();
		this.foreground = otherAttribute.getForegroundColor();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		this.noBorder = otherAttribute.hasNoBorder();
		this.noFill = otherAttribute.hasNoFill();
		this.visualisableProperty.setLabel(this);
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
	

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getBackgroundColor()
	 */
	@Override
	public RGB getBackgroundColor() {
		return this.background;
	}

	@Override
	public void setBackgroundColor(RGB color) {
		if (color == null)
			throw new IllegalArgumentException("Color cannot be null.");

		if(this.background != color){
			RGB oldColour = this.background;
			this.background = color;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.FILL_COLOUR, oldColour, this.background);
		}
	}
	
	private void setLocation(Point location) {
		if (location == null)
			throw new IllegalArgumentException("location cannot be null.");

		if(!this.position.equals(location)){
			Point oldValue = this.position;
			this.position = location;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LOCATION, oldValue, this.position);
		}
	}

	private void setSize(Dimension size) {
		if (size == null)
			throw new IllegalArgumentException("size cannot be null.");

		if(!this.size.equals(size)){
			Dimension oldValue = this.size;
			this.size = size;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.SIZE, oldValue, this.size);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void addChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	@Override
	public List<ICanvasAttributePropertyChangeListener> getChangeListeners() {
		return this.listenablePropertyChangeItem.getChangeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributePropertyChangeListener listener) {
		this.listenablePropertyChangeItem.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	@Override
	public Envelope getBounds() {
		return new Envelope(this.position, this.size);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	@Override
	public void setBounds(Envelope newBounds) {
		this.setLocation(newBounds.getOrigin());
		this.setSize(newBounds.getDimension());
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getForegroundColor()
	 */
	@Override
	public RGB getForegroundColor() {
		return this.foreground;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setForegroundColor(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	@Override
	public void setForegroundColor(RGB color) {
		if(this.foreground != color){
			RGB oldValue = this.foreground;
			this.foreground = color;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldValue, this.foreground);
		}
	}

	@Override
	public void setNoBorder(boolean noBorder){
		if(this.noBorder != noBorder){
			boolean oldValue = this.noBorder;
			this.noBorder = noBorder;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.NO_BORDER, oldValue, this.noBorder);
		}
	}
	
	@Override
	public boolean hasNoBorder(){
		return this.noBorder;
	}
	
	boolean getNoBorder(){
		return this.noBorder;
	}

	@Override
	public void setNoFill(boolean noFill){
		if(this.noFill != noFill){
			boolean oldValue = this.noFill;
			this.noFill = noFill;
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.NO_FILL, oldValue, this.noFill);
		}
	}
	
	@Override
	public boolean hasNoFill(){
		return this.noFill;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLineStyle()
	 */
	@Override
	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLineWidth()
	 */
	@Override
	public double getLineWidth() {
		return this.lineWidth;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	@Override
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
	@Override
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
	@Override
	public Dimension getMinimumSize() {
		return this.visualisableProperty.getDefinition().getLabelDefaults().getMinimumSize();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)
	 */
	@Override
	public void resize(Point locationDelta, Dimension sizeDelta) {
		Point origPosition = this.position;
		this.position = this.position.translate(locationDelta);
		Dimension origSize = this.size;
		this.size = this.size.resize(sizeDelta.getWidth(), sizeDelta.getHeight());
		boolean posChanged = !this.position.equals(origPosition);
		boolean sizeChange = !this.size.equals(origSize);
		
		if(posChanged || sizeChange){
			this.nodeListenerHandler.notifyNodeResize(locationDelta, sizeDelta);
		}
		if(posChanged){
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LOCATION, origPosition, this.position);
		}
		if(sizeChange){
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.SIZE, origSize, this.size);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		Point origPosition = this.position;
		this.position = this.position.translate(delta);
		if(!this.position.equals(origPosition)){
			this.listenablePropertyChangeItem.notifyPropertyChange(CanvasAttributePropertyChange.LOCATION, origPosition, this.position);
			this.nodeListenerHandler.notifyNodeTranslation(delta);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#addDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)
	 */
	@Override
	public void addDrawingNodeAttributeListener(IDrawingNodeAttributeListener listener) {
		this.nodeListenerHandler.addDrawingNodeAttributeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#getDrawingNodeAttributeListeners()
	 */
	@Override
	public List<IDrawingNodeAttributeListener> getDrawingNodeAttributeListeners() {
		return this.nodeListenerHandler.getDrawingNodeAttributeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#removeDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)
	 */
	@Override
	public void removeDrawingNodeAttributeListener(IDrawingNodeAttributeListener listener) {
		this.nodeListenerHandler.removeDrawingNodeAttributeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#getRootAttribute()
	 */
	@Override
	public IRootAttribute getRootAttribute() {
		return this.rootAttribute;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#getObjectType()
	 */
	@Override
	public IObjectType getObjectType() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor)
	 */
	@Override
	public void visit(ICanvasElementAttributeVisitor visitor) {
		visitor.visitLabel(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getProperty()
	 */
	@Override
	public IAnnotationProperty getProperty() {
		return this.visualisableProperty;
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeCopyFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeCopyFactory() {
		return new LabelAttributeCopyFactory(this.rootAttribute.getCreationSerialCounter(), this.visualisableProperty);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		return new ElementAttributeMoveFactory(this);
	}

}
