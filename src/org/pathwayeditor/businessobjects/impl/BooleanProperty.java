/*
Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.businessobjects.impl;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class BooleanProperty extends AnnotationProperty implements IBooleanAnnotationProperty {
	private static final long serialVersionUID = 3415354271386318411L;
	private Boolean boolValue;
	private IBooleanPropertyDefinition propertyDefinition;

	public BooleanProperty(AnnotatedCanvasAttribute owner, IBooleanPropertyDefinition propDefn) {
		super(owner);
		this.propertyDefinition = propDefn;
		this.boolValue = propDefn.getDefaultValue();
	}

	public BooleanProperty(AnnotatedCanvasAttribute newOwner, BooleanProperty other) {
		super(newOwner);
		boolValue = other.boolValue;
		this.propertyDefinition = other.propertyDefinition;
	}

	@Override
	public void setValue(Boolean numberValue) {
		if(!numberValue.equals(this.boolValue)){
			Boolean oldValue = this.boolValue;
			this.boolValue = numberValue;
			this.getListenerHandler().notifyPropertyChange(oldValue, this.boolValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty#getDefinition()
	 */
	@Override
	public IBooleanPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	@Override
	public Boolean getValue() {
		return this.boolValue;
	}

	@Override
	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IBooleanPropertyDefinition) propertyDefinition;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visit(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor)
	 */
	@Override
	public void visit(IAnnotationPropertyVisitor visitor) {
		visitor.visitBooleanAnnotationProperty(this);
	}
}
