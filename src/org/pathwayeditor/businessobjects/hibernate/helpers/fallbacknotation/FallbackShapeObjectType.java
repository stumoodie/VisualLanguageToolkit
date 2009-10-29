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
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import java.io.InputStream;
import java.util.EnumSet;

import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;
import org.pathwayeditor.figure.figuredefn.rendering.NotationIconGenerator;


public class FallbackShapeObjectType implements IShapeObjectType {
	private static final IShapeAttributeDefaults ATTRIBUTE_DEFAULTS = new FallbackShapeAttributeDefaults();
	private final HibObjectType hibObjectType;
	private final INotationSyntaxService syntaxService;
	private final IShapeParentingRules parentingRules;
	
	public FallbackShapeObjectType(INotationSyntaxService context, HibObjectType hibObjectType){
		this.syntaxService = context;
		this.hibObjectType = hibObjectType;
		this.parentingRules = new FallbackShapeParentingRules(this);
	}
	
	public String getDescription() {
		return this.hibObjectType.getDescription();
	}

	public String getName() {
		return this.hibObjectType.getName();
	}

	public IShapeParentingRules getParentingRules() {
		return this.parentingRules;
	}

	public int getUniqueId() {
		return this.hibObjectType.getUniqueId();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getDefaultAttributes()
	 */
	public IShapeAttributeDefaults getDefaultAttributes() {
		return ATTRIBUTE_DEFAULTS;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeObjectType#getEditableAttributes()
	 */
	public EnumSet<EditableShapeAttributes> getEditableAttributes() {
		return EnumSet.noneOf(EditableShapeAttributes.class);
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
	 */
	public INotationSyntaxService getSyntaxService() {
		return this.syntaxService;
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		return this.getUniqueId() == o.getUniqueId() ? 0 : this.getUniqueId() < o.getUniqueId() ? -1 : 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.syntaxService == null) ? 0 : this.syntaxService.hashCode());
		result = prime * result + this.getUniqueId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FallbackShapeObjectType))
			return false;
		FallbackShapeObjectType other = (FallbackShapeObjectType) obj;
		if (this.syntaxService == null) {
			if (other.syntaxService != null)
				return false;
		} else if (!this.syntaxService.equals(other.syntaxService))
			return false;
		if (this.getUniqueId() != other.getUniqueId())
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getIconAsSvgStream()
	 */
	public InputStream getIconAsSvgStream() {
		NotationIconGenerator iconGen = new NotationIconGenerator();
		iconGen.setFigureDefn(ATTRIBUTE_DEFAULTS.getShapeDefinition());
		iconGen.setFillColour(ATTRIBUTE_DEFAULTS.getFillColour());
		iconGen.setLineColour(ATTRIBUTE_DEFAULTS.getLineColour());
		iconGen.setLineStyle(ATTRIBUTE_DEFAULTS.getLineStyle());
		iconGen.setLineWidth(ATTRIBUTE_DEFAULTS.getLineWidth());
		iconGen.setProperties(ATTRIBUTE_DEFAULTS.propertyDefinitionIterator());
		iconGen.setSize(ATTRIBUTE_DEFAULTS.getSize());
		iconGen.buildSvg();
		return iconGen.getSvgAsInputStream();
	}
}
