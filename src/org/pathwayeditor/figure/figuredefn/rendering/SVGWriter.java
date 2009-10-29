package org.pathwayeditor.figure.figuredefn.rendering;

import java.awt.Graphics2D;

import org.pathwayeditor.figure.figuredefn.FigureDrawer;
import org.pathwayeditor.figure.figuredefn.GraphicsInstructionList;
import org.pathwayeditor.figure.figuredefn.IGraphicsEngine;

public class SVGWriter  {

	private GraphicsInstructionList shapeDefn;
	
	public SVGWriter(GraphicsInstructionList shapeDefn){
		super();
		this.shapeDefn = shapeDefn;
	}
	
	
	public void paint(Graphics2D g2d){
		IGraphicsEngine graphicsEngine = new Java2DGraphicsEngine(g2d);
		FigureDrawer drawer = new FigureDrawer(shapeDefn);
		drawer.drawFigure(graphicsEngine);
	}
	
}
