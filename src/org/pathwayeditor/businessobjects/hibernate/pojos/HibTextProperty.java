package org.pathwayeditor.businessobjects.hibernate.pojos;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class HibTextProperty extends HibProperty implements IPlainTextAnnotationProperty {
	private static final long serialVersionUID = 2408749022194227955L;

	private String textValue;
	private IPlainTextPropertyDefinition propertyDefinition;

	/**
	 * Constructor should only be used by hiberate.
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibTextProperty() {
	}

	public HibTextProperty(HibAnnotatedCanvasAttribute owner,IPlainTextPropertyDefinition defn) {
		super(owner, defn);
		this.propertyDefinition = defn;
		this.textValue = defn.getDefaultValue();
	}

	public HibTextProperty(HibAnnotatedCanvasAttribute newOwner, HibTextProperty other) {
		super(newOwner, other);
		this.propertyDefinition = other.propertyDefinition;
		this.textValue = other.textValue;
	}

	public void setValue(String textValue) {
		if (textValue == null)
			throw new IllegalArgumentException("Text value cannot be null.");

		this.textValue = textValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IPlainTextAnnotationProperty#getDefinition()
	 */
	public IPlainTextPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	public String getValue() {
		return textValue;
	}

	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IPlainTextPropertyDefinition) propertyDefinition;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
	 */
	public boolean canVisualiseProperty() {
		return this.propertyDefinition.isVisualisable();
	}

}
