/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IListPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;

/**
 * @author nhanlon
 *
 */
public class StubListPropertyDefinition extends StubPropertyDefinition implements IListPropertyDefinition {
	public static final List <String> DEFAULT_VALUE = new ArrayList<String>(Arrays.asList(new String []{"1","2'"}));
	public static final String NAME = "ShapeLinkProperty";
	public static final boolean IS_EDITABLE = false;
	public static final boolean IS_VISUALISABLE = true;
	
	public StubListPropertyDefinition() {
		super(NAME, IS_EDITABLE);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#copyProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder, org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	@Override
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProperty) {	
		return propertyBuilder.copyListProperty((IListAnnotationProperty) otherProperty);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#createProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	@Override
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createListProperty(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getDefaultValue()
	 */
	@Override
	public List<String> getDefaultValue() {
		return DEFAULT_VALUE;
	}

}
