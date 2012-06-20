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

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.rendering.GenericFont;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

public class ShapeAttribute extends AnnotatedCanvasAttribute implements IShapeAttribute {
//	private static final Point DEFAULT_POSITION = Point.ORIGIN;
//	private static final Dimension DEFAULT_SIZE = new Dimension(10,10);
//	private static final Colour DEFAULT_FILL = Colour.WHITE;
//	private static final Colour DEFAULT_LINE = Colour.BLACK;
//	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
//	private static final int DEFAULT_LINE_WIDTH = 1;
	private static final String DEFAULT_FIGURE_DEFN = "curbounds rect";

	private transient IShapeObjectType shapeObjectType;
	private String figureDefn = DEFAULT_FIGURE_DEFN;
	private final DrawingNodeAttributeHelper drawingNodeHelper;
	

	public ShapeAttribute(IModel canvas, int creationSerial, IShapeObjectType shapeObjectType){
		super(canvas, creationSerial, shapeObjectType.getDefaultAttributes());
		this.shapeObjectType = shapeObjectType;
		this.drawingNodeHelper = new DrawingNodeAttributeHelper(this, shapeObjectType.getDefaultAttributes());
		this.populateDefaults(shapeObjectType.getDefaultAttributes());
	}
	
	public ShapeAttribute(IModel canvas, int newCreationSerial, IShapeAttribute other) {
		super(canvas, newCreationSerial, other);
		this.shapeObjectType = other.getObjectType();
		this.figureDefn=other.getShapeDefinition();
		this.drawingNodeHelper = new DrawingNodeAttributeHelper(this, other);
	}
	
	
	private void populateDefaults(IShapeAttributeDefaults shapeDefaults){
		this.figureDefn = shapeDefaults.getShapeDefinition();
		Envelope newEnv = this.drawingNodeHelper.getBounds().changeDimension(shapeDefaults.getSize());
		this.drawingNodeHelper.setBounds(newEnv);
	}

	@Override
	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}

	@Override
	public LineStyle getLineStyle () {
		return this.drawingNodeHelper.getLineStyle() ;
	}
	

	@Override
	public double getLineWidth() {
		return this.drawingNodeHelper.getLineWidth();
	}

	@Override
	public void setLineWidth(double lineWidth) {
		this.drawingNodeHelper.setLineWidth(lineWidth);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getFillColour()
	 */
	@Override
	public Colour getFillColour() {
		return this.drawingNodeHelper.getFillColour();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineColour()
	 */
	@Override
	public Colour getLineColour() {
		return this.drawingNodeHelper.getLineColour();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setFillColour(Colour fillColour) {
		this.drawingNodeHelper.setFillColour(fillColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setLineColour(Colour lineColour) {
		this.drawingNodeHelper.setLineColour(lineColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	@Override
	public void setLineStyle(LineStyle lineStyle) {
		this.drawingNodeHelper.setLineStyle(lineStyle);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.drawingNodeHelper.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.drawingNodeHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#listenerIterator()
	 */
	@Override
	public List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.drawingNodeHelper.getChangeListeners();
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute#getShapeDefinition()
	 */
	@Override
	public String getShapeDefinition() {
		return this.figureDefn;
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor)
	 */
	@Override
	public void visit(ICanvasElementAttributeVisitor visitor) {
		visitor.visitShape(this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeCopyFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeCopyFactory() {
		return new ShapeAttributeCopyFactory(this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		return new TypedAttributeMoveFactory(this);
	}

	@Override
	public ICompoundNode getCurrentElement(){
		return (ICompoundNode)super.getCurrentElement();
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
