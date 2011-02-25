/*
Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class IntegerProperty extends AnnotationProperty implements IIntegerAnnotationProperty {
	private Integer numberValue;
	private IIntegerPropertyDefinition propertyDefinition;

	public IntegerProperty(AnnotatedCanvasAttribute owner, IIntegerPropertyDefinition propDefn) {
		super(owner);
		this.propertyDefinition = propDefn;
		this.numberValue = propDefn.getDefaultValue();
	}

	public IntegerProperty(AnnotatedCanvasAttribute newOwner, IntegerProperty other) {
		super(newOwner);
		numberValue = other.numberValue;
		this.propertyDefinition = other.propertyDefinition;
	}

	@Override
	public void setValue(Integer numberValue) {
		if (numberValue == null)
			throw new IllegalArgumentException("Number value should not be null.");
		if(!numberValue.equals(this.numberValue)){
			Integer oldValue = this.numberValue;
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
	public IIntegerPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	@Override
	public Integer getValue() {
		return this.numberValue;
	}

	@Override
	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IIntegerPropertyDefinition) propertyDefinition;
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
		visitor.visitIntegerAnnotationProperty(this);
	}
}
