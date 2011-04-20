/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.businessobjects.impl;

import java.text.Format;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

public class LabelAttribute extends CanvasAttribute implements ILabelAttribute {
	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final int DEFAULT_HEIGHT = 1;
	private static final int DEFAULT_WIDTH = 1;

	private IAnnotationProperty visualisableProperty;
	private RGB background;
	private RGB foreground;
	private boolean noBorder;
	private boolean noFill;
	private double lineWidth;
	private LineStyle lineStyle = LineStyle.SOLID;
	private final CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper = new CanvasAttributeChangeListenerHelper(this);
	private final BoundsHelper boundsDelegate = new BoundsHelper(new Envelope(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT), canvasAttributeChangeListenerHelper);
	private final ILabelObjectType objectType;
	private Format displayFormat;

	public LabelAttribute(IModel hibCanvas, int creationSerial, IAnnotationProperty property,	ILabelObjectType labelObjectType) {
		super(hibCanvas, creationSerial);
		this.visualisableProperty = property;
//		this.visualisableProperty.setLabel(this);
		this.objectType = labelObjectType;
		populateDefaults();
	}

	public LabelAttribute(IModel hibCanvas, int creationSerial, ILabelAttribute otherAttribute, IAnnotationProperty copiedProperty) {
		super(hibCanvas, creationSerial);
		this.objectType = otherAttribute.getObjectType();
		this.visualisableProperty = copiedProperty;
		this.background = otherAttribute.getBackgroundColor();
		this.foreground = otherAttribute.getForegroundColor();
		this.lineStyle = otherAttribute.getLineStyle();
		this.lineWidth = otherAttribute.getLineWidth();
		this.noBorder = otherAttribute.hasNoBorder();
		this.noFill = otherAttribute.hasNoFill();
		this.displayFormat = otherAttribute.getDisplayFormat();
//		this.visualisableProperty.setLabel(this);
		this.boundsDelegate.setBounds(otherAttribute.getBounds());
	}

	private void populateDefaults() {
		ILabelAttributeDefaults labelDefaults = this.objectType.getDefaultAttributes();
		this.background = labelDefaults.getFillColour();
		this.foreground = labelDefaults.getLineColour();
		this.lineStyle = labelDefaults.getLineStyle();
		this.lineWidth = labelDefaults.getLineWidth();
		this.noBorder = labelDefaults.hasNoBorder();
		this.noFill = labelDefaults.hasNoFill();
		this.displayFormat = labelDefaults.getDisplayFormat();
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
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.FILL_COLOUR, oldColour, this.background);
		}
	}
	
//	private void setLocation(Point location) {
//		if (location == null)
//			throw new IllegalArgumentException("location cannot be null.");
//
//		if(!this.position.equals(location)){
//			Point oldValue = this.position;
//			this.position = location;
//			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.BOUNDS, oldValue, this.position);
//		}
//	}
//
//	private void setSize(Dimension size) {
//		if (size == null)
//			throw new IllegalArgumentException("size cannot be null.");
//
//		if(!this.size.equals(size)){
//			Dimension oldValue = this.size;
//			this.size = size;
//			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.SIZE, oldValue, this.size);
//		}
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	@Override
	public List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.canvasAttributeChangeListenerHelper.getChangeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	@Override
	public Envelope getBounds() {
		return this.boundsDelegate.getBounds();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	@Override
	public void setBounds(Envelope newBounds) {
		this.boundsDelegate.setBounds(newBounds);
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
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldValue, this.foreground);
		}
	}

	@Override
	public void setNoBorder(boolean noBorderEnabled){
		if(this.noBorder != noBorderEnabled){
			boolean oldValue = this.noBorder;
			this.noBorder = noBorderEnabled;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.NO_BORDER, oldValue, this.noBorder);
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
	public void setNoFill(boolean noFillEnabled){
		if(this.noFill != noFillEnabled){
			boolean oldValue = this.noFill;
			this.noFill = noFillEnabled;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.NO_FILL, oldValue, this.noFill);
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
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_STYLE, oldValue, this.lineStyle);
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
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_WIDTH, oldValue, this.lineWidth);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getMinimumSize()
	 */
	@Override
	public Dimension getMinimumSize() {
		return this.objectType.getDefaultAttributes().getMinimumSize();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)
	 */
	@Override
	public void resize(Point locationDelta, Dimension sizeDelta) {
		this.boundsDelegate.resize(locationDelta, sizeDelta);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		this.boundsDelegate.translate(delta);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#getObjectType()
	 */
	@Override
	public ILabelObjectType getObjectType() {
		return this.objectType;
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
		return new LabelAttributeCopyFactory(this.visualisableProperty, this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		return new ElementAttributeMoveFactory(this);
	}

	@Override
	public ICompoundNode getCurrentElement(){
		return (ICompoundNode)super.getCurrentElement();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getDisplayedContent()
	 */
	@Override
	public String getDisplayedContent() {
		String retVal;
		if(this.displayFormat == null){
			retVal = this.visualisableProperty.getValue().toString();
		}
		else{
			retVal = this.displayFormat.format(this.visualisableProperty.getValue());
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setDisplayFormat(java.text.Format)
	 */
	@Override
	public void setDisplayFormat(Format displayFormat) {
		this.displayFormat = displayFormat;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getDisplayFormat()
	 */
	@Override
	public Format getDisplayFormat() {
		return this.displayFormat;
	}
}
