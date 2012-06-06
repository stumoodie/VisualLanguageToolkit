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
 * LineSegment is a class that represents a line segment and provides methods for querying and manipulating
 * a line segment. The class is immutable so can be passed around by reference without fear of breaking
 * encapsulation.
 *
 * @author Stuart Moodie
 *
 */
public class LineSegment {
	public static final double DOUBLE_ROUNDING_ERROR_TOL = 0.0001;
	private final Logger logger = Logger.getLogger(this.getClass());
	private final Point origin;
	private final Point terminus;

	/**
	 * Constructor that takes 2 ends points of the line segment.
	 * @param origin the origin or beginning of the line segment.
	 * @param terminus the end or terminus of the line segement.
	 */
	public LineSegment(Point origin, Point terminus){
		this.origin = origin;
		this.terminus = terminus;
	}
	
	/**
	 * Constructor that takes 2 end points of the line segment.
	 * @param startX the x coordinate of the beginning of the line.
	 * @param startY the y coordinate of the beginning of the line.
	 * @param endX the x coordinate of the end of the line.
	 * @param endY the y coordinate of the end of the line.
	 */
	public LineSegment(double startX, double startY, double endX, double endY){
		this.origin = new Point(startX, startY);
		this.terminus = new Point(endX, endY);
	}
	
	
	/**
	 * Create a new line segment starting at the current line's origin and with the same unit vector,
	 * but with a different magnitude.
	 * @param length the length of the new line segment, which must be <code>> 0</code>.
	 * @return the new line segment, which cannot be null
	 */
	public LineSegment newLineSegment(double length){
		if(length <= 0.0) throw new IllegalArgumentException("length must be a positive non-zero value");
	
		double theta = this.angle();
		double y = length * Math.sin(theta);
		double x = length * Math.cos(theta);
		
		return new LineSegment(this.origin, this.origin.translate(x, y));
	}
	
	/**
	 * The inverted line segment.
	 * @return return the reverse line segment to this one, with the origin and terminus swapped.
	 */
	public LineSegment invert(){
		return new LineSegment(this.terminus, this.origin);
	}
	
	public Point intersect(final LineSegment line) {
		return this.intersect(line, DOUBLE_ROUNDING_ERROR_TOL);
	}

		/**
	 * Determines the intersect point between this line and the line passed 
	 * in as a parameter.  If they intersect, then true is returned and the 
	 * point reference passed in will be set to the intersect point.  
	 * If they don't intersect, then the method returns <code>false</code>.
	 * 
	 * @param line <code>MyLine</code> to test the intersection against.
	 * @param nTolerance tolerance value for detecting the intersection.
	 * @return <code>Point</code> that represents the intersection with this line, 
	 * or <code>null</code> if the calculation is not possible.
	 */
	public Point intersect(final LineSegment line, double nTolerance) {
		if(logger.isDebugEnabled()){
			logger.debug("Looking for intersection between line this=" + this + ",other=" + line);
		}
		List<Point> intersections = getLinesIntersections(line);
		if (intersections.size()>1) {
			intersections.add(getOrigin());
			intersections.add(getTerminus());
		}
		Point retVal = null;
		for (int i=0; i<intersections.size() && retVal == null; i++) {
			Point result = intersections.get(i);
			if(logger.isTraceEnabled()){
				logger.trace("Found intersection at:" + result);
			}
			if(containsPoint(result) && line.containsPoint(result)) {
					logger.trace("Intersection point accepted: within line limits");
					retVal = result;
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("Intersection point found=" + retVal);
		}
		return retVal;
	}

	/**
	 * Tests if the line segment intersects with a circle. 
	 * @param centre the centre of the circle.
	 * @param radius the radius of the circle. 
	 * @return true of the circle intersects, false otherwise.
	 */
	public boolean intersectsWithCircle(Point centre, double radius){
		Point theOrigin = getOrigin();
		Point theTerminus = getTerminus();
		double x1 = theOrigin.getX();
		double y1 = theOrigin.getY();
		double x2 = theTerminus.getX();
		double y2 = theTerminus.getY();
		double x3 = centre.getX();
		double y3 = centre.getY();
		
		double a = pow2(x2-x1) + pow2(y2-y1);
		double b = 2*((x2-x1)*(x1-x3)+(y2-y1)*(y1-y3));
		double c = pow2(x3) + pow2(y3) + pow2(x1) + pow2(y1) - 2*(x3*x1+y3*y1) - pow2(radius);
		double determinant = pow2(b) - 4 * a * c;
		boolean retVal = true;
		if(determinant < 0){
			retVal = false;
		}
		return retVal;
	}
	
	
	private static double pow2(double value){
		return value * value;
	}
	
	/**
	 * Test is the line segment contains the point, i.e. does the point lie between the 2 points of the line segment.  
	 * @param aPoint the point to test.
	 * @return true if the point lies between the 2 points, false otherwise.
	 */
	public boolean containsPoint(Point aPoint){
		Point theOrigin = getOrigin();
		Point theTerminus = getTerminus();
		double x1 = theOrigin.getX();
		double y1 = theOrigin.getY();
		double x2 = theTerminus.getX();
		double y2 = theTerminus.getY();
		double x3 = aPoint.getX();
		double y3 = aPoint.getY();
		boolean retVal = false;
		// check first if the point can possibly lie between the 2 points of the line. 
		if(((x3 >= x1 && x3 <= x2) || (x3 >= x2 && x3 <= x1)) &&
				((y3 >= y1 && y3 <= y2) || (y3 >= y2 && y3 <= y1))){
			double det = Math.abs(x1*(y2 - y3) - y1*(x2 - x3) + (x2*y3) - (y2*x3));
			retVal = det < DOUBLE_ROUNDING_ERROR_TOL;
		}
		return retVal;
	}
	
	/**
	 * Calculate the length of the line segment.
	 * 
	 * @return the <code>double</code> length of the line segment.
	 */
	public final double length() {
		return getOrigin().getDistance(getTerminus());
	}

	/**
	 * Returns intersection points of two lines that contain this line segment and
	 * the argumet line segment.
	 * The list of intersection points may contain at most two points and will contain
	 * 2 points if and only if the lines are equal. The 2 points will be the end points
	 * of the parameter line segment 
	 * 
	 * @param line - the line segment
	 * @return intersection points of two lines that contain this line segment and
	 * the argumet line segment.
	 */
	public List<Point> getLinesIntersections (LineSegment line) {
		List<Point> intersections = new ArrayList<Point>();
		double temp[] = getEquation();
		double a1 = temp[0];
		double b1 = temp[1];
		double c1 = temp[2];

		temp = line.getEquation();
		double a2 = temp[0];
		double b2 = temp[1];
		double c2 = temp[2];
		// Cramer's rule for the system of linear equations
		double det = a1*b2 - b1*a2;
		if (det == 0) {
			if (a1==a2 && b1==b2 && c1==c2) {
				// if lines are the same, then instead of infinite number of intersections
				// we will put the end points of the line segment passed as an argument
				intersections.add(line.getOrigin());
				intersections.add(line.getTerminus());
			}
		}
		else {
			intersections.add(new Point((c1*b2-b1*c2)/det, (a1*c2-c1*a2)/det));
		}
		return intersections;
	}

	/**
	 * Creates a new line segment translated by the specified displacement. 
	 * @param p the point to test.
	 * @return a new translated line segment.
	 */
	public LineSegment translate(Point p){
		return new LineSegment(this.origin.translate(p), this.terminus.translate(p));
	}
	
	/**
	 * Get the angle of the vector of this line. Effectively the theta component of
	 * the polar coordinate of the end point of this line using the start point
	 * as the origin.
	 * @return the angle in radians which is in the range -pi to pi.
	 */
	public double angle(){
		return Math.atan2(this.getYDisplacement(), this.getXDisplacement());
	}
	
	/**
	 * Returns the coefficients of the generalized equation of the line passing through
	 * points (x1,y1) and (x2,y2)
	 * Generalized line equation: ax+by=c => a==result[0], b==result[1], c==result[2]
	 * 
	 * @param x1 - x coordinate of the 1st point
	 * @param y1 - y coordinate of the 1st point
	 * @param x2 - x coordinate of the 2nd point
	 * @param y2 - y coordinate of the 2nd point
	 * @return the coefficients of the generalized equation of the line passing through
	 * points (x1,y1) and (x2,y2)
	 */
	private static double [] getLineEquation(double x1, double y1, double x2, double y2) {
		double equation[] = new double[3];
		for (int i=0; i<3; i++)
			equation[i]=0;
		
		if (x1 == x2 && y1 == y2)
			return equation;
		
		if (x1 == x2) {
			equation[0]=1;
			equation[1]=0;
			equation[2]=x1;
			return equation;
		}
		
		equation[0]=(y1-y2)/(x2-x1);
		equation[1]=1.0;
		equation[2]=y2+equation[0]*x2;
		return equation;
	}

	/**
	 * Get the mid-point of the line segment, half way between the start and end of the line.  
	 * @return the mid point.
	 */
	public Point getMidPoint(){
		return new Point(this.origin.getX() + getXDisplacement()/2, this.origin.getY() + this.getYDisplacement()/2);
	}
	
	/**
	 * Gets the displacement from the origin to the terminus of this line segment in the x direction.  
	 * @return the x displacement.
	 */
	public double getXDisplacement(){
		return terminus.getX() - origin.getX();
	}
	
	/**
	 * Gets the displacement from the origin to the terminus of this line segment in the y direction.  
	 * @return the y displacement.
	 */
	public double getYDisplacement(){
		return terminus.getY() - origin.getY();
	}
	
	/**
	 * Gets the length of this line segment.
	 * @return the length, which must be >= 0.
	 */
	public double getLength(){
		double i = terminus.getX() - origin.getX();
		double j = terminus.getY() - origin.getY();
		return Math.sqrt(i * i + j * j);
	}
	
	/**
	 * Create a new vector corresponding to this line segment.
	 * @return the new line segment vector.
	 */
	public Vector getVector(){
		return new Vector(terminus.getX() - origin.getX(), terminus.getY() - origin.getY(), 0.0);
	}
	
	/**
	 * Gets the normal to this line on the left-hand-side of the line. This
	 * has the same magnitude as the line segment.
	 * @return the left hand normal of this line as a vector originating at the line's origin.
	 */
	public Vector getLeftHandNormal(){
		double i = terminus.getX() - origin.getX();
		double j = terminus.getY() - origin.getY();
		return new Vector(-j, i, 0);
	}

	/**
	 * Gets the normal to this line on the right-hand-side of the line. This
	 * has the same magnitude as the line segment.
	 * @return the right hand normal of this line as a vector originating at the line's origin.
	 */
	public Vector getRightHandNormal(){
		double i = terminus.getX() - origin.getX();
		double j = terminus.getY() - origin.getY();
		return new Vector(j, -i, 0);
	}

	/**
	 * Returns array with 3 numbers in it, which are the coefficients of the
	 * generalized line equation of the line corresponding to this line segment
	 * a*x+b*y=c is the equation => result[0]=a, result[1]=b, result[2]=c
	 * 
	 * @return an array with 3 numbers in it, which are the coefficients of the
	 * generalized line equation
	 */
	public double [] getEquation() {
		return getLineEquation(origin.getX(), origin.getY(), terminus.getX(), terminus.getY());
	}

	/**
	 * Gets the origin (start) of this line segment.
	 * @return the origin as a point.
	 */
	public Point getOrigin() {
		return origin;
	}

	/**
	 * Gets the terminus (end) of this line segment.
	 * @return the terminus as a point.
	 */
	public Point getTerminus() {
		return terminus;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(origin=");
		builder.append(this.origin);
		builder.append("terminus=");
		builder.append(this.terminus);
		builder.append(")");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result
				+ ((terminus == null) ? 0 : terminus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LineSegment))
			return false;
		LineSegment other = (LineSegment) obj;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (terminus == null) {
			if (other.terminus != null)
				return false;
		} else if (!terminus.equals(other.terminus))
			return false;
		return true;
	}
}
