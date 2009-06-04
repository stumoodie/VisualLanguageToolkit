package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ConvexHullCalculator implements IConvexHullCalculator {
	public static final double CURR_LINE_WIDTH = 1.0;
	private static final double HALF_ELLIPSE_SEGS = 9.0;
	private static final double DEFAULT_LINE_WIDTH = 1.0;

	private QuickHull quickHull;
	private ConvexHull hull = null;
	private final Logger logger = Logger.getLogger(this.getClass());
	private double lineWidth;

	// private static final Integer DEFAULT_COLOURS[] = new Integer[]{ 0, 0, 0
	// };
	// public static final String DEFAULT_FONT_STYLE = null;
	// public static final int DEFAULT_FONT_SIZE = 0;

	public ConvexHullCalculator() {
		this.quickHull = new QuickHull();
		this.lineWidth = DEFAULT_LINE_WIDTH;
	}

	public void reset() {
		this.quickHull = new QuickHull();
		this.hull = null;
	}
	
	public void setCurrentLineWidth(double lineWidth){
		this.lineWidth = lineWidth;
	}
	
	
	public double getCurrentLineWidth(){
		return this.lineWidth;
	}

	private double getLineWidthRadius(){
		return this.lineWidth/2;
	}
	
	public void addPoint(Point p) {
		this.addPoint(p.getX(), p.getY());
	}

	public void addPoint(double x, double y) {
//		this.quickHull.addPoint(new Point(x, y));
		double radius = getLineWidthRadius();
		double width = this.getLineWidthRadius();
		this.drawOval(x-radius, y-radius, width, width);
	}

	public void calculate() {
		if(this.quickHull.isEmpty()) throw new IllegalStateException("No points present to generate hull");
		this.quickHull.calculateConvexHull();
		this.hull = new ConvexHull(this.quickHull.getHullPoints());
	}

	public IConvexHull getConvexHull() {
		if(this.hull == null) throw new IllegalStateException("No hull has been created");
		return this.hull;
	}

	public List<Point> getOriginalPoints() {
		return new ArrayList<Point>(this.quickHull.getOriginalPoints());
	}

	public void addArc(double x, double y, double width, double height,
			double offset, double length) {
		//FIXME: Add line thickness to this one please
		double offsetRad = Math.toRadians(offset);
		double lengthRad = Math.toRadians(length);
		for (double angle = offsetRad; angle <= lengthRad; angle += Math.PI
				/ HALF_ELLIPSE_SEGS) {
			quickHull.addPoint(calcOvalPoint(angle, x, y, width, height));
		}
	}

	public void addLine(double startX, double startY, double endX,
			double endY) {
		// in order to handle the line thickness we must define a rectangle
		// that has the height of the line thickness and the direction of the line 
		double offset = this.getLineWidthRadius();
		double xAdjust = - offset * Math.sin(Math.toRadians(90.0));
		double yAdjust = - offset * Math.cos(Math.toRadians(90.0));
		double startX1 = startX - xAdjust;
		double startY1 = startY - yAdjust;
		quickHull.addPoint(new Point(startX1, startY1));
		double endX1 = endX - xAdjust;
		double endY1 = endY - yAdjust;
		quickHull.addPoint(new Point(endX1, endY1));
		double startX2 = startX + xAdjust;
		double startY2 = startY + yAdjust;
		quickHull.addPoint(new Point(startX2, startY2));
		double endX2 = endX + xAdjust;
		double endY2 = endY + yAdjust;
		quickHull.addPoint(new Point(endX2, endY2));
	}

	private void drawRectangle(double x, double y, double width, double height) {
		quickHull.addPoint(new Point(x, y));
		quickHull.addPoint(new Point(x + width, y));
		quickHull.addPoint(new Point(x + width, y + height));
		quickHull.addPoint(new Point(x, y + height));
	}
	
	private void drawOval(double x, double y, double width, double height) {
		for (double angle = 0; angle < Math.PI * 2; angle += Math.PI
				/ HALF_ELLIPSE_SEGS) {
			quickHull.addPoint(calcOvalPoint(angle, x, y, width, height));
		}
	}

	public void addOval(double x, double y, double width, double height) {
//		drawOval(x, y, width, height);
		double radius = this.getLineWidthRadius();
		double lineWidth = this.getCurrentLineWidth();
		drawOval(x - radius, y - radius, width + lineWidth, height + lineWidth);
	}

	private Point calcOvalPoint(double angle, double x, double y, double width,	double height) {
		double a = width / 2;
		double pointX = a * Math.cos(angle) + a + x;
		double b = height / 2;
		double pointY = b * Math.sin(angle) + b + y;
		return new Point(pointX, pointY);
	}

	public void addPolygon(double[] pointArr) {
		drawPolygon(pointArr);
	}

	public void addPolyline(double[] pointArr) {
		//FIXME: this should will overestimate the ends of a polyline in
		// some situations as the end will have the line thichness.
		// Probably best to have the first and last line segments
		// defined as a rectangle and the rest as circles
		drawPolygon(pointArr);
	}

	private void drawPolygon(double[] pointArr){
		int numPoints = pointArr.length/2;
		double radius = getLineWidthRadius();
		double width = this.getLineWidthRadius();
		for (int i = 0, j = 0; i < numPoints; i++) {
			double x = pointArr[j++] - radius;
			double y = pointArr[j++] - radius;
			this.drawOval(x, y, width, width);
//			quickHull.addPoint(new Point(pointArr[j++], pointArr[j++]));
		}
	}
	
	public void addRectangle(double x, double y, double width, double height) {
//		drawRectangle(x, y, width, height);
		double offset = this.getLineWidthRadius();
		drawRectangle(x-offset, y-offset, width + this.getCurrentLineWidth(),
				height + this.getCurrentLineWidth());
	}

	public void addRoundRectangle(double x, double y, double width,
			double height, double arcWidth, double arcHeight) {
		//FIXME: Add line thickness to this one please
		double a = arcWidth / 2;
		double b = arcHeight / 2;
		// write line segments that link to arcs at corner
		quickHull.addPoint(new Point(x + a, y));
		quickHull.addPoint(new Point(x + width - a, y));
		quickHull.addPoint(new Point(x, y + b));
		quickHull.addPoint(new Point(x + width, y + b));
		quickHull.addPoint(new Point(x + width, y + height - b));
		quickHull.addPoint(new Point(x, y + height - b));
		quickHull.addPoint(new Point(x + width - a, y + height));
		quickHull.addPoint(new Point(x + a, y + height));
		// top LH corner
		polygonizeArc(x + a, y + b, a, b, 180.0, 270.0);
		// top RH corner
		polygonizeArc(x + width - a, y + b, a, b, 270.0, 360.0);
		// bottom LH corner
		polygonizeArc(x + a, y + height - b, a, b, 90.0, 180.0);
		// bottom RH corner
		polygonizeArc(x + width - a, y + height - b, a, b, 0.0, 90.0);
	}

	private void polygonizeArc(double centreX, double centreY,
			double semiMajorAxis, double semiMinorAxis, double startAngle,
			double endAngle) {
		double offsetRad = Math.toRadians(startAngle);
		double lengthRad = Math.toRadians(endAngle);
		logger.debug("polygonising arc: centre=(" + centreX + ", " + centreY + ") semiMajAx=" + semiMajorAxis + ", semiMinAx" + semiMinorAxis
				+ ", startAngle=" + startAngle + ", endAngle=" + endAngle);
		double increment = Math.PI/ HALF_ELLIPSE_SEGS;
		logger.trace("increment=" + Math.toDegrees(increment));
		for (double angle = offsetRad; angle < lengthRad; angle += increment) {
			double pointX = semiMajorAxis * Math.cos(angle) + centreX;
			double pointY = semiMinorAxis * Math.sin(angle) + centreY;
			Point arcPoint = new Point(pointX, pointY);
			quickHull.addPoint(arcPoint);
			logger.trace("adding arc point: angle=" + Math.toDegrees(angle) + ",point=" + arcPoint);
		}
	}
}
