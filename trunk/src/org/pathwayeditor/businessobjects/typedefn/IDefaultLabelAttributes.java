package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

/**
 * <br>$Id: ILabelDefinition.java,v 1.1 2008/06/14 13:18:08 asorokin Exp $
 * @author Anatoly Sorokin
 * @date 12 Jun 2008
 * 
 */
public interface IDefaultLabelAttributes { 

	Size getSize();

	LineStyle getLineStyle();

	RGB getLineColour();

	RGB getFillColour();

	int getLineWidth();
}
