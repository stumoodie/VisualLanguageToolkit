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

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationPropertyVisitor;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IBooleanPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class BooleanProperty extends AnnotationProperty implements IBooleanAnnotationProperty {
	private static final long serialVersionUID = 3415354271386318411L;
	private Boolean boolValue;
	private IBooleanPropertyDefinition propertyDefinition;

	public BooleanProperty(AnnotatedCanvasAttribute owner, IBooleanPropertyDefinition propDefn) {
		super(owner, propDefn);
		this.propertyDefinition = propDefn;
		this.boolValue = propDefn.getDefaultValue();
	}

	public BooleanProperty(AnnotatedCanvasAttribute newOwner, BooleanProperty other) {
		super(newOwner, other);
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
