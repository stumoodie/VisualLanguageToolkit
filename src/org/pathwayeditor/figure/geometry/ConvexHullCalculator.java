/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * ConvexHullCalculator is a class that is used to construct a convex hull from a set of points using the QuickHull
 * algorithm.
 *
 * @author Stuart Moodie
 *
 */
public class ConvexHullCalculator implements IConvexHullCalculator {
	private static final double HALF_ELLIPSE_SEGS = 12.0;

	private QuickHull quickHull;
	private IConvexHull hull = null;
	private final Logger logger = Logger.getLogger(this.getClass());

	public ConvexHullCalculator() {
		this.quickHull = new QuickHull();
	}

	@Override
	public void reset() {
		this.quickHull = new QuickHull();
		this.hull = null;
	}
	
	@Override
	public void addPoint(Point p) {
		this.addPoint(p.getX(), p.getY());
	}

	@Override
	public void addPoint(double x, double y) {
		this.quickHull.addPoint(new Point(x, y));
	}

	@Override
	public void calculate() {
		if(this.quickHull.isEmpty()) throw new IllegalStateException("No points present to generate hull");
		this.quickHull.calculateConvexHull();
		IConvexHull unscaledHull = new ConvexHull(this.quickHull.getHullPoints());
		this.hull = unscaledHull;
	}

	@Override
	public IConvexHull getConvexHull() {
//		if(this.hull == null) throw new IllegalStateException("No hull has been created");
		return this.hull;
	}

	@Override
	public List<Point> getOriginalPoints() {
		return new ArrayList<Point>(this.quickHull.getOriginalPoints());
	}

	@Override
	public void addArc(double x, double y, double width, double height,
			double offset, double length) {
		double offsetRad = Math.toRadians(offset);
		double lengthRad = Math.toRadians(length);
		for (double angle = offsetRad; angle <= lengthRad; angle += Math.PI
				/ HALF_ELLIPSE_SEGS) {
			quickHull.addPoint(calcOvalPoint(angle, x, y, width, height));
		}
	}

	@Override
	public void addLine(double startX, double startY, double endX,
			double endY) {
		quickHull.addPoint(new Point(startX, startY));
		quickHull.addPoint(new Point(endX, endY));
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

	@Override
	public void addOval(double x, double y, double width, double height) {
		drawOval(x, y, width, height);
	}

	private Point calcOvalPoint(double angle, double x, double y, double width,	double height) {
		double a = width / 2;
		double pointX = a * Math.cos(angle) + a + x;
		double b = height / 2;
		double pointY = b * Math.sin(angle) + b + y;
		return new Point(pointX, pointY);
	}

	@Override
	public void addPolygon(double[] pointArr) {
		drawPolygon(pointArr);
	}

	@Override
	public void addPolyline(double[] pointArr) {
		drawPolygon(pointArr);
	}

	private void drawPolygon(double[] pointArr){
		int numPoints = pointArr.length;
		for (int j = 0; j < numPoints;) {
			quickHull.addPoint(new Point(pointArr[j++], pointArr[j++]));
		}
	}
	
	@Override
	public void addRectangle(double x, double y, double width, double height) {
		drawRectangle(x, y, width, height);
	}

	@Override
	public void addRoundRectangle(double x, double y, double width,
			double height, double arcWidth, double arcHeight) {
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
		if(logger.isDebugEnabled()){
			logger.debug("polygonising arc: centre=(" + centreX + ", " + centreY + ") semiMajAx=" + semiMajorAxis + ", semiMinAx" + semiMinorAxis
					+ ", startAngle=" + startAngle + ", endAngle=" + endAngle);
		}
		double increment = Math.PI/ HALF_ELLIPSE_SEGS;
		if(logger.isTraceEnabled()){
			logger.trace("increment=" + Math.toDegrees(increment));
		}
		for (double angle = offsetRad; angle < lengthRad; angle += increment) {
			double pointX = semiMajorAxis * Math.cos(angle) + centreX;
			double pointY = semiMinorAxis * Math.sin(angle) + centreY;
			Point arcPoint = new Point(pointX, pointY);
			quickHull.addPoint(arcPoint);
			if(logger.isTraceEnabled()){
				logger.trace("adding arc point: angle=" + Math.toDegrees(angle) + ",point=" + arcPoint);
			}
		}
	}
}
