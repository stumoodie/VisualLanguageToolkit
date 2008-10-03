/**
 * 
 */
package org.pathwayeditor.businessobjects.typedefn;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;

/**
 * @author smoodie
 *
 */
public interface ILinkAttributeDefaults {
	String getName();
	
	String getDescription();
	
	String getDetailedDescription();
	
	String getUrl();
	
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
	RGB getLineColour();

	/**
	 * Get the default router type to be used for this link.
	 * @return the router type, cannot be null.
	 */
	ConnectionRouter getRouter();
	
//	/**
//	 * Get the definition of the link's source.
//	 * @return A non-null link end definition.
//	 */
//	ILinkTerminusDefaults getLinkSource();
//	
//	/**
//	 * Get the definition of the link's target.
//	 * @return A non-null link end definition.
//	 */
//	ILinkTerminusDefaults getLinkTarget();
	
	/**
	 * Gets the default for any label associated with this link attribute
	 * @return the label defaults, which cannot be null.
	 */
	ILabelAttributeDefaults getDefaultLabelAttributes();
	
	/**
	 * Get the context configurable properties via a utility class.
	 * The filter should be aware of changes made to the properties list
	 * in the ILinkObjectType instance.
	 * @return A non-null instance of IPropertyDefinitionFilter.
	 */
	IPropertyDefinitionFilter getPropertyDefinitionFilter();
	
}
