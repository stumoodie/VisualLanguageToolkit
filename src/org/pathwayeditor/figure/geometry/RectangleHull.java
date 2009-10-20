/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author smoodie
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

	public IConvexHull changeEnvelope(Envelope newEnvelope) {
		return this.hull.changeEnvelope(newEnvelope);
	}

	public boolean containsPoint(Point p) {
		return this.hull.containsPoint(p);
	}

	public double getArea() {
		return this.hull.getArea();
	}

	public Point getCentre() {
		return this.hull.getCentre();
	}

	public Dimension getDimension() {
		return this.hull.getDimension();
	}

	public Envelope getEnvelope() {
		return this.hull.getEnvelope();
	}

	public List<LineSegment> getLines() {
		return this.hull.getLines();
	}

	public Point getOrigin() {
		return this.hull.getOrigin();
	}

	public Point getPointLineIntersects(Point reference) {
		return this.hull.getPointLineIntersects(reference);
	}

	public List<Point> getPoints() {
		return this.hull.getPoints();
	}

	public boolean hullsIntersect(IConvexHull otherHull) {
		return this.hull.hullsIntersect(otherHull);
	}

	public Iterator<Point> iterator() {
		return this.hull.iterator();
	}

	public int numPoints() {
		return this.hull.numPoints();
	}

	public Iterator<Point> pointIterator() {
		return this.hull.pointIterator();
	}

	public IConvexHull scale(Dimension newDim) {
		return this.hull.scale(newDim);
	}

	public IConvexHull scale(double scale, double scale2) {
		return this.hull.scale(scale, scale2);
	}

	public String toString() {
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(bounds=");
		buf.append(this.hull.getEnvelope());
		buf.append(")");
		return this.hull.toString();
	}

	public IConvexHull translate(double d, double e) {
		return this.hull.translate(d, e);
	}

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
