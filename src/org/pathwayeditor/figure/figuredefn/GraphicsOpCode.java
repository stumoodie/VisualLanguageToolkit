/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.figure.figuredefn;

/**
 * 
 * GraphicsOpCode is an enumerated type defining the OpCodes used to encode the primitive operations required to render the
 * figure on a graphics device..
 *
 * @author Stuart Moodie
 *
 */
public enum GraphicsOpCode {
	DRAW_RECT, FILL_RECT, DRAW_RRECT, FILL_RRECT, DRAW_OVAL, FILL_OVAL, DRAW_ARC, FILL_ARC,
	DRAW_POLYGON, FILL_POLYGON, DRAW_POLYLINE, DRAW_LINE, DRAW_POINT, DRAW_TEXT, FILL_TEXT,
	FILL_COLOUR, LINE_COLOUR, FONT_STYLE, FONT_SIZE, LINE_WIDTH, LINE_STYLE
}