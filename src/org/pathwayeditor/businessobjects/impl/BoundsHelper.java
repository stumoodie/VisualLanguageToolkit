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

import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributeChangeListenerHelper;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.CanvasAttributePropertyChange;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
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
