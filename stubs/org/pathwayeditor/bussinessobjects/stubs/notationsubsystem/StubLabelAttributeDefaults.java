/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LabelLocationPolicy;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.figure.figuredefn.GenericFont;
import org.pathwayeditor.figure.figuredefn.IFont;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * @author Stuart Moodie
 *
 */
public class StubLabelAttributeDefaults implements ILabelAttributeDefaults {
	public static final LineStyle LINE_STYLE = LineStyle.SOLID;
	public static final int LINE_WIDTH = 1;
	public static final RGB FILL_COLOUR = RGB.WHITE;
	public static final RGB LINE_COLOUR = RGB.BLACK;
	public static final Dimension SIZE = new Dimension(20, 10);

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getFillColour()
	 */
	@Override
	public RGB getFillColour() {
		return FILL_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLineColour()
	 */
	@Override
	public RGB getLineColour() {
		return LINE_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLineStyle()
	 */
	@Override
	public LineStyle getLineStyle() {
		return LINE_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLineWidth()
	 */
	@Override
	public double getLineWidth() {
		return LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getSize()
	 */
	public Dimension getSize() {
		return SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLabelLocationPolicy()
	 */
	@Override
	public LabelLocationPolicy getLabelLocationPolicy() {
		return LabelLocationPolicy.CENTRE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getFont()
	 */
	public IFont getFont() {
		return new GenericFont();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getMinimumSize()
	 */
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(10.0, 10.0);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#hasNoBorder()
	 */
	@Override
	public boolean hasNoBorder() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#hasNoFill()
	 */
	@Override
	public boolean hasNoFill() {
		return false;
	}

}
