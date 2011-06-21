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

package org.pathwayeditor.figurevm;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.pathwayeditor.figure.definition.DrawingVmLexer;
import org.pathwayeditor.figure.definition.DrawingVmParser;
import org.pathwayeditor.figure.definition.FigureDefinitionInterpreter;
import org.pathwayeditor.figure.definition.ICompiledFigureDefinition;
import org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter;
import org.pathwayeditor.figure.definition.IInterpreterErrorHandler;
import org.pathwayeditor.figure.definition.IOpCodeHandler;
import org.pathwayeditor.figure.definition.InstructionFactoryImpl;
import org.pathwayeditor.figure.definition.TextAlignment;
import org.pathwayeditor.figure.definition.TreeDrawingVm;
import org.pathwayeditor.figure.definition.Value;
import org.pathwayeditor.figure.geometry.PointList;

public class ShapeDefinitionInterpreterTest implements IOpCodeHandler {
//	private static String FIG = 		"(C) setanchor\n" +
//	"curbounds /h exch def /w exch def /y exch def /x exch def\n" +
//	"/xoffset { w mul x add } def /yoffset { h mul y add } def\n" +
//	"/cardinalityBox { /card exch def /fsize exch def /cpy exch def /cpx exch def\n" +
//	"fsize setfontsize\n" +
//	"card cvs textbounds /hoff exch curlinewidth 2 mul add h div def /woff exch curlinewidth 2 mul add w div def \n" +
//	"cpx woff 2 div sub xoffset cpy hoff 2 div sub yoffset woff w mul hoff h mul rect\n" +
//	"gsave\n" +
//	"null setfillcol cpx xoffset cpy yoffset (C) card cvs text\n" +
//	"grestore\n" +
//	"} def\n" +
//	":cardinality 1 gt {\n" +
//	"0.10 xoffset 0.10 yoffset 0.90 w mul 0.90 h mul 0.20 w mul 0.20 h mul rrect\n" +
//	"0 xoffset 0 yoffset 0.90 w mul 0.90 h mul 0.20 w mul 0.20 h mul rrect\n" +
//	"0.3 0 :cardFontSize :cardinality cardinalityBox\n" +
//	"}\n" +
//	"{ x y w h 0.2 w mul 0.20 h mul rrect } ifelse";

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        DrawingVmLexer lex = new DrawingVmLexer(new ANTLRStringStream(args[0]));
//        DrawingVmLexer lex = new DrawingVmLexer(new ANTLRStringStream(FIG));
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
            ICompiledFigureDefinition instructions = walker.getInstructions();
            System.out.println("Insts: " + instructions);
            ShapeDefinitionInterpreterTest testHarness = new ShapeDefinitionInterpreterTest();
            IFigureDefinitionInterpreter interpreter = new FigureDefinitionInterpreter(instructions, testHarness,
            		new IInterpreterErrorHandler() {

						@Override
						public void reportError(String msg) {
							System.err.println("Error: " + msg);
						}

						@Override
						public void reportError(ErrorCode errorCode, Value value) {
							System.err.println("Error: Code=" + errorCode + ", value=" + value);
						}
            	
            });
            interpreter.setBindInteger("cardinality", 1);
            interpreter.setBindDouble("cardFontSize", 12.0);
            System.out.println("Bind vars = " + interpreter.getCompiledFigureDefinition().getBindVariableNames());
            interpreter.execute();
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
        System.exit(0);
	}

	@Override
	public void handleArc(double x, double y, double width, double height,
			double offset, double length) {
		System.out.println("Arc(" + x + ", " + y + ", " + width + ", " + height
				+ ", " + offset + ", " + length + ")");
	}

	@Override
	public void handleLine(double startX, double startY, double endX,
			double endY) {
		System.out.println("Line(" + startX + ", " + startY + ", " + endX + ", " + endX + ")");
	}

	@Override
	public void handleOval(double x, double y, double width, double height) {
		System.out.println("Oval(" + x + ", " + y + ", " + width + ", " + height + ")");
	}

	@Override
	public void handlePoint(double x, double y) {
		System.out.println("Point(" + x + ", " + y + ")");
	}

	@Override
	public void handlePolygon(double[] pointArr) {
		System.out.println("Polygon(" + Arrays.toString(pointArr) + ")");
	}

	@Override
	public void handlePolyline(double[] pointArr) {
		System.out.println("Polyline(" + Arrays.toString(pointArr) + ")");
	}

	@Override
	public void handleRectangle(double x, double y, double width, double height) {
		System.out.println("Rectangle(" + x + ", " + y + ", " + width + ", " + height + ")");
	}

	@Override
	public void handleRoundRectangle(double x, double y, double width,
			double height, double arcWidth, double arcHeight) {
		System.out.println("RReactangle(" + x + ", " + y + ", " + width + ", " + height
				+ ", " + arcWidth + ", " + arcHeight + ")");
		
	}

	@Override
	public void handleText(double x, double y, TextAlignment align, String text) {
		System.out.println("Text(" + x + ", " + y + ", align=" + align + ",text=" + text + ")");
	}

//	@Override
//	public void setNoFill() {
//		System.out.println("SetNoFill()");
//	}

	@Override
	public void setFillColour(int red, int green, int blue, int alpha) {
		print("setFillColour(" + red + ", " + green + ", " + blue + ", " + alpha + ")");
	}

	private void print(String string) {
		System.out.println(string);
	}

	@Override
	public List<Integer> getCurFillColour() {
		print("getFillColour()");
		return Arrays.asList(new Integer[] { 20, 30, 50 });
	}

	@Override
	public List<Integer> getCurLineColour() {
		print("getLineColour()");
		return Arrays.asList(new Integer[] { 20, 30, 50 });
	}

	@Override
	public void setLineColour(int red, int green, int blue, int alpha) {
		print("setLineColour(" + red + ", " + green + ", " + blue + ", " + alpha + ")");
	}

//	@Override
//	public void setNoLine() {
//		print("setNoLine()");
//	}

	@Override
	public double getCurFontSize() {
		print("getCurFontSize()");
		return 12;
	}

	@Override
	public String getCurFontStyle() {
		print("getCurFontStyle()");
		return "I";
	}

	@Override
	public void setFontStyle(String styleString) {
		print("setFontStyle(" + styleString + ")");
	}

	@Override
	public void setFontSize(double fontSize) {
		print("setFontSize(" + fontSize + ")");
	}

	@Override
	public void restoreGraphicsState() {
		print("popGraphicsState()");
	}

	@Override
	public void saveGraphicsState() {
		print("pushGraphicsState()");
	}

	@Override
	public double currentLineWidth() {
		print("currentLineWidth()");
		return 1;
	}

	@Override
	public void setLineWidth(double lineWidth) {
		print("setLineWidth(" + lineWidth + ")");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IOpCodeHandler#getCurBounds()
	 */
	@Override
	public List<Double> getCurBounds() {
		print("curBounds()");
		Double retVal[] = new Double[]{ 0.0, 1.0, 2.0, 3.0 };
		return Arrays.asList(retVal);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IOpCodeHandler#setChopHullAnchor()
	 */
	@Override
	public void setChopHullAnchor() {
		print("setChopHullAnchor()");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IOpCodeHandler#setSemiFixedAnchorCode(java.util.List)
	 */
	@Override
	public void setSemiFixedAnchorCode(PointList points) {
		print("setSemiFixedAnchorCode(" + points.toString() + ")");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IOpCodeHandler#getTextBounds(java.lang.String)
	 */
	@Override
	public List<Double> getTextBounds(String text) {
        System.setProperty("java.awt.headless", "true");
    	Font f = new Font("Arial", Font.ITALIC, 12);
    	AffineTransform af = new AffineTransform();
    	FontRenderContext ctx = new FontRenderContext(af, false, false);
    	Rectangle2D bounds = f.getStringBounds(text, ctx);
    	List<Double> retVal = new LinkedList<Double>();
    	retVal.add(bounds.getWidth());
    	retVal.add(bounds.getHeight());
    	print("textbounds=" + retVal);
		return retVal;
	}

}
