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
 * PointBuilder is a class used to build a point. It's aim is to create a point where a number of transformations
 * are required, but the expense of the immutable {@link Point} class is to be avoided. 
 * 
 * @author Stuart Moodie
 *
 */
public class PointBuilder {
	private static final double DEFAULT_VAL = 0.0;
	private double x;
	private double y;
	
	/**
	 * Default constructor.
	 */
	public PointBuilder(){
		x = DEFAULT_VAL;
		y = DEFAULT_VAL;
	}
	
	/**
	 * Constructs a point with the specified coordinates.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 */
	public PointBuilder(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a builder from the specified point.
	 * @param p the initial point value for this builder.  
	 */
	public PointBuilder(Point p){
		this.x = p.getX();
		this.y = p.getY();
	}
	
	
	/**
	 * Scale the point by the specified amount.
	 * @param xScale the x scaling factor.
	 * @param yScale the y scaling factor.
	 * @return this instance of the builder to enable method chaining.
	 */
	public PointBuilder scale(double xScale, double yScale){
		this.x *= xScale;
		this.y *= yScale;
		return this;
	}
	
	/**
	 * Translate the point by the specified amount.
	 * @param xTrans the x displacement.
	 * @param yTrans the y displacement.
	 * @return this instance of the builder to enable method chaining.
	 */
	public PointBuilder translate(double xTrans, double yTrans){
		this.x += xTrans;
		this.y += yTrans;
		return this;
	}

	/**
	 * Negate the point held by this builder. 
	 * @return this instance of the builder to enable method chaining.
	 */
	public PointBuilder negate(){
		this.x *= -1;
		this.y *= -1;
		return this;
	}
	
	/**
	 * Get a new point equivalent to the current point held by this builder.
	 * @return a new point with the current point values.
	 */
	public Point getPoint(){
		return new Point(this.x, this.y);
	}
	
	/**
	 * Get the current x coordinate.
	 * @return the current x coordinate
	 */
	public double getX(){
		return this.x;
	}
	
	/**
	 * Get the current y coordinate.
	 * @return the current y coordinate
	 */
	public double getY(){
		return this.y;
	}
}
