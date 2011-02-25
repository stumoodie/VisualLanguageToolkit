/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;

/**
 * @author Stuart Moodie
 *
 */
public final class CanvasPropertyChangeHelper	implements ICanvasPropertyChangeListenee {
	private final List<ICanvasPropertyChangeListener> listeners;
	private final ICanvas canvas;
	private boolean enabled = true; 
	
	public CanvasPropertyChangeHelper(ICanvas canvas){
		this.listeners = new LinkedList<ICanvasPropertyChangeListener>();
		this.canvas = canvas;
	}

	public final void firePropertyChange(ICanvasPropertyChangeEvent evt){
		if(enabled){
			for(ICanvasPropertyChangeListener listener : listeners){
				listener.propertyChange(evt);
			}
		}
	}
	
	public final void notifyPropertyChange(final CanvasPropertyChange type, final Object oldValue, final Object newValue){
		ICanvasPropertyChangeEvent event = new ICanvasPropertyChangeEvent(){

			@Override
			public ICanvas getCanvas(){
				return canvas;
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
			public CanvasPropertyChange getPropertyChange() {
				return type;
			}
			
		};
		firePropertyChange(event);
	}
	
	@Override
	public final List<ICanvasPropertyChangeListener> getCanvasPropertyChangeListeners(){
		return new ArrayList<ICanvasPropertyChangeListener>(this.listeners);
	}
	
	@Override
	public final void addCanvasPropertyChangeListener(ICanvasPropertyChangeListener listener){
		this.listeners.add(listener);
	}

	@Override
	public final void removeCanvasPropertyChangeListener(ICanvasPropertyChangeListener listener){
		this.listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#areListenersEnabled()
	 */
	public boolean areListenersEnabled() {
		return this.enabled;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.listeners.ISuppressableChangeListenee#setListenersEnabled(boolean)
	 */
	public void setListenersEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
