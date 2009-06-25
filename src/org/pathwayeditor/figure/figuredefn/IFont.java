package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;

public interface IFont {
	enum Style { NORMAL, ITALIC, BOLD };

	double getFontSize();

	IFont newSize(double fontSize);

	EnumSet<Style> getStyle();

	IFont newStyle(EnumSet<Style> style);
}
