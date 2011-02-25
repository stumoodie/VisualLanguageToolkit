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
public class StubShapeAttributeDefaultsWithListPropertyDefinition extends StubShapeAttributeDefaults{

public final Set<IPropertyDefinition> propertyDefinitionList;
	
	public StubShapeAttributeDefaultsWithListPropertyDefinition() {
		this.propertyDefinitionList = new HashSet<IPropertyDefinition>();
		this.propertyDefinitionList.add(new StubListPropertyDefinition());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAttributeDefaults#getpropdefList()
	 */
	@Override
	protected Set<IPropertyDefinition> getpropdefns() {
		return propertyDefinitionList;
	}

}
