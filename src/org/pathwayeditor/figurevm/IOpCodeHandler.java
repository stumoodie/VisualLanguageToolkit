package org.pathwayeditor.figurevm;

import java.util.List;

import org.pathwayeditor.figure.geometry.PointList;

public 	interface IOpCodeHandler {
	// Compass positions plus centre
	// note that origin is in the bottom lefthand corner in this coordinate system!
	public enum TextAlignment { N, NE, E, SE, S, SW, W, NW, C;
	
		public static TextAlignment createFromString(String str){
			TextAlignment retVal = null;
			if(str.equals(N.name())){
				retVal = N;
			}
			else if(str.equals(NE.name())){
				retVal = NE;
			}
			else if(str.equals(E.name())){
				retVal = E;
			}
			else if(str.equals(SE.name())){
				retVal = SE;
			}
			else if(str.equals(S.name())){
				retVal = S;
			}
			else if(str.equals(SW.name())){
				retVal = SW;
			}
			else if(str.equals(W.name())){
				retVal = W;
			}
			else if(str.equals(NW.name())){
				retVal = NW;
			}
			else if(str.equals(C.name())){
				retVal = C;
			}
			return retVal;
		}	
	};
	
	void handleRoundRectangle(double x, double y, double width,
			double height, double arcWidth, double arcHeight);

	void handleRectangle(double x, double y, double width, double height);

	void handleOval(double x, double y, double width, double height);

	void handleLine(double startX, double startY, double endX, double endY);

	void handlePolygon(double[] pointArr);

	void handlePolyline(double[] pointArr);

	void handlePoint(double x, double y);

	void handleArc(double x, double y, double width, double height,	double offset, double length);
	
	void handleText(double x, double y, TextAlignment alignment, String text);

	void setNoFill();

	void setFillColour(int red, int green, int blue);

	List<Integer> getCurFillColour();

	void setNoLine();

	List<Integer> getCurLineColour();

	void setLineColour(int red, int green, int blue);

	void setFontSize(double fontSize);

	double getCurFontSize();

	String getCurFontStyle();

	void setFontStyle(String styleString);

	void saveGraphicsState();

	void restoreGraphicsState();

	void setLineWidth(double lineWidth);

	List<Double> getCurBounds();
	
	double currentLineWidth();
	
	void setChopHullAnchor();

	void setSemiFixedAnchorCode(PointList points);

	List<Double> getTextBounds(String text);
}
