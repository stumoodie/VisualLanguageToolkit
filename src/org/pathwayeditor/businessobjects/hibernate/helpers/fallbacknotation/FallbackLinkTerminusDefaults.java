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

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.figure.geometry.Dimension;

/**
 * @author smoodie
 *
 */
public class FallbackLinkTerminusDefaults implements ILinkTerminusDefaults {
	private static final LinkEndDecoratorShape DEFAULT_ARROWHEAD = LinkEndDecoratorShape.NONE;
	private static final Dimension DEFAULT_END_SIZE = new Dimension(0, 0);
	private static final short DEFAULT_GAP = 0;
	private static final RGB DEFAULT_COLOUR = new RGB(0, 0, 0);
	private static final Dimension DEFAULT_TERM_SIZE = new Dimension(0, 0);
	private static final ILabelAttributeDefaults LABEL_DEFAULTS = new FallbackLabelAttributeDefaults();
	
	public FallbackLinkTerminusDefaults(){
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getDefaultLabelAttributes()
	 */
	public ILabelAttributeDefaults getDefaultLabelAttributes() {
		return LABEL_DEFAULTS;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndDecoratorType()
	 */
	public LinkEndDecoratorShape getEndDecoratorType() {
		return DEFAULT_ARROWHEAD;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndSize()
	 */
	public Dimension getEndSize() {
		return DEFAULT_END_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getGap()
	 */
	public double getGap() {
		return DEFAULT_GAP;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermColour()
	 */
	public RGB getTermColour() {
		return DEFAULT_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermSize()
	 */
	public Dimension getTermSize() {
		return DEFAULT_TERM_SIZE;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#propertyDefinitionIterator()
	 */
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		final Set<IPropertyDefinition> collection = Collections.emptySet(); 
		return collection.iterator();
	}

	/**
	 * @return
	 */
	private Set<IPropertyDefinition> getpropdefns() {
		return Collections.emptySet();
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


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionContainer#containsPropertyDefinition(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition)
	 */
	public boolean containsPropertyDefinition(IPropertyDefinition name) {
		return false;
	}
}
