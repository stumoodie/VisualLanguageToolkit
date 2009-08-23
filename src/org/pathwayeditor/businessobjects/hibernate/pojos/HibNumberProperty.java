/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
 */
package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.math.BigDecimal;

import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyBuilder;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;

public class HibNumberProperty extends HibProperty implements INumberAnnotationProperty {
	private static final long serialVersionUID = 1951406655193239331L;

	private BigDecimal numberValue;
	private INumberPropertyDefinition propertyDefinition;
	/**
	 * Constructor should only be used by hiberate.
	 * 
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibNumberProperty() {
	}

	public HibNumberProperty(HibAnnotatedCanvasAttribute owner, INumberPropertyDefinition propDefn) {
		super(owner, propDefn);
		this.propertyDefinition = propDefn;
		this.numberValue = propDefn.getDefaultValue();
	}

	public HibNumberProperty(HibAnnotatedCanvasAttribute newOwner, HibNumberProperty other) {
		super(newOwner, other);
		numberValue = other.numberValue;
		this.propertyDefinition = other.propertyDefinition;
	}

	public void setValue(BigDecimal numberValue) {
		if (numberValue == null)
			throw new IllegalArgumentException("Number value should not be null.");
		if(!numberValue.equals(this.numberValue)){
			BigDecimal oldValue = this.numberValue;
			this.numberValue = numberValue;
			this.getListenerHandler().notifyPropertyChange(this.propertyDefinition, oldValue, this.numberValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty#getDefinition()
	 */
	public INumberPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	public BigDecimal getValue() {
		return this.numberValue;
	}

	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (INumberPropertyDefinition) propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
	 */
	public boolean canVisualiseProperty() {
		return this.propertyDefinition.isVisualisable();
	}

	public INumberAnnotationProperty copyProperty(IPropertyBuilder propertyBuilder) {
		return propertyBuilder.copyNumberProperty(this);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visitProperty(org.pathwayeditor.businessobjects.drawingprimitives.properties.IVisitor)
	 */
	public void visitProperty(IAnnotationPropertyVisitor visitor) {
		visitor.visitNumberProperty(this);
	}
}
