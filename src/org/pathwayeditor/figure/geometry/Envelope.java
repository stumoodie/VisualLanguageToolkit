package org.pathwayeditor.figure.geometry;

public class Envelope {
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
		return other.getOrigin().getX() <= this.origin.getX() + this.dim.getWidth() 
			&& other.getOrigin().getY() <= this.origin.getY() + this.dim.getHeight() 
			&& other.getOrigin().getX() + other.getDimension().getWidth() >= this.origin.getX() 
			&& other.getOrigin().getY() + other.getDimension().getHeight() >= this.origin.getY();

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
	
	public Point calculateTranslation(Envelope destinationBounds){
		double x = destinationBounds.getOrigin().getX() - this.getOrigin().getX();
		double y = destinationBounds.getOrigin().getY() - this.getOrigin().getY();
		return new Point(x, y);
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

	public Point getHorizontalCorner() {
		return this.origin.translate(this.dim.getWidth(), 0);
	}

	public Point getVerticalCorner() {
		return this.origin.translate(0, this.dim.getHeight());
	}

	public Point getDiagonalCorner() {
		return this.origin.translate(this.dim.getWidth(), this.dim.getHeight());
	}

	private boolean containsPoint(double x, double y) {
		return x >= this.origin.getX() && x <= this.origin.getX() + this.dim.getWidth()
			&& y >= this.origin.getY() && y <= this.origin.getY() + this.dim.getHeight();
	}

	public boolean containsPoint(Point p) {
		return containsPoint(p.getX(), p.getY());
	}
	
	public boolean contains(Envelope envelope) {
		return origin.getX() <= envelope.origin.getX()
			&& (origin.getX() + this.dim.getWidth()) >= (envelope.origin.getX() + envelope.getDimension().getWidth())
			&& origin.getY() <= envelope.origin.getY()
			&& (origin.getY() + this.dim.getHeight()) >= (envelope.origin.getY() + envelope.getDimension().getHeight());
	}

	public Envelope translate(Point translation) {
		return new Envelope(this.origin.translate(translation), this.dim);
	}

	public Envelope changeOrigin(Point newLocation) {
		return new Envelope(newLocation, this.getDimension());
	}

	public Envelope changeDimension(Dimension newSize) {
		return new Envelope(this.origin, newSize);
	}

}
