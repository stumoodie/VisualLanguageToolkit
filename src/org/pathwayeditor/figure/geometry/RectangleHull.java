/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.geometry;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * RectangleHull is a specialised implementation of {@link IConvexHull} that specifically represents a rectangle.
 * Because the shape of the hull is known we can provide optimizations to some of the operations specified in the interface.
 * As specified in the interface this class is immutable. 
 * 
 * @author Stuart Moodie
 *
 */
public class RectangleHull implements IConvexHull {
	private final IConvexHull hull;
	
	public RectangleHull(Envelope initialBounds){
		List<Point> points = new LinkedList<Point>();
		points.add(initialBounds.getOrigin());
		points.add(initialBounds.getHorizontalCorner());
		points.add(initialBounds.getDiagonalCorner());
		points.add(initialBounds.getVerticalCorner());
		this.hull = new ConvexHull(points);
	}

	@Override
	public IConvexHull changeEnvelope(Envelope newEnvelope) {
		return this.hull.changeEnvelope(newEnvelope);
	}

	@Override
	public boolean containsPoint(Point p) {
		return this.hull.containsPoint(p);
	}

	@Override
	public double getArea() {
		return this.hull.getArea();
	}

	@Override
	public Point getCentre() {
		return this.hull.getCentre();
	}

//	@Override
//	public Dimension getDimension() {
//		return this.hull.getDimension();
//	}

	@Override
	public Envelope getEnvelope() {
		return this.hull.getEnvelope();
	}

	@Override
	public List<LineSegment> getLines() {
		return this.hull.getLines();
	}

//	@Override
//	public Point getOrigin() {
//		return this.hull.getOrigin();
//	}

	@Override
	public Point getPointLineIntersects(Point reference) {
		return this.hull.getPointLineIntersects(reference);
	}

	@Override
	public List<Point> getPoints() {
		return this.hull.getPoints();
	}

	@Override
	public boolean hullsIntersect(IConvexHull otherHull) {
		return this.hull.hullsIntersect(otherHull);
	}

	@Override
	public Iterator<Point> iterator() {
		return this.hull.pointIterator();
	}

	@Override
	public int numPoints() {
		return this.hull.numPoints();
	}

	@Override
	public Iterator<Point> pointIterator() {
		return this.hull.pointIterator();
	}

	@Override
	public IConvexHull scale(Dimension newDim) {
		return this.hull.scale(newDim);
	}

	@Override
	public IConvexHull scale(double scale, double scale2) {
		return this.hull.scale(scale, scale2);
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(bounds=");
		buf.append(this.hull.getEnvelope());
		buf.append(")");
		return this.hull.toString();
	}

	@Override
	public IConvexHull translate(double d, double e) {
		return this.hull.translate(d, e);
	}

	@Override
	public IConvexHull translate(Point p) {
		return this.hull.translate(p);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.hull == null) ? 0 : this.hull.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RectangleHull))
			return false;
		RectangleHull other = (RectangleHull) obj;
		if (this.hull == null) {
			if (other.hull != null)
				return false;
		} else if (!this.hull.equals(other.hull))
			return false;
		return true;
	}

}
