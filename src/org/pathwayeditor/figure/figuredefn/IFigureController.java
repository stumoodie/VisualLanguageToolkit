package org.pathwayeditor.figure.figuredefn;

import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;

public interface IFigureController {

	void setRequestedEnvelope(Envelope newEnvelope);
	
	Envelope getRequestedEnvelope();
	
	Envelope getEnvelope();
	
	public IConvexHull getConvexHull();

	void setFillColour(RGB newFillColour);
	
	RGB getFillColour();
	
	void setLineColour(RGB newLineColour);
	
	RGB getLineColour();
	
	LineStyle getLineStyle();
	
	void setLineStyle(LineStyle lineStyle);

	double getLineWidth();
	
	void setLineWidth(double lineWidth);

	void setBindBoolean(String name, Boolean value);

	void setBindString(String name, String value);

	void setBindInteger(String name, int value);

	void setBindDouble(String name, double value);
	
	Set<String> getBindVariableNames();
	
	void generateFigureDefinition();
	
	GraphicsInstructionList getFigureDefinition();
	
	IAnchorLocatorFactory getAnchorLocatorFactory();
	
	void addListener(IFigureChangeListener listener);
	
	void removeListener(IFigureChangeListener listener);

	Iterator<IFigureChangeListener> listenerIterator();
}
