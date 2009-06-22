package org.pathwayeditor.figure.geometry;



public class Point {
	public static final Point ORIGIN = new Point(0, 0);
	
	private final double x;
	private final double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	public static Point createFromPolar(double r, double theta){
		double x = r * Math.cos(theta);
		double y = r * Math.sin(theta);
		return new Point(x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public Point newOrigin(Point p){
		return newOrigin(p.x, p.y);
	}
	
	public Point newOrigin(double x, double y){
		return new Point(this.x - x, this.y - y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append('(');
		builder.append(this.x);
		builder.append(",");
		builder.append(this.y);
		builder.append(')');
		return builder.toString();
	}

	public double getDistance(Point other) {
		double xDist = this.x - other.getX();
		double yDist = this.getY() - other.getY();
		return Math.sqrt(xDist * xDist + yDist * yDist);
	}

	public Point translate(double d, double e) {
		return new Point(this.x + d, this.y + e);
	}

	public Point translate(Point t) {
		return new Point(this.x + t.getX(), this.y + t.getY());
	}
	
	public Point difference(Point t){
		return new Point(t.getX()-x, t.getY()-y);
	}
	
	public Point negate(){
		return new Point(-this.x, -this.y);
	}

	public Point newX(double x2) {
		return new Point(x2, this.y);
	}

	public Point newY(double y2) {
		return new Point(this.x, y2);
	}
}
