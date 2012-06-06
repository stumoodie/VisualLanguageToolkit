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

import java.util.Iterator;
import java.util.List;

/**
 * 
 * IConvexHull in an interface that defines a convex hull. A convex hull is a polygon in which all its edges form a
 * convex shape {@link <a href="http://en.wikipedia.org/wiki/Convex_hull">definition here</a>}. It is typically used
 * as an approximation for a shape since once can perform a number of optimisations when calculating such queries as
 * intersection and point containment. This interface provides methods for interrogating it
 * and performing geometric queries. Note that the interface is designed to support an implementation that is immutable.
 * 
 * A convex hull must have a minimum of 3 points to be valid.
 *
 * @author Stuart Moodie
 *
 */
public interface IConvexHull {

	/**
	 * Create a transformed convex hull so that the bounding box of the new hull corresponds to the
	 * bounding box provided 
	 * @param newEnvelope the bounding box to transform the node to.
	 * @return a new instance of a convex hull transformed to the new bounding box.
	 */
	IConvexHull changeEnvelope(Envelope newEnvelope);

	/**
	 * Tests if the point is contained within this hull? 
	 * @param p the point to test.
	 * @return true if the point is contained, false otherwise.
	 */
	boolean containsPoint(Point p);

	/**
	 * Calculate the area of the convex hull.
	 * @return the area of the convex hull.
	 */
	double getArea();

	/**
	 * Gets the centre point of the hull. This is calculated as the centre of gravity of the
	 *  all the points making up the hull, therefore it is not necessarily the centre of the hull's
	 *  bounding box. 
	 * @return the centre point of the hull.
	 */
	Point getCentre();

	/**
	 * Get the bounding box of this hull.
	 * @return the bounding box of this hull.
	 */
	Envelope getEnvelope();
	
	/**
	 * Gets the line segments that define the edges of this convex hull.
	 * @return the lines defining the edges of this convex hull. The list should be a modifiable copy.
	 */
	List<LineSegment> getLines();

	/**
	 * Calculates the point on the boundary of this convex hull that intersects a line drawn between the reference
	 * point and the centre of the hull (as defined by {@link #getCentre()}. If the reference point is inside the
	 * hull then an arbitrary point is chosen.
	 * @param reference the reference point that should not be null.
	 * @return the point of intersection.
	 */
	Point getPointLineIntersects(Point reference);
	
	/**
	 * The list of points that define this hull. 
	 * @return the list of points, which cannot be null, not empty. The list should be modifiable copy.
	 */
	List<Point> getPoints();
	
	/**
	 * Tests if this hull intersects another hull.
	 * @param otherHull the other hull to test.
	 * @return true if the hulls do interect, false otherwise.
	 */
	boolean hullsIntersect(IConvexHull otherHull);
	
	/**
	 * Get an iterator over all the points in this convex hull. 
	 * @return a new iterator for the list of points.
	 * @deprecated use {@link #pointIterator()} instead. 
	 */
	@Deprecated
	Iterator<Point> iterator();
	
	/**
	 * Get the number of points in this hull.
	 * @return the number of points.
	 */
	int numPoints();
	
	/**
	 * Get an iterator over all the points in this convex hull. 
	 * @return a new iterator for the list of points.
	 */
	Iterator<Point> pointIterator();
	
	/**
	 * Creates a new convex hull resized to the size specified by a dimension. 
	 * @param newDim the new dimension, which should not be null.
	 * @return a new convex hull scaled to the new size.
	 */
	IConvexHull scale(Dimension newDim);

	/**
	 * Creates a new convex hull resized by the new scaling factors.
	 * @param scaleX the x scaling factor
	 * @param scaleY the y scaling factor
	 * @return a new convex hull scaled by the given scaling factors.
	 */
	IConvexHull scale(double scaleX, double scaleY);

	/**
	 * Creates a new convex hull that has been translated by the displacement specified.
	 * @param x the x displacement.
	 * @param y the y displacement.
	 * @return a new convex hull translated by the x and y displacements.
	 */
	IConvexHull translate(double x, double y);
	
	/**
	 * Creates a new convex hull that has been translated by the displacement specified.
	 * @param p the displacement expressed as a point.
	 * @return a new convex hull translated by the given displacement.
	 */
	IConvexHull translate(Point p);

	/**
	 * Tests if line intersects this hull.
	 * @param line the line, which can be null.
	 * @return true if line intersects hull, false otherwise.
	 */
	boolean hullIntersectsLine(LineSegment line);
}