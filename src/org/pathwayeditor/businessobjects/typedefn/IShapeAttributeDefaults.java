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

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

/**
 * @author smoodie
 *
 */
public interface IShapeAttributeDefaults extends IAnnotatedCanvasAttributeDefaults, IPropertyDefinitionContainer  {
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
}
