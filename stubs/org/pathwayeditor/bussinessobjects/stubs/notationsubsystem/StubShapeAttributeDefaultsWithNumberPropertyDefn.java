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
public class StubShapeAttributeDefaultsWithNumberPropertyDefn extends StubShapeAttributeDefaults {

public final Set<IPropertyDefinition> propertyDefinitionList;
	
	public StubShapeAttributeDefaultsWithNumberPropertyDefn() {
		this.propertyDefinitionList = new HashSet<IPropertyDefinition>();
		this.propertyDefinitionList.add(new StubNumberPropertyDefinition());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAttributeDefaults#getpropdefList()
	 */
	@Override
	protected Set<IPropertyDefinition> getpropdefns() {
		return propertyDefinitionList;
	}
}
