/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegmentVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.IStraightLineCurveSegment;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentChangeEvent;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentChangeListener;
import org.pathwayeditor.figure.geometry.LineSegment;
import org.pathwayeditor.figure.geometry.Point;

/**
 * StraightLineCurveSegment
 *
 * @author Stuart Moodie
 *
 */
public class StraightLineCurveSegment implements IStraightLineCurveSegment {
	private Point start;
	private Point end;
	private final List<ICurveSegmentChangeListener> listeners = new LinkedList<ICurveSegmentChangeListener>();

	/**
	 * @param startPoint
	 * @param endPoint
	 */
	public StraightLineCurveSegment(Point startPoint, Point endPoint) {
		this.start = startPoint;
		this.end = endPoint;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegmentVisitor)
	 */
	@Override
	public void visit(ICurveSegmentVisitor visitor) {
		visitor.visitStraightLineCurveSegment(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#addCurveSegmentChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentChangeListener)
	 */
	@Override
	public void addCurveSegmentChangeListener(ICurveSegmentChangeListener l) {
		this.listeners.add(l);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#removeCurveSegmentChangeListener(org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentChangeListener)
	 */
	@Override
	public void removeCurveSegmentChangeListener(ICurveSegmentChangeListener l) {
		this.listeners.remove(l);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#getCurveSegmentChangeListeners()
	 */
	@Override
	public List<ICurveSegmentChangeListener> getCurveSegmentChangeListeners() {
		return new ArrayList<ICurveSegmentChangeListener>(this.listeners);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#getStartPoint()
	 */
	@Override
	public Point getStartPoint() {
		return this.start;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#getEndPoint()
	 */
	@Override
	public Point getEndPoint() {
		return this.end;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#setEndPoint(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void setEndPoint(Point newLocation) {
		if(!newLocation.equals(this.end)){
			Point oldEnd = this.end;
			this.end = newLocation;
			notifyLocationChange(new LineSegment(this.start, oldEnd), new LineSegment(this.start, this.end));
		}
	}

	/**
	 * @param asList
	 * @param asList2
	 */
	private void notifyLocationChange(final LineSegment oldSeg, final LineSegment newSeg) {
		final ICurveSegmentChangeEvent e = new ICurveSegmentChangeEvent() {
			
			@Override
			public Object getOldSegment() {
				return oldSeg;
			}
			
			@Override
			public Object getNewSegment() {
				return newSeg;
			}
		};
		for(ICurveSegmentChangeListener l : this.listeners){
			l.locationChange(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#setStartPoint(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void setStartPoint(Point newLocation) {
		if(!newLocation.equals(this.start)){
			Point oldStart = this.start;
			this.start = newLocation;
			notifyLocationChange(new LineSegment(oldStart, this.end), new LineSegment(this.start, this.end));
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#translate(org.pathwayeditor.figure.geometry.Point)
	 */
	@Override
	public void translate(Point translation) {
		if(!translation.equals(Point.ORIGIN)){
			Point oldStart = this.start;
			Point oldEnd = this.end;
			this.start = this.start.translate(translation);
			this.end = this.end.translate(translation);
			notifyLocationChange(new LineSegment(oldStart, oldEnd), new LineSegment(this.start, this.end));
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IStraightLineCurveSegment#getLineSegement()
	 */
	@Override
	public LineSegment getLineSegement() {
		return new LineSegment(this.start, this.end);
	}

}
