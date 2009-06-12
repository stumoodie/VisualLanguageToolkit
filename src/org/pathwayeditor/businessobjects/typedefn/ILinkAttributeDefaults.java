/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
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
public interface ILinkAttributeDefaults extends IAnnotatedCanvasAttributeDefaults, IPropertyDefinitionContainer  {
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
