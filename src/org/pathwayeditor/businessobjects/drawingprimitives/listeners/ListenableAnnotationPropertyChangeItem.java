/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;

/**
 * @author Stuart Moodie
 *
 */
public final class ListenableAnnotationPropertyChangeItem	implements IAnnotationPropertyChangeListenee {
	private final List<IAnnotationPropertyChangeListener> listeners;
	private IAnnotationProperty annot;
	
	public ListenableAnnotationPropertyChangeItem(IAnnotationProperty annot){
		this.listeners = new CopyOnWriteArrayList<IAnnotationPropertyChangeListener>();
		this.annot = annot;
	}

	/**
	 * 
	 */
	public final List<IAnnotationPropertyChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IAnnotationPropertyChangeEvent evt){
		for(IAnnotationPropertyChangeListener listener : this.getListeners()){
			listener.propertyChange(evt);
		}
	}
	
	public final void notifyPropertyChange(final Object oldValue, final Object newValue){
		IAnnotationPropertyChangeEvent event = new IAnnotationPropertyChangeEvent(){

			@Override
			public Object getNewValue() {
				return newValue;
			}

			@Override
			public Object getOldValue() {
				return oldValue;
			}

			@Override
			public IAnnotationProperty getPropertyDefinition() {
				return annot;
			}
			
		};
		firePropertyChange(event);
	}
	
	@Override
	public final Iterator<IAnnotationPropertyChangeListener> listenerIterator(){
		return this.listeners.iterator();
	}
	
	@Override
	public final void addChangeListener(IAnnotationPropertyChangeListener listener){
		this.listeners.add(listener);
	}

	@Override
	public final void removeChangeListener(IAnnotationPropertyChangeListener listener){
		this.listeners.remove(listener);
	}

}
