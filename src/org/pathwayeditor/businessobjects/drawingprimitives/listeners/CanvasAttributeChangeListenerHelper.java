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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public final class CanvasAttributeChangeListenerHelper implements ICanvasAttributeChangeListenee {
	private final List<ICanvasAttributeChangeListener> listeners;
	private final ICanvasElementAttribute attribute;
	
	public CanvasAttributeChangeListenerHelper(ICanvasElementAttribute attribute){
		this.listeners = new CopyOnWriteArrayList<ICanvasAttributeChangeListener>();
		this.attribute = attribute;
	}

	/**
	 * 
	 */
	public final List<ICanvasAttributeChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(ICanvasAttributePropertyChangeEvent evt){
		for(ICanvasAttributeChangeListener listener : this.getListeners()){
			listener.propertyChange(evt);
		}
	}
	
	public final void notifyPropertyChange(final CanvasAttributePropertyChange type, final Object oldValue, final Object newValue){
		ICanvasAttributePropertyChangeEvent event = new ICanvasAttributePropertyChangeEvent(){

			@Override
			public ICanvasElementAttribute getAttribute(){
				return attribute;
			}
			
			@Override
			public Object getNewValue() {
				return newValue;
			}

			@Override
			public Object getOldValue() {
				return oldValue;
			}

			@Override
			public CanvasAttributePropertyChange getPropertyChange() {
				return type;
			}
			
		};
		firePropertyChange(event);
	}
	
	public final void fireNodeResizeEvent(ICanvasAttributeResizedEvent evt){
		for(ICanvasAttributeChangeListener listener : this.getListeners()){
			listener.nodeResized(evt);
		}
	}
	
	public final void fireNodeTranslationEvent(ICanvasAttributeTranslationEvent evt){
		for(ICanvasAttributeChangeListener listener : this.getListeners()){
			listener.nodeTranslated(evt);
		}
	}

	public final void notifyNodeResize(final Point originDelta, final Dimension sizeDelta){
		ICanvasAttributeResizedEvent event = new ICanvasAttributeResizedEvent() {

			@Override
			public ICanvasElementAttribute getAttibuteChanged() {
				return attribute;
			}

			@Override
			public Point getOriginChange() {
				return originDelta;
			}

			@Override
			public Dimension getSizeChange() {
				return sizeDelta;
			}
		};
		fireNodeResizeEvent(event);
	}

	public final void notifyNodeTranslation(final Point delta){
		ICanvasAttributeTranslationEvent event = new ICanvasAttributeTranslationEvent() {
			
			@Override
			public Point getTranslationDelta() {
				return delta;
			}
			
			@Override
			public ICanvasElementAttribute getAttibuteChanged() {
				return attribute;
			}
		};
		fireNodeTranslationEvent(event);
	}

	@Override
	public final List<ICanvasAttributeChangeListener> getChangeListeners(){
		return new ArrayList<ICanvasAttributeChangeListener>(this.listeners);
	}
	
	@Override
	public final void addChangeListener(ICanvasAttributeChangeListener listener){
		this.listeners.add(listener);
	}

	@Override
	public final void removeChangeListener(ICanvasAttributeChangeListener listener){
		this.listeners.remove(listener);
	}
}
