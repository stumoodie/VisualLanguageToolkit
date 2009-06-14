package org.pathwayeditor.figure.geometry;

import java.util.List;

public interface IConvexHullCalculator {

	void reset();

	void addPoint(Point p);
	
	void addPoint(double x, double y);
	
	void calculate();
	
	IConvexHull getConvexHull();
	
	List<Point> getOriginalPoints();

	void addArc(double x, double y, double width, double height,	double offset, double length);

	void addLine(double startX, double startY, double endX, double endY);

	void addOval(double x, double y, double width, double height);

	void addPolygon(double[] pointArr);

	void addPolyline(double[] pointArr);

	void addRectangle(double x, double y, double width, double height);

	void addRoundRectangle(double x, double y, double width,	double height, double arcWidth, double arcHeight);
}
