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
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;

/**
 * @author smoodie
 *
 */
public class FallbackLinkAttributeDefaults implements ILinkAttributeDefaults {
	private static final Set<IPropertyDefinition> PROP_SET = Collections.emptySet();
	private static final String NAME = "Default Link";
	private static final int LINE_WIDTH = 0;
	private static final LineStyle LINE_STYLE = LineStyle.SOLID;
	private static final RGB LINE_COLOUR = RGB.BLACK;
	private static final String DETAILED_DESCN = "Default link Detailed Descn";
	private static final String DESCN = "Default Link Desn";
	private static final ILabelAttributeDefaults LABEL_ATTRIBUTES = new FallbackLabelAttributeDefaults();

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getDefaultLabelAttributes()
	 */
	public ILabelAttributeDefaults getDefaultLabelAttributes() {
		return LABEL_ATTRIBUTES;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getDescription()
	 */
	public String getDescription() {
		return DESCN;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getDetailedDescription()
	 */
	public String getDetailedDescription() {
		return DETAILED_DESCN;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getLineColour()
	 */
	public RGB getLineColour() {
		return LINE_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		return LINE_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getLineWidth()
	 */
	public double getLineWidth() {
		return LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#getPropertyDefinitionFilter()
	 */
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return PROP_SET.iterator();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#containsPropertyDefinition(java.lang.String)
	 */
	public boolean containsPropertyDefinition(String name) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#getPropertyDefinition(java.lang.String)
	 */
	public IPropertyDefinition getPropertyDefinition(String name) {
		throw new IllegalArgumentException("No property definition of this name exists");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#numPropertyDefinitions()
	 */
	public int numPropertyDefinitions() {
		return PROP_SET.size();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#containsPropertyDefinition(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean containsPropertyDefinition(IPropertyDefinition name) {
		return false;
	}

}
