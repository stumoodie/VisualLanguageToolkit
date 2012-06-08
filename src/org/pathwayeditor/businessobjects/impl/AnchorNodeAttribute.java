/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttributeVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IAnchorNodeObjectType;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.rendering.GenericFont;

import uk.ac.ed.inf.graph.compound.ICompoundNode;
import uk.ac.ed.inf.graph.compound.IElementAttributeFactory;

/**
 * AnchorNodeAttribute
 *
 * @author Stuart Moodie
 *
 */
public class AnchorNodeAttribute extends CanvasAttribute implements IAnchorNodeAttribute {
	private static final String DEFAULT_FIGURE_DEFN = "curbounds rect";

	private final IAnchorNodeObjectType anchorNodeObjectType;
	private String figureDefn = DEFAULT_FIGURE_DEFN;
	private final DrawingNodeAttributeHelper drawingNodeHelper;
	private final ICurveSegment curveSegment;
	
	public AnchorNodeAttribute(IModel canvas, int creationSerial, ICurveSegment associatedCurveSegment, IAnchorNodeObjectType objectType) {
		super(canvas, creationSerial);
		this.drawingNodeHelper = new DrawingNodeAttributeHelper(this, objectType.getDefaultAttributes());
		this.anchorNodeObjectType = objectType;
		this.curveSegment = associatedCurveSegment;
		IAnchorNodeAttributeDefaults anchorDefaults = anchorNodeObjectType.getDefaultAttributes(); 
		this.drawingNodeHelper.setSize(anchorDefaults.getSize());
		this.drawingNodeHelper.setFillColour(anchorDefaults.getFillColour());
		this.drawingNodeHelper.setFont(anchorDefaults.getFont());
		this.drawingNodeHelper.setFontColour(anchorDefaults.getFontColour());
		this.drawingNodeHelper.setLineColour(anchorDefaults.getLineColour());
		this.drawingNodeHelper.setLineStyle(anchorDefaults.getLineStyle());
		this.drawingNodeHelper.setLineWidth(anchorDefaults.getLineWidth());
		this.figureDefn = anchorDefaults.getShapeDefinition();
	}


	public AnchorNodeAttribute(IModel model, int newCreationSerial, IAnchorNodeAttribute otherAttribute) {
		super(model, newCreationSerial);
		this.anchorNodeObjectType = otherAttribute.getObjectType();
		this.figureDefn = otherAttribute.getShapeDefinition();
		this.drawingNodeHelper = new DrawingNodeAttributeHelper(this);
		this.curveSegment = otherAttribute.getAssociatedCurveSegment();
		this.drawingNodeHelper.setBounds(otherAttribute.getBounds());
		this.drawingNodeHelper.setFillColour(otherAttribute.getFillColour());
		this.drawingNodeHelper.setFont(otherAttribute.getFont());
		this.drawingNodeHelper.setFontColour(otherAttribute.getFontColour());
		this.drawingNodeHelper.setLineColour(otherAttribute.getLineColour());
		this.drawingNodeHelper.setLineStyle(otherAttribute.getLineStyle());
		this.drawingNodeHelper.setLineWidth(otherAttribute.getLineWidth());
		this.figureDefn = otherAttribute.getShapeDefinition();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#getObjectType()
	 */
	@Override
	public IAnchorNodeObjectType getObjectType() {
		return this.anchorNodeObjectType;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute#translate(org.pathwayeditor.figure.geometry.Point)
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
		visitor.visitAnchorNode(this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#getCurrentElement()
	 */
	@Override
	public ICompoundNode getCurrentElement() {
		return (ICompoundNode)super.getCurrentElement();
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeCopyFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeCopyFactory() {
		return new AnchorNodeAttributeCopyFactory(this);
	}

	/* (non-Javadoc)
	 * @see uk.ac.ed.inf.graph.compound.IElementAttribute#elementAttributeMoveFactory()
	 */
	@Override
	public IElementAttributeFactory elementAttributeMoveFactory() {
		return new TypedAttributeMoveFactory(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListenee#addChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener)
	 */
	@Override
	public void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.drawingNodeHelper.addChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListenee#removeChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener)
	 */
	@Override
	public void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.drawingNodeHelper.removeChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListenee#getChangeListeners()
	 */
	@Override
	public List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.drawingNodeHelper.getChangeListeners();
	}

	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttribute#getAssociatedTerminus()
	 */
	@Override
	public ICurveSegment getAssociatedCurveSegment() {
		return this.curveSegment;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute#getBounds()
	 */
	@Override
	public Envelope getBounds() {
		return this.drawingNodeHelper.getBounds();
	}


	@Override
	public boolean equals(Object arg0) {
		return this.drawingNodeHelper.equals(arg0);
	}


	@Override
	public Colour getFillColour() {
		return this.drawingNodeHelper.getFillColour();
	}


	@Override
	public void setFillColour(Colour color) {
		this.drawingNodeHelper.setFillColour(color);
	}


	@Override
	public Colour getLineColour() {
		return this.drawingNodeHelper.getLineColour();
	}


	@Override
	public void setLineColour(Colour color) {
		this.drawingNodeHelper.setLineColour(color);
	}


	@Override
	public LineStyle getLineStyle() {
		return this.drawingNodeHelper.getLineStyle();
	}


	@Override
	public double getLineWidth() {
		return this.drawingNodeHelper.getLineWidth();
	}


	@Override
	public void setLineStyle(LineStyle lineStyle) {
		this.drawingNodeHelper.setLineStyle(lineStyle);
	}


	@Override
	public void setLineWidth(double lineWidth) {
		this.drawingNodeHelper.setLineWidth(lineWidth);
	}


	@Override
	public GenericFont getFont() {
		return this.drawingNodeHelper.getFont();
	}


	@Override
	public void setFont(GenericFont font) {
		this.drawingNodeHelper.setFont(font);
	}


	@Override
	public Colour getFontColour() {
		return this.drawingNodeHelper.getFontColour();
	}


	@Override
	public int hashCode() {
		return this.drawingNodeHelper.hashCode();
	}


	@Override
	public void setFontColour(Colour colour) {
		this.drawingNodeHelper.setFontColour(colour);
	}


	@Override
	public void resize(Point locationDelta, Dimension sizeDelta) {
		this.drawingNodeHelper.resize(locationDelta, sizeDelta);
	}


	@Override
	public void setBounds(Envelope newBounds) {
		this.drawingNodeHelper.setBounds(newBounds);
	}


	public void setSize(Dimension size) {
		this.drawingNodeHelper.setSize(size);
	}


	@Override
	public String toString() {
		return this.drawingNodeHelper.toString();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IAnchorNodeAttribute#getShapeDefinition()
	 */
	@Override
	public String getShapeDefinition() {
		return this.figureDefn;
	}
}
