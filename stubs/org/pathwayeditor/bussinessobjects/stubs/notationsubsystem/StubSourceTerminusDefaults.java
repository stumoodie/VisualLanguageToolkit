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

import java.util.HashSet;
import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkTermType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * @author smoodie
 *
 */
public class StubSourceTerminusDefaults implements ILinkTerminusDefaults {
	public static final LinkTermType LINK_END_TYPE = LinkTermType.SOURCE;
	public static final short EXPECTED_OFFSET = 1 ;
	public static final Dimension EXPECTED_END_SIZE = new Dimension(10, 20);
	public static final Dimension EXPECTED_TERM_SIZE = new Dimension(10, 20);
	public static final RGB EXPECTED_TERM_COLOUR = new RGB(100, 200, 190);
	public static final PrimitiveShapeType EXPECTED_TERM_DEC = PrimitiveShapeType.ELLIPSE;
	public static final LinkEndDecoratorShape EXPECTED_END_DEC = LinkEndDecoratorShape.ARROW;
	private final HashSet<IPropertyDefinition> propertyDefinitionList;

	public StubSourceTerminusDefaults() {
		this.propertyDefinitionList = new HashSet<IPropertyDefinition>();
		this.propertyDefinitionList.add(new StubTextPropertyDefinition());
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndDecoratorType()
	 */
	public LinkEndDecoratorShape getEndDecoratorType() {
		return EXPECTED_END_DEC;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndSize()
	 */
	public Dimension getEndSize() {
		return EXPECTED_END_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getGap()
	 */
	public double getGap() {
		return EXPECTED_OFFSET;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermColour()
	 */
	public RGB getTermColour() {
		return EXPECTED_TERM_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermDecoratorType()
	 */
	public PrimitiveShapeType getTermDecoratorType() {
		return EXPECTED_TERM_DEC;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermSize()
	 */
	public Dimension getTermSize() {
		return EXPECTED_TERM_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getPropertiesIterator()
	 */
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return this.propertyDefinitionList.iterator();
	}

	/**
	 * @return
	 */
	private HashSet<IPropertyDefinition> getpropdefns() {
		return this.propertyDefinitionList;
	}

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
	
	public IPropertyDefinition getPropertyDefinition(String name) {
		return findPropDefn(name);
	}

	public int numPropertyDefinitions() {
		return this.getpropdefns().size();
	}

	public boolean containsPropertyDefinition(IPropertyDefinition propDefn){
		return propDefn != null && this.getpropdefns().contains(propDefn);
	}
}
