/**
 * 
 */
package org.pathwayeditor.figure.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author smoodie
 *
 */
public class PointList {
	private final List<Point> points;
	
	public PointList(List<Point> points){
		this.points = new ArrayList<Point>(points);
	}
	
	private PointList(int arrSize){
		this.points = new ArrayList<Point>(arrSize);
	}
	
	public static PointList createFromDoubles(List<Double> pointVals){
		if((pointVals.size() % 2) != 0) throw new IllegalArgumentException("There must be an even number of point values");
		
		PointList retVal = new PointList(pointVals.size()/2);
		for(int i = 0; i < pointVals.size();){
			Point p = new Point(pointVals.get(i++), pointVals.get(i++));
			retVal.points.add(p);
		}
		return retVal;
	}

	public static PointList createFromDoubleArray(double[] pointArr){
		if((pointArr.length % 2) != 0) throw new IllegalArgumentException("There must be an even number of point values");
		
		PointList retVal = new PointList(pointArr.length/2);
		for(int i = 0; i < pointArr.length;){
			Point p = new Point(pointArr[i++], pointArr[i++]);
			retVal.points.add(p);
		}
		return retVal;
	}

	public boolean contains(Object o) {
		return this.points.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return this.points.containsAll(c);
	}

	@Override
	public boolean equals(Object o) {
		return this.points.equals(o);
	}

	public Point get(int index) {
		return this.points.get(index);
	}

	@Override
	public int hashCode() {
		return this.points.hashCode();
	}

	public int indexOf(Object o) {
		return this.points.indexOf(o);
	}

	public boolean isEmpty() {
		return this.points.isEmpty();
	}

	public Iterator<Point> iterator() {
		return this.points.iterator();
	}

	public int lastIndexOf(Object o) {
		return this.points.lastIndexOf(o);
	}

	public ListIterator<Point> listIterator() {
		return this.points.listIterator();
	}

	public ListIterator<Point> listIterator(int index) {
		return this.points.listIterator(index);
	}

	public int size() {
		return this.points.size();
	}

	public List<Point> subList(int fromIndex, int toIndex) {
		return this.points.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return this.points.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return this.points.toArray(a);
	}
	
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

	public Point nearestPointTo(double x, double y){
		return nearestPointTo(new Point(x, y));
	}
	
	@Override
	public String toString(){
		return this.points.toString();
	}
}
