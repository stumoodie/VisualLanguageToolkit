package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class LineSegment {
	private static final double LINE_WIDTH_TOL = 0.1;
	private final Logger logger = Logger.getLogger(this.getClass());
	private final Point origin;
	private final Point terminus;

	
	public LineSegment(Point origin, Point terminus){
		this.origin = origin;
		this.terminus = terminus;
	}
	
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
			if (containsPoint(result, nTolerance)
					&& line.containsPoint(result, nTolerance)) {
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
	 * Checks if this line segment contains the given point within a tolerance value.
	 * 
	 * @param aPoint <code>Point</code> to test if contained in this line.
	 * @param lineWidth the width of the line to be tested. The thickness of the line is 
	 * considered as part of the line for the pusposes of containment.
	 * @return <code>boolean</code> <code>true</code> if the given point lies on this 
	 * segment, <code>false</code> otherwise.
	 */
	public final boolean containsPoint(final Point aPoint, final double lineWidth) {
		Point theOrigin = getOrigin();
		Point theTerminus = getTerminus();
		double x1 = theTerminus.getX() - theOrigin.getX();
		double y1 = theTerminus.getY()-theOrigin.getY();
		double x2 = aPoint.getX() - theOrigin.getX();
		double y2 = aPoint.getY() - theOrigin.getY();
		// Calc if on the line by getting the cross-product of the vector of the line and the vector
		// from the origin to the point.
		double v_xy_cross_v_xp = (x1 * y2)	- (y1 * x2);
		double v1 = theOrigin.getDistance(theTerminus);
		double v2 = theOrigin.getDistance(aPoint);
		// sine theta from cross-product
		double sinTheta = v_xy_cross_v_xp / (v1 * v2);
		// cos theta from scalar product
		double cosTheta = (x1 * x2 + y1 * y2) / (v1 * v2);
		// if both +ve then in 1st quadrant and so we should check tolerane
		// otherwise there cannot be an intersection
		boolean retVal = false;
//		if(sinTheta >= 0.0 && cosTheta >= 0.0){
		if(cosTheta >= 0.0){
			// now check the length of the opposite side of the RHT and see if it is within the tolerance length
			double adj = v2 * cosTheta;
			// check adj length is less than length of line - i.e. is the point on the line
			if(adj <= v1){
				double opp = v2 * sinTheta;
				double halfLineHeight = (lineWidth/2);
				halfLineHeight += halfLineHeight * LINE_WIDTH_TOL;
				retVal = opp < halfLineHeight;
			}
		}
		if(logger.isTraceEnabled()){
			logger.trace("containsPoint=" + retVal + ",point=" + aPoint + ",start=" + theOrigin + ",end=" + theTerminus + ",sinTheta=" + sinTheta + ",cosTheta=" + cosTheta
						+ "lineWidth=" + lineWidth);
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

	public Point getMidPoint(){
		return new Point(this.origin.getX() + getXDisplacement()/2, this.origin.getY() + this.getYDisplacement()/2);
	}
	
	public double getXDisplacement(){
		return terminus.getX() - origin.getX();
	}
	
	public double getYDisplacement(){
		return terminus.getY() - origin.getY();
	}
	
	public double getLength(){
		double i = terminus.getX() - origin.getX();
		double j = terminus.getY() - origin.getY();
		return Math.sqrt(i * i + j * j);
	}
	
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
