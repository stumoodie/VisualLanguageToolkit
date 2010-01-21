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
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public final class DrawingNodeAttributeListenerHandler	implements IDrawingNodeAttributeListenee {
	private final List<IDrawingNodeAttributeListener> listeners;
	private final IDrawingNodeAttribute nodeAttribute;
	private boolean enabled = true; 
	
	public DrawingNodeAttributeListenerHandler(IDrawingNodeAttribute canvas){
		this.listeners = new LinkedList<IDrawingNodeAttributeListener>();
		this.nodeAttribute = canvas;
	}

	/**
	 * 
	 */
	public final List<IDrawingNodeAttributeListener> getListeners() {
		return this.listeners;
	}

	public final void fireNodeResizeEvent(IDrawingNodeAttributeResizedEvent evt){
		if(enabled){
			for(IDrawingNodeAttributeListener listener : this.getListeners()){
				listener.nodeResized(evt);
			}
		}
	}
	
	public final void fireNodeTranslationEvent(IDrawingNodeAttributeTranslationEvent evt){
		if(enabled){
			for(IDrawingNodeAttributeListener listener : this.getListeners()){
				listener.nodeTranslated(evt);
			}
		}
	}
	
	public final void notifyNodeResize(final Point originDelta, final Dimension sizeDelta){
		IDrawingNodeAttributeResizedEvent event = new IDrawingNodeAttributeResizedEvent() {

			public IDrawingNodeAttribute getAttibuteChanged() {
				return nodeAttribute;
			}

			public Point getOriginChange() {
				return originDelta;
			}

			public Dimension getSizeChange() {
				return sizeDelta;
			}
		};
		fireNodeResizeEvent(event);
	}

	public final void notifyNodeTranslation(final Point delta){
		IDrawingNodeAttributeTranslationEvent event = new IDrawingNodeAttributeTranslationEvent() {
			
			public Point getTranslationDelta() {
				return delta;
			}
			
			public IDrawingNodeAttribute getAttibuteChanged() {
				return nodeAttribute;
			}
		};
		fireNodeTranslationEvent(event);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#addDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)
	 */
	public void addDrawingNodeAttributeListener(IDrawingNodeAttributeListener listener) {
		this.listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#getDrawingNodeAttributeListeners()
	 */
	public List<IDrawingNodeAttributeListener> getDrawingNodeAttributeListeners() {
		return new ArrayList<IDrawingNodeAttributeListener>(this.listeners);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListenee#removeDrawingNodeAttributeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.IDrawingNodeAttributeListener)
	 */
	public void removeDrawingNodeAttributeListener(IDrawingNodeAttributeListener listener) {
		this.listeners.remove(listener);
	}
	
}
