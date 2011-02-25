/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LabelLocationPolicy;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.figure.geometry.Dimension;

public interface ILabelAttributeDefaults { 
	LabelLocationPolicy getLabelLocationPolicy();
	
	RGB getLineColour();

	RGB getFillColour();
	
	boolean hasNoFill();
	
	boolean hasNoBorder();
	
	Dimension getMinimumSize();

	LineStyle getLineStyle();

	double getLineWidth();
}
