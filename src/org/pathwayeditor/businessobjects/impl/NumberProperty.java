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
		super(owner, propDefn);
		this.propertyDefinition = propDefn;
		this.numberValue = propDefn.getDefaultValue();
	}

	public NumberProperty(AnnotatedCanvasAttribute newOwner, NumberProperty other) {
		super(newOwner, other);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
	 */
	@Override
	public boolean canVisualiseProperty() {
		return this.propertyDefinition.isVisualisable();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visit(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor)
	 */
	@Override
	public void visit(IAnnotationPropertyVisitor visitor) {
		visitor.visitNumberAnnotationProperty(this);
	}

}
