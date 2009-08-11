package org.pathwayeditor.figure.figuredefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.figuredefn.GraphicsInstruction.GraphicalTextAlignment;

public interface IGraphicsEngine {

	void drawPoint(double pos, double pos2);

	void fillArc(double pos, double pos2, double widthSize, double heightSize, double roundedOffset, double roundedLength);

	void drawArc(double pos, double pos2, double widthSize, double heightSize, double roundedOffset, double roundedLength);

	void fillPolygon(double[] pointArr);

	void drawPolygon(double[] pointArr);

	void drawPolyline(double[] pointArr);

	void fillOval(double pos, double pos2, double widthSize, double heightSize);

	void drawOval(double pos, double pos2, double widthSize, double heightSize);

	void fillRectangle(double pos, double pos2, double widthSize, double heightSize);

	void drawRoundRectangle(double x, double y, double width, double height, double arcWidthSize, double arcHeightSize);

	void fillRoundRectangle(double x, double y, double width, double height, double arcWidthSize, double arcHeightSize);

	void drawRectangle(double pos, double pos2, double widthSize, double heightSize);

	void drawLine(double beginPos, double beginPos2, double endPos, double endPos2);

	void fillString(String text, double pos, double pos2, GraphicalTextAlignment alignment);

	void drawString(String text, double pos, double pos2, GraphicalTextAlignment alignment);

	void setFillColor(RGB color);

	void setLineColor(RGB color);

	void setLineWidth(double lineWidthVal);

	void setFont(IFont modifiedFont);

	void setLineStyle(LineStyle values);

}
