/**
 * 
 */
package org.pathwayeditor.figure.geometry;

/**
 * @author smoodie
 *
 */
public class PointBuilder {
	private static final double DEFAULT_VAL = 0.0;
	private double x;
	private double y;
	
	public PointBuilder(){
		x = DEFAULT_VAL;
		y = DEFAULT_VAL;
	}
	
	public PointBuilder(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public PointBuilder(Point p){
		this.x = p.getX();
		this.y = p.getY();
	}
	
	
	public PointBuilder scale(double xScale, double yScale){
		this.x *= xScale;
		this.y *= yScale;
		return this;
	}
	
	public PointBuilder translate(double xTrans, double yTrans){
		this.x += xTrans;
		this.y += yTrans;
		return this;
	}
	
	public PointBuilder negatedTranslation(double xTrans, double yTrans){
		this.x -= xTrans;
		this.y -= yTrans;
		return this;
	}
	
	public PointBuilder negate(){
		this.x *= -1;
		this.y *= -1;
		return this;
	}
	
	public Point getPoint(){
		return new Point(this.x, this.y);
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
}
