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

// Generated 07-May-2008 22:43:44 by Hibernate Tools 3.2.1.GA

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.IRootAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

public class ShapeAttribute extends AnnotatedCanvasAttribute implements IShapeAttribute {
	private static final Point DEFAULT_POSITION = Point.ORIGIN;
	private static final Dimension DEFAULT_SIZE = new Dimension(10,10);
	private static final RGB DEFAULT_FILL = RGB.WHITE;
	private static final RGB DEFAULT_LINE = RGB.BLACK;
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final int DEFAULT_LINE_WIDTH = 1;
	private static final String DEFAULT_FIGURE_DEFN = "curbounds rect";

	private transient IShapeObjectType shapeObjectType;
	private transient RGB fillColour = DEFAULT_FILL;
	private transient RGB lineColour = DEFAULT_LINE;
	private LineStyle lineStyle = DEFAULT_LINE_STYLE;
	private double lineWidth = DEFAULT_LINE_WIDTH;
	private String figureDefn = DEFAULT_FIGURE_DEFN;
	private transient final CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper = new CanvasAttributeChangeListenerHelper(this);
	private final IRootAttribute rootAttribute;
	private final BoundsHelper boundsDelegate = new BoundsHelper(new Envelope(DEFAULT_POSITION, DEFAULT_SIZE), canvasAttributeChangeListenerHelper);
	

	public ShapeAttribute(IRootAttribute rootAttribute, int creationSerial, IShapeObjectType shapeObjectType){
		super(creationSerial, shapeObjectType.getDefaultAttributes());
		this.rootAttribute = rootAttribute;
		this.shapeObjectType = shapeObjectType;
		this.populateDefaults(shapeObjectType.getDefaultAttributes());
	}
	
	public ShapeAttribute(IRootAttribute rootAttribute, int newCreationSerial, IShapeAttribute other) {
		super(newCreationSerial, other);
		this.rootAttribute = rootAttribute;
		this.boundsDelegate.setBounds(other.getBounds());
		this.fillColour = other.getFillColour();
		this.lineColour = other.getLineColour();
		this.lineStyle = other.getLineStyle();
		this.lineWidth = other.getLineWidth();
		this.shapeObjectType = other.getObjectType();
		this.figureDefn=other.getShapeDefinition();
	}
	
	
	private void populateDefaults(IShapeAttributeDefaults shapeDefaults){
		this.fillColour = shapeDefaults.getFillColour();
		this.boundsDelegate.setSize(shapeDefaults.getSize());
		this.lineColour = shapeDefaults.getLineColour();
		this.lineStyle = shapeDefaults.getLineStyle();
		this.lineWidth = shapeDefaults.getLineWidth();
		this.figureDefn = shapeDefaults.getShapeDefinition();
	}

	@Override
	public IShapeObjectType getObjectType() {
		return this.shapeObjectType;
	}

	@Override
	public LineStyle getLineStyle () {
		return this.lineStyle ;
	}
	

	@Override
	public double getLineWidth() {
		return this.lineWidth;
	}

	@Override
	public void setLineWidth(double lineWidth) {
		if ( lineWidth < MIN_LINE_WIDTH )
			throw new IllegalArgumentException ("Line width cannot be less than " + MIN_LINE_WIDTH) ;

		double oldLineWidth = this.lineWidth;
		this.lineWidth = lineWidth;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_WIDTH, oldLineWidth, this.lineWidth);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getFillColour()
	 */
	@Override
	public RGB getFillColour() {
		return this.fillColour;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#getLineColour()
	 */
	@Override
	public RGB getLineColour() {
		return this.lineColour;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setFillColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	@Override
	public void setFillColour(RGB fillColour) {
		if ( fillColour == null )
			throw new IllegalArgumentException ("Fill colour cannot be null") ;

		RGB oldFillColour = this.fillColour;
		this.fillColour = fillColour;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.FILL_COLOUR, oldFillColour, this.fillColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineColour(org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB)
	 */
	@Override
	public void setLineColour(RGB lineColour) {
		if ( lineColour == null )
			throw new IllegalArgumentException ("Line colour cannot be null") ;

		RGB oldLineColour = this.lineColour;
		this.lineColour = lineColour;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldLineColour, this.lineColour);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IShape#setLineStyle(org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle)
	 */
	@Override
	public void setLineStyle(LineStyle lineStyle) {
		if ( lineStyle == null )
			throw new IllegalArgumentException ( "Line style cannot be null") ;

		LineStyle oldLineStyle = this.lineStyle;
		this.lineStyle = lineStyle ;
		this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_STYLE, oldLineStyle, this.lineStyle);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IPropertyChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ChangeListenee#listenerIterator()
	 */
	@Override
	public List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.canvasAttributeChangeListenerHelper.getChangeListeners();
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
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute#getRootAttribute()
	 */
	@Override
	public IRootAttribute getRootAttribute() {
		return this.rootAttribute;
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
		return new ShapeAttributeCopyFactory(this.rootAttribute.getCreationSerialCounter(), this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		return new TypedAttributeMoveFactory(this);
	}
}
