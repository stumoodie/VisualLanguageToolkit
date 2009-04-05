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
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * @author smoodie
 *
 */
public final class Location {
	public static final Location ORIGIN = new Location(0, 0);
	
	private final int x;
	private final int y;
	
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Location other = (Location) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * Creates a new Location with a new x set and the y position of this location
	 * @param x the new x position.
	 * @return a new location, with the new x location.
	 */
	public Location newX(int x) {
		return new Location(x, this.y);
	}

	/**
	 * Creates a new Location with a new y set and the x position of this location
	 * @param y the new y position.
	 * @return a new location, with the new y location.
	 */
	public Location newY(int y) {
		return new Location(this.x, y);
	}
	
	public Location translate(Location translation) {
		return new Location(this.x + translation.x, this.y + translation.y);
	}

	public Location difference(Location otherLocation){
		return new Location(this.x - otherLocation.getX(), this.y - otherLocation.getY());
	}
	
	public Location negate() {
		return new Location(-this.x, -this.y);
	}
	
	public String toString(){
		StringBuilder retVal = new StringBuilder("Location(x=");
		retVal.append(x);
		retVal.append(", y=");
		retVal.append(y);
		retVal.append(")");
		return retVal.toString();
	}

	public Location translate(int i, int j) {
		return new Location(i, j);
	}

	/**
	 * Gets the difference between this location and the parameter location expressed as a <code>Size</code object.
	 * E..g., <code> new Location(20,20).getDifference (new Location(10,0))</code><br>
	 * will return a Size object width 10, height 20.
	 * @param loc2 
	 * @return A {@link Size}object
	 */
	public Size getDifference (Location loc2) {
		return new Size(getX() - loc2.getX(), getY() - loc2.getY());
	}
	
	/**
	 * Calculates the Euclidian distance between this object and the argument <code>Location</code>
	 * @param loc2 A {@link Location} object
	 * @return a <code>double</code> of the distance in the coordinate system.
	 */
	public double getDistance(Location loc2) {
		return Math.sqrt(Math.pow((getX() - loc2.getX()),2) + Math.pow((getY() - loc2.getY()),2));
	}
	
	/**
	 * Returns the midpoint of this Location and the Location argument. This method is transitive.
	 * I.e., loc2.getMidpoint(loc1) will return the same result as loc1.getMidpoint(loc2);
	 * @param loc2 A {@link Location} objects
	 * @return A new {@link Location} representing the midpoint.
	 */
	public Location getMidPoint (Location loc2) {
		return new Location((int)((getX() + loc2.getX()) /2),(int)((getY() + loc2.getY()) /2));
				               
	}
	
}
