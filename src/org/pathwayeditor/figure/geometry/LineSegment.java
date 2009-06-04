package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class LineSegment {
	private final Logger logger = Logger.getLogger(this.getClass());
	private final Point origin;
	private final Point terminus;

	
	public LineSegment(Point origin, Point terminus){
		this.origin = origin;
		this.terminus = terminus;
	}
	
	/**
	 * Determines the intersect point between this line and the line passed 
	 * in as a parameter.  If they intersect, then true is returned and the 
	 * point reference passed in will be set to the intersect point.  
	 * If they don't intersect, then the method returns <code>false</code>.
	 * 
	 * @param line <code>MyLine</code> to test the intersection against.
	 * @param nTolerance int tolerance value for detecting the intersection.
	 * @return <code>Point</code> that represents the intersection with this line, 
	 * or <code>null</code> if the calculation is not possible.
	 */
	public Point intersect(final LineSegment line, final double nTolerance) {
		logger.debug("Looking for intersection between line this=" + this + ",other=" + line);
		List<Point> intersections = getLinesIntersections(line);
		if (intersections.size()>1) {
			intersections.add(getOrigin());
			intersections.add(getTerminus());
		}
		Point retVal = null;
		for (int i=0; i<intersections.size() && retVal == null; i++) {
			Point result = intersections.get(i);
			logger.trace("Found intersection at:" + result);
			if (containsPoint(result, nTolerance)
					&& line.containsPoint(result, nTolerance)) {
					logger.trace("Intersection point accepted: within line limits");
					retVal = result;
			}
		}
		logger.debug("Intersection point found=" + retVal);
		return retVal;
	}

	/**
	 * Checks if this line segment contains the given point within a tolerance value.
	 * 
	 * @param aPoint <code>Point</code> to test if contained in this line.
	 * @param tolerance int tolerance value for detecting the intersection.
	 * 
	 * @return <code>boolean</code> <code>true</code> if the given point lies on this 
	 * segment, <code>false</code> otherwise.
	 */
	public final boolean containsPoint(final Point aPoint, final double tolerance) {
		Point theOrigin = getOrigin();
		Point theTerminus = getTerminus();

		double diff = theOrigin.getDistance(aPoint) + aPoint.getDistance(theTerminus) - length();
		return Math.abs(diff) < tolerance;
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
	private List<Point> getLinesIntersections (LineSegment line) {
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
			intersections.add(new Point(Math.round((c1*b2-b1*c2)/det), Math.round((a1*c2-c1*a2)/det)));
		}
		return intersections;
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

	public Point getOrigin() {
		return origin;
	}

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
