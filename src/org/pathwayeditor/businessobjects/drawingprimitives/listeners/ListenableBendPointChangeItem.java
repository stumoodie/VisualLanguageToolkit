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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public final class ListenableBendPointChangeItem implements IBendPointChangeListenee {
	private final List<IBendPointChangeListener> listeners;
	private final IBendPointContainer linkAttribute;
	
	public ListenableBendPointChangeItem(IBendPointContainer linkAttribute){
		this.listeners = new CopyOnWriteArrayList<IBendPointChangeListener>();
		this.linkAttribute = linkAttribute;
	}

	/**
	 * 
	 */
	public final List<IBendPointChangeListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IBendPointChangeEvent evt){
		for(IBendPointChangeListener listener : this.getListeners()){
			listener.propertyChange(evt);
		}
	}
	
	public final void fireLocationChange(IBendPointLocationChangeEvent evt){
		for(IBendPointChangeListener listener : this.getListeners()){
			listener.locationChange(evt);
		}
	}
	
	public final void notifyPropertyChange(final int idx, final Point oldLocation, final Point newLocation){
		IBendPointLocationChangeEvent event = new IBendPointLocationChangeEvent(){

			@Override
			public Point getNewPosition() {
				return newLocation;
			}

			@Override
			public Point getOldPosition() {
				return oldLocation;
			}

			@Override
			public IBendPointContainer getOwningLink() {
				return linkAttribute;
			}

			@Override
			public int getBendPointIndex() {
				return idx;
			}

		};
		fireLocationChange(event);
	}
	
	public final void notifyPropertyChange(final BendPointChange type, final Point bp, final int oldIdxPos, final int newIdxPos){
		IBendPointChangeEvent event = new IBendPointChangeEvent(){

			@Override
			public BendPointChange getChangeType() {
				return type;
			}

			@Override
			public int getNewIndexPos() {
				return newIdxPos;
			}

			@Override
			public int getOldIndexPos() {
				return oldIdxPos;
			}

			@Override
			public IBendPointContainer getBendPointContainer() {
				return linkAttribute;
			}

			@Override
			public Point getBendPoint() {
				return bp;
			}

		};
		firePropertyChange(event);
	}
	
	@Override
	public final Iterator<IBendPointChangeListener> bendPointListenerIterator(){
		return this.listeners.iterator();
	}
	
	@Override
	public final void addChangeListener(IBendPointChangeListener listener){
		this.listeners.add(listener);
	}

	@Override
	public final void removeChangeListener(IBendPointChangeListener listener){
		this.listeners.remove(listener);
	}

	/**
	 * @param oldPoints
	 * @param newBendPoints
	 */
	public void notifyTranslation(final Point delta, final List<Point> oldPoints, final List<Point> newBendPoints) {
		IBendPointTranslationEvent event = new IBendPointTranslationEvent(){

			@Override
			public int numPoints(){
				return newBendPoints.size();
			}
			
			@Override
			public Iterator<Point> newPointIterator() {
				return newBendPoints.iterator();
			}

			@Override
			public Iterator<Point> oldPointIterator() {
				return oldPoints.iterator();
			}

			@Override
			public IBendPointContainer getBendPointContainer() {
				return linkAttribute;
			}

			@Override
			public Point getDelta() {
				return delta;
			}

		};
		fireTranslationEvent(event);
	}

	/**
	 * @param event
	 */
	public void fireTranslationEvent(IBendPointTranslationEvent event) {
		for(IBendPointChangeListener listener : this.getListeners()){
			listener.translationChange(event);
		}
	}

	public void notifyTranslation(Point delta, Point oldLocn, Point newLocn) {
		List<Point> oldBendPoints = Arrays.asList(new Point[] { oldLocn }); 
		List<Point> newBendPoints = Arrays.asList(new Point[] { newLocn });
		notifyTranslation(delta, oldBendPoints, newBendPoints);
	}
}
