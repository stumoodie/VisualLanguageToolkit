/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

/**
 * @author smoodie
 *
 */
public final class Bounds {
	private final Location location;
	private final Size size;

	public Bounds(double x, double y, double width, double height){
		this(new Location(x, y), new Size(width, height));
	}
	
	public Bounds (Location location, Size size){
		this.location = location;
		this.size = size;
	}

	public Location getOrigin() {
		return this.location;
	}

	public Location getHorizontalCorner(){
		return new Location(this.location.getX() + this.size.getWidth(), this.location.getY());
	}
	
	public Location getVerticalCorner(){
		return new Location(this.location.getX(), this.location.getY() + this.size.getHeight());
	}
	
	public Location getDiagonalCorner(){
		return new Location(this.location.getX() + this.size.getWidth(),
				this.location.getY() + this.size.getHeight());
	}
	
	public Size getSize() {
		return this.size;
	}
	
	public boolean isPointWithinBounds(Location point){
		Location origin = this.getOrigin();
		Location dCorner = this.getDiagonalCorner(); 
		return origin.getX() <= point.getX() && dCorner.getX() >= point.getX()
			&& origin.getY() <= point.getY() && dCorner.getY() >= point.getY();
	}

	public Location transformPointToNewBounds(Location point, Bounds newBounds){
		Location adjustedPoint = point.newOrigin(this.getOrigin());
		double scaleX = ((double)newBounds.getSize().getWidth())/((double)this.getSize().getWidth());
		double scaleY = ((double)newBounds.getSize().getHeight())/((double)this.getSize().getHeight());
		double newX = adjustedPoint.getX() * scaleX; 
		double newY = adjustedPoint.getY() * scaleY;
		newX += newBounds.getOrigin().getX();
		newY += newBounds.getOrigin().getY();
		return new Location((int)Math.round(newX), (int)Math.round(newY));
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
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(locn=");
		builder.append(this.location);
		builder.append(",size=");
		builder.append(this.size);
		builder.append(")");
		return builder.toString();
	}
}
