/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;

/**
 * 
 * IGraphicsEngine is an interface that defines the primitive operations that must be supported by a
 * graphics engine that renders a figure definition.
 *
 * @author Stuart Moodie
 *
 */
public interface IGraphicsEngine {

	void drawPoint(double x, double y);

	void fillArc(double x, double y, double w, double h, double roundedOffset, double roundedLength);

	void drawArc(double x, double y, double w, double h, double roundedOffset, double roundedLength);

	void fillPolygon(double[] pointArr);

	void drawPolygon(double[] pointArr);

	void drawPolyline(double[] pointArr);

	void fillOval(double x, double y, double w, double h);

	void drawOval(double pos, double pos2, double w, double h);

	void fillRectangle(double pos, double pos2, double w, double h);

	void drawRoundRectangle(double x, double y, double w, double h, double arcWidthSize, double arcHeightSize);

	void fillRoundRectangle(double x, double y, double w, double h, double arcWidthSize, double arcHeightSize);

	void drawRectangle(double x, double y, double w, double h);

	void drawLine(double x1, double y1, double x2, double y2);

	void fillString(String text, double x, double y, GraphicalTextAlignment alignment);

	void drawString(String text, double x, double y, GraphicalTextAlignment alignment);

	void setFillColor(RGB color);

	void setLineColor(RGB color);

	void setLineWidth(double lineWidth);

	void setFont(IFont newFont);

	void setLineStyle(LineStyle lineStyle);

}
