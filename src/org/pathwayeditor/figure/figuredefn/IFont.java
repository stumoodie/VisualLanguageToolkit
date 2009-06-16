package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;

import org.pathwayeditor.figure.geometry.Dimension;

public interface IFont {
	enum Style { NORMAL, ITALIC, BOLD };

	int getFontSize();

	void setSize(int fontSize);

	EnumSet<Style> getStyle();

	void setStyle(EnumSet<Style> style);
	
	Dimension getStringExtends(String str);

	int getFontWidth();
	
}
