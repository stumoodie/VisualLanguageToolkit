/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package org.pathwayeditor.figure.geometry;

/**
 * This class defines a rectangular bounds or envelope. Typically is used to represent the bounds of a graphical object or shape.
 * It has two components, a point defining it's origin and a dimension defining the size of the envelope. The API tries not to assume
 * an orientation of the coordinate system.
 * 
 * The class is immutable so all API methods that could modify the envelope, in fact return a new instance. This means that envelopes can
 * be shared with no danger of unexpected modification by another object. 
 * 
 * @author Stuart Moodie
 *
 */

public class Envelope {
	public static final Envelope NULL_ENVELOPE = new Envelope(0.0, 0.0, 0.0, 0.0);
	
	private final Dimension dim;
	private final Point origin;
	
	/**
	 * Creates a new envelope with the specified origin and dimension
	 * @param origin the origin, which should not be null;
	 * @param dim the size or dimension of the envelope which shuld not be null. 
	 */
	public Envelope(Point origin, Dimension dim){
		this.dim = dim;
		this.origin = origin;
	}
	
	/**
	 * Creates a new envelope with the origin and dimensions provided as individual values.
	 * @param x the x coordinate of the envelope origin.
	 * @param y the y coordinate of the envelope origin.
	 * @param w the width f the envelope.
	 * @param h the height of the envelope.
	 */
	public Envelope(double x, double y, double w, double h){
		this.dim = new Dimension(w, h);
		this.origin = new Point(x, y);
	}

	/**
	 * Creates an envelope using a corner and the translation to the diagonal corner.
	 * @param cornerPosn the absolute position of one corner of the envelope. 
	 * @param deltaToDiagonalCorner the translation to the diagonally opposite corner.
	 */
	public Envelope(Point cornerPosn, Point deltaToDiagonalCorner){
		double x = cornerPosn.getX();
		double width = deltaToDiagonalCorner.getX();
		if(width < 0.0){
			x = x + width;
			width = -width;
		}
		double y = cornerPosn.getY();
		double height = deltaToDiagonalCorner.getY();
		if(height < 0.0){
			y = y + height;
			height = -height;
		}
		this.origin = new Point(x, y);
		this.dim = new Dimension(width, height);
	}
	
	/**
	 * Get the dimension (or size) of this envelope. 
	 * @return the envelope which will not be null.
	 */
	public Dimension getDimension() {
		return dim;
	}

	/**
	 * Get the origin of the envelope.
	 * @return the origin point, which will not be null.
	 */
	public Point getOrigin() {
		return origin;
	}
	
	/**
	 * Gets the centre point of the envelope.
	 * @return the centre point.
	 */
	public Point getCentre(){
		return new Point(origin.getX() + dim.getWidth()/2, origin.getY() + dim.getHeight()/2);
	}
	
	/**
	 * Tests whether the <code>other</code> intersects with this one. 
	 * @param other the other envelope, which can be null.
	 * @return true of the other envelope intersects, false otherwise.
	 */
	public boolean intersects(Envelope other){
		return other != null && other.getOrigin().getX() <= this.origin.getX() + this.dim.getWidth() 
			&& other.getOrigin().getY() <= this.origin.getY() + this.dim.getHeight() 
			&& other.getOrigin().getX() + other.getDimension().getWidth() >= this.origin.getX() 
			&& other.getOrigin().getY() + other.getDimension().getHeight() >= this.origin.getY();

	}
	
	/**
	 * Takes a point and applies the transformation to it obtained by transforming this envelope into 
	 * to the reference envelope. Specifically it makes the point (P) relative to the origin of this envelope (O) to give the point defined by the vector OP.
	 * OP is scaled the scaling factor (s) required to convert this envelope's dimension to that of the reference envelope's dimension: s * OP = OP'. Finally
	 * the transformed point is calculated by adding the reference envelope's origin (R) to the point' relative position: OR + OP' = OP'. 
	 * @param point the point to be transformed.
	 * @param reference the reference envelope.
	 * @return a new point transformed as above.
	 */
	public Point transformPointToNewEnvelope(Point point, Envelope reference){
		Point adjustedPoint = point.newOrigin(this.getOrigin());
		double scaleX = reference.getDimension().getWidth()/this.getDimension().getWidth();
		double scaleY = reference.getDimension().getHeight()/this.getDimension().getHeight();
		double newX = adjustedPoint.getX() * scaleX; 
		double newY = adjustedPoint.getY() * scaleY;
		newX += reference.getOrigin().getX();
		newY += reference.getOrigin().getY();
		return new Point(newX, newY);
	}
	
	/**
	 * Calculate the translation required to move this envelope to the origin of the reference envelope.
	 * @param referenceEnvelope the reference envelope.
	 * @return the translation expressed as a point.
	 */
	public Point calculateTranslation(Envelope referenceEnvelope){
		double x = referenceEnvelope.getOrigin().getX() - this.getOrigin().getX();
		double y = referenceEnvelope.getOrigin().getY() - this.getOrigin().getY();
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

	/**
	 * Gets the corner of the envelope that is horizontally adjacent to the origin of this envelope.
	 * @return the horizontally adjacent corner.
	 */
	public Point getHorizontalCorner() {
		return this.origin.translate(this.dim.getWidth(), 0);
	}

	/**
	 * Gets the corner of the envelope that is vertically adjacent to the origin of this envelope.
	 * @return the vertically adjacent corner.
	 */
	public Point getVerticalCorner() {
		return this.origin.translate(0, this.dim.getHeight());
	}

	/**
	 * Gets the corner of the envelope that is diagonally opposite the origin of this envelope.
	 * @return the diagonally opposite corner.
	 */
	public Point getDiagonalCorner() {
		return this.origin.translate(this.dim.getWidth(), this.dim.getHeight());
	}

	private boolean containsPoint(double x, double y) {
		return x >= this.origin.getX() && x <= this.origin.getX() + this.dim.getWidth()
			&& y >= this.origin.getY() && y <= this.origin.getY() + this.dim.getHeight();
	}

	
	/**
	 * Does this envelope contain this point. This includes sitting exactly on the edge of the envelope.
	 * @param p the point to test.
	 * @return true if this envelope contains the point, false otherwise. 
	 */
	public boolean containsPoint(Point p) {
		return containsPoint(p.getX(), p.getY());
	}
	
	/**
	 * Tests if envelope is contained by this envelope
	 * @param envelope to compare, which can be null.
	 * @return true if the envelope is contained by this envelope, false otherwise.
	 */
	public boolean contains(Envelope envelope) {
		return envelope != null && origin.getX() <= envelope.origin.getX()
			&& (origin.getX() + this.dim.getWidth()) >= (envelope.origin.getX() + envelope.getDimension().getWidth())
			&& origin.getY() <= envelope.origin.getY()
			&& (origin.getY() + this.dim.getHeight()) >= (envelope.origin.getY() + envelope.getDimension().getHeight());
	}

	/**
	 * Creates a new envelope that has been translated by the specified amount.
	 * @param translation the translation, which should not be null.
	 * @return a new enveloped translated relative to this one.  
	 */
	public Envelope translate(Point translation) {
		return new Envelope(this.origin.translate(translation), this.dim);
	}
	
	/**
	 * Creates a new envelope that has been resized relative to this one. Resizing can involve a change
	 * to the origin as well as the height and width of the envelope.  
	 * @param translation the translation, which should not be null.
	 * @param sizeDelta the amount to change the height and width of the envelope by.
	 * @return a new enveloped resized relative to this one.  
	 */
	public Envelope resize(Point translation, Dimension sizeDelta){
		return new Envelope(this.origin.translate(translation), this.dim.resize(sizeDelta));
	}

	/**
	 * Creates a new envelope with a the new specified origin.
	 * @param newOrigin the newly specified origin.
	 * @return a new instance with the new origin and same dimension.
	 */
	public Envelope changeOrigin(Point newOrigin) {
		return new Envelope(newOrigin, this.getDimension());
	}

	/**
	 * Creates a new envelope with a the new specified size.
	 * @param newSize the newly specified size.
	 * @return a new instance with the same origin and new size.
	 */
	public Envelope changeDimension(Dimension newSize) {
		return new Envelope(this.origin, newSize);
	}
	
//	public Transformation calcTransformation(Envelope transformedEnvelope){
//		Scale newScale = this.dim.calcScalingFactors(transformedEnvelope.getDimension());
//		Point newTrans = this.origin.difference(transformedEnvelope.getOrigin());
//		return new Transformation(newTrans, newScale);
//	}
//
//	
//	public Envelope applyTransformation(Transformation transform){
//		Point newOrigin = this.origin.translate(transform.getTranslation());
//		Dimension newDim = this.dim.scale(transform.getScale());
//		return new Envelope(newOrigin, newDim);
//	}

	/**
	 * Resize the envelope based on delta changes.
	 * @param deltaX
	 * @param deltaY
	 * @param deltaWidth
	 * @param deltaHeight
	 * @return the envelope.
	 */
	public Envelope deltaResize(double deltaX, double deltaY, double deltaWidth, double deltaHeight) {
		return new Envelope(this.origin.translate(deltaX, deltaY), this.dim.resize(deltaWidth, deltaHeight));
	}
}
