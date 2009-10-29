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
import java.util.ArrayList;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectTypeParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectParentingRules;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.figure.figuredefn.rendering.NotationIconGenerator;
import org.pathwayeditor.figure.geometry.Dimension;

public class FallbackRootObjectType implements IRootObjectType {

	private static final String FIGURE_DEFN = "currbounds rect";
	private static final double LINE_WIDTH = 1.0;
	private static final Dimension ICON_SIZE = new Dimension(100.0, 100.0);
	private final INotationSyntaxService syntaxService;
	private final IRootObjectParentingRules parentingRules;
	private final HibObjectType hibObjectType;
	
	public FallbackRootObjectType(INotationSyntaxService syntaxService, HibObjectType hibObjectType){
		this.syntaxService = syntaxService;
		this.hibObjectType = hibObjectType;
		this.parentingRules = new FallbackObjectTypeParentingRules(this);
	}
	
	public IObjectTypeParentingRules getShapeParentingRules() {
		return this.parentingRules;
	}

	public int getUniqueId() {
		return this.hibObjectType.getUniqueId();
	}

	public String getName() {
		return this.hibObjectType.getName();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IRootObjectType#getParentingRules()
	 */
	public IRootObjectParentingRules getParentingRules() {
		return this.parentingRules;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		return hibObjectType.getDescription();
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
		return (this.getUniqueId() == o.getUniqueId()) ?  0 : (this.getUniqueId() < o.getUniqueId()) ? -1 : 1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.syntaxService == null) ? 0 : this.syntaxService.hashCode());
		result = prime * result + this.getUniqueId();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FallbackRootObjectType))
			return false;
		FallbackRootObjectType other = (FallbackRootObjectType) obj;
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
		iconGen.setFigureDefn(FIGURE_DEFN);
		iconGen.setFillColour(RGB.WHITE);
		iconGen.setLineColour(RGB.WHITE);
		iconGen.setLineStyle(LineStyle.SOLID);
		iconGen.setLineWidth(LINE_WIDTH);
		iconGen.setProperties(new ArrayList<IPropertyDefinition>(0).iterator());
		iconGen.setSize(ICON_SIZE);
		iconGen.buildSvg();
		return iconGen.getSvgAsInputStream();
	}
}
