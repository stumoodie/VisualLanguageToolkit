/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

/**
 * @author Stuart Moodie
 *
 */
public class StubNumberPropertyDefinition extends StubPropertyDefinition implements INumberPropertyDefinition {
	public static final BigDecimal DEFAULT_VALUE = new BigDecimal(2468);
	public static final String NAME = "NumberProperty";
	public static final boolean IS_EDITABLE = false;
	private final BigDecimal value;

	public StubNumberPropertyDefinition(){
		super(NAME, IS_EDITABLE);
		this.value = DEFAULT_VALUE;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#copyProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	@Override
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProp) {
		return propertyBuilder.copyNumberProperty((INumberAnnotationProperty)otherProp);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#createProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	@Override
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createNumberProperty(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getDefaultValue()
	 */
	@Override
	public BigDecimal getDefaultValue() {
		return value;
	}

}
