/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.figuredefn;

import java.util.EnumSet;

/**
 * 
 * IFont is an interface that defines an abstract font for use in a figure definition. The interface
 * provides the basic attributes of a font, such as size and style.
 * The <code>NORMAL</code> and <code>ITALIC</code> styles are mutually exclusive, while the BOLD style must
 * be used in combination with the other two styles.
 *
 * It is envisaged that a class implementing this interface will be immutable.
 *
 * @author Stuart Moodie
 *
 */
public interface IFont {
	/**
	 * 
	 * Style is an enumerated type that provides the style options for the font.
	 *
	 * @author Stuart Moodie
	 *
	 */
	enum Style { NORMAL, ITALIC, BOLD };

	/**
	 * Gets the font size in points.
	 * @return the font size, which must be positive.
	 */
	double getFontSize();

	/**
	 * Create an new font with the new font size and the same styles as this instance. 
	 * @param fontSize the new font size.
	 * @return a new instance of a font with the new font size.
	 */
	IFont newSize(double fontSize);

	/**
	 * Get the styles in use for this font.
	 * @return an enumerated set of the styles used by this font.
	 */
	EnumSet<Style> getStyle();

	/**
	 * Create an new font with the new font styles and the same size as this instance. 
	 * @param styles the new font styles.
	 * @return a new instance of a font with the new font styles.
	 */
	IFont newStyle(EnumSet<Style> styles);
}
