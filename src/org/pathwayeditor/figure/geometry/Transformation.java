/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.geometry;

/**
 * @author Stuart Moodie
 *
 */
public class Transformation {
	private final Point translation;
	private final Scale scale;
	
	
	public Transformation(Point translation, Scale scale){
		this.translation = translation;
		this.scale = scale;
	}


	public Point getTranslation() {
		return this.translation;
	}


	public Scale getScale() {
		return this.scale;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.scale == null) ? 0 : this.scale.hashCode());
		result = prime * result + ((this.translation == null) ? 0 : this.translation.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Transformation))
			return false;
		Transformation other = (Transformation) obj;
		if (this.scale == null) {
			if (other.scale != null)
				return false;
		} else if (!this.scale.equals(other.scale))
			return false;
		if (this.translation == null) {
			if (other.translation != null)
				return false;
		} else if (!this.translation.equals(other.translation))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder buf = new StringBuilder(this.getClass().getSimpleName());
		buf.append("(trans=");
		buf.append(this.translation);
		buf.append(",scale=");
		buf.append(this.scale);
		buf.append(")");
		return buf.toString();
	}
	
}
