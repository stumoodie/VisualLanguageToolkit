/*
Copyright 2009-2011, Court of the University of Edinburgh
*/
package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPlainTextPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class TextProperty extends AnnotationProperty implements IPlainTextAnnotationProperty {
	private static final long serialVersionUID = 2408749022194227955L;

	private String textValue;
	private IPlainTextPropertyDefinition propertyDefinition;

	public TextProperty(AnnotatedCanvasAttribute owner,IPlainTextPropertyDefinition defn) {
		super(owner);
		this.propertyDefinition = defn;
		this.textValue = defn.getDefaultValue();
	}

	public TextProperty(AnnotatedCanvasAttribute newOwner, TextProperty other) {
		super(newOwner);
		this.propertyDefinition = other.propertyDefinition;
		this.textValue = other.textValue;
	}

	@Override
	public void setValue(String textValue) {
		if (textValue == null)
			throw new IllegalArgumentException("Text value cannot be null.");

		if(!textValue.equals(this.textValue)){
			String oldValue = this.textValue;
			this.textValue = textValue;
			this.getListenerHandler().notifyPropertyChange(oldValue, this.textValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IPlainTextAnnotationProperty#getDefinition()
	 */
	@Override
	public IPlainTextPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.pathwayeditor.businessobjects.drawingprimitives.properties.
	 * IAnnotationProperty#getValue()
	 */
	@Override
	public String getValue() {
		return textValue;
	}

	@Override
	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IPlainTextPropertyDefinition) propertyDefinition;
	}

//	/* (non-Javadoc)
//	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
//	 */
//	@Override
//	public boolean canVisualiseProperty() {
//		return this.propertyDefinition.isVisualisable();
//	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visit(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor)
	 */
	@Override
	public void visit(IAnnotationPropertyVisitor visitor) {
		visitor.visitPlainTextAnnotationProperty(this);
	}

}
