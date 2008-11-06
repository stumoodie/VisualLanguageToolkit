package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

public interface ILabelAttributeDefaults { 

	Size getSize();

	LineStyle getLineStyle();

	RGB getLineColour();

	RGB getFillColour();

	int getLineWidth();
}
