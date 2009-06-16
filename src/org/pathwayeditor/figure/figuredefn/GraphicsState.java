package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;

public class GraphicsState {
	private static final double DEFAULT_LINE_WIDTH = 1;
	private RGB fill; 
	private RGB line;
	private double lineWidth;
	private IFont font;
	
//	public GraphicsState(IGraphicsEngine g){
//		this.fill = g.getFillColour();
//		this.line = g.getLineColour();
//		this.lineWidth = g.getLineWidth();
//	}
	
	public GraphicsState(){
		this.fill = RGB.WHITE;
		this.line = RGB.BLACK;
		this.lineWidth = DEFAULT_LINE_WIDTH;
		this.font = new GenericFont();
	}
	
	public GraphicsState(GraphicsState other){
		this.fill = other.fill;
		this.line = other.line;
		this.lineWidth = other.lineWidth;
		this.font = new GenericFont(other.font);
	}
	
	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double newLineWidth){
		this.lineWidth = newLineWidth;
	}

	public void setFont(IFont font){
		this.font = font;
	}
	
	public RGB getFillColour() {
		return this.fill;
	}

	public void setFillColour(RGB newFillColour){
		this.fill = newFillColour;
	}

	public RGB getLineColour() {
		return this.line;
	}
	
	public void setLineColour(RGB newLineColour){
		this.line = newLineColour;
	}

	public IFont getFont() {
		return this.font;
	}
}
