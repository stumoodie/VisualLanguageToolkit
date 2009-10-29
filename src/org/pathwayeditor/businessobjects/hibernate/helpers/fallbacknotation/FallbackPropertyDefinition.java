/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibProperty;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;

/**
 * @author smoodie
 *
 */
public abstract class FallbackPropertyDefinition implements IPropertyDefinition {
	private final HibProperty property;
	private final ILabelAttributeDefaults labelDefaults;
	
	public FallbackPropertyDefinition(HibProperty property) {
		this.property = property;
		labelDefaults = new FallbackLabelAttributeDefaults();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#copyProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder, org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty)
	 */
	public IAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder, IAnnotationProperty otherProperty) {
		throw new UnsupportedOperationException("This is a falllback notation and is not defigned to be used for write operations");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#createProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder)
	 */
	public IAnnotationProperty createProperty(IPropertyBuilder propertyBuilder) {
		throw new UnsupportedOperationException("This is a falllback notation and is not defigned to be used for write operations");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#getDisplayName()
	 */
	public String getDisplayName() {
		return this.property.getDisplayName();
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
		return this.property.getName();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#isAlwaysDisplayed()
	 */
	public boolean isAlwaysDisplayed() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#isEditable()
	 */
	public boolean isEditable() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition#isVisualisable()
	 */
	public boolean isVisualisable() {
		// it is if it is currently displayed, otherwise it is not
		// since this is read-only this shouldn't matter
		return this.property.isDisplayed();
	}

}
