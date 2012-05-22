/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICanvasAttributeChangeListener;
import org.pathwayeditor.businessobjects.typedefn.IDrawingNodeAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.rendering.GenericFont;

/**
 * DrawingNodeAttributeHelper
 *
 * @author Stuart Moodie
 *
 */
public class DrawingNodeAttributeHelper {
	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final int DEFAULT_HEIGHT = 1;
	private static final int DEFAULT_WIDTH = 1;

	private Colour background;
	private Colour foreground;
	private double lineWidth;
	private LineStyle lineStyle = LineStyle.SOLID;
	private final CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper;
	private Colour fontColour;
	private GenericFont font;
	private Envelope bounds; 

	public DrawingNodeAttributeHelper(IDrawingNodeAttribute owner, IDrawingNodeAttributeDefaults labelDefaults) {
		canvasAttributeChangeListenerHelper = new CanvasAttributeChangeListenerHelper(owner);
		this.background = labelDefaults.getFillColour();
		this.foreground = labelDefaults.getLineColour();
		this.lineStyle = labelDefaults.getLineStyle();
		this.lineWidth = labelDefaults.getLineWidth();
		this.font = labelDefaults.getFont();
		this.fontColour = labelDefaults.getFontColour();
		this.bounds = new Envelope(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public DrawingNodeAttributeHelper(IDrawingNodeAttribute owner) {
		canvasAttributeChangeListenerHelper = new CanvasAttributeChangeListenerHelper(owner);
		this.background = owner.getFillColour();
		this.foreground = owner.getLineColour();
		this.lineStyle = owner.getLineStyle();
		this.lineWidth = owner.getLineWidth();
		this.font = owner.getFont();
		this.fontColour = owner.getFontColour();
		this.bounds = owner.getBounds();
	}

	public Colour getFillColour() {
		return this.background;
	}

	public void setFillColour(Colour color) {
		if (color == null)
			throw new IllegalArgumentException("Color cannot be null.");

		if(!this.background.equals(color)){
			Colour oldColour = this.background;
			this.background = color;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.FILL_COLOUR, oldColour, this.background);
		}
	}
	
	public void addChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.addChangeListener(listener);
	}

	public List<ICanvasAttributeChangeListener> getChangeListeners() {
		return this.canvasAttributeChangeListenerHelper.getChangeListeners();
	}

	public void removeChangeListener(ICanvasAttributeChangeListener listener) {
		this.canvasAttributeChangeListenerHelper.removeChangeListener(listener);
	}

	public Colour getLineColour() {
		return this.foreground;
	}

	public void setLineColour(Colour color) {
		if(!this.foreground.equals(color)){
			Colour oldValue = this.foreground;
			this.foreground = color;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_COLOUR, oldValue, this.foreground);
		}
	}

	public LineStyle getLineStyle() {
		return this.lineStyle;
	}

	public double getLineWidth() {
		return this.lineWidth;
	}

	public void setLineStyle(LineStyle lineStyle) {
		if(!this.lineStyle.equals(lineStyle)){
			LineStyle oldValue = this.lineStyle;
			this.lineStyle = lineStyle;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_STYLE, oldValue, this.lineStyle);
		}
	}

	public void setLineWidth(double lineWidth) {
		if(this.lineWidth != lineWidth){
			double oldValue = this.lineWidth;
			this.lineWidth = lineWidth;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.LINE_WIDTH, oldValue, this.lineWidth);
		}
	}


	public GenericFont getFont() {
		return this.font;
	}

	public void setFont(GenericFont font) {
		if(!this.font.equals(font)){
			GenericFont oldValue = this.font;
			this.font = font;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.FONT, oldValue, this.font);
		}
	}

	public Colour getFontColour() {
		return this.fontColour;
	}

	public void setFontColour(Colour colour) {
		if(!this.fontColour.equals(colour)){
			Colour oldColour = this.background;
			this.fontColour = colour;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.FONT_COLOUR, oldColour, this.fontColour);
		}
		this.fontColour = colour;
	}
	
	public void resize(Point locationDelta, Dimension sizeDelta) {
		Envelope oldValue = this.bounds;
		this.bounds = this.bounds.resize(locationDelta, sizeDelta);
		
		if(!this.bounds.equals(oldValue)){
			this.canvasAttributeChangeListenerHelper.notifyNodeResize(locationDelta, sizeDelta);
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.BOUNDS, oldValue, this.bounds);
			this.canvasAttributeChangeListenerHelper.notifyDrawnPathChange();
		}
	}

	public void translate(Point delta) {
		Envelope oldValue = this.bounds;
		this.bounds = this.bounds.translate(delta);
		if(!this.bounds.equals(oldValue)){
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.BOUNDS, oldValue, this.bounds);
			this.canvasAttributeChangeListenerHelper.notifyNodeTranslation(delta);
			this.canvasAttributeChangeListenerHelper.notifyDrawnPathChange();
		}
	}

	public Envelope getBounds() {
		return this.bounds;
	}

	public void setBounds(Envelope newBounds) {
		if(!this.bounds.equals(newBounds)){
			Envelope oldValue = this.bounds;
			this.bounds = newBounds;
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.BOUNDS, oldValue, newBounds);
			this.canvasAttributeChangeListenerHelper.notifyDrawnPathChange();
		}
	}

	/**
	 * @param size
	 */
	public void setSize(Dimension size) {
		Envelope oldValue = this.bounds;
		this.bounds = this.bounds.changeDimension(size);
		if(!this.bounds.equals(oldValue)){
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.BOUNDS, oldValue, this.bounds);
			this.canvasAttributeChangeListenerHelper.notifyDrawnPathChange();
		}
	}
	
}
