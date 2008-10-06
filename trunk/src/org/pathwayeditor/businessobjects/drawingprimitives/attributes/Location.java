package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * @author smoodie
 *
 */
public final class Location {
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
}
