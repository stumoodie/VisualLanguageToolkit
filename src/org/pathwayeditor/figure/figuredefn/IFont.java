/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;

/**
 * 
 * IFont
 *
 * @author Stuart Moodie
 *
 */
public interface IFont {
	enum Style { NORMAL, ITALIC, BOLD };

	double getFontSize();

	IFont newSize(double fontSize);

	EnumSet<Style> getStyle();

	IFont newStyle(EnumSet<Style> style);
}
