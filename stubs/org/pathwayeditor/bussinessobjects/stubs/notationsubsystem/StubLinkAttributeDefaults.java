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
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Iterator;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author Stuart Moodie
 *
 */
public abstract class StubLinkAttributeDefaults implements ILinkAttributeDefaults {
	public static final int EXPECTED_DEFAULT_LINE_WIDTH = 3;
	public static final  String EXPECTED_DEFAULT_NAME = "link name";
	public static final  String EXPECTED_DEFAULT_SHAPE_TYPE = "curbounds oval";
	public static final Dimension EXPECTED_DEFAULT_SIZE = new Dimension(15,25);
	public static final String EXPECTED_DEFAULT_URL = "http://www.google.com";
	public static final String EXPECTED_DEFAULT_DESCRIPTION = "descn";
	public static final String EXPECTED_DEFAULT_DETAILED_DESCRIPTION = "detailed descn";
	public static final RGB EXPECTED_DEFAULT_FILL_COLOUR = new RGB(1,2,3);
	public static final RGB EXPECTED_DEFAULT_LINE_COLOUR = new RGB(4,5, 6);
	public static final LineStyle EXPECTED_DEFAULT_LINE_STYLE = LineStyle.DASH_DOT;
	public static final Point EXPECTED_INITIAL_LOCATION = new Point(235,5543);

//	private  ILinkTerminusDefaults sourceTermDefaults = new StubSourceTerminusDefaults();
//	private  ILinkTerminusDefaults targetTermDefaults = new StubTargetTerminusDefaults();

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDefaultLabelAttributes()
	 */
	public ILabelAttributeDefaults getDefaultLabelAttributes() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDescription()
	 */
	public String getDescription() {
		return EXPECTED_DEFAULT_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDetailedDescription()
	 */
	public String getDetailedDescription() {
		return EXPECTED_DEFAULT_DETAILED_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineColour()
	 */
	@Override
	public RGB getLineColour() {
		return EXPECTED_DEFAULT_LINE_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineStyle()
	 */
	@Override
	public LineStyle getLineStyle() {
		return EXPECTED_DEFAULT_LINE_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineWidth()
	 */
	@Override
	public double getLineWidth() {
		return EXPECTED_DEFAULT_LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getName()
	 */
	public String getName() {
		return EXPECTED_DEFAULT_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getLinkSource()
	 */
	public ILinkTerminusDefaults getLinkSource() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getLinkTarget()
	 */
	public ILinkTerminusDefaults getLinkTarget() {
		throw new UnsupportedOperationException("not implemented");
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getUrl()
	 */
	public String getUrl() {
		return EXPECTED_DEFAULT_URL;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#propertyIterator()
	 */
	@Override
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return getpropdefns().iterator();
	}

	/**
	 * @return
	 */
	protected abstract Set<IPropertyDefinition> getpropdefns() ;
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#containsPropertyDefinition(java.lang.String)
	 */
	@Override
	public boolean containsPropertyDefinition(String name) {
		return findPropDefn(name) != null;
	}

	IPropertyDefinition findPropDefn(String name){
		IPropertyDefinition retVal = null;
		for(IPropertyDefinition propDefn : this.getpropdefns()){
			if(propDefn.getName().equals(name)){
				retVal = propDefn;
				break;
			}
		}
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#getPropertyDefinition(java.lang.String)
	 */
	@Override
	public IPropertyDefinition getPropertyDefinition(String name) {
		return findPropDefn(name);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#numPropertyDefinitions()
	 */
	@Override
	public int numPropertyDefinitions() {
		return this.getpropdefns().size();
	}
	
	@Override
	public boolean containsPropertyDefinition(IPropertyDefinition propDefn){
		return propDefn != null && this.getpropdefns().contains(propDefn);
	}
}
