/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IHtmlPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

/**
 * @author smoodie
 *
 */
public class StubHtmlPropertyDefinition implements IHtmlPropertyDefinition {
	public static final String DEFAULT_VALUE = "<html><head><title>Default Property Value</title></head><body><h1>Default Property</h1></body></html>";
	public static final String NAME = "RichTextProperty";
	public static final boolean IS_EDITABLE = true;
	public static final boolean IS_VISUALISABLE = false;

	private final ILabelAttributeDefaults labelDefaults;
	
	public StubHtmlPropertyDefinition(){
		this.labelDefaults = new StubLabelAttributeDefaults();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#copyProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProp) {
		return propertyBuilder.copyHtmlProperty((IHtmlAnnotationProperty)otherProp);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#createProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.createHtmlProperty(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getDefaultValue()
	 */
	public String getDefaultValue() {
		return DEFAULT_VALUE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getLabelDefaults()
	 */
	public ILabelAttributeDefaults getLabelDefaults() {
		return this.labelDefaults;
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
