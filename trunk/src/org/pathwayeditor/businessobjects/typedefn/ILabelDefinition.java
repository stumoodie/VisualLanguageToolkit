package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;

/**
 * <br>$Id: ILabelDefinition.java,v 1.1 2008/06/14 13:18:08 asorokin Exp $
 * @author Anatoly Sorokin
 * @date 12 Jun 2008
 * 
 */
public interface ILabelDefinition { 
	
	boolean hasFill();
	
	boolean hasLine();
	
	LineStyle getLineStyle();

	int getLineColourRed();

	int getLineColourGreen();

	int getLineColourBlue();

	int getFillColourRed();

	int getFillColourGreen();

	int getFillColourBlue();

	int getLineWidth();
}


/*
 * $Log: ILabelDefinition.java,v $
 * Revision 1.1  2008/06/14 13:18:08  asorokin
 * Configurable Labels. At the moment the only focus was transparency (noFill) and borders(noLine).
 *
 */