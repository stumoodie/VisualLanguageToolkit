package org.pathwayeditor.figurevm;

import java.util.List;

import org.pathwayeditor.figure.geometry.PointList;

public 	interface IOpCodeHandler {
	void handleRoundRectangle(double x, double y, double width,
			double height, double arcWidth, double arcHeight);

	void handleRectangle(double x, double y, double width, double height);

	void handleOval(double x, double y, double width, double height);

	void handleLine(double startX, double startY, double endX, double endY);

	void handlePolygon(double[] pointArr);

	void handlePolyline(double[] pointArr);

	void handlePoint(double x, double y);

	void handleArc(double x, double y, double width, double height,	double offset, double length);
	
	void handleText(double x, double y, String text);

	void setNoFill();

	void setFillColour(int red, int green, int blue);

	List<Integer> getCurFillColour();

	void setNoLine();

	List<Integer> getCurLineColour();

	void setLineColour(int red, int green, int blue);

	void setFontSize(int fontSize);

	int getCurFontSize();

	String getCurFontStyle();

	void setFontStyle(String styleString);

	void saveGraphicsState();

	void restoreGraphicsState();

	double getTextHeight(String text);

	double getTextLength(String text);

	void setLineWidth(double lineWidth);

	List<Double> getCurBounds();
	
	double currentLineWidth();
	
	void setChopHullAnchor();

	void setSemiFixedAnchorCode(PointList points);
}
