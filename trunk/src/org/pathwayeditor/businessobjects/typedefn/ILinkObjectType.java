package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;

public interface ILinkObjectType extends IObjectType {

	/**
	 * Get the line style of the object type.
	 * @return a non-null LineStyle.
	 */
	LineStyle getLineStyle();

	/**
	 * Get the width of the line drawing the link. 
	 * @return the line width.
	 */
	int getLineWidth();

	/**
	 * The red component of the RGB fill colour. The number can be between 0 and 255.
	 * @return a red colour value.
	 */
	int getLineColourRed();

	/**
	 * The green component of the RGB fill colour. The number can be between 0 and 255.
	 * @return a green colour value.
	 */
	int getLineColourGreen();

	/**
	 * The blue component of the RGB fill colour. The number can be between 0 and 255.
	 * @return a blue colour value.
	 */
	int getLineColourBlue();
	
	/**
	 * Get the definition of the link's source.
	 * @return A non-null link end definition.
	 */
	ILinkEndDefinition getLinkSource();
	
	/**
	 * Get the definition of the link's target.
	 * @return A non-null link end definition.
	 */
	ILinkEndDefinition getLinkTarget();
	
	/**
	 * Get the context configurable properties via a utility class.
	 * The filter should be aware of changes made to the properties list
	 * in the ILinkObjectType instance.
	 * @return A non-null instance of IPropertyDefinitionFilter.
	 */
	IPropertyDefinitionFilter getPropertyDefinitionFilter();
	
	/**
	 * Gets the connection rules for this link.
	 * @return A non-null instance of the connection rules.
	 */
	ILinkConnectionRules getLinkConnectionRules();

	/**
	 * Determines whether the line colour of the link can be modified. 
	 * @return true is it editable, false otherwise.
	 */
	boolean isLineColourEditable();
	
	/**
	 * Determines whether the line width of the link can be modified. 
	 * @return true is it editable, false otherwise.
	 */
	boolean isLineWidthEditable();

	/**
	 * Determines whether the line style of the link can be modified. 
	 * @return true is it editable, false otherwise.
	 */
	boolean isLineStyleEditable();
}
