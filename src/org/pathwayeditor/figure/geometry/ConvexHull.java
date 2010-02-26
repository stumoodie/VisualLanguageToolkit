package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class ConvexHull implements IConvexHull {
	private static final int FIRST_IDX_POS = 0;
	private static final int MIN_NUM_POINTS = 3;

	private final Logger logger = Logger.getLogger(this.getClass());
	private final List<Point> pointList;
	private final Envelope envelope;
	
	ConvexHull(List<Point> localPointList){
		if(localPointList.size() < MIN_NUM_POINTS) throw new IllegalArgumentException("Cannot define hull with less than " + MIN_NUM_POINTS + " points.");
		this.pointList = new ArrayList<Point>(localPointList.size());
		Point firstPoint = localPointList.get(FIRST_IDX_POS);
		this.pointList.add(firstPoint);
		double minX = firstPoint.getX();
		double minY = firstPoint.getY();
		double maxX = firstPoint.getX();
		double maxY = firstPoint.getY();
		for(int i = FIRST_IDX_POS+1; i < localPointList.size(); i++){
			Point p = localPointList.get(i);
			this.pointList.add(p);
			minX = Math.min(minX, p.getX());
			minY = Math.min(minY, p.getY());
			maxX = Math.max(maxX, p.getX());
			maxY = Math.max(maxY, p.getY());
		}
		Point origin = new Point(minX, minY);
		Dimension dim = new Dimension(Math.abs(maxX - minX), Math.abs(maxY - minY));
		this.envelope = new Envelope(origin, dim);
	}
	
		
	ConvexHull(Envelope env, List<PointBuilder> localPointList){
		if(localPointList.size() < MIN_NUM_POINTS) throw new IllegalArgumentException("Cannot define hull with less than " + MIN_NUM_POINTS + " points.");
		this.pointList = new ArrayList<Point>(localPointList.size());
		for(PointBuilder p : localPointList){
			this.pointList.add(p.getPoint());
		}
		this.envelope = env;
	}
		
		//	public ConvexHull(){
//		this(EMPTY_LIST);
//	}
	

	public Point getOrigin(){
		return this.envelope.getOrigin();
	}
	
	
	public Dimension getDimension(){
		return this.envelope.getDimension();
	}
	
	public Envelope getEnvelope(){
		return this.envelope;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.customshape.IConvexHull#iterator()
	 */
	public Iterator<Point> iterator(){
		return this.pointList.iterator();
	}
	
//	public boolean containsPoint(Point p){
//		return this.containsPoint(p.getX(), p.getY());
//	}

	public boolean containsPoint(Point p){
		boolean retVal = false;
		if(logger.isDebugEnabled()){
			logger.debug("testing point: x=" + p.getX() + ",y=" + p.getY());
		}
		if(this.envelope.containsPoint(p)){
			logger.trace("inside envelope");
			retVal = true;
			// check cross product has -k vector if not then the point must be outside polygon
			Point lastPoint = this.pointList.get(this.pointList.size()-1);
			for(int i = 0 ; i < this.pointList.size() && retVal; i++){
				Point currPoint = this.pointList.get(i);
				if(logger.isTraceEnabled()){
					logger.trace("Next point: Last point=" + lastPoint + ",curr point=" + currPoint);
				}	
				Vector edge = new Vector(currPoint.getX() - lastPoint.getX(), currPoint.getY() - lastPoint.getY(), 0);
				if(logger.isTraceEnabled()){
					logger.trace("Examining edge=" + edge);
				}
				Vector pointVect = new Vector(p.getX() - lastPoint.getX(), p.getY() - lastPoint.getY(), 0);
				if(logger.isTraceEnabled()){
					logger.trace("Examining pointVect=" + pointVect);
				}
				Vector crossProd = edge.crossProduct(pointVect);
				if(logger.isTraceEnabled()){
					logger.trace("Crossproduct=" + crossProd);
				}
				if(crossProd.getKMagnitude() < 0){
					if(logger.isTraceEnabled()){
						logger.trace("Crossproduct is negative so not contained");
					}
					retVal = false;
				}
				lastPoint = currPoint;
			}
		}
		if(logger.isTraceEnabled()){
			logger.trace("Contains point=" + retVal);
		}
		return retVal;
	}
	
//	private double crossProduct(double ax, double ay, double bx, double by, double cx, double cy) {
//		return (ax - cx) * (by - cy) - (ay - cy) * (bx - cx);
//	}

	public IConvexHull changeEnvelope(Envelope newEnvelope){
		IConvexHull retVal = this;
 		if(!this.envelope.equals(newEnvelope)){
 			double xOrig = this.getOrigin().getX();
 			double yOrig = this.getOrigin().getY();
			double scaleX = newEnvelope.getDimension().getWidth()/this.getDimension().getWidth();
			double scaleY = newEnvelope.getDimension().getHeight()/this.getDimension().getHeight();
			List<Point> newList = new ArrayList<Point>(this.pointList.size());
			for(Point p : this.pointList){
				double scaledX = newEnvelope.getOrigin().getX() + ((p.getX() - xOrig) * scaleX); 
				double scaledY = newEnvelope.getOrigin().getY() + ((p.getY() - yOrig) * scaleY); 
				newList.add(new Point(scaledX, scaledY));
			}
			retVal = new ConvexHull(newList);
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.customshape.IConvexHull#hullsIntersect(org.pathwayeditor.figure.customshape.IConvexHull)
	 */
	public boolean hullsIntersect(IConvexHull otherHull){
		boolean retVal = false;
		if(this.envelope.intersects(otherHull.getEnvelope())){
			// check if hulls overlap at all
			retVal = this.testIntersection(otherHull);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Hulls intersect=" + retVal + ", this=" + this + ", other=" + otherHull);
		}
		return retVal;
	}
	
	private boolean testIntersection(IConvexHull otherHull) {
		boolean retVal = testLines(this.getLines(), otherHull) && testLines(otherHull.getLines(), otherHull);
		return retVal;
	}

	
	private boolean testLines(List<LineSegment> lines, IConvexHull otherHull){
		boolean retVal = true;
		if(logger.isTraceEnabled()){
			logger.trace("Testing lines");
			logger.trace("Comparing with hull=" + otherHull);
		}
		for (LineSegment line : lines) {
			if(retVal){
				double min0 = Double.POSITIVE_INFINITY;
				double max0 = Double.NEGATIVE_INFINITY;
				double min1 = Double.POSITIVE_INFINITY;
				double max1 = Double.NEGATIVE_INFINITY;
				if(logger.isDebugEnabled()){
					logger.debug("Looking at line:" + line);
				}
				Vector originalOuterNormal = line.getRightHandNormal(); 
				Vector outerNormal = originalOuterNormal.unitVector();
				if(logger.isTraceEnabled()){
					logger.trace("testLines: normal=" + outerNormal);
				}
				if(logger.isDebugEnabled()){
					logger.debug("Testing this hull");
				}
				for(Point p : this.pointList){
					double projection = outerNormal.scalarProduct(new Vector(p.getX(), p.getY(), 0));
					if(logger.isTraceEnabled()){
						logger.trace("testLines: projection=" + projection + ", for p=" + p);
					}
					min0 = Math.min(min0, projection);
					max0 = Math.max(max0, projection);
				}
				if(logger.isTraceEnabled()){
					logger.trace("testLines: before offset: min0=" + min0 + ",max0=" + max0);
				}
//				double offset = thisHullCentre.getX() * outerNormal.getIMagnitude() + thisHullCentre.getY() * outerNormal.getJMagnitude();
//				min0 += offset;
//				max0 += offset;
				logger.debug("Testing other hull");
				Iterator<Point> otherHullPointIter = otherHull.pointIterator();
				while(otherHullPointIter.hasNext()){
					Point p = otherHullPointIter.next();
					double projection = outerNormal.scalarProduct(new Vector(p.getX(), p.getY(), 0));
					if(logger.isTraceEnabled()){
						logger.trace("testLines: projection=" + projection + ", for p=" + p);
					}
					min1 = Math.min(min1, projection);
					max1 = Math.max(max1, projection);
				}
				if(logger.isTraceEnabled()){
					logger.trace("testLines: before offset: min1=" + min1 + ",max1=" + max1);
				}
//				offset = otherHullCentre.getX() * outerNormal.getIMagnitude() + otherHullCentre.getY() * outerNormal.getJMagnitude();
//				min1 += offset;
//				max1 += offset;
				if(logger.isDebugEnabled()){
					logger.debug("testLines: min0=" + min0 + ",max0=" + max0 + ",min1=" + min1 + ",max1=" + max1);
				}
				if(max0 < min1 || min0 > max1){
					logger.debug("testLines: no intersection");
					retVal = false;
				}
			}
			else{
				break;
			}
		}
		return retVal;
	}
	
//	private boolean testIntersection(IConvexHull otherHull){
//		boolean retVal = false;
//		for(LineSegment parentLs: this.getLines()){
//			if(retVal) break;
//			for(LineSegment childLS: otherHull.getLines()){
//				if(parentLs.intersect(childLS, INTERSECT_TOLERANCE) !=null){
//					retVal = true;
//					break;
//				}
//			}
//		}
//		return retVal;
//	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.customshape.IConvexHull#getLines()
	 */
	public List<LineSegment> getLines() {
		List<LineSegment>rc = new ArrayList<LineSegment>();
		Point previous = this.pointList.get(this.pointList.size()-1);
		for(int i = 0; i < this.pointList.size(); i++) {
			Point curr = this.pointList.get(i);
			rc.add(new LineSegment(previous, curr));
			previous = curr;
		}
		return rc;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.customshape.IConvexHull#getPoints()
	 */
	public List<Point> getPoints() {
		return new ArrayList<Point>(this.pointList);
	}

	public Iterator<Point> pointIterator(){
		return this.pointList.iterator();
	}

	public IConvexHull translate(double d, double e) {
		List<Point> retVal = new ArrayList<Point>(this.pointList.size()); 
		for(Point point : this.pointList){
			retVal.add(point.translate(d, e));
		}
		return new ConvexHull(retVal);
	}

	public IConvexHull scale(double xScale, double yScale){
		Point origin = this.getOrigin();
		List<Point> retVal = new ArrayList<Point>(this.pointList.size()); 
		for(Point p : this.pointList){
			Point adjusted = p.translate(origin.negate());
			double scaledX = adjusted.getX() * xScale;
			double scaledY = adjusted.getY() * yScale;
			Point scaledPoint = origin.translate(scaledX, scaledY);
			retVal.add(scaledPoint);
		}
		return new ConvexHull(retVal);
	}
	
	public IConvexHull scale(Dimension newDim){
		Dimension currDim = this.getDimension();
		double scaleX = newDim.getWidth()/currDim.getWidth();
		double scaleY = newDim.getHeight()/currDim.getHeight();
		return this.scale(scaleX, scaleY);
	}
	
	
	public Point getPointLineIntersects(double refX, double refY){
		return this.getPointLineIntersects(new Point(refX, refY));
	}
	
    public Point getPointLineIntersects(Point reference) {
        Point c = getCentre();
        Point p;
        // this assumes that the point is outside the hull
        // if it is not then we need to set a point on the boundary of the hull
        if(this.containsPoint(reference)){
        	reference = this.getOrigin().translate(-1.0, -1.0);
        }
        double distance = reference.getDistance(c);
        int n = this.pointList.size();

        if (n > 1) { //If there are at least 2 Points (4 ints)

            double x0 = this.pointList.get(n-1).getX(),
                y0 = this.pointList.get(n-1).getY(),
                x1,
                y1,
                x2 = reference.getX(),
                y2 = reference.getY(),
                x3 = c.getX(),
                y3 = c.getY();

            double dx10, dy10, dx32, dy32, dx02, dy02;

            for (int i = 0; i < n; x0 = x1, y0 = y1, i++) {

                x1 = this.pointList.get(i).getX();
                y1 = this.pointList.get(i).getY();

                dy10 = y1 - y0;
                dx10 = x1 - x0;
                dy32 = y3 - y2;
                dx32 = x3 - x2;
                double d = dx10 * dy32 - dy10 * dx32;
                if (d != 0) {
                    dx02 = x0 - x2;
                    dy02 = y0 - y2;
                    double d1 = dy02 * dx32 - dx02 * dy32,
                        d2 = dy02 * dx10 - dx02 * dy10;
                    double k1 = (double) d1 / d, k2 = (double) d2 / d;
                    if (k1 >= -0.000001f
                        && k1 <= 1.000001f
                        && k2 >= -0.000001f
                        && k2 <= 1.000001f) {

                        p = new Point(x0 + (int) (dx10 * k1), y0 + (int) (dy10 * k1));
                        double pdist = reference.getDistance(p);

                        if (pdist < distance) {
                            c = p;
                            distance = pdist;
                        }
                    }
                }
            }
        }
        return c;
    }

    public double getArea(){
		int i, j, n = this.pointList.size();
		double area = 0;

		for (i = 0; i < n; i++) {
			j = (i + 1) % n;
			area += this.pointList.get(i).getX() * this.pointList.get(j).getY();
			area -= this.pointList.get(j).getX() * this.pointList.get(i).getY();
		}
		area /= 2.0;
		return (area);
    }
    
    public Point getCentre() {
		double cx = 0, cy = 0;
		double area = getArea();
		int n = this.pointList.size();

		double factor = 0;
		for (int i = 0; i < n; i++) {
			int j = (i + 1) % n;
			factor = (this.pointList.get(i).getX() * this.pointList.get(j).getY()
					- this.pointList.get(j).getX() * this.pointList.get(i).getY());
			cx += (this.pointList.get(i).getX() + this.pointList.get(j).getX()) * factor;
			cy += (this.pointList.get(i).getY() + this.pointList.get(j).getY()) * factor;
		}
		area *= 6.0;
		factor = 1 / area;
		cx *= factor;
		cy *= factor;
		return new Point(cx, cy);
    }
    
    @Override
    public String toString(){
    	StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
    	builder.append("(env=");
    	builder.append(this.envelope);
    	builder.append(")");
    	return builder.toString();
    }


	public int numPoints() {
		return this.pointList.size();
	}

	public IConvexHull translate(Point p) {
		return this.translate(p.getX(), p.getY());
	}
}
