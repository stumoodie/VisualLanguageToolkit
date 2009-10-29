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
package org.pathwayeditor.bussinessobjects.stubs;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumSet;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkConnectionRules;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefinition;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubLinkAttributeDefaultsWithRichText;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubSourceLinkTerminusDefinition;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubTargetLinkTerminusDefinition;
import org.pathwayeditor.figure.figuredefn.rendering.NotationIconGenerator;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * @author smoodie
 *
 */
public class StubLinkObjectType implements ILinkObjectType {
	public static final String EXPECTED_NAME = "linkType";
	public static final String EXPECTED_DESCRIPTION = "linkType description";
	public static final int EXPECTED_UNIQUE_ID = 1;
	private static final String FIGURE_DEFN = null;
	private static final RGB DEFAULT_LINE_COLOUR = RGB.BLACK;
	private static final LineStyle DEFAULT_LINE_STYLE = LineStyle.SOLID;
	private static final double DEFAULT_LINE_WIDTH = 1.0;
	private static final Dimension FIG_SIZE = new Dimension(40, 5);
	
	private final ILinkTerminusDefinition sourceTermDefn = new StubSourceLinkTerminusDefinition(this);
	private final ILinkTerminusDefinition targetTermDefn = new StubTargetLinkTerminusDefinition(this);
	private final ILinkConnectionRules connectionRules = new StubLinkConnectionRules(this); 
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getDefaultLinkAttributes()
	 */
	public ILinkAttributeDefaults getDefaultAttributes() {
		return new StubLinkAttributeDefaultsWithRichText();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getEditiableAttributes()
	 */
	public EnumSet<LinkEditableAttributes> getEditableAttributes() {
		return EnumSet.noneOf(LinkEditableAttributes.class);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getLinkConnectionRules()
	 */
	public ILinkConnectionRules getLinkConnectionRules() {
		return this.connectionRules;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getSourceTerminusDefinition()
	 */
	public ILinkTerminusDefinition getSourceTerminusDefinition() {
		return this.sourceTermDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getTargetTerminusDefinition()
	 */
	public ILinkTerminusDefinition getTargetTerminusDefinition() {
		return this.targetTermDefn;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDefiningContext()
	 */
	public INotationSyntaxService getSyntaxService() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
	 */
	public String getDescription() {
		return EXPECTED_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
	 */
	public String getName() {
		return EXPECTED_NAME;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IObjectType o) {
		throw new UnsupportedOperationException("do not use!");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkObjectType#getUniqueId()
	 */
	public int getUniqueId() {
		return EXPECTED_UNIQUE_ID;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getIconAsSvgStream()
	 */
	public InputStream getIconAsSvgStream() {
		NotationIconGenerator iconGen = new NotationIconGenerator();
		iconGen.setFigureDefn(FIGURE_DEFN);
		iconGen.setFillColour(null);
		iconGen.setLineColour(DEFAULT_LINE_COLOUR);
		iconGen.setLineStyle(DEFAULT_LINE_STYLE);
		iconGen.setLineWidth(DEFAULT_LINE_WIDTH);
		iconGen.setProperties(new ArrayList<IPropertyDefinition>(0).iterator());
		iconGen.setSize(FIG_SIZE);
		iconGen.buildSvg();
		return iconGen.getSvgAsInputStream();
	}

}
