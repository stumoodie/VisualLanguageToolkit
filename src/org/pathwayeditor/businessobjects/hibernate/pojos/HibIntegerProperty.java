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

import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerAnnotationProperty;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IIntegerPropertyDefinition;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class HibIntegerProperty extends HibProperty implements IIntegerAnnotationProperty {
	private static final long serialVersionUID = -9120568699608562860L;
	private Integer numberValue;
	private IIntegerPropertyDefinition propertyDefinition;
	/**
	 * Constructor should only be used by hiberate.
	 * 
	 * @deprecated Application code should not use this constructor. Use one of the other constructors instead.
	 */
	HibIntegerProperty() {
	}

	public HibIntegerProperty(HibAnnotatedCanvasAttribute owner, IIntegerPropertyDefinition propDefn) {
		super(owner, propDefn);
		this.propertyDefinition = propDefn;
		this.numberValue = propDefn.getDefaultValue();
	}

	public HibIntegerProperty(HibAnnotatedCanvasAttribute newOwner, HibIntegerProperty other) {
		super(newOwner, other);
		numberValue = other.numberValue;
		this.propertyDefinition = other.propertyDefinition;
	}

	public void setValue(Integer numberValue) {
		if (numberValue == null)
			throw new IllegalArgumentException("Number value should not be null.");
		if(!numberValue.equals(this.numberValue)){
			Integer oldValue = this.numberValue;
			this.numberValue = numberValue;
			this.getListenerHandler().notifyPropertyChange(this.propertyDefinition, oldValue, this.numberValue);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.INumberAnnotationProperty#getDefinition()
	 */
	public IIntegerPropertyDefinition getDefinition() {
		return this.propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#getValue()
	 */
	public Integer getValue() {
		return this.numberValue;
	}

	public void setPropertyDefinition(IPropertyDefinition propertyDefinition) {
		this.propertyDefinition = (IIntegerPropertyDefinition) propertyDefinition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty#canVisualiseProperty()
	 */
	public boolean canVisualiseProperty() {
		return this.propertyDefinition.isVisualisable();
	}

}
