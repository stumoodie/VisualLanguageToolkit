/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public class BoundsHelper {
	private final CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper;
	private Envelope bounds; 
	
	public BoundsHelper(Envelope iniitalValue, CanvasAttributeChangeListenerHelper canvasAttributeChangeListenerHelper){
		this.canvasAttributeChangeListenerHelper = canvasAttributeChangeListenerHelper;
		this.bounds = iniitalValue;
	}
	
	public void resize(Point locationDelta, Dimension sizeDelta) {
		Envelope oldValue = this.bounds;
		this.bounds = this.bounds.resize(locationDelta, sizeDelta);
		
		if(!this.bounds.equals(oldValue)){
			this.canvasAttributeChangeListenerHelper.notifyNodeResize(locationDelta, sizeDelta);
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.BOUNDS, oldValue, this.bounds);
		}
	}

	public void translate(Point delta) {
		Envelope oldValue = this.bounds;
		this.bounds = this.bounds.translate(delta);
		if(!this.bounds.equals(oldValue)){
			this.canvasAttributeChangeListenerHelper.notifyPropertyChange(CanvasAttributePropertyChange.BOUNDS, oldValue, this.bounds);
			this.canvasAttributeChangeListenerHelper.notifyNodeTranslation(delta);
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
		}
	}
}
