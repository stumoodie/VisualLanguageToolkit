package org.pathwayeditor.figure.geometry;

import java.util.Iterator;
import java.util.List;

public interface IConvexHull {

	Iterator<Point> iterator();

//	boolean containsPoint(double x, double y);

	boolean containsPoint(Point p);

	boolean hullsIntersect(IConvexHull otherHull);

	List<LineSegment> getLines();

	List<Point> getPoints();

	Iterator<Point> pointIterator();
	
	Point getPointLineIntersects(Point reference);
	
//	Point getPointLineIntersects(double refX, double refY);
	
	Point getCentre();
	
	double getArea();
	
	int numPoints();
	
	Envelope getEnvelope();
	
	Dimension getDimension();
	
	Point getOrigin();
	
	IConvexHull changeEnvelope(Envelope newEnvelope);
	
	IConvexHull translate(double x, double y);

	IConvexHull translate(Point p);

	IConvexHull scale(double scaleX, double scaleY);
	
	IConvexHull scale(Dimension newDim);
}