/*
Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.businessobjects.impl;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class NumberProperty extends AnnotationProperty implements INumberAnnotationProperty {
	private BigDecimal numberValue;
	private INumberPropertyDefinition propertyDefinition;

	public NumberProperty(AnnotatedCanvasAttribute owner, INumberPropertyDefinition propDefn) {
		super(owner);
		this.propertyDefinition = propDefn;
		this.numberValue = propDefn.getDefaultValue();
	}

	public NumberProperty(AnnotatedCanvasAttribute newOwner, NumberProperty other) {
		super(newOwner);
		numberValue = other.numberValue;
		this.propertyDefinition = other.propertyDefinition;
	}

	@Override
	public void setValue(BigDecimal numberValue) {
		if (numberValue == null)
			throw new IllegalArgumentException("Number value should not be null.");
		if(!numberValue.equals(this.numberValue)){
			BigDecimal oldValue = this.numberValue;
			this.numberValue = numberValue;
			this.getListenerHandler().notifyPropertyChange(oldValue, this.numberValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty#getDefinition()
	 */
	@Override
	public INumberPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	@Override
	public BigDecimal getValue() {
		return this.numberValue;
	}

	@Override
	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (INumberPropertyDefinition) propertyDefinition;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
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
		visitor.visitNumberAnnotationProperty(this);
	}

}
