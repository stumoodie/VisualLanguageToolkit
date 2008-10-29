/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

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

	Iterator<IPropertyDefinition> propertyDefinitionIterator();

	ILabelAttributeDefaults getDefaultLabelAttributes();
}
