/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.geometry;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * ConvexHullBuilder is a class that builds a convex hull. This class should be used by clients to construct an implementation
 * of <code>IConvexHull</code> as it ensures that the hull implementation can be immutable and so mnore easily passed by reference
 * without side-effects. 
 * 
 * @author Stuart Moodie
 *
 */
public class ConvexHullBuilder {
	private static final int SECOND_ELEMENT = 1;
	private final LinkedList<PointBuilder> pointList;
	
	/**
	 * Default constructor
	 */
	public ConvexHullBuilder(){
		this.pointList = new LinkedList<PointBuilder>();
	}

	/**
	 * Adds a point to the builder.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return this instance, which allows this method to be daisy chained for convenience.
	 */
	public ConvexHullBuilder addPoint(double x, double y){
		this.pointList.add(new PointBuilder(x, y));
		return this;
	}
	
	/**
	 * Adds a point to the builder.
	 * @param p the point to add, which should not be null.
	 * @return this instance, which allows this method to be daisy chained for convenience.
	 */
	public ConvexHullBuilder addPoint(Point p){
		this.pointList.add(new PointBuilder(p));
		return this;
	}
	
	/**
	 * Calculate the bounding box of the convex hull as defined by the point sos far added.
	 * @return the bounding box of the convex hull, which cannot be null. 
	 */
	public Envelope calcCurrEnvelope(){
		double minX = this.pointList.getFirst().getX();
		double minY = this.pointList.getFirst().getY();
		double maxX = minX;
		double maxY = minY;
		Iterator<PointBuilder> iter = this.pointList.listIterator(SECOND_ELEMENT);
		while(iter.hasNext()){
			PointBuilder p = iter.next();
			minX = Math.min(minX, p.getX());
			minY = Math.min(minY, p.getY());
			maxX = Math.max(maxX, p.getX());
			maxY = Math.max(maxY, p.getY());
		}
		Point origin = new Point(minX, minY);
		Dimension dim = new Dimension(maxX - minX, maxY - minY);
		return new Envelope(origin, dim);
	}
	
	/**
	 * Applies a new bounding box to the convex hull by moving its origin to the origin of the new envelope and
	 * scaling the points to fit its new height and width.
	 * @param newEnvelope the new bounding box, which can be null.
	 * @return this instance to enable daisy chaining of methods.
	 */
	public ConvexHullBuilder changeEnvelope(Envelope newEnvelope){
		Envelope currEnv = calcCurrEnvelope();
		double xOrig = currEnv.getOrigin().getX();
		double yOrig = currEnv.getOrigin().getY();
		double scaleX = newEnvelope.getDimension().getWidth()/currEnv.getDimension().getWidth();
		double scaleY = newEnvelope.getDimension().getHeight()/currEnv.getDimension().getHeight();
		for(PointBuilder p : this.pointList){
			p.translate(-xOrig, -yOrig).scale(scaleX, scaleY)
				.translate(newEnvelope.getOrigin().getX(), newEnvelope.getOrigin().getY());
//			double scaledX = newEnvelope.getOrigin().getX() + ((p.getX() - xOrig) * scaleX); 
//			double scaledY = newEnvelope.getOrigin().getY() + ((p.getY() - yOrig) * scaleY); 
		}
		return this;
	}

	/**
	 * Gets the convex hull constructed by this builder. Note that the convex hull implementation
	 * returned will be immutable.
	 * @return the newly constructed convex hull.
	 */
	public IConvexHull getConvexHull(){
		return new ConvexHull(calcCurrEnvelope(), this.pointList);
	}
}
