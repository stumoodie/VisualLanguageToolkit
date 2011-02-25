/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.geometry;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Stuart Moodie
 *
 */
public class ConvexHullBuilder {
	private static final int SECOND_ELEMENT = 1;
	private final LinkedList<PointBuilder> pointList;
	
	public ConvexHullBuilder(){
		this.pointList = new LinkedList<PointBuilder>();
	}

	public ConvexHullBuilder addPoint(double x, double y){
		this.pointList.add(new PointBuilder(x, y));
		return this;
	}
	
	public ConvexHullBuilder addPoint(Point p){
		this.pointList.add(new PointBuilder(p));
		return this;
	}
	
	
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
	
	public ConvexHullBuilder changeEnvelope(Envelope newEnvelope){
		Envelope currEnv = calcCurrEnvelope();
		double xOrig = currEnv.getOrigin().getX();
		double yOrig = currEnv.getOrigin().getY();
		double scaleX = newEnvelope.getDimension().getWidth()/currEnv.getDimension().getWidth();
		double scaleY = newEnvelope.getDimension().getHeight()/currEnv.getDimension().getHeight();
		for(PointBuilder p : this.pointList){
			p.negatedTranslation(xOrig, yOrig).scale(scaleX, scaleY)
				.translate(newEnvelope.getOrigin().getX(), newEnvelope.getOrigin().getY());
//			double scaledX = newEnvelope.getOrigin().getX() + ((p.getX() - xOrig) * scaleX); 
//			double scaledY = newEnvelope.getOrigin().getY() + ((p.getY() - yOrig) * scaleY); 
		}
		return this;
	}

	public IConvexHull getConvexHull(){
		return new ConvexHull(calcCurrEnvelope(), this.pointList);
	}
}
