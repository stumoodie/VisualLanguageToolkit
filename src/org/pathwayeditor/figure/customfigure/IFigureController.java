package org.pathwayeditor.figure.customfigure;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;

public interface IFigureController {

	double getLineWidth();
	
	void setLineWidth(double lineWidth);

	void setEnvelope(Envelope newEnvelope);
	
	Envelope getEnvelope();
	
	void setFillColour(RGB newFillColour);
	
	RGB getFillColour();
	
	void setLineColour(RGB newLineColour);
	
	RGB getLineColour();

	public IConvexHull getConvexHull();

	void setBindBoolean(String name, Boolean value);

	void setBindString(String name, String value);

	void setBindInteger(String name, int value);

	void setBindDouble(String name, double value);
	
	void generateFigureDefinition();
	
	GraphicsInstructionList getFigureDefinition();
}
