/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.HashSet;
import java.util.Set;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

/**
 * @author nhanlon
 *
 */
public class StubLinkAttributeDefaultsWithRichText extends StubLinkAttributeDefaults{

	public final Set<IPropertyDefinition> propertyDefinitionList;
	
	
	public StubLinkAttributeDefaultsWithRichText()  {
		this.propertyDefinitionList = new HashSet<IPropertyDefinition>();
		this.propertyDefinitionList.add(new StubHtmlPropertyDefinition());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAttributeDefaults#getpropdefList()
	 */
	@Override
	protected Set<IPropertyDefinition> getpropdefns() {
		return propertyDefinitionList;
	}
}
