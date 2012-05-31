/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegmentVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.IStraightLineCurveSegment;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentChangeListener;
import org.pathwayeditor.businessobjects.drawingprimitives.listeners.ICurveSegmentLocationChangeEvent;
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
	private final List<ICurveSegmentChangeListener> listeners;

	/**
	 * @param startPoint
	 * @param endPoint
	 */
	public StraightLineCurveSegment(Point startPoint, Point endPoint) {
		this.start = startPoint;
		this.end = endPoint;
		this.listeners = new LinkedList<ICurveSegmentChangeListener>();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegment#visit(org.pathwayeditor.businessobjects.drawingprimitives.ICurveSegmentVisitor)
	 */
	@Override
	public void visit(ICurveSegmentVisitor visitor) {
		visitor.visitStraightLineCurveSegment(this);
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
			notifyLocationChange(Arrays.asList(new Point[]{this.start, oldEnd}), Arrays.asList(new Point[]{this.start, this.end}));
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
			notifyLocationChange(Arrays.asList(new Point[]{oldStart, this.end}), Arrays.asList(new Point[]{this.start, this.end}));
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
			notifyLocationChange(Arrays.asList(new Point[]{oldStart, oldEnd}), Arrays.asList(new Point[]{this.start, this.end}));
		}
	}

	/**
	 * @param asList
	 * @param asList2
	 */
	private void notifyLocationChange(final List<Point> origPoints, final List<Point> newPoints) {
		ICurveSegmentLocationChangeEvent e = new ICurveSegmentLocationChangeEvent() {
			
			@Override
			public List<Point> getOldPosition() {
				return origPoints;
			}
			
			@Override
			public List<Point> getNewPosition() {
				return newPoints;
			}
		};
		for(ICurveSegmentChangeListener l : this.listeners){
			l.locationChange(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IStraightLineCurveSegment#getLineSegement()
	 */
	@Override
	public LineSegment getLineSegement() {
		return new LineSegment(this.start, this.end);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.end == null) ? 0 : this.end.hashCode());
		result = prime * result + ((this.start == null) ? 0 : this.start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StraightLineCurveSegment))
			return false;
		StraightLineCurveSegment other = (StraightLineCurveSegment) obj;
		if (this.end == null) {
			if (other.end != null)
				return false;
		} else if (!this.end.equals(other.end))
			return false;
		if (this.start == null) {
			if (other.start != null)
				return false;
		} else if (!this.start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(start=");
		builder.append(this.start);
		builder.append(", end=");
		builder.append(this.end);
		builder.append(")");
		return builder.toString();
	}

}
