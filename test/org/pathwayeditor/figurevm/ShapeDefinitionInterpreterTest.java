package org.pathwayeditor.figurevm;

import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.pathwayeditor.figure.geometry.PointList;

public class ShapeDefinitionInterpreterTest implements IOpCodeHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        DrawingVmLexer lex = new DrawingVmLexer(new ANTLRStringStream(args[0]));
//        DrawingVmLexer lex = new DrawingVmLexer(new ANTLRStringStream("10 10 (C) (\\\\) text curfontsize 0.8 mul setfontsize"));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        DrawingVmParser parser = new DrawingVmParser(tokens);
        
        try {
            DrawingVmParser.shapeDefn_return treeReturn = parser.shapeDefn();
            CommonTree t = (CommonTree)treeReturn.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            nodes.setTokenStream(tokens);
            TreeDrawingVm walker = new TreeDrawingVm(nodes);
            walker.setInstructionFactory(new InstructionFactoryImpl());
            walker.shapeDefn();
            IFigureDefinition instructions = walker.getInstructions();
            System.out.println("Insts: " + instructions);
            ShapeDefinitionInterpreterTest testHarness = new ShapeDefinitionInterpreterTest();
            ShapeDefinitionInterpreter interpreter = new ShapeDefinitionInterpreter(instructions, testHarness,
            		new IInterpreterErrorHandler() {

						public void reportError(String msg) {
							System.err.println("Error: " + msg);
						}

						public void reportError(ErrorCode errorCode, Value value) {
							System.err.println("Error: Code=" + errorCode + ", value=" + value);
						}
            	
            });
            interpreter.setBindBoolean("bindTest", Boolean.FALSE);
            interpreter.execute();
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
        System.exit(0);
	}

	public void handleArc(double x, double y, double width, double height,
			double offset, double length) {
		System.out.println("Arc(" + x + ", " + y + ", " + width + ", " + height
				+ ", " + offset + ", " + length + ")");
	}

	public void handleLine(double startX, double startY, double endX,
			double endY) {
		System.out.println("Line(" + startX + ", " + startY + ", " + endX + ", " + endX + ")");
	}

	public void handleOval(double x, double y, double width, double height) {
		System.out.println("Oval(" + x + ", " + y + ", " + width + ", " + height + ")");
	}

	public void handlePoint(double x, double y) {
		System.out.println("Point(" + x + ", " + y + ")");
	}

	public void handlePolygon(double[] pointArr) {
		System.out.println("Polygon(" + Arrays.toString(pointArr) + ")");
	}

	public void handlePolyline(double[] pointArr) {
		System.out.println("Polyline(" + Arrays.toString(pointArr) + ")");
	}

	public void handleRectangle(double x, double y, double width, double height) {
		System.out.println("Rectangle(" + x + ", " + y + ", " + width + ", " + height + ")");
	}

	public void handleRoundRectangle(double x, double y, double width,
			double height, double arcWidth, double arcHeight) {
		System.out.println("RReactangle(" + x + ", " + y + ", " + width + ", " + height
				+ ", " + arcWidth + ", " + arcHeight + ")");
		
	}

	public void handleText(double x, double y, TextAlignment align, String text) {
		System.out.println("Text(" + x + ", " + y + ", align=" + align + ",text=" + text + ")");
	}

	public void setNoFill() {
		System.out.println("SetNoFill()");
	}

	public void setFillColour(int red, int green, int blue) {
		print("setFillColour(" + red + ", " + green + ", " + blue + ")");
	}

	private void print(String string) {
		System.out.println(string);
	}

	public List<Integer> getCurFillColour() {
		print("getFillColour()");
		return Arrays.asList(new Integer[] { 20, 30, 50 });
	}

	public List<Integer> getCurLineColour() {
		print("getLineColour()");
		return Arrays.asList(new Integer[] { 20, 30, 50 });
	}

	public void setLineColour(int red, int green, int blue) {
		print("setLineColour(" + red + ", " + green + ", " + blue + ")");
	}

	public void setNoLine() {
		print("setNoLine()");
	}

	public double getCurFontSize() {
		print("getCurFontSize()");
		return 12;
	}

	public String getCurFontStyle() {
		print("getCurFontStyle()");
		return "I";
	}

	public void setFontStyle(String styleString) {
		print("setFontStyle(" + styleString + ")");
	}

	public void setFontSize(double fontSize) {
		print("setFontSize(" + fontSize + ")");
	}

	public void restoreGraphicsState() {
		print("popGraphicsState()");
	}

	public void saveGraphicsState() {
		print("pushGraphicsState()");
	}

	public double currentLineWidth() {
		print("currentLineWidth()");
		return 1;
	}

	public void setLineWidth(double lineWidth) {
		print("setLineWidth(" + lineWidth + ")");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figurevm.IOpCodeHandler#getCurBounds()
	 */
	public List<Double> getCurBounds() {
		print("curBounds()");
		Double retVal[] = new Double[]{ 0.0, 1.0, 2.0, 3.0 };
		return Arrays.asList(retVal);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figurevm.IOpCodeHandler#setChopHullAnchor()
	 */
	public void setChopHullAnchor() {
		print("setChopHullAnchor()");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figurevm.IOpCodeHandler#setSemiFixedAnchorCode(java.util.List)
	 */
	public void setSemiFixedAnchorCode(PointList points) {
		print("setSemiFixedAnchorCode(" + points.toString() + ")");
	}

}
