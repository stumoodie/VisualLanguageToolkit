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
public class StubShapeAttributeDefaultsWithPlainText extends StubShapeAttributeDefaults{
public final Set<IPropertyDefinition> propertyDefinitionList;
	
	
	public StubShapeAttributeDefaultsWithPlainText(){
		this.propertyDefinitionList = new HashSet<IPropertyDefinition>();
		this.propertyDefinitionList.add(new StubTextPropertyDefinition());
	}
	/* (non-Javadoc)
	 * @see org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAttributeDefaults#getpropdefList()
	 */
	@Override
	protected Set<IPropertyDefinition> getpropdefns() {
		return propertyDefinitionList;
	}
}
