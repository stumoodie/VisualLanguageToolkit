/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package org.pathwayeditor.businessobjects.graphics;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;



/**
 * Equivalent to a draw2d rectangle, used for convenience bounds calculations on model.
 * These are just value objects whose location and Size components can only be set
 * through the constructor. The location is of the top left-hand corner of the rectangle
 * that represents the bounds. The coordinate system is flipped along the x-axis so that
 * the y-axis points downwards.
 * 
 * @author Richard Adams/Stuart Moodie
 *
 */
public class Bounds implements Comparable<Bounds>{
	private final Location location;
	private final Size size;
	
	public Bounds (int x, int y, int width, int height) {
		this(new Location(x,y), new Size(width, height));
	}
	public Bounds(Location location, Size size) {
		super();
		this.location = location;
		this.size = size;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Size getSize() {
		return size;
	}
	/**
	 * Shrinks bounds, keeps centre point the same.
	 * @param x Pixels to shrink width
	 * @param y Pixels to shrink height
	 * @return This {@link Bounds} object.
	 */
	public Bounds shrink( int x, int y){
		Location newLocation = new Location(location.getX() + x, location.getY() + y);
		Size newSize = new Size(size.getWidth() - x - x, size.getHeight() - y - y);
		return new Bounds(newLocation, newSize);
	}
	
	
	/**
	 * Expands bounds, keeps centre point the same.
	 * @param x Pixels to expand width
	 * @param y Pixels to expand height
	 * @return This {@link Bounds} object.
	 */
	public Bounds expand( int x, int y){
		return shrink(-x, -y);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(");
		builder.append("location=");
		builder.append(location);
		builder.append(", size=");
		builder.append(size);
		builder.append(")");
		return builder.toString(); 
	}
	
	public int getWidth () {
		return size.getWidth();
	}
	
	public int getHeight () {
		return size.getHeight();
	}

	public int getX(){
		return location.getX();
	}
	
	public int getY() {
		return location.getY();
	
	}
	
	public Location getTopLeft (){
		return location;
	}
	public Location getTopRight (){
		return new Location(getX()+getWidth(), getY());
	}
	public Location getBottomRight (){
		return new Location(getX()-getWidth(), getY()+getHeight());
	}
	
	public Location getBottomLeft (){
		return new Location(getX()-getWidth(), getY());
	}
	/**
	 * Uses same algorithm as draw2d rectangle
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return This Bounds object for convenience
	 */
	public Bounds union(int x, int y, int w, int h) {
		int right = Math.max(this.getX() + getWidth(), x + w);
		int bottom = Math.max(this.getY() + getHeight(), y + h);
		Location newLocation = new Location( Math.min(this.getX(), x), Math.min(this.getY(), y));
		Size newSize = new Size( right - getX(), bottom - getY());
		return new Bounds(newLocation, newSize);
	}
	

	
	public Bounds union(Bounds b) {
		return union(b.getX(), b.getY(), b.getWidth(), b.getHeight());
	}
	/**
	 * 
	 * @return A copy of this Bounds, translated by the Location
	 */
	public Bounds translate (Location loc) {
		return new Bounds (getLocation().translate(loc), getSize());
	}
	
	public double getArea (){
		return size.getHeight() * size.getWidth();
	}
	
	/**
	 * getCentre
	 * @return the central location of these bounds.
	 */
	public Location getCentre() {
		return new Location  ( (int)(getX() + (0.5 * getWidth())), 
				(int)(getY() + (0.5 * getHeight())) );
	}
	/**
	 * Sorts on basis of bounded area, will sort in increasing size order
	 */
	public int compareTo(Bounds o) {
		if (Math.abs(o.getArea() - this.getArea()) <0.001 ){
			return 0;
		}
	    else if(o.getArea() < this.getArea()){
			return 1;
		} else {
			return -1;
		}
	}
	/**
	 * 
	 * @param other
	 * @return <code>true</code> if the parameter bounds are wholly contained by this bounds
	 * For two equals bounds, will return false
	 */
	public boolean contains (Bounds other){
	  if(size.getWidth() + location.getX() > other.getWidth() + other.getLocation().getX()&&
			  size.getHeight() + location.getY() > other.getHeight() + other.getLocation().getY()&&
	     location.getX()< other.getLocation().getX() &&
	     location.getY() < other.getLocation().getY()){
		  return true;
	  }
	  return false;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.location == null) ? 0 : this.location.hashCode());
		result = prime * result + ((this.size == null) ? 0 : this.size.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bounds))
			return false;
		Bounds other = (Bounds) obj;
		if (this.location == null) {
			if (other.location != null)
				return false;
		} else if (!this.location.equals(other.location))
			return false;
		if (this.size == null) {
			if (other.size != null)
				return false;
		} else if (!this.size.equals(other.size))
			return false;
		return true;
	}
}
