package org.pathwayeditor.figure.figuredefn;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.figuredefn.GraphicsInstruction.GraphicsOpCode;
import org.pathwayeditor.figure.figuredefn.IFont.Style;


public class GraphicsInstructionFactory {

	private static GraphicsInstructionFactory anInstance = null;
	
	public static GraphicsInstructionFactory getInstance(){
		if(anInstance == null){
			anInstance = new GraphicsInstructionFactory();
		}
		return anInstance;
	}
	
	private GraphicsInstructionFactory(){
	}
	
	public GraphicsInstruction drawRectangle(double x, double y, double w, double h){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		return new GraphicsInstruction(GraphicsOpCode.DRAW_RECT, valList);
	}
	
	public GraphicsInstruction fillRectangle(double x, double y, double w, double h){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		return new GraphicsInstruction(GraphicsOpCode.FILL_RECT, valList);
	}
	
	public GraphicsInstruction drawRoundRectangle(double x, double y, double w, double h,
			double cornerWidth, double cornerHeight){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		valList.add(cornerWidth);
		valList.add(cornerHeight);
		return new GraphicsInstruction(GraphicsOpCode.DRAW_RRECT, valList);
	}
	
	public GraphicsInstruction fillRoundRectangle(double x, double y, double w, double h,
			double cornerWidth, double cornerHeight){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		valList.add(cornerWidth);
		valList.add(cornerHeight);
		return new GraphicsInstruction(GraphicsOpCode.FILL_RRECT, valList);
	}
	
	public GraphicsInstruction drawOval(double x, double y, double w, double h){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		return new GraphicsInstruction(GraphicsOpCode.DRAW_OVAL, valList);
	}
	
	public GraphicsInstruction fillOval(double x, double y, double w, double h){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		return new GraphicsInstruction(GraphicsOpCode.FILL_OVAL, valList);
	}	
	
	public GraphicsInstruction drawArc(double x, double y, double w, double h, double startAngle, double length){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		valList.add(startAngle);
		valList.add(length);
		return new GraphicsInstruction(GraphicsOpCode.DRAW_ARC, valList);
	}
	
	public GraphicsInstruction fillArc(double x, double y, double w, double h, double startAngle, double length){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		valList.add(w);
		valList.add(h);
		valList.add(startAngle);
		valList.add(length);
		return new GraphicsInstruction(GraphicsOpCode.FILL_ARC, valList);
	}
	
	public GraphicsInstruction drawPolygon(double points[]){
		return new GraphicsInstruction(GraphicsOpCode.DRAW_POLYGON, points);
	}
	
	public GraphicsInstruction fillPolygon(double points[]){
		return new GraphicsInstruction(GraphicsOpCode.FILL_POLYGON, points);
	}
	
	public GraphicsInstruction drawPolyline(double points[]){
		return new GraphicsInstruction(GraphicsOpCode.DRAW_POLYLINE, points);
	}
	
	public GraphicsInstruction drawLine(double x1, double y1, double x2, double y2){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x1);
		valList.add(y1);
		valList.add(x2);
		valList.add(y2);
		return new GraphicsInstruction(GraphicsOpCode.DRAW_LINE, valList);
	}

	public GraphicsInstruction drawPoint(double x, double y){
		List<Double> valList = new ArrayList<Double>();
		valList.add(x);
		valList.add(y);
		return new GraphicsInstruction(GraphicsOpCode.DRAW_POINT, valList);
	}

	public GraphicsInstruction drawText(double x, double y, String text){
		List<Object> valList = new ArrayList<Object>();
		valList.add(x);
		valList.add(y);
		valList.add(text);
		return new GraphicsInstruction(GraphicsOpCode.DRAW_TEXT, valList);
	}

	public GraphicsInstruction fillText(double x, double y, String text){
		List<Object> valList = new ArrayList<Object>();
		valList.add(x);
		valList.add(y);
		valList.add(text);
		return new GraphicsInstruction(GraphicsOpCode.FILL_TEXT, valList);
	}

	public GraphicsInstruction lineColour(RGB colour){
		return new GraphicsInstruction(GraphicsOpCode.LINE_COLOUR, colour);
	}

	public GraphicsInstruction fillColour(RGB colour){
		return new GraphicsInstruction(GraphicsOpCode.FILL_COLOUR, colour);
	}

	public GraphicsInstruction fontSize(int fontSize){
		return new GraphicsInstruction(GraphicsOpCode.FONT_SIZE, Integer.valueOf(fontSize));
	}

	public GraphicsInstruction fontStyle(EnumSet<Style> fontStyles){
		return new GraphicsInstruction(GraphicsOpCode.FONT_STYLE, fontStyles);
	}

	public GraphicsInstruction setLineWidth(double lineWidth) {
		return new GraphicsInstruction(GraphicsOpCode.LINE_WIDTH, Double.valueOf(lineWidth));
	}

	public GraphicsInstruction setLineStyle(LineStyle lineStyle) {
		return new GraphicsInstruction(GraphicsOpCode.LINE_STYLE, lineStyle);
	}
}
