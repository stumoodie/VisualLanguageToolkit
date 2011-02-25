/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.geometry;

/**
 * @author Stuart Moodie
 *
 */
public class Vector {
	private final double i;
	private final double j;
	private final double k;
	
	
	public Vector(double i, double j, double k){
		this.i = i;
		this.j = j;
		this.k = k;
	}
	
	public double getIMagnitude(){
		return i;
	}
	
	public double getJMagnitude(){
		return j;
	}
	
	public double getKMagnitude(){
		return k;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.IVector#crossProduct(org.pathwayeditor.figure.geometry.IVector)
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(this.j * other.getKMagnitude() - this.k * other.getJMagnitude(),
							this.k * other.getIMagnitude() - this.i * other.getKMagnitude(),
							this.i * other.getJMagnitude() - this.j * other.getIMagnitude());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.IVector#magnitude()
	 */
	public double magnitude() {
		return Math.sqrt(i*i + j*j + k*k);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.IVector#scalarProduct(org.pathwayeditor.figure.geometry.IVector)
	 */
	public double scalarProduct(Vector other) {
		return this.i * other.getIMagnitude() + this.j * other.getJMagnitude() + this.k + other.getKMagnitude();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.IVector#translate(org.pathwayeditor.figure.geometry.IVector)
	 */
	public Vector translate(Vector translation) {
		return new Vector(this.i + translation.getIMagnitude(),
				this.j + translation.getJMagnitude(),
				this.k + translation.getKMagnitude());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.geometry.IVector#unitVector()
	 */
	public Vector unitVector() {
		double magnitude = this.magnitude();
		return new Vector(this.i/magnitude, this.j/magnitude, this.k/magnitude);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.i);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.j);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.k);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(this.i) != Double.doubleToLongBits(other.i))
			return false;
		if (Double.doubleToLongBits(this.j) != Double.doubleToLongBits(other.j))
			return false;
		if (Double.doubleToLongBits(this.k) != Double.doubleToLongBits(other.k))
			return false;
		return true;
	}
	
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append("(");
		builder.append(this.i);
		builder.append(",");
		builder.append(this.j);
		builder.append(",");
		builder.append(this.k);
		builder.append(")");
		return builder.toString();
	}

	/**
	 * Scales the vector so it has a magnitude commensurate with the scale factor. 
	 * @param scaleFactor the scaleFactor which must be a positive nonzero number. 
	 * @return the scaled vector
	 */
	public Vector scale(double scaleFactor) {
		return new Vector(scaleFactor * this.getIMagnitude(), scaleFactor * this.getJMagnitude(), 0.0);
	}
}
