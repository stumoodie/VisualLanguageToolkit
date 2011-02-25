/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.geometry;

/**
 * @author Stuart Moodie
 *
 */
public class Scale {
	private final double xfactor;
	private final double yFactor;
	
	public Scale(double xFactor, double yFactor){
		this.xfactor = xFactor;
		this.yFactor = yFactor;
	}

	public double getXfactor() {
		return this.xfactor;
	}

	public double getYFactor() {
		return this.yFactor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.xfactor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.yFactor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Scale))
			return false;
		Scale other = (Scale) obj;
		if (Double.doubleToLongBits(this.xfactor) != Double.doubleToLongBits(other.xfactor))
			return false;
		if (Double.doubleToLongBits(this.yFactor) != Double.doubleToLongBits(other.yFactor))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(xFactor=");
		buf.append(this.xfactor);
		buf.append(",yfactor=");
		buf.append(this.yFactor);
		buf.append(")");
		return buf.toString();
	}
}
