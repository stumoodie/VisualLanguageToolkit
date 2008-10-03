package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;


/**
 * Define the link end appearance and default properties. The link end has a decorator at the end of
 *  the link which is typically an arrowhead or some kind and a decorator at the terminus of the link
 *  which can be any of the shape primitives. The offset defines the gap between both of these. The line
 *  drawn for the link finished at the link end decorator (hence the name).
 *    
 * @author smoodie
 *
 */
public interface ILinkTerminusDefaults {
	/**
	 * Get the offset value. The default is zero.
	 * @return the gap size, which cannot be negative: <code>getGap() >= 0</code>.
	 */
	short getGap();
	
	/**
	 * Get the property filter to access the properties.
	 * @return Returns a property filter. Guaranteed to be not null.
	 */
	IPropertyDefinitionFilter getPropertiesFilter();

	/**
	 * get the shape type of the decorator.
	 * @return the shape type.
	 */
	PrimitiveShapeType getTermDecoratorType();

	/**
	 * Get Decorator size 
	 * @return a positive integer value.
	 */
	Size getTermSize();

	/**
	 * Get the fill and line RGB colour.
	 * @return and RBG instance.
	 */
	RGB getTermColour();

	/**
	 * Get the arrowhead style associated with the decorator.
	 * @return A non-null instance of an arrowhead type.
	 */
	LinkEndDecoratorShape getEndDecoratorType();

	/**
	 * Get the width of the decorator (arrowhead typically). 
	 * @return a non-negative integer value.
	 */
	Size getEndSize();

	ILabelAttributeDefaults getDefaultLabelAttributes();
	
}
