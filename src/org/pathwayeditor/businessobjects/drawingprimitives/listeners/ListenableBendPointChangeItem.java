/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public final class ListenableBendPointChangeItem implements IBendPointChangeListenee {
	private final List<IBendPointContainerListener> listeners;
	private final IBendPointContainer linkAttribute;
	
	public ListenableBendPointChangeItem(IBendPointContainer linkAttribute){
		this.listeners = new CopyOnWriteArrayList<IBendPointContainerListener>();
		this.linkAttribute = linkAttribute;
	}

	/**
	 * 
	 */
	public final List<IBendPointContainerListener> getListeners() {
		return this.listeners;
	}

	public final void firePropertyChange(IBendPointStructureChangeEvent evt){
		for(IBendPointContainerListener listener : this.getListeners()){
			listener.structureChange(evt);
		}
	}
	
	public final void fireLocationChange(IBendPointLocationChangeEvent evt){
		for(IBendPointContainerListener listener : this.getListeners()){
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
			public IBendPointContainer getBendPointContainer() {
				return linkAttribute;
			}

			@Override
			public int getBendPointIndex() {
				return idx;
			}

		};
		fireLocationChange(event);
	}
	
	public final void notifyPropertyChange(final BendPointStructureChange type, final Point bp, final int oldIdxPos, final int newIdxPos){
		IBendPointStructureChangeEvent event = new IBendPointStructureChangeEvent(){

			@Override
			public BendPointStructureChange getChangeType() {
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
	public final List<IBendPointContainerListener> bendPointListeners(){
		return new ArrayList<IBendPointContainerListener>(this.listeners);
	}
	
	@Override
	public final void addChangeListener(IBendPointContainerListener listener){
		this.listeners.add(listener);
	}

	@Override
	public final void removeChangeListener(IBendPointContainerListener listener){
		this.listeners.remove(listener);
	}

//	/**
//	 * @param oldPoints
//	 * @param newBendPoints
//	 */
//	public void notifyTranslation(final Point delta, final List<Point> oldPoints, final List<Point> newBendPoints) {
//		IBendPointTranslationEvent event = new IBendPointTranslationEvent(){
//
//			@Override
//			public int numPoints(){
//				return newBendPoints.size();
//			}
//			
//			@Override
//			public Iterator<Point> newPointIterator() {
//				return newBendPoints.iterator();
//			}
//
//			@Override
//			public Iterator<Point> oldPointIterator() {
//				return oldPoints.iterator();
//			}
//
//			@Override
//			public IBendPointContainer getBendPointContainer() {
//				return linkAttribute;
//			}
//
//			@Override
//			public Point getDelta() {
//				return delta;
//			}
//
//		};
//		fireTranslationEvent(event);
//	}

//	/**
//	 * @param event
//	 */
//	public void fireTranslationEvent(IBendPointTranslationEvent event) {
//		for(IBendPointChangeListener listener : this.getListeners()){
//			listener.translationChange(event);
//		}
//	}

//	public void notifyTranslation(Point delta, Point oldLocn, Point newLocn) {
//		List<Point> oldBendPoints = Arrays.asList(new Point[] { oldLocn }); 
//		List<Point> newBendPoints = Arrays.asList(new Point[] { newLocn });
//		notifyTranslation(delta, oldBendPoints, newBendPoints);
//	}
}
