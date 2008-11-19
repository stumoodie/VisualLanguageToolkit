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
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

/**
 * @author nhanlon
 *
 */
public class StubListPropertyDefinition implements IListPropertyDefinition {
	public static final List <String> DEFAULT_VALUE = new ArrayList(Arrays.asList(new String []{"1","2'"}));
	public static final String NAME = "ShapeLinkProperty";
	public static final boolean IS_EDITABLE = false;
	public static final boolean IS_VISUALISABLE = true;
	
	private final ILabelAttributeDefaults labelDefaults= new StubLabelAttributeDefaults();
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#copyProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder, org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder,
			IAnnotationProperty otherProperty) {	
		return propertyBuilder.copyListProperty((IListAnnotationProperty) otherProperty);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#createProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createListProperty(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getDefaultValue()
	 */
	public List<String> getDefaultValue() {
		return DEFAULT_VALUE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getLabelDefaults()
	 */
	public ILabelAttributeDefaults getLabelDefaults() {
		return labelDefaults;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#isEditable()
	 */
	public boolean isEditable() {
		return IS_EDITABLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#isVisualisable()
	 */
	public boolean isVisualisable() {
		return IS_VISUALISABLE;
	}

}
