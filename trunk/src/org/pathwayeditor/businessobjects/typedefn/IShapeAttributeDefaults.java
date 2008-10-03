/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

/**
 * @author smoodie
 *
 */
public interface IShapeAttributeDefaults {
	String getName();

	String getDescription();

	String getDetailedDescription();

	PrimitiveShapeType getShapeType();

	String getURL();

	Size getSize();

	LineStyle getLineStyle();

	RGB getLineColour();

	RGB getFillColour();

	int getLineWidth();

	IPropertyDefinitionFilter getPropertiesFilter();

	ILabelAttributeDefaults getDefaultLabelAttributes();
}
