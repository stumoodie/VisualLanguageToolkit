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

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class HibBooleanProperty extends HibProperty implements IBooleanAnnotationProperty {
	private static final long serialVersionUID = 3415354271386318411L;
	private Boolean boolValue;
	private IBooleanPropertyDefinition propertyDefinition;
	/**
	 * Constructor should only be used by hiberate.
	 * 
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibBooleanProperty() {
	}

	public HibBooleanProperty(HibAnnotatedCanvasAttribute owner, IBooleanPropertyDefinition propDefn) {
		super(owner, propDefn);
		this.propertyDefinition = propDefn;
		this.boolValue = propDefn.getDefaultValue();
	}

	public HibBooleanProperty(HibAnnotatedCanvasAttribute newOwner, HibBooleanProperty other) {
		super(newOwner, other);
		boolValue = other.boolValue;
		this.propertyDefinition = other.propertyDefinition;
	}

	public void setValue(Boolean numberValue) {
		if(!numberValue.equals(this.boolValue)){
			Boolean oldValue = this.boolValue;
			this.boolValue = numberValue;
			this.getListenerHandler().notifyPropertyChange(this.propertyDefinition, oldValue, this.boolValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty#getDefinition()
	 */
	public IBooleanPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	public Boolean getValue() {
		return this.boolValue;
	}

	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IBooleanPropertyDefinition) propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
	 */
	public boolean canVisualiseProperty() {
		return this.propertyDefinition.isVisualisable();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#visit(org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor)
	 */
	public void visit(IAnnotationPropertyVisitor visitor) {
		visitor.visitBooleanAnnotationProperty(this);
	}
}
