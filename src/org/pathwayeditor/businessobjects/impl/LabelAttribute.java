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
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILabelObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.rendering.GenericFont;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

public class LabelAttribute extends CanvasAttribute implements ILabelAttribute {
//	private static final int DEFAULT_X = 0;
//	private static final int DEFAULT_Y = 0;
//	private static final int DEFAULT_HEIGHT = 1;
//	private static final int DEFAULT_WIDTH = 1;

	private final IAnnotationProperty visualisableProperty;
//	private final BoundsHelper boundsDelegate = new BoundsHelper(new Envelope(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT), canvasAttributeChangeListenerHelper);
	private final ILabelObjectType objectType;
	private Format displayFormat;
	private final DrawingNodeAttributeHelper drawingNodeHelper; 

	public LabelAttribute(IModel hibCanvas, int creationSerial, IAnnotationProperty property, ILabelObjectType labelObjectType) {
		super(hibCanvas, creationSerial);
		this.drawingNodeHelper = new DrawingNodeAttributeHelper(this, labelObjectType.getDefaultAttributes());
		this.visualisableProperty = property;
		this.objectType = labelObjectType;
		populateDefaults();
	}

	public LabelAttribute(IModel hibCanvas, int creationSerial, ILabelAttribute otherAttribute, IAnnotationProperty copiedProperty) {
		super(hibCanvas, creationSerial);
		this.objectType = otherAttribute.getObjectType();
		this.drawingNodeHelper = new DrawingNodeAttributeHelper(this, otherAttribute);
		this.visualisableProperty = copiedProperty;
		this.displayFormat = otherAttribute.getDisplayFormat();
//		this.boundsDelegate.setBounds(otherAttribute.getBounds());
	}

	private void populateDefaults() {
		ILabelAttributeDefaults labelDefaults = this.objectType.getDefaultAttributes();
		this.displayFormat = labelDefaults.getDisplayFormat();
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#
	 * getFillColour()
	 */
	@Override
	public Colour getFillColour() {
		return this.drawingNodeHelper.getFillColour();
	}

	@Override
	public void setFillColour(Colour color) {
		this.drawingNodeHelper.setFillColour(color);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.drawingNodeHelper.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#listenerIterator()
	 */
	@Override
	public List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.drawingNodeHelper.getChangeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.drawingNodeHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	@Override
	public Envelope getBounds() {
		return this.drawingNodeHelper.getBounds();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setBounds(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Bounds)
	 */
	@Override
	public void setBounds(Envelope newBounds) {
		this.drawingNodeHelper.setBounds(newBounds);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLineColour()
	 */
	@Override
	public Colour getLineColour() {
		return this.drawingNodeHelper.getLineColour();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setLineColour(Colour colour) {
		this.drawingNodeHelper.setLineColour(colour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLineStyle()
	 */
	@Override
	public LineStyle getLineStyle() {
		return this.drawingNodeHelper.getLineStyle();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#getLineWidth()
	 */
	@Override
	public double getLineWidth() {
		return this.drawingNodeHelper.getLineWidth();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	@Override
	public void setLineStyle(LineStyle lineStyle) {
		this.drawingNodeHelper.setLineStyle(lineStyle);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ILabelAttribute#setLineWidth(int)
	 */
	@Override
	public void setLineWidth(double lineWidth) {
		this.drawingNodeHelper.setLineWidth(lineWidth);
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
		this.drawingNodeHelper.resize(locationDelta, sizeDelta);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		this.drawingNodeHelper.translate(delta);
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

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getFont()
	 */
	@Override
	public GenericFont getFont() {
		return this.drawingNodeHelper.getFont();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setFont(org.pathwayeditor.figure.rendering.GenericFont)
	 */
	@Override
	public void setFont(GenericFont font) {
		this.drawingNodeHelper.setFont(font);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getFontColour()
	 */
	@Override
	public Colour getFontColour() {
		return this.drawingNodeHelper.getFontColour();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setFontColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setFontColour(Colour colour) {
		this.drawingNodeHelper.setFontColour(colour);
	}
}
