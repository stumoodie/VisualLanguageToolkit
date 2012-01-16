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

/**
 * 
 */
package org.pathwayeditor.businessobjects.impl;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasPropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasPropertyChangeHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.rendering.GenericFont;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;
import uk.ac.ed.inf.graph.compound.IRootCompoundNode;

/**
 * @author Stuart Moodie
 *
 */
public class RootAttribute extends CanvasAttribute implements IRootAttribute {
	public static final Point INITIAL_POS = new Point(-0.5 * Double.MAX_VALUE, -0.5 *Double.MAX_VALUE);
	public static final Dimension INITIAL_SIZE = new Dimension(Double.MAX_VALUE, Double.MAX_VALUE);
	public static final int ROOT_IDX = 0;
	public static final Colour DEFAULT_BACKGROUND_COLOUR = Colour.WHITE;
	private final IRootObjectType objectType;
	private final CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper = new CanvasAttributeChangeListenerHelper(this);
	private final CanvasPropertyChangeHelper canvasPropertyChangeHelper = new CanvasPropertyChangeHelper(this);
	private final BoundsHelper boundsDelegate = new BoundsHelper(new Envelope(INITIAL_POS, INITIAL_SIZE), canvasAttributeChangeListenerHelper);
	private Colour backgroundColour = DEFAULT_BACKGROUND_COLOUR;
	private Envelope canvasBounds = new Envelope(INITIAL_POS, INITIAL_SIZE);

	public RootAttribute(IModel model, int creationSerial, IRootObjectType objectType) {
		super(model, creationSerial);
		this.objectType = objectType;
	}

	public RootAttribute(IModel model, RootAttribute otherAttribute) {
		super(model, ROOT_IDX);
		this.objectType = otherAttribute.getObjectType();
		this.boundsDelegate.setBounds(otherAttribute.getBounds());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getObjectType()
	 */
	@Override
	public IRootObjectType getObjectType() {
		return this.objectType;
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

	@Override
	public final void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.addChangeListener(listener);
	}

	@Override
	public final List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.canvasAttributeChangeListenerHelper.getChangeListeners();
	}

	@Override
	public final void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#resize(org.pathwayeditor.figure.geometry.Point, org.pathwayeditor.figure.geometry.Dimension)
	 */
	@Override
	public void resize(Point locationDelta, Dimension sizeDelta) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point delta) {
		// do nothing
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor)
	 */
	@Override
	public void visit(ICanvasElementAttributeVisitor visitor) {
		visitor.visitRoot(this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeCopyFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeCopyFactory() {
		throw new UnsupportedOperationException("The root attribute should never be copied in this way");
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		throw new UnsupportedOperationException("The root attribute should never be moved in this way");
	}

	@Override
	public IRootCompoundNode getCurrentElement(){
		return (IRootCompoundNode)super.getCurrentElement();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#setBackgroundColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setBackgroundColour(Colour newBackgroundColour) {
		if(!this.backgroundColour.equals(newBackgroundColour)){
			Colour oldColour = this.backgroundColour;
			this.backgroundColour = newBackgroundColour;
			this.canvasPropertyChangeHelper.notifyPropertyChange(CanvasPropertyChange.BACKGROUND_COLOUR, oldColour, this.backgroundColour);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#getBackgroundColour()
	 */
	@Override
	public Colour getBackgroundColour() {
		return this.backgroundColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#getCanvasBounds()
	 */
	@Override
	public Envelope getCanvasBounds() {
		return this.canvasBounds;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute#setCanvasBounds(org.pathwayeditor.figure.geometry.Envelope)
	 */
	@Override
	public void setCanvasBounds(Envelope canvasBounds) {
		if(!canvasBounds.equals(this.canvasBounds)){
			Envelope oldBounds = this.canvasBounds;
			this.canvasBounds = canvasBounds;
			this.canvasPropertyChangeHelper.notifyPropertyChange(CanvasPropertyChange.CANVAS_SIZE, oldBounds, this.canvasBounds);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListenee#addCanvasPropertyChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener)
	 */
	@Override
	public void addCanvasPropertyChangeListener(ICanvasPropertyChangeListener listener) {
		this.canvasPropertyChangeHelper.addCanvasPropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListenee#removeCanvasPropertyChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListener)
	 */
	@Override
	public void removeCanvasPropertyChangeListener(ICanvasPropertyChangeListener listener) {
		this.canvasPropertyChangeHelper.removeCanvasPropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasPropertyChangeListenee#getCanvasPropertyChangeListeners()
	 */
	@Override
	public List<ICanvasPropertyChangeListener> getCanvasPropertyChangeListeners() {
		return this.canvasPropertyChangeHelper.getCanvasPropertyChangeListeners();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getFillColour()
	 */
	@Override
	public Colour getFillColour() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setFillColour(Colour fillColour) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getLineWidth()
	 */
	@Override
	public double getLineWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setLineWidth(double)
	 */
	@Override
	public void setLineWidth(double lineWidth) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getLineColour()
	 */
	@Override
	public Colour getLineColour() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setLineColour(Colour lineColour) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getLineStyle()
	 */
	@Override
	public LineStyle getLineStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	@Override
	public void setLineStyle(LineStyle lineStyle) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getFont()
	 */
	@Override
	public GenericFont getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setFont(org.pathwayeditor.figure.rendering.GenericFont)
	 */
	@Override
	public void setFont(GenericFont font) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getFontColour()
	 */
	@Override
	public Colour getFontColour() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#setFontColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour)
	 */
	@Override
	public void setFontColour(Colour colour) {
		// TODO Auto-generated method stub
		
	}

}
