package org.pathwayeditor.figure.geometry;

public final class Envelope {
	private final Dimension dim;
	private final Point origin;
	
	public Envelope(Point origin, Dimension dim){
		this.dim = dim;
		this.origin = origin;
	}
	
	public Envelope(double x, double y, double w, double h){
		this.dim = new Dimension(w, h);
		this.origin = new Point(x, y);
	}

	public Dimension getDimension() {
		return dim;
	}

	public Point getOrigin() {
		return origin;
	}
	
	boolean intersects(Envelope other){
		return other.getOrigin().getX() < this.origin.getX() + this.dim.getWidth() 
			&& other.getOrigin().getY() < this.origin.getY() + this.dim.getHeight() 
			&& other.getOrigin().getX() + other.getDimension().getWidth() > this.origin.getX() 
			&& other.getOrigin().getY() + other.getDimension().getHeight() > this.origin.getY();

	}
	
	public Point transformPointToNewEnvelope(Point point, Envelope newEnvelope){
		Point adjustedPoint = point.newOrigin(this.getOrigin());
		double scaleX = newEnvelope.getDimension().getWidth()/this.getDimension().getWidth();
		double scaleY = newEnvelope.getDimension().getHeight()/this.getDimension().getHeight();
		double newX = adjustedPoint.getX() * scaleX; 
		double newY = adjustedPoint.getY() * scaleY;
		newX += newEnvelope.getOrigin().getX();
		newY += newEnvelope.getOrigin().getY();
		return new Point(newX, newY);
	}
	
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dim == null) ? 0 : dim.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Envelope))
			return false;
		Envelope other = (Envelope) obj;
		if (dim == null) {
			if (other.dim != null)
				return false;
		} else if (!dim.equals(other.dim))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(");
		buf.append(this.origin);
		buf.append(", ");
		buf.append(this.dim);
		buf.append(")");
		return buf.toString();
	}

	public Point getHorizontal() {
		return this.origin.translate(this.dim.getWidth(), 0);
	}

	public Point getVerical() {
		return this.origin.translate(0, this.dim.getHeight());
	}

	public Point getDiagonal() {
		return this.origin.translate(this.dim.getWidth(), this.dim.getHeight());
	}

	public boolean containsPoint(Point p) {
		return p.getX() >= this.origin.getX() && p.getX() <= this.origin.getX() + this.dim.getWidth()
			&& p.getY() >= this.origin.getY() && p.getY() <= this.origin.getY() + this.dim.getHeight();
	}
	
}
