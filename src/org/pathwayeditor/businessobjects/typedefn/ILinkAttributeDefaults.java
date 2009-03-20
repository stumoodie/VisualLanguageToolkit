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
public interface ILinkAttributeDefaults extends IPropertyDefinitionContainer {
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
	
}
