package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;

import org.pathwayeditor.figure.geometry.Dimension;

public interface IFont {
	enum Style { NORMAL, ITALIC, BOLD };

	int getFontSize();

	IFont newSize(int fontSize);

	EnumSet<Style> getStyle();

	IFont newStyle(EnumSet<Style> style);
	
	Dimension getStringExtends(String str);

	int getFontWidth();
	
}
