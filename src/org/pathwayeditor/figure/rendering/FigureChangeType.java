/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.rendering;

/**
 * FigureChangeType is an enumerated type that defines the kind of change
 * events that may be handled by a {@link IFigureRenderingControllerAttributeChangeEvent}. 
 * 
 * @author Stuart Moodie
 *
 */
public enum FigureChangeType {
	BOUND_VALUE, ANCHOR, FIGURE_DEFN, LINE_WIDTH, LINE_STYLE, LINE_COLOUR, FILL_COLOUR, REQUESTED_ENVELOPE;
}
