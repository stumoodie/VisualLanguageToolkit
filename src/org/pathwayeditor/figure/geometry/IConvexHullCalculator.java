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

import java.util.List;

/**
 * 
 * IConvexHullCalculator is an interface that defines a convex hull builder. The builder takes a set of points, defined
 * as primitive drawing shapes and uses these to define a convex hull. This is akin to sticking pins in a drawing board
 * and then putting an elastic band round them. The elastic band defines the convex hull of the set of points. Note
 * that a minumum of 3 points must be defined in order to create a convex hull. 
 *
 * @author Stuart Moodie
 *
 */
public interface IConvexHullCalculator {

	/**
	 * Empties this instance of points an calculated convex hull. 
	 */
	void reset();

	/**
	 * Add a single point to this calculator.
	 * @param p the point, which should not be null.
	 */
	void addPoint(Point p);
	
	/**
	 * Add a single point to this calculator.
	 * @param x the x coordinate of the point.
	 * @param y the y coordinate of the point.
	 */
	void addPoint(double x, double y);
	
	/**
	 * Calculate the convex hull from the points given.
	 */
	void calculate();
	
	IConvexHull getConvexHull();
	
	List<Point> getOriginalPoints();

	void addArc(double x, double y, double width, double height, double offset, double length);

	void addLine(double startX, double startY, double endX, double endY);

	void addOval(double x, double y, double width, double height);

	void addPolygon(double[] pointArr);

	void addPolyline(double[] pointArr);

	void addRectangle(double x, double y, double width, double height);

	void addRoundRectangle(double x, double y, double width,	double height, double arcWidth, double arcHeight);
}
