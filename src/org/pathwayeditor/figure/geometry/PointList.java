/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * PointList is a class that provides access to a list of points. It is essentially an immutable
 * version of a list and is provided for efficiency to permit lists of points to be passed around 
 * by reference without breaking encapsulation. 
 * 
 * @author Stuart Moodie
 *
 */
public class PointList {
	
	/**
	 * Creates a point list from an array of doubles, which contains an alternating list of x and y coordinates.
	 * @param pointArr the array of points, which must contain an even number of points.
	 * @return the newly created point list.
	 */
	public static PointList createFromDoubleArray(double[] pointArr){
		if((pointArr.length % 2) != 0) throw new IllegalArgumentException("There must be an even number of point values");
		
		PointList retVal = new PointList(pointArr.length/2);
		for(int i = 0; i < pointArr.length;){
			Point p = new Point(pointArr[i++], pointArr[i++]);
			retVal.points.add(p);
		}
		return retVal;
	}
	
	/**
	 * Creates a point list from a List of doubles, which contains an alternating list of x and y coordinates.
	 * @param pointVals the list of points, which must contain an even number of points.
	 * @return the newly created point list.
	 */
	public static PointList createFromDoubles(List<Double> pointVals){
		if((pointVals.size() % 2) != 0) throw new IllegalArgumentException("There must be an even number of point values");
		
		PointList retVal = new PointList(pointVals.size()/2);
		for(int i = 0; i < pointVals.size();){
			Point p = new Point(pointVals.get(i++), pointVals.get(i++));
			retVal.points.add(p);
		}
		return retVal;
	}
	
	private final List<Point> points;

	private PointList(int arrSize){
		this.points = new ArrayList<Point>(arrSize);
	}

	/**
	 * Constructor instance from a list of points.
	 * @param points the points list, which will be copied and should not be nul.
	 */
	public PointList(List<Point> points){
		this.points = new ArrayList<Point>(points);
	}

	/**
	 * Does the list contain this object.
	 * @param o the object to test.
	 * @return true if it does, false otherwise.
	 */
	public boolean contains(Object o) {
		return this.points.contains(o);
	}

	/**
	 * Does the list contain all the points in the collection?
	 * @param c the collection to test.
	 * @return true if all the objects in the collection are found in this list, false otherwise. 
	 */
	public boolean containsAll(Collection<?> c) {
		return this.points.containsAll(c);
	}

	@Override
	public boolean equals(Object o) {
		return this.points.equals(o);
	}

	/**
	 * Gets the point at the given position in the list (first element is 0).
	 * @param index the position in the list.
	 * @return the current element at the given index position.
	 * @throws IndexOutOfBoundsException if the index is outwith the range of values in the list.
	 */
	public Point get(int index) {
		return this.points.get(index);
	}

	@Override
	public int hashCode() {
		return this.points.hashCode();
	}

	/**
	 * Returns the first index matching this object. 
	 * @param o the object to match.
	 * @return the first index matching this object.
	 */
	public int indexOf(Object o) {
		return this.points.indexOf(o);
	}

	/**
	 * Tests is the list is empty.
	 * @return true if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.points.isEmpty();
	}

	/**
	 * Creates a new iterator for this list.
	 * @return the new iterator.
	 */
	public Iterator<Point> iterator() {
		return this.points.iterator();
	}

	/**
	 * Get the last index in the list that matches the given object.
	 * @param o the object to test.
	 * @return the last index of the given object. 
	 */
	public int lastIndexOf(Object o) {
		return this.points.lastIndexOf(o);
	}

	
	/**
	 * Creates a new list iterator for this list.
	 * @return a new list iterator.
	 */
	public ListIterator<Point> listIterator() {
		return this.points.listIterator();
	}

	
	/**
	 * Creates a new list iterator for this list start starts from the given index position.
	 * @return a new list iterator.
	 */
	public ListIterator<Point> listIterator(int index) {
		return this.points.listIterator(index);
	}

	/**
	 * Returns the point that is nearest to the specified coordinates. If more than one point could be matched then the
	 * first point is returned. 
	 * @param x the x coordinate of the test point.
	 * @param y the y coordinate of the test point.
	 * @return the point that is nearest the test coordinates.
	 */
	public Point nearestPointTo(double x, double y){
		return nearestPointTo(new Point(x, y));
	}

	/**
	 * Returns the point that is nearest to the specified point. If more than one point could be matched then the
	 * first point is returned. 
	 * @param testPoint the test point.
	 * @return the point that is nearest the test point.
	 */
	public Point nearestPointTo(Point testPoint){
		Point retVal = null;
		double shortedDistance = Double.POSITIVE_INFINITY;
		for(Point p : this.points){
			double refDist = p.getSqrDistance(testPoint);
			if(refDist < shortedDistance){
				shortedDistance = refDist;
				retVal = p;
			}
		}
		return retVal;
	}

	/**
	 * Gets the size of the list.
	 * @return the number of elements in the list.
	 */
	public int size() {
		return this.points.size();
	}


	@Override
	public String toString(){
		return this.points.toString();
	}
}
